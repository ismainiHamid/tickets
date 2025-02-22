package ma.pub.ticketmanageservice.runner;

import ma.pub.ticketmanageservice.user.UserEntity;
import ma.pub.ticketmanageservice.user.UserJpaRepository;
import ma.pub.ticketmanageservice.user.enums.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandLineRunnerService implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;

    public CommandLineRunnerService(PasswordEncoder passwordEncoder, UserJpaRepository userJpaRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void run(String... args) {
        List<UserEntity> users = List.of(UserEntity.Builder.anUserEntity()
                        .withUsername("hamid.ismaini")
                        .withPassword(passwordEncoder.encode("26*06*1997!"))
                        .withRole(RoleEnum.IT_SUPPORT)
                        .build(),
                UserEntity.Builder.anUserEntity()
                        .withUsername("simo.ismaini")
                        .withPassword(passwordEncoder.encode("26*06*1997!"))
                        .withRole(RoleEnum.EMPLOYEE)
                        .build()
        );

        users.forEach(user->{
            if(!this.userJpaRepository.existsByUsername(user.getUsername()))
                this.userJpaRepository.saveAndFlush(user);
        });
    }
}
