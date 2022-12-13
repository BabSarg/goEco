package eco_service.Eco.services;

import eco_service.Eco.dtos.OrderAddDTO;
import eco_service.Eco.dtos.OrderDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.response.Response;

import java.util.List;

public interface OrderService {

    Response<ErrorResponse, OrderDTO> add(OrderAddDTO orderDTO);

    Response<ErrorResponse, List<OrderDTO>> getAll();

    Response<ErrorResponse, List<OrderDTO>> getByEcoServiceId(Long id);

    Response<ErrorResponse, OrderDTO> updateStatus(Long orderId, String status);

}
