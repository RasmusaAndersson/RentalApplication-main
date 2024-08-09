package completed.RentalApplication;

import completed.RentalApplication.model.dto.RentalDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RentalApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void createRentalDto() {
        RentalDto rentalDto = RentalDto.builder()
                .carSelection("Volvo S60")
                .pickupDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(2))
                .driverName("Rasmus Andersson")
                .driverAge(32)
                .pricePerDay(1500)
                .revenue(BigDecimal.valueOf(3000.0))
                .build();

        assertNotNull(rentalDto);
    }
}

