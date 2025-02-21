package ma.pub.ticketmanageservice.ticket.mapper;

import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.ticket.dto.TicketRequestDto;
import ma.pub.ticketmanageservice.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketEntity toTicketEntity(TicketRequestDto ticketRequestDto) {
        return TicketEntity.Builder.aTicketEntity()
                .withTitle(ticketRequestDto.getTitle())
                .withDescription(ticketRequestDto.getDescription())
                .withPriority(ticketRequestDto.getPriority())
                .withCategory(ticketRequestDto.getCategory())
                .build();
    }

    @Override
    public TicketResponseDto toTicketResponseDto(TicketEntity ticketEntity) {
        return TicketResponseDto.Builder.aTicketResponseDto()
                .withId(ticketEntity.getId())
                .withTitle(ticketEntity.getTitle())
                .withDescription(ticketEntity.getDescription())
                .withPriority(ticketEntity.getPriority())
                .withCategory(ticketEntity.getCategory())
                .withStatus(ticketEntity.getStatus())
                .withCreatedAt(ticketEntity.getCreatedAt())
                .build();
    }

    @Override
    public List<TicketResponseDto> toTicketResponseDtoList(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream().map(this::toTicketResponseDto).toList();
    }
}
