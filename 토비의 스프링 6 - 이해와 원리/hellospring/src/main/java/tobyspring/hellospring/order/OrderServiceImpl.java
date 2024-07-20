package tobyspring.hellospring.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
//    private final PlatformTransactionManager transactionManager;

    public OrderServiceImpl(
            OrderRepository orderRepository
//            , PlatformTransactionManager transactionManager
    ) {
        this.orderRepository = orderRepository;
//        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {

        Order order = new Order(no, total);

//        return new TransactionTemplate(transactionManager).execute(status -> {
            this.orderRepository.save(order);
            return order;
//        });
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {

//        return new TransactionTemplate(transactionManager).execute(status -> {
            return reqs.stream()
                    .map(req -> createOrder(req.no(), req.total()))
                    .toList();
//        });
    }
}
