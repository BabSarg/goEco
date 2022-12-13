package eco_service.Eco.mappers;

import eco_service.Eco.dtos.RatingAddDTO;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.Rating;
import eco_service.Eco.models.Waste;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingAddMapper implements BaseMapper<Rating, RatingAddDTO> {

    private final EcoServiceMapper ecoServiceMapper;

    private final WasteMapper wasteMapper;

    public RatingAddMapper(EcoServiceMapper ecoServiceMapper, WasteMapper wasteMapper) {
        this.ecoServiceMapper = ecoServiceMapper;
        this.wasteMapper = wasteMapper;
    }

    @Override
    public RatingAddDTO toDTO(Rating rating) {
        return RatingAddDTO.builder()
                .rating(rating.getRating())
                .comment(rating.getComment())
                .ecoServiceId(rating.getEcoService() != null ? rating.getEcoService().getId(): null)
                .wasteId(rating.getWaste() != null ? rating.getWaste().getId() : null)
                .build();
    }

    @Override
    public List<RatingAddDTO> toDTO(List<Rating> e) {

        return e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Rating toEntity(RatingAddDTO ratingDTO) {
        return Rating.builder()
                .rating(ratingDTO.getRating())
                .comment(ratingDTO.getComment())
                .ecoService(ratingDTO.getEcoServiceId() != null ? EcoService.builder()
                        .id(ratingDTO.getEcoServiceId())
                        .build(): null)
                .waste(ratingDTO.getWasteId() != null ? Waste.builder()
                        .id(ratingDTO.getWasteId())
                        .build() : null)
                .build();
    }

    @Override
    public List<Rating> toEntity(List<RatingAddDTO> d) {

        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
