package ma.pub.ticketmanageservice.user;

import ma.pub.ticketmanageservice.exceptions.DuplicatedPasswordException;
import ma.pub.ticketmanageservice.user.dto.RegisterRequestDto;
import ma.pub.ticketmanageservice.user.dto.UserDto;
import ma.pub.ticketmanageservice.user.mapper.UserMapper;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserJpaRepository userJpaRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(RegisterRequestDto registerRequestDto) throws DuplicatedPasswordException {
        if (!registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword()))
            throw new DuplicatedPasswordException("The passwords don't match");
        return this.userMapper.toUserDto(
                this.userJpaRepository.saveAndFlush(UserEntity.Builder.anUserEntity()
                        .withUsername(registerRequestDto.getUsername())
                        .withPassword(this.passwordEncoder.encode(
                                registerRequestDto.getPassword()
                        ))
                        .withRole(registerRequestDto.getRole())
                        .build()
                )
        );
    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);

        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);

        return new PageImpl<>(this.userMapper.toUserDtoList(
                this.userJpaRepository.findAllBy(pageable)
        ), pageable, this.userJpaRepository.count());
    }
}
