package fms.api.hotels.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// L'entité Bedroom représente une chambre d'hôtel qui appartient à un hôtel et qui peut être réservée.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bedroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String type;
    private double price;
    private boolean available;
    // Une chambre appartient à un hôtel, donc on définit une relation
    // plusieurs-à-un entre Bedroom et Hotel.
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    // Une chambre peut avoir plusieurs réservations, donc on définit une relation
    // un-à-plusieurs entre Bedroom et Reservation.
    @OneToMany(mappedBy = "bedroom")
    private List<Reservation> reservations;
}
