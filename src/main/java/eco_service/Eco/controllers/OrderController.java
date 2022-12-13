package eco_service.Eco.controllers;

import eco_service.Eco.dtos.ChangePasswordEcoServiceDto;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.dtos.OrderAddDTO;
import eco_service.Eco.dtos.OrderDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.models.Order;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/goeco/order")
public class OrderController {
    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Response<ErrorResponse, List<OrderDTO>>> getAll() {
        Response<ErrorResponse, List<OrderDTO>> all = orderService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{ecoServiceId}")
    public ResponseEntity<Response<ErrorResponse, List<OrderDTO>>> getByEcoServiceId(@PathVariable("ecoServiceId") Long id) {
        Response<ErrorResponse, List<OrderDTO>> byId = orderService.getByEcoServiceId(id);
        return ResponseEntity.ok(byId);
    }

    @PostMapping
    public ResponseEntity<Response<ErrorResponse, OrderDTO>> add(@Valid @RequestBody() OrderAddDTO orderDTO) {
        Response<ErrorResponse, OrderDTO> add = orderService.add(orderDTO);
        return ResponseEntity.ok(add);
    }

    @PatchMapping("/{orderId}/{status}")
    public ResponseEntity<Response<ErrorResponse, OrderDTO>> updateStatus(@PathVariable Long orderId,@PathVariable String status) {
        Response<ErrorResponse, OrderDTO> changedOrder = orderService.updateStatus(orderId, status);
        return ResponseEntity.ok(changedOrder);
    }
}
