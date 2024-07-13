package tobyspring.hellospring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecute implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {

        String response;
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

        return response;
    }
}
