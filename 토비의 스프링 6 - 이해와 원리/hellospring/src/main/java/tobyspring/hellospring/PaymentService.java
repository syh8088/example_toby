package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final WebApiExRateProvider webApiExRateProvider;

    public PaymentService() {
        this.webApiExRateProvider =  new WebApiExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        // 환율 가져오기
        // https://open.er-api.com/v6/latest/USD
        WebApiExRateProvider webApiExRateProvider = new WebApiExRateProvider();
        BigDecimal exRate = webApiExRateProvider.getWebExRate(currency);

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // 유효시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}
