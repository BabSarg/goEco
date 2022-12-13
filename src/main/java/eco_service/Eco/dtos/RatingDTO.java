package eco_service.Eco.dtos;

import eco_service.Eco.models.EcoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingDTO {

    private Long id;

    private double rating;

    private String comment;

    private EcoServiceDTO ecoServiceDto;

    private WasteDTO wasteDTO;
}
