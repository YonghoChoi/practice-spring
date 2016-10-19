package practice.spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;
import practice.spring.OrderService;

public class MainUsingAutowired {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");
        // annotation을 사용하여 @AutoWired 사용
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        if(orderService.getAuthFailLogger() == null) {
            System.out.println("logger in orderService is null");
        }

        if(orderService.getAuthenticationService() == null) {
            System.out.println("service in orderService is null");
        }

        if(orderService.getSearchServiceHealthChecker() != null) {
            System.out.println("it should be null because healthChecker is not defiend bean.");
        }

        ctx.close();
    }
}
