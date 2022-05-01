import java.io.IOException;

public class Converter {
    private final ApiManager apiManager = new ApiManager("https://currate.ru/api/", "fb3ee47a36c4a6d067f453dc56b868c1");
//    private List<String> getCurrenciesList() {
//        return new ArrayList<>(labelToCurrency.values());
//        List<String> currenciesList = new ArrayList<>();
//
//        try {
//            currenciesList = apiManager.getCurrenciesList();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        return currenciesList
//                .stream()
//                .map(pair -> pair.substring(0, 3))
//                .distinct()
//                .toList();
//            //возвращаем лист разделенных пар как лист уникальных валют
//    }

    public double convert (String currencyFrom, String currencyTo, double valueFrom) throws IOException {
        double rate = apiManager.getRate(currencyFrom, currencyTo);
        return valueFrom * rate;
    }
}