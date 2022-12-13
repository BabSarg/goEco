package eco_service.Eco.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "eco_service",schema = "heroku_d5cce69b7d2eea9")
public class EcoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "work_hours")
    private String workHours;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "photo")
    private String photo;
}
