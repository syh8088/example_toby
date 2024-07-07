package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit 이 이 ContextConfiguration 이용 할려면 이게
 * 스프링의 기능을 이용해서 확장을 해달라고 ExtendWith 이라는 걸 추가 해야 합니다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Clock clock;

    @Autowired
    // 인텔리제이가 이런 타입을 못 찾겠다고 에러 밑줄이 나오는 경우가 있는데 에러 아님
    private ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {

        // exRate: 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(valueOf(10_000));


        // exRate: 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져온다
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));

        // 원화환산금액 계산
        assertThat(payment2.getConvertedAmount())
                .isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    @DisplayName("원화환산금액의 유효시간 계산")
    void validUntil() throws IOException {

        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until 이 prepare() 30분 뒤로 설정 되어 있는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}