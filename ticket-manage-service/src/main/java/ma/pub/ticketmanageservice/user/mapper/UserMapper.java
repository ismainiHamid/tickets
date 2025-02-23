package ma.pub.ticketmanageservice.user.mapper;

import ma.pub.ticketmanageservice.user.UserEntity;
import ma.pub.ticketmanageservice.user.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    UserDto toUserDto(UserEntity userEntity);

    List<UserDto> toUserDtoList(List<UserEntity> userEntities);
}
