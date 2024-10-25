package com.casabella.backend.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Passenger;
import com.casabella.backend.repository.PassengerRepository;  // Ensure you have a PassengerRepository
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepo;

    // Add Passenger
    public Passenger postPassenger(Passenger passenger) {
        return passengerRepo.save(passenger);
    }

    // Get Passenger by ID
    public Passenger getPassengerById(Long id) {
        return passengerRepo.findById(id).orElse(null);
    }

    // Update Passenger
    public Passenger updatePassenger(Long id, Passenger passenger) {
        Optional<Passenger> optionalPassenger = passengerRepo.findById(id);

        if (optionalPassenger.isPresent()) {
            Passenger existingPassenger = optionalPassenger.get();

            existingPassenger.setPassengerName(passenger.getPassengerName());
            existingPassenger.setPassengerEmail(passenger.getPassengerEmail());
            existingPassenger.setPassengerNic(passenger.getPassengerNic());

            return passengerRepo.save(existingPassenger);
        }
        return null;
    }
}
