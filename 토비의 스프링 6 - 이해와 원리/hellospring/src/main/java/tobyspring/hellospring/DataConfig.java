package tobyspring.hellospring;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    // data source
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    /**
     * Factory Bean 은 경우 주입 받으면 Factory Bean 을 등록했지만 실제로 만들어지는 건
     * 이 클래스 타입의 오브젝트가 아닙니다.
     * Factory Bean 안에서 먼가 코드에 의해서 만들어주는 그런 어떤 Object 타입의 빈이 등록이 됩니다.
     * 이러한 작업을 수행하는 Factory 자체를 Bean 으로 등록 해주는 겁니다.
     *
     */
    // entity manager factory
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(this.dataSource());
//
//        /**
//         * @Entity 로 생성된 객체를 자동으로 JPA 에서 데이터베이스에 맵핑 해주는 클래스라고 인식을 해줘야 하는데
//         * 그러려면 이 클래스들이 어느 패키지 밑에 존재하는가 이거를 알려주면 애가 자동으로 찾아준다.
//         */
//        em.setPackagesToScan("tobyspring.hellospring");
//
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{ // 더블 브래킷 기법
//            setDatabase(Database.H2);
//            setGenerateDdl(true);
//            setShowSql(true);
//        }});
//
//        return em;
//    }
//
//    @Bean
//    public BeanPostProcessor persistenceAnnotationPostProcessor() {
//        return new PersistenceAnnotationBeanPostProcessor();
//    }

    @Bean
    public PlatformTransactionManager transactionManager(
//            EntityManagerFactory emf
    ) {
//        return new JpaTransactionManager(emf);
        return new DataSourceTransactionManager(this.dataSource());
    }

}
