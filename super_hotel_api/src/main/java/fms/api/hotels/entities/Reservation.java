package fms.api.hotels.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la réservation.
    private String startDate; // Date de début de la réservation.
    private String endDate; // Date de fin de la réservation.
    // Permet de gérer les disponibilités des chambres et d’éviter les conflits de
    // réservation.
    @ManyToOne
    @JoinColumn(name = "bedroom_id")
    private Bedroom bedroom; // La chambre concernée par la réservation.
}
