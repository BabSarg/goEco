package eco_service.Eco.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingResponseDto {

    private List<RatingDTO> ratingDTOList;
    private double average;

}
