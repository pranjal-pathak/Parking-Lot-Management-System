package Service;

import Models.Gate;
import Models.Ticket;
import Models.Vehicle;
import Models.VehicleType;
import Models.ParkingSpot;
import Repository.TicketRepository;
import Strategy.SpotAssignmentStrategy;
import Exception.NoParkingSpotFoundException;


import java.util.Date;

public class TicketService {

    private VehicleService vehicleService;

    private GateService gateService;

    private ParkingSpotService parkingSpotService;

    private TicketRepository ticketRepository;

   private ParkingLotService parkingLotService;
    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, long gateId) throws NoParkingSpotFoundException {


//        Flow:
//        1- get vehicle from DB using vehicleNumber
//        2- if vehicle not found, register vehicle

        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        if(vehicle==null){
            vehicle = vehicleService.registerVehicle(vehicleNumber,vehicleType);
        }

        Gate gate = gateService.getGate(gateId);

//        3-Assign parking spot
          ParkingSpot parkingSpot = parkingLotService.getParkingSpot(vehicle,gate);
          if(parkingSpot==null)
          {
              throw new NoParkingSpotFoundException("No Parking Spot Found");
          }

//        4- update parking spot as occupied
        parkingSpot = parkingSpotService.assignParkingSpot(parkingSpot);

//        5- generate ticket
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setGate(gate);
        ticket.setParkingSpot(parkingSpot);
        ticket.setOperator(gate.getOperator());
        ticket.setEntryTime(new Date());

        //6- save ticket in db
        ticketRepository.save(ticket);



        return ticket;
    }
}
