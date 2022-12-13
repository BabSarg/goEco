package eco_service.Eco.mappers;

import eco_service.Eco.dtos.RatingDTO;
import eco_service.Eco.models.Rating;
import eco_service.Eco.services.EcoServiceService;
import eco_service.Eco.services.WasteService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingMapper implements BaseMapper<Rating, RatingDTO> {

    private final EcoServiceMapper ecoServiceMapper;

    private final WasteMapper wasteMapper;
    private final EcoServiceService ecoServiceService;
    private final WasteService wasteService;

    public RatingMapper(EcoServiceMapper ecoServiceMapper, WasteMapper wasteMapper, EcoServiceService ecoServiceService, WasteService wasteService) {
        this.ecoServiceMapper = ecoServiceMapper;
        this.wasteMapper = wasteMapper;
        this.ecoServiceService = ecoServiceService;
        this.wasteService = wasteService;
    }

    @Override
    public RatingDTO toDTO(Rating rating) {
        return RatingDTO.builder()
                .id(rating.getId())
                .rating(rating.getRating())
                .comment(rating.getComment())
                .ecoServiceDto(rating.getEcoService() != null ? ecoServiceService.getById(rating.getEcoService().getId()).getSuccessObject(): null)
                .wasteDTO(rating.getWaste() != null ? wasteService.getById(rating.getWaste().getId()).getSuccessObject() : null)
                .build();
    }

    @Override
    public List<RatingDTO> toDTO(List<Rating> e) {

        return e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Rating toEntity(RatingDTO ratingDTO) {
        return Rating.builder()
                .id(ratingDTO.getId())
                .rating(ratingDTO.getRating())
                .comment(ratingDTO.getComment())
                .ecoService(ratingDTO.getEcoServiceDto() != null ? ecoServiceMapper.toEntity(ratingDTO.getEcoServiceDto()): null)
                .waste(ratingDTO.getWasteDTO() != null ? wasteMapper.toEntity(ratingDTO.getWasteDTO()) : null)
                .build();
    }

    @Override
    public List<Rating> toEntity(List<RatingDTO> d) {

        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
