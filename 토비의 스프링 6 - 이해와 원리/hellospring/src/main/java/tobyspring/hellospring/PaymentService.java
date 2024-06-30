package tobyspring.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {

        // 환율 가져오기
        // 금액 계산
        // 유효시간 계산
        return new Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment prepare = paymentService.prepare(100L, "UDD", BigDecimal.valueOf(50.7));
        System.out.println("prepare = " + prepare);
    }
}
