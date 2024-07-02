package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // Json 데이터 중 특정 property 값이 존재하지 않아도 무시해라
public record ExRateData(String result, Map<String, BigDecimal> rates) {


}
