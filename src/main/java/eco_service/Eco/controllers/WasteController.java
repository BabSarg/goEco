package eco_service.Eco.controllers;

import eco_service.Eco.dtos.WasteAddDTO;
import eco_service.Eco.dtos.WasteDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.filter.WasteFilter;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.WasteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/goeco/waste")
public class WasteController {

    private final WasteService wasteService;
    private static final Logger log = LoggerFactory.getLogger(WasteController.class);

    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @GetMapping
    public ResponseEntity<Response<ErrorResponse, List<WasteDTO>>> getAll(@RequestParam(value = "types",required = false) List<String> types,
                                                                          @RequestParam(value = "longitude",required = false) Double longitude,
                                                                          @RequestParam(value = "latitude",required = false) Double latitude,
                                                                          @RequestParam(value = "country",required = false) String country,
                                                                          @RequestParam(value = "city",required = false) String city,
                                                                          @RequestParam(value = "street",required = false) String street,
                                                                          @RequestParam(value = "ecoServiceName",required = false) String ecoServiceName,
                                                                          @RequestParam(value = "ecoServiceAddress",required = false) String ecoServiceAddress,
                                                                          @RequestParam(value = "ecoServicePhoneNumber",required = false) String ecoServicePhoneNumber,
                                                                          @RequestParam(value = "ecoServiceCountry",required = false) String ecoServiceCountry,
                                                                          @RequestParam(value = "ecoServiceCity",required = false) String ecoServiceCity
    ) {
        Response<ErrorResponse, List<WasteDTO>> all = wasteService.getAll(WasteFilter.where()
                .type(types)
                .longitude(longitude)
                .latitude(latitude)
                .country(country)
                .city(city)
                .street(street)
                .ecoServiceName(ecoServiceName)
                .ecoServiceAddress(ecoServiceAddress)
                .ecoServicePhoneNumber(ecoServicePhoneNumber)
                .ecoServiceCountry(ecoServiceCountry)
                .ecoServiceCity(ecoServiceCity)
                .build());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ErrorResponse, WasteDTO>> getById(@PathVariable("id") Long id) {
        Response<ErrorResponse, WasteDTO> byId = wasteService.getById(id);
        return ResponseEntity.ok(byId);
    }

    @GetMapping(value = "/ecoServiceId/{id}")
    public ResponseEntity<Response<ErrorResponse, List<WasteDTO>>> getByEcoServiceId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wasteService.getByEcoServiceId(id));
    }

    @PostMapping
    public ResponseEntity<Response<ErrorResponse, WasteAddDTO>> add(@Valid @RequestBody() WasteAddDTO wasteDTO) {
        Response<ErrorResponse, WasteAddDTO> add = wasteService.add(wasteDTO);
        return ResponseEntity.ok(add);
    }

}
