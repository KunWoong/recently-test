package kwy.taxi.allocation.model;

import kwy.taxi.allocation.utils.AllocationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TAXI_ALLOCATION")
@SequenceGenerator(
        name = "TAXI_SEQ_GENERATOR",
        sequenceName = "TAXI_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class TaxiAllocationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXI_SEQ_GENERATOR")
    private int id;
    private String address;
    private int driverId;
    private int passengerId;
    @Enumerated(EnumType.STRING)
    private AllocationStatus status = AllocationStatus.standBy;
    private LocalDateTime acceptedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Version
    private long version;

    public TaxiAllocationDto() {
    }

    public TaxiAllocationDto(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public AllocationStatus getStatus() {
        return status;
    }

    public void setStatus(AllocationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public static class Builder {
        private int id;
        private String address;
        private int driverId;
        private int passengerId;
        private AllocationStatus status;
        private LocalDateTime acceptedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private long version;

        private Builder() {
        }

        public static Builder aTaxiAllocation() {
            return new Builder();
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder driverId(int driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder passengerId(int passengerId) {
            this.passengerId = passengerId;
            return this;
        }

        public Builder status(AllocationStatus status) {
            this.status = status;
            return this;
        }

        public Builder acceptedAt(LocalDateTime acceptedAt) {
            this.acceptedAt = acceptedAt;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        public Builder version(long version) {
            this.version = version;
            return this;
        }

        public TaxiAllocationDto build() {
            TaxiAllocationDto taxiAllocationDto = new TaxiAllocationDto(passengerId);
            taxiAllocationDto.setId(id);
            taxiAllocationDto.setAddress(address);
            taxiAllocationDto.setDriverId(driverId);
            taxiAllocationDto.setStatus(status);
            taxiAllocationDto.setAcceptedAt(acceptedAt);
            taxiAllocationDto.setCreatedAt(createdAt);
            taxiAllocationDto.setUpdatedAt(updatedAt);
            taxiAllocationDto.setVersion(version);
            return taxiAllocationDto;
        }
    }
}
