package eco_service.Eco.mappers;

import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.dtos.OrderDTO;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements BaseMapper<Order, OrderDTO> {
    @Override
    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getOrderId())
                .customerEmail(order.getCustomerEmail())
                .wasteId(order.getWasteId())
                .customerName(order.getCustomerName())
                .description(order.getDescription())
                .status(order.getStatus())
                .customerEmail(order.getCustomerEmail())
                .customerPhone(order.getCustomerPhone())
                .orderTime(order.getOrderTime())
                .build();
    }

    @Override
    public List<OrderDTO> toDTO(List<Order> e) {
        return e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Order toEntity(OrderDTO order) {
        return Order.builder()
                .orderId(order.getId())
                .customerEmail(order.getCustomerEmail())
                .wasteId(order.getWasteId())
                .customerName(order.getCustomerName())
                .description(order.getDescription())
                .status(order.getStatus())
                .customerEmail(order.getCustomerEmail())
                .customerPhone(order.getCustomerPhone())
                .orderTime(order.getOrderTime())
                .build();
    }

    @Override
    public List<Order> toEntity(List<OrderDTO> d) {
        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
