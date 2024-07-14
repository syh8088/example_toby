package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // Entity Manager Create
        EntityManager entityManager = emf.createEntityManager();

        // transaction start
        entityManager.getTransaction().begin();

        // em.persist - DB 저장해라 즉 영속화 해라
        // 어떤 메소드 작업이 끝나고 어떤 요청을 하나 처리하고 나서 시간이 좀 지난 다음에도
        // 다시 들어가보면 그 정보가 남아 있게 해달라 심지어는 그 처음 만들어던 Order 라는 오브젝트가
        // 가비지 컬렉션 되가지고 메모리에서 사라진다고 할지라도 언제든지 다음에 가서 다시 찾으면
        // Order 를 꺼내올 수 있는 그런 상태로 만들어 달라
        Order order = new Order("100", BigDecimal.TEN);
        entityManager.persist(order);

        System.out.println("order = " + order);

        // transaction end
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
