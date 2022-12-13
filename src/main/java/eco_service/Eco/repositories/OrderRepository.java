package eco_service.Eco.repositories;

import eco_service.Eco.models.EcoService;
import eco_service.Eco.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();

    List<Order> findByEcoServiceId(Long id);

    Order getByOrderId(Long orderId);
}
