package kwy.taxi.allocation.repository;

import kwy.taxi.allocation.controller.bind.TaxiAllocation;
import kwy.taxi.allocation.model.TaxiAllocationDto;
import kwy.taxi.allocation.utils.AllocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaxiAllocationRepository extends JpaRepository<TaxiAllocationDto, Integer> {

    Optional<TaxiAllocationDto> findOneByPassengerIdOrderByIdDesc(@Param("passenger") int passengerId);

    List<TaxiAllocation> findByPassengerIdOrderByIdDesc(int userId);

    List<TaxiAllocation> findByStatusOrderByIdDesc(AllocationStatus status);
}
