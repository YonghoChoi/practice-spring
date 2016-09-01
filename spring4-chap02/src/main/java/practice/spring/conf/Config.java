package practice.spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.spring.*;

import java.util.Arrays;

@Configuration
public class Config {
    @Bean
    public User user1() {
        return new User("yhchoi", "1234");
    }

    @Bean(name="user2")
    public User user() {
        return new User("ysjeong", "qwer");
    }

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepo = new UserRepository();
        userRepo.setUsers(Arrays.asList(user1(), user()));
        return userRepo;
    }

    @Bean
    public PasswordChangeService pwChangeSvc() {
        return new PasswordChangeService(userRepository());
    }

    @Bean
    public AuthFailLogger authFailLogger() {
        AuthFailLogger logger = new AuthFailLogger();
        logger.setThreshold(2);
        return logger;
    }

    @Bean
    public AuthenticationService authenticationService() {
        AuthenticationService authSvc = new AuthenticationService();
        authSvc.setFailLogger(authFailLogger());
        authSvc.setUserRepository(userRepository());
        return authSvc;
    }
}
