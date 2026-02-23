import java.util.*;

// New room types can be added via register() - no need to edit the calculator
public class RoomPricing {
    private final Map<Integer, Double> prices = new HashMap<>();

    public RoomPricing() {
        prices.put(LegacyRoomTypes.SINGLE, 14000.0);
        prices.put(LegacyRoomTypes.DOUBLE, 15000.0);
        prices.put(LegacyRoomTypes.TRIPLE, 12000.0);
        prices.put(LegacyRoomTypes.DELUXE, 16000.0);
    }

    public void register(int roomType, double price) {
        prices.put(roomType, price);
    }

    public double getPrice(int roomType) {
        return prices.getOrDefault(roomType, 16000.0);
    }
}
