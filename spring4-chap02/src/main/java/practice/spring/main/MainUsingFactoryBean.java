package practice.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import practice.spring.OrderService;
import practice.spring.conf.Config;
import practice.spring.search.SearchClientFactory;
import practice.spring.search.SearchServiceHealthChecker;

import java.util.List;

public class MainUsingFactoryBean {
    public static void main(String[] args) {
        // xml 파일을 사용하여 FactoryBean 사용
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");
        SearchClientFactory factory = ctx.getBean("searchClientFactory", SearchClientFactory.class);
        if(factory == null) {
            System.out.println("factory is null");
        }

        ctx.close();

        // annotation을 사용하여 FactoryBean 사용
        AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext(Config.class);
        SearchClientFactory orderFactory = actx.getBean("orderSearchClientFactory", SearchClientFactory.class);
        if(orderFactory == null) {
            System.out.println("orderFactory is null");
        }

        // annotaion을 사용하여 다른 bean에서 FactoryBean 객체를 포함.
        SearchServiceHealthChecker healthChecker = actx.getBean("searchServiceHealthChecker", SearchServiceHealthChecker.class);
        List<SearchClientFactory> factories = healthChecker.getFactories();
        System.out.println("factory in healthChecker count : " + factories.size());
        for(SearchClientFactory f : factories) {
            if(f == null) {
                System.out.println("factory in healthChecker is null");
            }
        }

        // annotation을 사용하여 @AutoWired 사용
        OrderService orderService = actx.getBean("orderService", OrderService.class);
        if(orderService.getAuthFailLogger() == null) {
            System.out.println("logger in orderService is null");
        }

        if(orderService.getAuthenticationService() == null) {
            System.out.println("service in orderService is null");
        }

        actx.close();
    }
}
