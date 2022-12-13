package eco_service.Eco.services.impl;

import eco_service.Eco.dtos.AuthenticationRequestDto;
import eco_service.Eco.dtos.EcoServiceDTO;
import eco_service.Eco.exceptions.ErrorResponse;
import eco_service.Eco.exceptions.RecordNotFoundException;
import eco_service.Eco.mappers.EcoServiceMapper;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.repositories.EcoServiceRepository;
import eco_service.Eco.response.Response;
import eco_service.Eco.security.jwt.JwtTokenProvider;
import eco_service.Eco.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EcoServiceRepository ecoServiceRepository;
    private final EcoServiceMapper ecoServiceMapper;

    public AuthServiceImpl(EcoServiceRepository ecoServiceRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, EcoServiceMapper ecoServiceMapper) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.ecoServiceRepository = ecoServiceRepository;
        this.ecoServiceMapper = ecoServiceMapper;
    }

    private void getTokens(HttpServletResponse response, String email, EcoService user) {
        List<String> tokens = jwtTokenProvider.createTokens(email);
        user.setRefreshToken(tokens.get(1));
        ecoServiceRepository.save(user);
        response.setHeader("access_token", tokens.get(0));
        response.setHeader("refresh_token", tokens.get(1));
    }

    @Override
    public Response<ErrorResponse, EcoServiceDTO> login(AuthenticationRequestDto requestDto, HttpServletResponse response) {
        String email = requestDto.getEmail();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

        EcoService user = ecoServiceRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException("Email : " + email + " is not found"));
        getTokens(response, email, user);
        EcoServiceDTO userDto = ecoServiceMapper.toDTO(user);
        return new Response<>(null, userDto, null);

    }
}
