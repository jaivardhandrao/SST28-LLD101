public class EntryGate {
    private final int gateId;
    private final int nearestSlotStart;

    public EntryGate(int gateId, int nearestSlotStart) {
        this.gateId = gateId;
        this.nearestSlotStart = nearestSlotStart;
    }

    public int getGateId() { return gateId; }
    public int getNearestSlotStart() { return nearestSlotStart; }

    @Override
    public String toString() {
        return "Gate-" + gateId;
    }
}
