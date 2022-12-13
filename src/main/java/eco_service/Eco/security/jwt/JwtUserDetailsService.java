package eco_service.Eco.security.jwt;

import eco_service.Eco.exceptions.RecordNotFoundException;
import eco_service.Eco.models.EcoService;
import eco_service.Eco.repositories.EcoServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final EcoServiceRepository ecoServiceRepository;

    @Autowired
    public JwtUserDetailsService(EcoServiceRepository ecoServiceRepository) {
        this.ecoServiceRepository = ecoServiceRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        EcoService user = ecoServiceRepository.findByEmail(email).orElseThrow(() ->
                new RecordNotFoundException("Email : " + email + " is not found"));
        return JwtUserFactory.create(user);
    }
}
