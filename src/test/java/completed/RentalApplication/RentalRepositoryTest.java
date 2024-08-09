package completed.RentalApplication;

import completed.RentalApplication.model.entity.Rental;
import completed.RentalApplication.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalRepositoryTest {

    @Mock
    private RentalRepository rentalRepository;

    @Test
    public void testGetAllRentals() {
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        when(rentalRepository.findAll()).thenReturn(Arrays.asList(rental1, rental2));

        List<Rental> rentals = rentalRepository.findAll();

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    public void testGetRental() {
        Rental rental = new Rental();
        rental.setId(1);
        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        Optional<Rental> result = rentalRepository.findById(1);

        assertEquals(1, result.get().getId());
        verify(rentalRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveRental() {
        Rental rental = new Rental();
        rental.setCarSelection("Sedan");
        rental.setPickupDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(1));
        rental.setDriverName("John Doe");
        rental.setDriverAge(30);
        rental.setPricePerDay(100);
        rental.setRevenue(BigDecimal.valueOf(100.0));

        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        Rental result = rentalRepository.save(rental);

        assertEquals(rental, result);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    public void testUpdateRental() {
        Rental rental = new Rental();
        rental.setId(1);
        rental.setCarSelection("Sedan");
        rental.setPickupDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(1));
        rental.setDriverName("John Doe");
        rental.setDriverAge(30);
        rental.setPricePerDay(100);
        rental.setRevenue(BigDecimal.valueOf(100.0));

        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        Rental result = rentalRepository.save(rental);

        assertEquals(rental, result);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    public void testDeleteRental() {
        doNothing().when(rentalRepository).deleteById(1);

        rentalRepository.deleteById(1);

        verify(rentalRepository, times(1)).deleteById(1);
    }
}