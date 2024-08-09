package completed.RentalApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import completed.RentalApplication.model.dto.RentalDto;
import completed.RentalApplication.model.entity.Rental;
import completed.RentalApplication.repository.RentalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RentalDto getRental(Integer id) {
        return rentalRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public RentalDto saveRental(RentalDto rentalDto) {
        Rental rental = convertToEntity(rentalDto);
        rental = rentalRepository.save(rental);
        return convertToDto(rental);
    }

    public RentalDto updateRental(Integer id, RentalDto rentalDto) {
        Rental rental = convertToEntity(rentalDto);
        rental.setId(id);
        rental = rentalRepository.save(rental);
        return convertToDto(rental);
    }

    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }

    private RentalDto convertToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setCarSelection(rental.getCarSelection());
        return rentalDto;
    }

    private Rental convertToEntity(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setCarSelection(rentalDto.getCarSelection());
        return rental;
    }
    public int getPricePerDay(String carSelection) {

        switch (carSelection) {
            case "Volvo S60":
                return 1500;
            case "Volkswagen Golf":
                return 1333;
            case "Ford Mustang":
                return 3000;
            case "Ford Transit":
                return 2400;
            default:
                throw new IllegalArgumentException("Unknown car selection: " + carSelection);
        }
}