package eco_service.Eco.mappers;

import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.models.EcoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EcoServiceMapper implements BaseMapper<EcoService, EcoServiceDTO> {
    @Override
    public EcoServiceDTO toDTO(EcoService ecoService) {
        return EcoServiceDTO.builder()
                .id(ecoService.getId())
                .name(ecoService.getName())
                .email(ecoService.getEmail())
                .address(ecoService.getAddress())
                .workHours(ecoService.getWorkHours())
                .phoneNumber(ecoService.getPhoneNumber())
                .country(ecoService.getCountry())
                .city(ecoService.getCity())
                .build();
    }

    @Override
    public List<EcoServiceDTO> toDTO(List<EcoService> e) {
        return e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public EcoService toEntity(EcoServiceDTO ecoServiceDTO) {
        return EcoService.builder()
                .id(ecoServiceDTO.getId())
                .name(ecoServiceDTO.getName())
                .email(ecoServiceDTO.getEmail())
                .address(ecoServiceDTO.getAddress())
                .phoneNumber(ecoServiceDTO.getPhoneNumber())
                .workHours(ecoServiceDTO.getWorkHours())
                .country(ecoServiceDTO.getCountry())
                .city(ecoServiceDTO.getCity())
                .build();
    }

    @Override
    public List<EcoService> toEntity(List<EcoServiceDTO> d) {
        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
