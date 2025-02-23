package ma.pub.ticketmanageservice.ticket.mapper;

import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.ticket.dto.TicketDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketEntity toTicketEntity(TicketDto ticketDto) {
        return TicketEntity.Builder.aTicketEntity()
                .withId(ticketDto.getId())
                .withTitle(ticketDto.getTitle())
                .withDescription(ticketDto.getDescription())
                .withPriority(ticketDto.getPriority())
                .withCategory(ticketDto.getCategory())
                .build();
    }

    @Override
    public TicketDto toTicketDto(TicketEntity ticketEntity) {
        return TicketDto.Builder.aTicketDto()
                .withId(ticketEntity.getId())
                .withTicketId(ticketEntity.getTicketId())
                .withTitle(ticketEntity.getTitle())
                .withDescription(ticketEntity.getDescription())
                .withPriority(ticketEntity.getPriority())
                .withCategory(ticketEntity.getCategory())
                .withStatus(ticketEntity.getStatus())
                .withCreatedAt(ticketEntity.getCreatedAt())
                .build();
    }

    @Override
    public List<TicketDto> toTicketDtoList(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream().map(this::toTicketDto).toList();
    }
}
