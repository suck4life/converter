import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ApiManager {
    String baseUrl;
    String apiKey;

    public ApiManager (String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    private String get(String url) throws java.io.IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        //создаем объект HttpURLConnection чтобы сделать get запрос к апишке
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        //получем входной поток
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //считываем ответ от апишки в стрингбилдер и потом в строку его
        return response.toString();
    }

    public List<String> getCurrenciesList() throws java.io.IOException {
        var strResponse = get(baseUrl + "?get=currency_list&key=" + apiKey);
        var gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(strResponse, JsonObject.class);
        //строку пришедшую делаем в обжектджсон, затем по ключу дата достаем массив склеенных валют, его в стринг массив, его в лист.
        return Arrays.asList(gson.fromJson(jsonResponse.getAsJsonArray("data"), String[].class));
    }

    public double getRate (String currencyFrom, String currencyTo) throws java.io.IOException {
        var strResponse = get(baseUrl + "?get=rates&pairs=" + currencyFrom + currencyTo + "&key=" + apiKey);
        //получили ответ в виде строки, с пмощью гсона переводим эту строку в джсон, достаем дату(данные сами), их берем как джсон обжект, достаем коэфициент по этим склейкам ну и в дабл его
        return new Gson()
                .fromJson(strResponse, JsonObject.class)
                .getAsJsonObject("data")
                .get(currencyFrom + currencyTo)
                .getAsDouble();
    }
}
