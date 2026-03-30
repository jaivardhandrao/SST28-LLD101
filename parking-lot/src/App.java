import java.time.LocalDateTime;
import java.util.*;

public class App {
    public static void main(String[] args) {

        // setup: 2 floors, slots numbered 1-12
        List<ParkingSlot> slots = new ArrayList<>();
        // floor 1
        slots.add(new ParkingSlot(1, SlotType.SMALL, 1, 1));
        slots.add(new ParkingSlot(2, SlotType.SMALL, 1, 2));
        slots.add(new ParkingSlot(3, SlotType.MEDIUM, 1, 3));
        slots.add(new ParkingSlot(4, SlotType.MEDIUM, 1, 4));
        slots.add(new ParkingSlot(5, SlotType.LARGE, 1, 5));
        slots.add(new ParkingSlot(6, SlotType.LARGE, 1, 6));
        // floor 2
        slots.add(new ParkingSlot(7, SlotType.SMALL, 2, 7));
        slots.add(new ParkingSlot(8, SlotType.SMALL, 2, 8));
        slots.add(new ParkingSlot(9, SlotType.MEDIUM, 2, 9));
        slots.add(new ParkingSlot(10, SlotType.MEDIUM, 2, 10));
        slots.add(new ParkingSlot(11, SlotType.LARGE, 2, 11));
        slots.add(new ParkingSlot(12, SlotType.LARGE, 2, 12));

        // two entry gates
        List<EntryGate> gates = List.of(
            new EntryGate(1, 1),   // gate 1 near slot 1
            new EntryGate(2, 12)   // gate 2 near slot 12
        );

        ParkingLot lot = new ParkingLot(slots, gates);
        System.out.println("=== Parking Lot Demo ===\n");

        lot.status();
        System.out.println();

        // park a bike from gate 1 -> should get nearest SMALL slot (slot 1)
        LocalDateTime now = LocalDateTime.of(2026, 3, 30, 10, 0);
        Vehicle bike = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        ParkingTicket t1 = lot.park(bike, now, SlotType.SMALL, 1);
        System.out.println("Parked: " + t1);

        // park a car from gate 1 -> should get nearest MEDIUM slot (slot 3)
        Vehicle car = new Vehicle("KA-02-5678", VehicleType.CAR);
        ParkingTicket t2 = lot.park(car, now, SlotType.MEDIUM, 1);
        System.out.println("Parked: " + t2);

        // park a bus from gate 2 -> should get nearest LARGE slot (slot 12)
        Vehicle bus = new Vehicle("KA-03-9999", VehicleType.BUS);
        ParkingTicket t3 = lot.park(bus, now, SlotType.LARGE, 2);
        System.out.println("Parked: " + t3);

        // park a bike in MEDIUM slot (overflow) from gate 2
        Vehicle bike2 = new Vehicle("KA-04-0000", VehicleType.TWO_WHEELER);
        ParkingTicket t4 = lot.park(bike2, now, SlotType.MEDIUM, 2);
        System.out.println("Parked (bike in medium): " + t4);

        System.out.println();
        lot.status();
        System.out.println();

        // exit the car after 3 hours -> billed at MEDIUM rate
        LocalDateTime exitTime = now.plusHours(3);
        Bill bill1 = lot.exit(t2, exitTime);
        System.out.println("Exit: " + bill1);

        // exit the bike that was in MEDIUM slot after 1.5 hours -> billed at MEDIUM rate
        LocalDateTime exitTime2 = now.plusMinutes(90);
        Bill bill2 = lot.exit(t4, exitTime2);
        System.out.println("Exit (bike billed as medium): " + bill2);

        System.out.println();
        lot.status();
    }
}
