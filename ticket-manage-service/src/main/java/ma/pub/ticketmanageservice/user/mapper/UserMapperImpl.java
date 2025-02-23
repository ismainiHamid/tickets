package ma.pub.ticketmanageservice.user.mapper;

import ma.pub.ticketmanageservice.user.UserEntity;
import ma.pub.ticketmanageservice.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.Builder.anUserDto()
                .withId(userEntity.getId())
                .withUsername(userEntity.getUsername())
                .withRole(userEntity.getRole())
                .build();
    }

    @Override
    public List<UserDto> toUserDtoList(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::toUserDto).toList();
    }
}
