package eco_service.Eco.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "waste",schema = "public")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<WasteType> types;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @ManyToOne
    @JoinColumn(name = "eco_service_id")
    private EcoService ecoService;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "waste_address_id")
    private WasteAddress wasteAddress;

    @Column(name = "photo")
    private String photo;

    @Column(name = "is_delivery")
    private Boolean isDelivery;

    @Column(name = "is_free")
    private Boolean isFree;
}
