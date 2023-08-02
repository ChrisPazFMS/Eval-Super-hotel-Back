package fms.api.hotels.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date") // Date de début de la réservation
    private String startDate;

    @Column(name = "end_date") // Date de fin de la réservation
    private String endDate;

    // Gérer les chambres sans conflits de réservation.
    @ManyToOne
    @JoinColumn(name = "bedroom_id")
    private Bedroom bedroom; // Pour la réservation.
}
