package Repository;

import Models.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {

    Map<String, Vehicle> vehicleStore;

    public VehicleRepository(){
        this.vehicleStore =  new HashMap<>();
    }

    public Vehicle addVehicle(Vehicle vehicle)
    {
        vehicleStore.put(vehicle.getNumber(),vehicle);
        return vehicle;

    }
    public Vehicle getVehicle(String number){
        return vehicleStore.get(number);
    }



}
