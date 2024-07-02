package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(// 사람이 알아보기 쉽게 텍스트 한줄 한줄 가져옴
                new InputStreamReader( // 우리가 알아볼수 있는 형태로 변환 (캐릭터 형태로 변경)
                        connection.getInputStream() // 파일이나 네트워트 통해서 넘겨오는 데이터를 바이트 형태로 변환
                )
        );

        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();

        // mapper 의 정보를 가져온다. java Object 로 변환
        ExRateData data = mapper.readValue(response, ExRateData.class);

        System.out.println("API ExRate: " + data.rates().get("KRW"));

        return data.rates().get("KRW");
    }

}
