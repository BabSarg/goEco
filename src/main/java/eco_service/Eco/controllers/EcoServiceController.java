package eco_service.Eco.controllers;

import eco_service.Eco.dtos.ChangePasswordEcoServiceDto;
import eco_service.Eco.dtos.EcoServiceAddDTO;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.filter.EcoServiceFilter;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.EcoServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/goeco/ecoservice")
public class EcoServiceController {

    private final EcoServiceService ecoServiceService;
    private static final Logger log = LoggerFactory.getLogger(EcoServiceController.class);

    public EcoServiceController(EcoServiceService ecoServiceService) {
        this.ecoServiceService = ecoServiceService;
    }

    @GetMapping
    public ResponseEntity<Response<ErrorResponse, List<EcoServiceDTO>>> getAll(@RequestParam(value = "name", required = false) String name,
                                                                               @RequestParam(value = "address",required = false)String address,
                                                                               @RequestParam(value = "country", required = false) String country,
                                                                               @RequestParam(value = "city", required = false) String city


                                                                               ) {
        Response<ErrorResponse, List<EcoServiceDTO>> all = ecoServiceService.getAll(EcoServiceFilter.where()
                .name(name)
                .address(address)
                .country(country)
                .city(city)
                .build());
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, EcoServiceDTO>> update(@PathVariable Long id, @RequestBody EcoServiceDTO ecoServiceDTO) {
        Response<ErrorResponse, EcoServiceDTO> updateEcoService = ecoServiceService.update(id, ecoServiceDTO);
        return ResponseEntity.ok(updateEcoService);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, EcoServiceDTO>> changePassword(@PathVariable Long id, @RequestBody ChangePasswordEcoServiceDto ecoServiceDto) {
        Response<ErrorResponse, EcoServiceDTO> changedPassword = ecoServiceService.changePassword(id, ecoServiceDto);
        return ResponseEntity.ok(changedPassword);
    }

    @PostMapping
    public ResponseEntity<Response<ErrorResponse, EcoServiceDTO>> add(@Valid @RequestBody() EcoServiceAddDTO ecoServiceAddDTO) {
        Response<ErrorResponse, EcoServiceDTO> add = ecoServiceService.add(ecoServiceAddDTO);
        return ResponseEntity.ok(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, EcoServiceDTO>> getById(@PathVariable("id") Long id) {
        Response<ErrorResponse, EcoServiceDTO> byId = ecoServiceService.getById(id);
        return ResponseEntity.ok(byId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ecoServiceService.delete(id);
        return ResponseEntity.ok(id);
    }

}

