package eco_service.Eco.repositories;

import eco_service.Eco.models.EcoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcoServiceRepository extends JpaRepository<EcoService, Long>, QuerydslPredicateExecutor<EcoService> {

    EcoService findByName(String name);

    boolean existsByEmail(String email);

    Optional<EcoService> findByEmail(String email);


}
