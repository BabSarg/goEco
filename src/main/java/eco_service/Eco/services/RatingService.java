package eco_service.Eco.services;

import eco_service.Eco.dtos.RatingAddDTO;
import eco_service.Eco.dtos.RatingDTO;
import eco_service.Eco.dtos.RatingResponseDto;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.response.Response;

import java.util.List;

public interface RatingService {
    Response<ErrorResponse, RatingDTO> add(RatingAddDTO ratingDTO);

    Response<ErrorResponse, RatingResponseDto> getAllByEcoServiceId(Long ecoServiceId);
    Response<ErrorResponse, RatingResponseDto> getAllByWasteId(Long ecoServiceId);
    Response<ErrorResponse, List<RatingDTO>> getAll(String type);
}
