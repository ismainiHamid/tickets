package ma.pub.ticketmanageservice.user;

import ma.pub.ticketmanageservice.exceptions.DuplicatedPasswordException;
import ma.pub.ticketmanageservice.user.dto.RegisterRequestDto;
import ma.pub.ticketmanageservice.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    UserDto registerUser(RegisterRequestDto registerRequestDto) throws DuplicatedPasswordException;

    Page<UserDto> getAllUsers(int page, int size, String[] sort);
}
