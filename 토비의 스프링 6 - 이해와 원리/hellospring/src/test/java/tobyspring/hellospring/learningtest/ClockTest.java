package tobyspring.hellospring.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {

    // Clock 을 이용해서 LocalDateTime.now ?
    @Test
    void clock() throws InterruptedException {

        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        Thread.sleep(100);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isAfter(dt1);
    }

    // Clock 을 Test 에서 사용 할때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
    @Test
    void fixedClock() throws InterruptedException {

        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dt1 = LocalDateTime.now(clock);
        Thread.sleep(100);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        Thread.sleep(100);
        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(3);

        Assertions.assertThat(dt2).isEqualTo(dt1);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(3));
    }
}
