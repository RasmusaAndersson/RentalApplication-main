package completed.RentalApplication.repository;

import completed.RentalApplication.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByCarSelectionAndActive(String carSelection, boolean active);
}