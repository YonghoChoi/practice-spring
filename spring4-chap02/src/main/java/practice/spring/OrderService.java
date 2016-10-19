package practice.spring;

import org.springframework.beans.factory.annotation.Autowired;
import practice.spring.search.SearchServiceHealthChecker;

/**
 * Autowired가 호출되는 순서
 * 1. 생성자에 Autowired가 적용
 * 2. 필드에 Autowired 적용
 * 3. 메서드에 Autowired 적용
 *
 * 생성자에 Autowired를 적용한 경우 기본 생성자는 호출되지 않음.
 */
public class OrderService {
    @Autowired
    private AuthFailLogger authFailLogger;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired(required = false)  // 스프링 컨테이너에 등록하지 않은 bean에 @Autowired 적용. (required 속성을 지정하지 않으면 최소 한개의 후보가 필요하다는 익셉션 발생)
    SearchServiceHealthChecker searchServiceHealthChecker;

    public OrderService() {
        System.out.println("constructor");
    }

    @Autowired
    public OrderService(AuthFailLogger authFailLogger, AuthenticationService authenticationService) {
        this.authFailLogger = authFailLogger;
        this.authenticationService = authenticationService;
    }

    @Autowired  // 메서드에 @Autowired가 적용되면 스프링 프레임워크에서 해당 메서드를 수행한다. (메서드명 상관 없음)
    public void init(AuthFailLogger authFailLogger, AuthenticationService authenticationService) {
        this.authFailLogger = authFailLogger;
        this.authenticationService = authenticationService;
    }

    public AuthFailLogger getAuthFailLogger() {
        return authFailLogger;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public SearchServiceHealthChecker getSearchServiceHealthChecker() {
        return searchServiceHealthChecker;
    }
}
