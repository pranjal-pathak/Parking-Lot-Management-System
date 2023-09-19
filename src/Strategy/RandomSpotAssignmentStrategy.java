package Strategy;

import Models.Gate;
import Models.ParkingSpot;
import Models.ParkingSpotStatus;
import Models.VehicleType;

import java.util.List;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public ParkingSpot assignSpot(VehicleType type, Gate gate, List<ParkingSpot> parkingSpotList) {

        for(ParkingSpot parkingSpot: parkingSpotList)
        {
            if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)&&
                parkingSpot.getSupportedVehicleTypes().contains((type))){
                return parkingSpot;
            }
        }
        return null;
    }
}
