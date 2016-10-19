package practice.spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainUsingQualifier {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("qualifier-config.xml");
//        ctx.getBean("searchClientFactory", SearchClientFactory.class);

    }
}
