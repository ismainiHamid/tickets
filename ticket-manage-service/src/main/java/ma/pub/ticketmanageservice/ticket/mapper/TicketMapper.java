package ma.pub.ticketmanageservice.ticket.mapper;

import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.ticket.dto.TicketRequestDto;
import ma.pub.ticketmanageservice.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMapper {
    TicketEntity toTicketEntity(TicketRequestDto ticketRequestDto);

    TicketResponseDto toTicketResponseDto(TicketEntity ticketEntity);

    List<TicketResponseDto> toTicketResponseDtoList(List<TicketEntity> ticketEntities);
}
