public enum SlotType {
    SMALL,
    MEDIUM,
    LARGE;

    public double hourlyRate() {
        return switch (this) {
            case SMALL -> 10.0;
            case MEDIUM -> 20.0;
            case LARGE -> 50.0;
        };
    }
}
