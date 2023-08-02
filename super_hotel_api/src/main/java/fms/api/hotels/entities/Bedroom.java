package fms.api.hotels.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bedrooms")
public class Bedroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "available")
    private boolean available;

    // Une chambre appartient à un hôtel.
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;

    // Une chambre peut avoir plusieurs réservations.
    @OneToMany(mappedBy = "bedroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
}
