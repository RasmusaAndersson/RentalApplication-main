package completed.RentalApplication.controller;

import completed.RentalApplication.model.dto.RentalDto;
import completed.RentalApplication.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        List<RentalDto> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Integer id) {
        RentalDto rental = rentalService.getRental(id);
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found");
        }
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveRental(@Valid @RequestBody RentalDto rentalDto) {
        if (rentalService.hasActiveRentals(rentalDto.getCarSelection())) {
            return new ResponseEntity<>("This car is currently rented out", HttpStatus.BAD_REQUEST);
        }

        int pricePerDay = rentalService.getPricePerDay(rentalDto.getCarSelection());
        long rentalDays = ChronoUnit.DAYS.between(rentalDto.getPickupDate(), rentalDto.getReturnDate());
        double revenue = pricePerDay * rentalDays;

        rentalDto.setPricePerDay(pricePerDay);
        rentalDto.setRevenue(revenue);

        RentalDto savedRental = rentalService.saveRental(rentalDto);
        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable Integer id, @Valid @RequestBody RentalDto rentalDto) {
        if (!rentalDto.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rental data");
        }
        RentalDto updatedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(updatedRental, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Integer id) {
        RentalDto rental = rentalService.getRental(id);
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found");
        }
        rentalService.deleteRental(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}