package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderStub;
import tobyspring.hellospring.payment.PaymentService;

import static java.math.BigDecimal.valueOf;

@Configuration
//@ComponentScan
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(this.exRateProvider());
    }

//    @Bean
//    public ExRateProvider cashedExRateProvider() {
//        return new CashedExRateProvider(exRateProvider());
//    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(1_000));
    }
}
