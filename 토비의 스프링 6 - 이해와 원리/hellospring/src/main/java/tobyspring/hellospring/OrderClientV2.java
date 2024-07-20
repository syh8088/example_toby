package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderService;

import java.math.BigDecimal;

public class OrderClientV2 {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);
//        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}
