package DTO;

import Models.Ticket;

public class GenerateTicketResponseDto {
    private Ticket ticket;
    private ResponseStatus status;
    private String FailureMessage;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getFailureMessage() {
        return FailureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        FailureMessage = failureMessage;
    }
}
