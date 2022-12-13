package eco_service.Eco.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingAddDTO {

    private double rating;

    private String comment;

    private Long ecoServiceId;

    private Long wasteId;
}
