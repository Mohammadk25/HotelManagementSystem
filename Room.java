
public class Room {
    private int number;

    private String type;
    private boolean available;
    private boolean occupied;
    private Guest occupant;
    public boolean isAvailable() {
        return available && !occupied;
    }
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public Room(int number, String type) {
        this.number = number;
        this.type = type;
        available = true;
        occupied = false;
        occupant = null;
    }

    public int getRoomNumber() { return number; }
    public void setRoomNumber(int number) { this.number = number; }

    public String getType() { return type; }
    public void setType(String type) { this.number = number; }

    public boolean getAvailability() { return available; }
    public void setAvailability(boolean available) { this.available = available; }

    public boolean getOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied;}

    public Guest getOccupant() { return occupant; }
    public void setOccupant(Guest occupant) { this.occupant = occupant; }

    public String toString() { return "Room number: " + number + ", room type: " + type + ", Availability: " + getAvailability() + ", Occupant: " + occupant;}

    public void setPrice(double v) {
    }
}