package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderStub;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;

@Configuration
//@ComponentScan
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(this.exRateProvider(), this.clock());
    }

//    @Bean
//    public ExRateProvider cashedExRateProvider() {
//        return new CashedExRateProvider(exRateProvider());
//    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(1_000));
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
