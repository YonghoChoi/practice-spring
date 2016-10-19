package practice.spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.spring.*;
import practice.spring.search.SearchClientFactory;
import practice.spring.search.SearchClientFactoryBean;
import practice.spring.search.SearchServiceHealthChecker;

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

    /**
     * FactoryBean 인터페이스를 구현한 클래스를 자바에서 설정
     */
    @Bean
    public SearchClientFactoryBean orderSearchClientFactory() {
        SearchClientFactoryBean searchClientFactoryBean = new SearchClientFactoryBean();
        searchClientFactoryBean.setServer("127.0.0.1");
        searchClientFactoryBean.setPort(8888);
        searchClientFactoryBean.setContentType("json");
        return searchClientFactoryBean;
    }

    /**
     * 다른 Bean 객체에서 SearchClientFactory Bean이 필요한 경우
     * FactoryBean 구현 객체를 리턴하는 메서드를 호출해서 FactoryBean 객체를 구하고
     * 그 객체의 getObject() 메서드를 호출하여 필요한 객체를 구한다.
     *
     * 사용하는 쪽에서는 AnnotationConfigApplicationContext 객체로 getBean을 호출하여 필요한 객체를 얻는다.
     */
    @Bean
    public SearchServiceHealthChecker searchServiceHealthChecker() throws Exception {
        SearchServiceHealthChecker healthChecker = new SearchServiceHealthChecker();
        healthChecker.setFactories(Arrays.asList(
                orderSearchClientFactory().getObject(),
                productSearchClientFactory().getObject()
        ));

        return healthChecker;
    }

    /**
     * 파라미터로 전달 받는 방법.
     * getBean 시에 파라미터 이름과 같은 객체를 스프링 컨테이너에서 가져와 전달된다.
     *
     * 스프링은 @Bean 애노테이션이 적용된 메서드에 파라미터가 존재할 경우,
     * 해당 파라미터의 타입과 이름을 사용해서 빈 객체를 전달한다.
     */
    @Bean
    public SearchServiceHealthChecker searchServiceHealthChecker(SearchClientFactory orderSearchClientFactory, SearchClientFactory productSearchClientFactory) {
        SearchServiceHealthChecker healthChecker = new SearchServiceHealthChecker();
        healthChecker.setFactories(Arrays.asList(orderSearchClientFactory, productSearchClientFactory));
        return healthChecker;
    }

    private SearchClientFactoryBean productSearchClientFactory() {
        return new SearchClientFactoryBean();
    }
}
