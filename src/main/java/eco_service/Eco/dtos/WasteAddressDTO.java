package eco_service.Eco.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WasteAddressDTO {

    private Long id;

    private String country;

    private String city;

    private String street;
}
