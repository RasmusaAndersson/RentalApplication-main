package completed.RentalApplication;

import completed.RentalApplication.controller.RentalController;
import completed.RentalApplication.model.dto.RentalDto;
import completed.RentalApplication.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RentalControllerTest {

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }

    @Test
    public void testGetAllRentals() throws Exception {
        RentalDto rentalDto1 = new RentalDto();
        RentalDto rentalDto2 = new RentalDto();
        when(rentalService.getAllRentals()).thenReturn(Arrays.asList(rentalDto1, rentalDto2));

        mockMvc.perform(get("/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetRental() throws Exception {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(1);
        when(rentalService.getRental(1)).thenReturn(rentalDto);

        mockMvc.perform(get("/rentals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}