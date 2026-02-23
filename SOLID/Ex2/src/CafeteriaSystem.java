import java.util.*;

// Now only orchestrates - delegates pricing and formatting to separate classes
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final FileStore store = new FileStore();
    private final PricingCalculator pricing = new PricingCalculator();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = pricing.subtotal(menu, lines);
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = pricing.tax(subtotal, customerType);
        double discount = pricing.discount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = InvoiceFormatter.format(invId, menu, lines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
