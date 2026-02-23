import java.util.*;

// Responsible only for calculating prices
public class PricingCalculator {
    public double subtotal(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double sum = 0.0;
        for (OrderLine l : lines) {
            sum += menu.get(l.itemId).price * l.qty;
        }
        return sum;
    }

    public double tax(double subtotal, String customerType) {
        return subtotal * (TaxRules.taxPercent(customerType) / 100.0);
    }

    public double discount(String customerType, double subtotal, int lineCount) {
        return DiscountRules.discountAmount(customerType, subtotal, lineCount);
    }
}
