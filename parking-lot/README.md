## Multilevel Parking Lot Design

### Class Diagram

```
+-------------+       +------------+       +-----------+
| VehicleType |       |  SlotType  |       | EntryGate |
| TWO_WHEELER |       | SMALL      |       | gateId    |
| CAR         |       | MEDIUM     |       | nearestSlotStart |
| BUS         |       | LARGE      |       +------------+
+-------------+       +------------+
      |                     |
+----------+         +--------------+
| Vehicle  |         | ParkingSlot  |
| plate    |         | slotNumber   |
| type     |         | type         |
+----------+         | floor        |
      |               | occupied     |
      |               +--------------+
      |                     |
      +-------+  +----------+
              |  |
        +---------------+        +--------+
        | ParkingTicket  |------->|  Bill  |
        | ticketId       |        | hours  |
        | vehicle        |        | amount |
        | slot           |        +--------+
        | entryTime      |
        +---------------+
              |
        +------------+
        | ParkingLot |
        | park()     |
        | exit()     |
        | status()   |
        +------------+
```

### What each class does

| Class | Responsibility |
|---|---|
| VehicleType | Enum - knows which slot types each vehicle is compatible with |
| Vehicle | Holds license plate + type |
| SlotType | Enum - SMALL/MEDIUM/LARGE with hourly rate |
| ParkingSlot | One slot with number, type, floor, distance from gate, occupied flag |
| EntryGate | A gate with an ID and which slot number it's closest to |
| ParkingTicket | Generated when vehicle parks - holds vehicle, slot, entry time |
| Bill | Generated on exit - holds hours parked and amount (based on slot type rate) |
| ParkingLot | The main service with park(), exit(), status() |

### Key design decisions

- **Nearest slot**: park() picks the available compatible slot with minimum distance from the entry gate
- **Overflow**: A bike asked for MEDIUM gets a MEDIUM or LARGE. Bike from gate 2 got slot 11 (LARGE) because that was nearest to gate 2
- **Billing by slot type**: The bike in the LARGE slot was billed at Rs.50/hr (LARGE rate), not Rs.10/hr (SMALL rate) - exactly per the requirements
- **Ceiling hours**: 90 minutes = 2 hours for billing (rounded up)

### Build & Run

```
cd parking-lot/src
javac *.java
java App
```
