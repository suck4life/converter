public class Converter {
    private final ApiManager apiManager = new ApiManager("https://currate.ru/api/", "fb3ee47a36c4a6d067f453dc56b868c1");

    public double convert(String currencyFrom, String currencyTo, double valueFrom) throws java.io.IOException {
        double rate = apiManager.getRate(currencyFrom, currencyTo);
        //запрашиваем коэффициент выбранных пользователем валют
        return valueFrom * rate;
        //умножаем введенное значение на коэффициент.
    }
}