import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final Map<Integer, EntryGate> gates;
    private final Map<String, ParkingTicket> activeTickets;
    private int ticketCounter;

    public ParkingLot(List<ParkingSlot> slots, List<EntryGate> gates) {
        this.slots = slots;
        this.gates = new HashMap<>();
        for (EntryGate g : gates) {
            this.gates.put(g.getGateId(), g);
        }
        this.activeTickets = new HashMap<>();
        this.ticketCounter = 0;
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime,
                              SlotType requestedSlotType, int entryGateId) {

        EntryGate gate = gates.get(entryGateId);
        if (gate == null) {
            throw new IllegalArgumentException("Invalid gate: " + entryGateId);
        }

        List<SlotType> compatible = vehicle.getType().compatibleSlots();

        // find the nearest available slot that is compatible and >= requested type
        ParkingSlot best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : slots) {
            if (slot.isOccupied()) continue;
            if (!compatible.contains(slot.getType())) continue;
            if (slot.getType().ordinal() < requestedSlotType.ordinal()) continue;

            int distance = Math.abs(slot.getSlotNumber() - gate.getNearestSlotStart());
            if (distance < bestDistance) {
                bestDistance = distance;
                best = slot;
            }
        }

        if (best == null) {
            throw new RuntimeException("No available slot for " + vehicle);
        }

        best.occupy();
        ticketCounter++;
        String ticketId = "TKT-" + String.format("%04d", ticketCounter);
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, best, entryTime);
        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    public Bill exit(ParkingTicket ticket, LocalDateTime exitTime) {
        if (!activeTickets.containsKey(ticket.getTicketId())) {
            throw new IllegalArgumentException("Ticket not found: " + ticket.getTicketId());
        }

        ticket.getSlot().free();
        activeTickets.remove(ticket.getTicketId());

        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);
        if (hours == 0) hours = 1;

        double amount = hours * ticket.getSlot().getType().hourlyRate();
        return new Bill(ticket, exitTime, hours, amount);
    }

    public void status() {
        Map<SlotType, int[]> counts = new LinkedHashMap<>();
        for (SlotType t : SlotType.values()) {
            counts.put(t, new int[]{0, 0});
        }

        for (ParkingSlot slot : slots) {
            int[] arr = counts.get(slot.getType());
            arr[0]++;
            if (!slot.isOccupied()) arr[1]++;
        }

        System.out.println("--- Parking Status ---");
        for (Map.Entry<SlotType, int[]> e : counts.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue()[1] + " free / " + e.getValue()[0] + " total");
        }
    }
}
