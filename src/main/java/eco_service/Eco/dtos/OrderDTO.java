package eco_service.Eco.dtos;

import eco_service.Eco.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {

    private Long id;
    private Long wasteId;

    private String customerName;

    private String customerEmail;

    private String customerPhone;

    private String description;

    private Order.Status status;

    private LocalDateTime orderTime;
}
