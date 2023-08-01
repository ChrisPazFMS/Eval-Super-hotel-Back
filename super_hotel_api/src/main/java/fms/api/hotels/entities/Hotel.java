package fms.api.hotels.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;

// L'entité Hotel représente un hôtel qui appartient à une ville et qui a des chambres.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    // Un hôtel appartient à une ville, donc on définit une relation plusieurs-à-un
    // entre Hotel et City.
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    // Un hôtel peut avoir plusieurs chambres, donc on définit une relation
    // un-à-plusieurs entre Hotel et Bedroom.
    @OneToMany(mappedBy = "hotel")
    private List<Bedroom> bedrooms;
}
