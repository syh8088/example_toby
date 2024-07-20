package tobyspring.hellospring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.OrderConfig;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit 이 이 ContextConfiguration 이용 할려면 이게
 * 스프링의 기능을 이용해서 확장을 해달라고 ExtendWith 이라는 걸 추가 해야 합니다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {

        var order = orderService.createOrder("0100", BigDecimal.ONE);
        assertThat(order.getId()).isGreaterThan(0);
    }

}