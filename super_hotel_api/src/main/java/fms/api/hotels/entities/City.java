package fms.api.hotels.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

// City représente une ville dans laquelle se trouvent des hôtels.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Une ville peut avoir plusieurs hôtels.
    @OneToMany(mappedBy = "city")
    private List<Hotel> hotels;
}
