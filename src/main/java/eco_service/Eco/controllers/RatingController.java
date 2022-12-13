package eco_service.Eco.controllers;

import eco_service.Eco.dtos.RatingAddDTO;
import eco_service.Eco.dtos.RatingDTO;
import eco_service.Eco.dtos.RatingResponseDto;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/goeco/rating")
public class RatingController {

    private final RatingService ratingService;
    private static final Logger log = LoggerFactory.getLogger(RatingController.class);

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Response<ErrorResponse, RatingDTO>> add(@Valid @RequestBody() RatingAddDTO ratingDTO) {
        Response<ErrorResponse, RatingDTO> add = ratingService.add(ratingDTO);
        return ResponseEntity.ok(add);
    }

    @GetMapping("/ecoService/{id}")
    public ResponseEntity<Response<ErrorResponse, RatingResponseDto>> getByEcoServiceId(@PathVariable long id){
        Response<ErrorResponse, RatingResponseDto> allByEcoServiceId = ratingService.getAllByEcoServiceId(id);
        return ResponseEntity.ok(allByEcoServiceId);
    }

    @GetMapping("/waste/{id}")
    public ResponseEntity<Response<ErrorResponse, RatingResponseDto>> getByWasteId(@PathVariable long id){
        Response<ErrorResponse, RatingResponseDto> allByEcoServiceId = ratingService.getAllByWasteId(id);
        return ResponseEntity.ok(allByEcoServiceId);
    }

    @GetMapping("/ecoService")
    public ResponseEntity<Response<ErrorResponse, List<RatingDTO>>> getAllEcoServiceRatings(){
        Response<ErrorResponse, List<RatingDTO>> ratingResponse = ratingService.getAll("ecoservice");
        return ResponseEntity.ok(ratingResponse);
    }

    @GetMapping("/waste")
    public ResponseEntity<Response<ErrorResponse, List<RatingDTO>>> getAllWasteRatings(){
        Response<ErrorResponse, List<RatingDTO>> ratingResponse = ratingService.getAll("waste");
        return ResponseEntity.ok(ratingResponse);
    }

}
