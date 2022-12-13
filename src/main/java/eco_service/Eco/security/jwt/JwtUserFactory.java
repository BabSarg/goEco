package eco_service.Eco.security.jwt;

import eco_service.Eco.models.EcoService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

public final class JwtUserFactory {

    public static JwtUser create(EcoService user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ECO_SERVICE")));
    }

}
