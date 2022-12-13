package eco_service.Eco.dtos;

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
public class WasteAddDTO {

    private List<String> types;

    private String description;

    private double longitude;

    private double latitude;

    private long ecoServiceId;

    private WasteAddressAddDTO wasteAddress;

    private Boolean isDelivery;

    private Boolean isFree;

}
