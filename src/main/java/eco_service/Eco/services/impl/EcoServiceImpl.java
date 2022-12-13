package eco_service.Eco.services.impl;

import eco_service.Eco.dtos.ChangePasswordEcoServiceDto;
import eco_service.Eco.dtos.EcoServiceAddDTO;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.email.MailService;
import eco_service.Eco.exceptions.ConflictException;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.exceptions.RecordNotFoundException;
import eco_service.Eco.filter.EcoServiceFilter;
import eco_service.Eco.mappers.EcoServiceAddMapper;
import eco_service.Eco.mappers.EcoServiceMapper;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.repositories.EcoServiceRepository;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.EcoServiceService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EcoServiceImpl implements EcoServiceService {

    private final EcoServiceRepository ecoServiceRepository;
    private final EcoServiceMapper ecoServiceMapper;
    private final EcoServiceAddMapper ecoServiceAddMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    public EcoServiceImpl(EcoServiceRepository ecoServiceRepository, EcoServiceMapper ecoServiceMapper, EcoServiceAddMapper ecoServiceAddMapper, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService) {
        this.ecoServiceRepository = ecoServiceRepository;
        this.ecoServiceMapper = ecoServiceMapper;
        this.ecoServiceAddMapper = ecoServiceAddMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
    }

    @Override
    public Response<ErrorResponse, List<EcoServiceDTO>> getAll(EcoServiceFilter ecoServiceFilter) {
        List<EcoService> ecoServiceList;
        if(ecoServiceFilter.getPredicate() == null){
            ecoServiceList = ecoServiceRepository.findAll();
        }else {
            Iterable<EcoService> iterable = ecoServiceRepository.findAll(ecoServiceFilter.getPredicate());
            ecoServiceList = StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return new Response<>(null, ecoServiceMapper.toDTO(ecoServiceList), EcoServiceDTO.class.getName());
    }

    @Override
    public Response<ErrorResponse, EcoServiceDTO> getById(Long id) {
        EcoService ecoService = ecoServiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("EcoService is not found with id : " + id));
        return new Response<>(null, ecoServiceMapper.toDTO(ecoService), EcoServiceDTO.class.getName());
    }

    @Override
    public Response<ErrorResponse, EcoServiceDTO> add(EcoServiceAddDTO ecoServiceAddDTO) {
        if (ecoServiceRepository.existsByEmail(ecoServiceAddDTO.getEmail())) {
            throw new ConflictException(ecoServiceAddDTO.getEmail() + "Email already exists");
        }
        EcoService ecoService = ecoServiceAddMapper.toEntity(ecoServiceAddDTO);
        ecoService.setPassword(bCryptPasswordEncoder.encode(ecoServiceAddDTO.getPassword()));
        EcoService savedEcoService = ecoServiceRepository.save(ecoService);
        mailService.sendMail(savedEcoService.getEmail(), "Registration confirmation", "Dear " + ecoService.getName() + "\n You are registered successfully");
        return new Response<>(null, ecoServiceMapper.toDTO(savedEcoService), EcoServiceDTO.class.getSimpleName());
    }

    @Override
    public Response<ErrorResponse, EcoServiceDTO> update(Long id, EcoServiceDTO ecoServiceDTO) {
        EcoService ecoService = ecoServiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("EcoService is not found with id:" + id));
        if (ecoServiceRepository.existsByEmail(ecoServiceDTO.getEmail()) && !(ecoService.getEmail().equals(ecoServiceDTO.getEmail()))) {
            throw new ConflictException("Email : " + ecoServiceDTO.getEmail() + " already exists");
        }
        EcoService updateEcoService = ecoServiceRepository.save(ecoServiceMapper.toEntity(ecoServiceDTO));
        return new Response<>(null, ecoServiceMapper.toDTO(updateEcoService), EcoServiceDTO.class.getSimpleName());
    }

    @Override
    public void delete(Long id) {
        ecoServiceRepository.deleteById(id);
    }

    @Override
    public Response<ErrorResponse, EcoServiceDTO> changePassword(Long id, ChangePasswordEcoServiceDto ecoServiceDto) {

        EcoService ecoService = ecoServiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("EcoService is not found with id: " + id));
        if (!ecoServiceDto.getNewPassword().equals(ecoServiceDto.getConfirmPassword())) {
            throw new ConflictException("password and confirm is different");
        }
        if (!bCryptPasswordEncoder.matches(ecoServiceDto.getOldPassword(), ecoService.getPassword())) {
            throw new ConflictException("old password is not correct");
        }

        ecoService.setPassword(bCryptPasswordEncoder.encode(ecoServiceDto.getNewPassword()));
        EcoService saveEcoService = ecoServiceRepository.save(ecoService);
        EcoServiceDTO savedEcoServiceDTO = ecoServiceMapper.toDTO(saveEcoService);

        return new Response<>(null,savedEcoServiceDTO,EcoServiceDTO.class.getSimpleName());
    }
}
