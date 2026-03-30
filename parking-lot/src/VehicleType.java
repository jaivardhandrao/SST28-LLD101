import java.util.List;

public enum VehicleType {
    TWO_WHEELER,
    CAR,
    BUS;

    public List<SlotType> compatibleSlots() {
        return switch (this) {
            case TWO_WHEELER -> List.of(SlotType.SMALL, SlotType.MEDIUM, SlotType.LARGE);
            case CAR -> List.of(SlotType.MEDIUM, SlotType.LARGE);
            case BUS -> List.of(SlotType.LARGE);
        };
    }
}
