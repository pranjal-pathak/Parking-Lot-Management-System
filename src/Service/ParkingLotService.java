package Service;

import Models.Gate;
import Models.ParkingSpot;
import Models.Vehicle;
import Strategy.SpotAssignmentStrategy;
import Repository.ParkingLotRepository;

import java.util.List;

public class ParkingLotService {

    private SpotAssignmentStrategy spotAssignmentStrategy;

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository,SpotAssignmentStrategy spotAssignmentStrategy)
    {
        this.parkingLotRepository = parkingLotRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public ParkingLotService(SpotAssignmentStrategy spotAssignmentStrategy){
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public ParkingSpot getParkingSpot(Vehicle vehicle, Gate gate){
        List<ParkingSpot> parkingSpotList = parkingLotRepository.getAllParkingSpots();

        return spotAssignmentStrategy.assignSpot(vehicle.getVehicleType(),gate,parkingSpotList);
    }

}
