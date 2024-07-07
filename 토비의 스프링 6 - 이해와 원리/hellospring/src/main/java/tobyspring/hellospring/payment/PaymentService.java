package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

//@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        // 환율 가져오기
        // https://open.er-api.com/v6/latest/USD
        BigDecimal exRate = exRateProvider.getExRate(currency);

        return Payment.createPrepared(
                orderId,
                currency,
                foreignCurrencyAmount,
                exRate,
                LocalDateTime.now(this.clock)
        );
    }

}
