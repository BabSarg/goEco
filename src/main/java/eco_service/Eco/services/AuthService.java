package eco_service.Eco.services;

import eco_service.Eco.dtos.AuthenticationRequestDto;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.response.Response;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    Response<ErrorResponse, EcoServiceDTO> login(AuthenticationRequestDto requestDto, HttpServletResponse response);
}
