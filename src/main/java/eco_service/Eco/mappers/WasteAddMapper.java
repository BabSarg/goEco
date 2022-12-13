package eco_service.Eco.mappers;

import eco_service.Eco.dtos.WasteAddDTO;
import eco_service.Eco.dtos.WasteAddressAddDTO;
import eco_service.Eco.dtos.WasteAddressDTO;
import eco_service.Eco.dtos.WasteDTO;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.Waste;
import eco_service.Eco.models.WasteAddress;
import eco_service.Eco.models.WasteType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WasteAddMapper implements BaseMapper<Waste, WasteAddDTO> {

    private final EcoServiceMapper ecoServiceMapper;

    public WasteAddMapper(EcoServiceMapper ecoServiceMapper) {
        this.ecoServiceMapper = ecoServiceMapper;
    }

    @Override
    public WasteAddDTO toDTO(Waste waste) {

        return WasteAddDTO.builder()
                .description(waste.getDescription())
                .types(waste.getTypes().stream().map(WasteType::getType).collect(Collectors.toList()))
                .latitude(waste.getLatitude())
                .ecoServiceId(waste.getEcoService().getId())
                .wasteAddress(WasteAddressAddDTO.builder()
                        .city(waste.getWasteAddress().getCity())
                        .country(waste.getWasteAddress().getCountry())
                        .street(waste.getWasteAddress().getStreet())
                        .build())
                .longitude(waste.getLongitude())
                .isFree(waste.getIsFree())
                .isDelivery(waste.getIsDelivery())
                .build();
    }

    @Override
    public List<WasteAddDTO> toDTO(List<Waste> e) {
       return e.stream().map(this::toDTO).collect(Collectors.toList());

    }

    @Override
    public Waste toEntity(WasteAddDTO waste) {

        return Waste.builder()
                .description(waste.getDescription())
                .types(waste.getTypes().stream().map(WasteType::new).collect(Collectors.toList()))
                .latitude(waste.getLatitude())
                .ecoService(EcoService.builder()
                        .id(waste.getEcoServiceId())
                        .build())
                .wasteAddress(WasteAddress.builder()
                        .city(waste.getWasteAddress().getCity())
                        .country(waste.getWasteAddress().getCountry())
                        .street(waste.getWasteAddress().getStreet())
                        .build())
                .longitude(waste.getLongitude())
                .isFree(waste.getIsFree())
                .isDelivery(waste.getIsDelivery())
                .build();
    }

    @Override
    public List<Waste> toEntity(List<WasteAddDTO> d) {
        return d.stream().map(this::toEntity).collect(Collectors.toList());

    }
}
