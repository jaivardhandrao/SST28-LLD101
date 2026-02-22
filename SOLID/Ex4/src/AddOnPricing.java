import java.util.*;

// New add-ons can be registered without modifying the calculator
public class AddOnPricing {
    private final Map<AddOn, Double> prices = new EnumMap<>(AddOn.class);

    public AddOnPricing() {
        prices.put(AddOn.MESS, 1000.0);
        prices.put(AddOn.LAUNDRY, 500.0);
        prices.put(AddOn.GYM, 300.0);
    }

    public void register(AddOn addOn, double price) {
        prices.put(addOn, price);
    }

    public double getPrice(AddOn addOn) {
        return prices.getOrDefault(addOn, 0.0);
    }
}
