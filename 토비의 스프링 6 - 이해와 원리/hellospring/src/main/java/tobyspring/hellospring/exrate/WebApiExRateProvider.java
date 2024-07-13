package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            try(
                    BufferedReader br = new BufferedReader(// 사람이 알아보기 쉽게 텍스트 한줄 한줄 가져옴
                        new InputStreamReader( // 우리가 알아볼수 있는 형태로 변환 (캐릭터 형태로 변경)
                                connection.getInputStream() // 파일이나 네트워트 통해서 넘겨오는 데이터를 바이트 형태로 변환
                        )
                    )
            ) {
                response = br.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();

        // mapper 의 정보를 가져온다. java Object 로 변환
        try {
            ExRateData data = mapper.readValue(response, ExRateData.class);

            System.out.println("API ExRate: " + data.rates().get("KRW"));

            return data.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
