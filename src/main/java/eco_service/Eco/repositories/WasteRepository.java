package eco_service.Eco.repositories;

import eco_service.Eco.models.Rating;
import eco_service.Eco.models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Long>, QuerydslPredicateExecutor<Waste> {
    List<Waste> findAllByEcoService_Id(@Param("id") Long id);
}
