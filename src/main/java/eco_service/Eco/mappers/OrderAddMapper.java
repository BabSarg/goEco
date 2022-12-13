package eco_service.Eco.mappers;

import eco_service.Eco.dtos.OrderAddDTO;
import eco_service.Eco.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAddMapper implements BaseMapper<Order, OrderAddDTO> {
    @Override
    public OrderAddDTO toDTO(Order order) {
        return OrderAddDTO.builder()
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
    public List<OrderAddDTO> toDTO(List<Order> e) {
        return e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Order toEntity(OrderAddDTO order) {
        return Order.builder()
                .customerEmail(order.getCustomerEmail())
                .ecoServiceId(order.getEcoServiceId())
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
    public List<Order> toEntity(List<OrderAddDTO> d) {
        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
