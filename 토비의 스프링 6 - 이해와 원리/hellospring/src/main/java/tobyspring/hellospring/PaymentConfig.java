package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ErApiExtractor;
import tobyspring.hellospring.api.SimpleApiExecute;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
//@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(this.exRateProvider(), this.clock());
    }

//    @Bean
//    public ExRateProvider cashedExRateProvider() {
//        return new CashedExRateProvider(exRateProvider());
//    }


    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecute(), new ErApiExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(this.apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
