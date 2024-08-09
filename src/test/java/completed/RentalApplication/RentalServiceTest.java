package completed.RentalApplication;

import completed.RentalApplication.model.entity.Rental;
import completed.RentalApplication.model.dto.RentalDto;
import completed.RentalApplication.repository.RentalRepository;
import completed.RentalApplication.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    public void testGetAllRentals() {
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        when(rentalRepository.findAll()).thenReturn(Arrays.asList(rental1, rental2));

        List<RentalDto> rentals = rentalService.getAllRentals();

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    public void testGetRental() {
        Rental rental = new Rental();
        rental.setId(1);
        when(rentalRepository.findById(1)).thenReturn(Optional.of(rental));

        RentalDto result = rentalService.getRental(1);

        assertEquals(1, result.getId());
        verify(rentalRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveRental() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setCarSelection("Volvo S60");
        Rental rental = new Rental();
        rental.setCarSelection("Volvo S60");
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        RentalDto result = rentalService.saveRental(rentalDto);

        assertEquals("Volvo S60", result.getCarSelection());
        verify(rentalRepository, times(1)).save(any(Rental.class));

        ArgumentCaptor<Rental> rentalCaptor = ArgumentCaptor.forClass(Rental.class);
        verify(rentalRepository).save(rentalCaptor.capture());
        assertEquals("Volvo S60", rentalCaptor.getValue().getCarSelection());
    }

    @Test
    public void testUpdateRental() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(1);
        rentalDto.setCarSelection("Volvo S60");
        Rental rental = new Rental();
        rental.setId(1);
        rental.setCarSelection("Volvo S60");
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        RentalDto result = rentalService.updateRental(1, rentalDto);

        assertEquals(1, result.getId());
        assertEquals("Volvo S60", result.getCarSelection());
        verify(rentalRepository, times(1)).save(any(Rental.class));

        ArgumentCaptor<Rental> rentalCaptor = ArgumentCaptor.forClass(Rental.class);
        verify(rentalRepository).save(rentalCaptor.capture());
        assertEquals(1, rentalCaptor.getValue().getId());
        assertEquals("Volvo S60", rentalCaptor.getValue().getCarSelection());
    }

    @Test
    public void testDeleteRental() {
        doNothing().when(rentalRepository).deleteById(1);

        rentalService.deleteRental(1);

        verify(rentalRepository, times(1)).deleteById(1);
    }
}