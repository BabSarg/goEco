package eco_service.Eco.services.impl;

import eco_service.Eco.dtos.WasteAddDTO;
import eco_service.Eco.dtos.WasteDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.exceptions.RecordNotFoundException;
import eco_service.Eco.filter.WasteFilter;
import eco_service.Eco.mappers.WasteAddMapper;
import eco_service.Eco.mappers.WasteMapper;
import eco_service.Eco.models.Waste;
import eco_service.Eco.repositories.WasteRepository;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.WasteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WasteServiceImpl implements WasteService {

    private final WasteRepository wasteRepository;

    private final WasteMapper wasteMapper;
    private final WasteAddMapper wasteAddMapper;

    public WasteServiceImpl(WasteRepository wasteRepository, WasteMapper wasteMapper, WasteAddMapper wasteAddMapper) {
        this.wasteRepository = wasteRepository;
        this.wasteMapper = wasteMapper;
        this.wasteAddMapper = wasteAddMapper;
    }

    @Override
    public Response<ErrorResponse, List<WasteDTO>> getAll(WasteFilter wasteFilter) {
        List<Waste> wasteList;
        if(wasteFilter.getPredicate() == null){
            wasteList = wasteRepository.findAll();
        }else {
            Iterable<Waste> iterable = wasteRepository.findAll(wasteFilter.getPredicate());
         wasteList = StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return new Response<>(null, wasteMapper.toDTO(wasteList), WasteDTO.class.getName());
    }

    @Override
    public Response<ErrorResponse, WasteDTO> getById(Long id) {
        Waste waste = wasteRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Waste is not found with id : " + id));
        return new Response<>(null, wasteMapper.toDTO(waste), WasteDTO.class.getName());
    }

    @Override
    public Response<ErrorResponse, List<WasteDTO>> getByEcoServiceId(Long id) {
        List<WasteDTO> wasteAddDTOS = wasteRepository.findAllByEcoService_Id(id)
                .stream()
                .map(wasteMapper::toDTO)
                .collect(Collectors.toList());
        return new Response<>(null, wasteAddDTOS, WasteDTO.class.getName());
    }

    @Override
    public Response<ErrorResponse, WasteAddDTO> add(WasteAddDTO wasteDTO) {
        Waste savedWaste=wasteRepository.save(wasteAddMapper.toEntity(wasteDTO));
        return new Response<>(null, wasteAddMapper.toDTO(savedWaste), WasteDTO.class.getSimpleName());
    }
}
