package completed.RentalApplication.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Car selection is required")
    @Column(name = "car_selection")
    private String carSelection;

    @NotNull(message = "Pick-up date is required")
    @Column(name = "pickup_date")
    private LocalDate pickupDate;

    @NotNull(message = "Return date is required")
    @Column(name = "return_date")
    private LocalDate returnDate;

    @NotNull(message = "Driver name is required")
    @Column(name = "driver_name")
    private String driverName;

    @NotNull(message = "Driver age is required")
    @Column(name = "driver_age")
    private Integer driverAge;

    @NotNull
    @Column(name = "price_per_day")
    private Integer pricePerDay;

    @NotNull
    @Column(name = "revenue")
    private double revenue;
}