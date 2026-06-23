package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.Vehicle;
/**
 * Spring Data JPA repository interface providing database access methods for Vehicle entities.
 *
 * @author Naveen Muthu
 */

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}