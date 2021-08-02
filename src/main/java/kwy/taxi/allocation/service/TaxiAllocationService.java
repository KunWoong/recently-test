package kwy.taxi.allocation.service;

import kwy.taxi.allocation.controller.bind.TaxiAllocation;
import kwy.taxi.allocation.model.TaxiAllocationDto;
import kwy.taxi.allocation.repository.TaxiAllocationRepository;
import kwy.taxi.allocation.utils.AllocationStatus;
import kwy.taxi.allocation.utils.UserType;
import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiAllocationService {

    private final TaxiAllocationRepository taxiAllocationRepository;

    public TaxiAllocationService(TaxiAllocationRepository taxiAllocationRepository) {
        this.taxiAllocationRepository = taxiAllocationRepository;
    }

    public TaxiAllocation createTaxiRequest(TaxiAllocationDto allocation) {
        if(!(allocation.getAddress().length() >= 1 && allocation.getAddress().length() <= 100)){
            throw new CustomException(ErrorCode.INVALID_ADDRESS_LENGTH);
        }
        Optional<TaxiAllocationDto> oneByPassengerId = taxiAllocationRepository.findOneByPassengerIdOrderByIdDesc(allocation.getPassengerId());
        oneByPassengerId.ifPresent(alloc -> {
            if(alloc.getStatus() == AllocationStatus.standBy) throw new CustomException(ErrorCode.DUPLICATE_TAXI_REQUEST);
        });
        allocation.setCreatedAt(LocalDateTime.now());
        allocation.setUpdatedAt(LocalDateTime.now());
        return new TaxiAllocation(taxiAllocationRepository.save(allocation));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public TaxiAllocation acceptAllocation(int taxiRequestId, int userId) {
        Optional<TaxiAllocationDto> optionalById = taxiAllocationRepository.findById(taxiRequestId);
        TaxiAllocationDto byId = optionalById.orElseThrow(() -> new CustomException(ErrorCode.TAXI_ALLOCATION_NOT_FOUND));
        if(byId.getStatus()==AllocationStatus.accepted) throw new CustomException(ErrorCode.OCCUPIED_TAXI_ALLOCATION);
        byId.setStatus(AllocationStatus.accepted);
        byId.setAcceptedAt(LocalDateTime.now());
        byId.setDriverId(userId);
        taxiAllocationRepository.save(byId);

        return new TaxiAllocation(byId);

    }

    public List<TaxiAllocation> getAllList(int userId, String userType) {
        if (UserType.valueOf(userType) == UserType.passenger){
            return taxiAllocationRepository.findByPassengerIdOrderByIdDesc(userId);
        }else{
            return taxiAllocationRepository.findByStatusOrderByIdDesc(AllocationStatus.standBy);
        }
    }
}
