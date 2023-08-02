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

    // Date de début de la réservation
    @Column(name = "start_date")
    private String startDate;

    // Date de fin de la réservation
    @Column(name = "end_date")
    private String endDate;

    // Gérer les chambres sans conflits de réservation.
    @ManyToOne
    @JoinColumn(name = "bedroom_id")
    private Bedroom bedroom;
}
