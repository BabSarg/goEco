package eco_service.Eco.dtos;

import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.WasteAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WasteDTO {

    private Long id;

    private List<String> types;

    private String description;

    private double longitude;

    private double latitude;

    private long ecoServiceId;

    private WasteAddressDTO wasteAddress;

    private Boolean isDelivery;

    private Boolean isFree;

}
