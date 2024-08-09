package completed.RentalApplication.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    private int id;

    @NotNull(message = "Car selection is required")
    private String carSelection;

    @FutureOrPresent(message = "Pick-up date should be in the present or future")
    @NotNull(message = "Pick-up date is required")
    private LocalDate pickupDate;

    @Future(message = "Return date should be in the future")
    @NotNull(message = "Return date is required")
    private LocalDate returnDate;

    @NotNull(message = "Driver name is required")
    private String driverName;

    @Min(value = 18, message = "Driver should be at least 18 years old")
    @NotNull(message = "Driver age is required")
    private Integer driverAge;

    @Positive(message = "Price per day should be positive")
    @NotNull
    private Integer pricePerDay;

    @Positive(message = "Revenue should be positive")
    @NotNull
    private double revenue;
}