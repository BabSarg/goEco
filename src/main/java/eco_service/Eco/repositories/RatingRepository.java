package eco_service.Eco.repositories;

import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByEcoService_Id(Long ecoServiceId);
    List<Rating> findByWaste_Id(Long ecoServiceId);
    List<Rating> findAllByEcoServiceIsNotNull();
    List<Rating> findAllByWasteIsNotNull();

}
