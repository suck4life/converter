import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiManager {
    String baseUrl;
    String apiKey;

    public ApiManager(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    private String get(String url) throws java.io.IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        //создаем объект HttpURLConnection чтобы сделать get запрос к API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        //получаем входной поток
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //считываем ответ от API в StringBuilder и потом приводим его к String
        return response.toString();
    }

    public double getRate(String currencyFrom, String currencyTo) throws java.io.IOException {
        var strResponse = get(baseUrl + "?get=rates&pairs=" + currencyFrom + currencyTo + "&key=" + apiKey);
        //получили ответ в виде строки, с помощью gson переводим эту строку в json, достаем data (сами данные),
        // их берем как json-object, достаем коэффициент по выбраннымв алютам и переводим все в double
        return new Gson()
                .fromJson(strResponse, JsonObject.class)
                .getAsJsonObject("data")
                .get(currencyFrom + currencyTo)
                .getAsDouble();
    }
}
