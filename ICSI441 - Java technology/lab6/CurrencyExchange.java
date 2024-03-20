import java.util.HashMap;
import java.util.Map;

class ExchangeRateChange<T> {
    private String bankName;
    private Map<T, Double> changes = new HashMap<>();

    public ExchangeRateChange(String bankName) {
        this.bankName = bankName;
    }

    public void addChange(T currency, double change) {
        changes.put(currency, change);
    }

    public String getBankName() {
        return bankName;
    }

    public Map<T, Double> getChanges() {
        return changes;
    }
}

public class CurrencyExchange {
    public static <T> void ExchangeRates(Map<String, ExchangeRateChange<T>> banks) {
        String bankWithLargestIncrease = null;
        String bankWithLargestDecrease = null;
        T currencyWithLargestIncrease = null;
        T currencyWithLargestDecrease = null;
        double largestIncrease = Double.NEGATIVE_INFINITY;
        double largestDecrease = Double.POSITIVE_INFINITY;

        for (ExchangeRateChange<T> bank : banks.values()) {
            Map<T, Double> changes = bank.getChanges();
            for (Map.Entry<T, Double> entry : changes.entrySet()) {
                T currency = entry.getKey();
                double change = entry.getValue();
                if (change > largestIncrease) {
                    largestIncrease = change;
                    bankWithLargestIncrease = bank.getBankName();
                    currencyWithLargestIncrease = currency;
                }
                if (change < largestDecrease) {
                    largestDecrease = change;
                    bankWithLargestDecrease = bank.getBankName();
                    currencyWithLargestDecrease = currency;
                }
            }
        }

        System.out.println("Хамгийн өндөр өсөлттэй Банк: " + bankWithLargestIncrease + " " +
                currencyWithLargestIncrease + ": " + largestIncrease);
        System.out.println("Хамгийн өндөр бууралттай Банк: " + bankWithLargestDecrease + " " +
                currencyWithLargestDecrease + ": " + largestDecrease);
    }

    public static void main(String[] args) {
        Map<String, ExchangeRateChange<String>> banks = new HashMap<>();

        // Add exchange rate change data for each bank
        banks.put("ХХБанк", new ExchangeRateChange<>("ХХБанк"));
        banks.get("ХХБанк").addChange("USD", -1.0);
        banks.get("ХХБанк").addChange("EUR", -1.0);
        banks.get("ХХБанк").addChange("JPY", -0.05);
        banks.get("ХХБанк").addChange("GBP", -19.0);
        banks.get("ХХБанк").addChange("RUB", -0.1);
        banks.get("ХХБанк").addChange("CNY", -0.5);
        banks.get("ХХБанк").addChange("KRW", -0.01);

        banks.put("Голомт Банк", new ExchangeRateChange<>("Голомт Банк"));
        banks.get("Голомт Банк").addChange("USD", -1.0);
        banks.get("Голомт Банк").addChange("EUR", 6.0);
        banks.get("Голомт Банк").addChange("JPY", -0.04);
        banks.get("Голомт Банк").addChange("GBP", -14.0);
        banks.get("Голомт Банк").addChange("RUB", 0.0);
        banks.get("Голомт Банк").addChange("CNY", -0.3);
        banks.get("Голомт Банк").addChange("KRW", -0.005);

        banks.put("Төрийн Банк", new ExchangeRateChange<>("Төрийн Банк"));
        banks.get("Төрийн Банк").addChange("USD", -1.0);
        banks.get("Төрийн Банк").addChange("EUR", 7.0);
        banks.get("Төрийн Банк").addChange("JPY", -0.03);
        banks.get("Төрийн Банк").addChange("GBP", -13.0);
        banks.get("Төрийн Банк").addChange("RUB", 0);
        banks.get("Төрийн Банк").addChange("CNY", 0.0);
        banks.get("Төрийн Банк").addChange("KRW", 0);

        banks.put("Богд Банк", new ExchangeRateChange<>("Богд Банк"));
        banks.get("Богд Банк").addChange("USD", 0);
        banks.get("Богд Банк").addChange("EUR", 6.0);
        banks.get("Богд Банк").addChange("JPY", -0.3);
        banks.get("Богд Банк").addChange("GBP", -12.0);
        banks.get("Богд Банк").addChange("RUB", 0);
        banks.get("Богд Банк").addChange("CNY", 0);
        banks.get("Богд Банк").addChange("KRW", -0.01);

        ExchangeRates(banks);
    }
}
