package kwy.taxi.allocation.controller;

import kwy.taxi.allocation.controller.bind.TaxiAllocation;
import kwy.taxi.allocation.model.TaxiAllocationDto;
import kwy.taxi.allocation.service.TaxiAllocationService;
import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/taxi-requests")
public class TaxiAllocationController {

    private final TaxiAllocationService taxiAllocationService;

    public TaxiAllocationController(TaxiAllocationService taxiAllocationService) {
        this.taxiAllocationService = taxiAllocationService;
    }

    @GetMapping
    public List<TaxiAllocation> listAllocation(@RequestAttribute("id") int userid, @RequestAttribute("userType") String userType) {

        return taxiAllocationService.getAllList(userid, userType);
    }

    @PostMapping
    public TaxiAllocation requestTaxi(
            @RequestBody TaxiAllocationDto allocation,
            @RequestAttribute("id") int userId,
            @RequestAttribute("userType") String userType

    ){
        if(!userType.equals("passenger")) throw new CustomException(ErrorCode.PASSENGER_EXCLUSIVE);
        allocation.setPassengerId(userId);
        return taxiAllocationService.createTaxiRequest(allocation);
    }

    @PostMapping("/{taxiRequestId}/accept")
    public TaxiAllocation acceptAllocation(
            @PathVariable("taxiRequestId") int taxiRequestId,
            @RequestAttribute("id") int userId,
            @RequestAttribute("userType") String userType
    ){
        if(!userType.equals("driver")) throw new CustomException(ErrorCode.DRIVER_EXCLUSIVE);

        return taxiAllocationService.acceptAllocation(taxiRequestId, userId);
    }


}
