package tobyspring.hellospring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * JUnit 이 이 ContextConfiguration 이용 할려면 이게
 * 스프링의 기능을 이용해서 확장을 해달라고 ExtendWith 이라는 걸 추가 해야 합니다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {

        var order = orderService.createOrder("0100", BigDecimal.ONE);
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {

        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TWO)
        );

        var orders = orderService.createOrders(orderReqs);
        assertThat(orders).hasSize(2);
        orders.forEach(order -> {
            assertThat(order.getId()).isGreaterThan(0);
        });
    }

    @Test
    void createDuplicatedOrders() {

        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TWO)
        );

        assertThatThrownBy(() -> {
            orderService.createOrders(orderReqs);
        }).isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(this.dataSource);
        var count = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
        assertThat(count).isEqualTo(0);
    }
}