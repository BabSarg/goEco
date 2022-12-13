package eco_service.Eco.controllers;

import eco_service.Eco.dtos.AuthenticationRequestDto;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.response.Response;
import eco_service.Eco.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/goeco/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Response<ErrorResponse, EcoServiceDTO>> login(@RequestBody AuthenticationRequestDto requestDto, HttpServletResponse response) {
        Response<ErrorResponse, EcoServiceDTO> login = authService.login(requestDto, response);
        return ResponseEntity.ok(login);
    }
}
