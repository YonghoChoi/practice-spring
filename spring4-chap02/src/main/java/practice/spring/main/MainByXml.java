package practice.spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;
import practice.spring.AuthException;
import practice.spring.AuthenticationService;
import practice.spring.PasswordChangeService;
import practice.spring.UserNotFoundException;

public class MainByXml {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");
        AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
        runAuthAndCatchAuthEx(authSvc, "yhchoi", "1111");
        runAuthAndCatchAuthEx(authSvc, "yhchoi", "11111");
        runAuthAndCatchAuthEx(authSvc, "yhchoi", "111111");

        try {
            authSvc.authenticate("yhchoi2", "1111");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        authSvc.authenticate("yhchoi", "1234");
        PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
        pwChgSvc.changePassword("yhchoi", "1234", "5678");
        runAuthAndCatchAuthEx(authSvc, "yhchoi", "1234");
        authSvc.authenticate("yhchoi", "5678");
        ctx.close();
    }

    private static void runAuthAndCatchAuthEx(AuthenticationService authSvc, String userId, String password) {
        try {
            authSvc.authenticate(userId, password);
        } catch(AuthException e) {
            e.printStackTrace();
        }
    }
}
