package Controller;

import DTO.GenerateTicketRequestDto;
import DTO.GenerateTicketResponseDto;
import DTO.ResponseStatus;
import Models.*;
import Service.TicketService;
import Exception.NoParkingSpotFoundException;


public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

//    DTO- data transfer object which transfers the data from client to the underlying system
//    we should not directly pass the data

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto generateTicketRequestDto){

//        expose only the data to the service which is required, do not pass the whole dto
        try {
            Ticket ticket = ticketService.generateTicket(
                    generateTicketRequestDto.getVehicleNumber(),
                    generateTicketRequestDto.getVehicleType(),
                    generateTicketRequestDto.getGateId());

            GenerateTicketResponseDto generateTicketResponseDto = new GenerateTicketResponseDto();
            generateTicketResponseDto.setTicket(ticket);
            generateTicketResponseDto.setStatus(ResponseStatus.SUCCESS);

            return generateTicketResponseDto;
        }catch (NoParkingSpotFoundException e) {
            GenerateTicketResponseDto generateTicketResponseDto
                    = new GenerateTicketResponseDto();
            generateTicketResponseDto.setStatus(ResponseStatus.FAILURE);
            generateTicketResponseDto.setFailureMessage(e.getMessage());
            return generateTicketResponseDto;
        }


//        generic exception catch block
        catch(Exception e)
        {
            GenerateTicketResponseDto generateTicketResponseDto = new GenerateTicketResponseDto();
            generateTicketResponseDto.setStatus(ResponseStatus.FAILURE);
            generateTicketResponseDto.setFailureMessage(e.getMessage());
            return  generateTicketResponseDto;
        }


    }
}
