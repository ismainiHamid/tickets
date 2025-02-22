package ma.pub.ticketmanageservice.ticket.mapper;

import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.ticket.dto.TicketDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMapper {
    TicketEntity toTicketEntity(TicketDto ticketDto);

    TicketDto toTicketDto(TicketEntity ticketEntity);

    List<TicketDto> toTicketDtoList(List<TicketEntity> ticketEntities);
}
