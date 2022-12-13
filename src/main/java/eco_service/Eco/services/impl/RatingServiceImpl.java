package eco_service.Eco.services.impl;

import eco_service.Eco.dtos.RatingAddDTO;
import eco_service.Eco.dtos.RatingDTO;
import eco_service.Eco.dtos.RatingResponseDto;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.mappers.RatingAddMapper;
import eco_service.Eco.mappers.RatingMapper;
import eco_service.Eco.models.Rating;
import eco_service.Eco.repositories.RatingRepository;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final RatingAddMapper ratingAddMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper, RatingAddMapper ratingAddMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.ratingAddMapper = ratingAddMapper;
    }


    @Override
    public Response<ErrorResponse, RatingDTO> add(RatingAddDTO ratingDTO) {
        Rating savedRating = ratingRepository.save(ratingAddMapper.toEntity(ratingDTO));
        return new Response<>(null, ratingMapper.toDTO(savedRating), RatingDTO.class.getSimpleName());
    }

    @Override
    public Response<ErrorResponse, RatingResponseDto> getAllByEcoServiceId(Long ecoServiceId) {
        List<Rating> ratings = ratingRepository.findByEcoService_Id(ecoServiceId);
        double sum = ratings.stream().map(Rating::getRating).reduce(Double::sum).orElse(0.0);
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder()
                .ratingDTOList(ratingMapper.toDTO(ratings))
                .average(sum > 0 ? sum / (double) ratings.size() : 0.0)
                .build();
        return new Response<>(null,ratingResponseDto,RatingResponseDto.class.getSimpleName());
    }

    @Override
    public Response<ErrorResponse, RatingResponseDto> getAllByWasteId(Long wasteId) {
        List<Rating> ratings = ratingRepository.findByWaste_Id(wasteId);
        double sum = ratings.stream().map(Rating::getRating).reduce(Double::sum).orElse(0.0);
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder()
                .ratingDTOList(ratingMapper.toDTO(ratings))
                .average(sum > 0 ? sum / (double) ratings.size() : 0.0)
                .build();
        return new Response<>(null,ratingResponseDto,RatingResponseDto.class.getSimpleName());
    }

    @Override
    public Response<ErrorResponse, List<RatingDTO>> getAll(String type) {
        List<Rating> ratings;
        if(type.equals("ecoservice")){
            ratings = ratingRepository.findAllByEcoServiceIsNotNull();
        }else {
            ratings = ratingRepository.findAllByWasteIsNotNull();
        }
        List<RatingDTO> ratingDTOList = ratings.stream().map(ratingMapper::toDTO).collect(Collectors.toList());
        return new Response<>(null,ratingDTOList,RatingResponseDto.class.getSimpleName());
    }


}
