public class Reservation {
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Guest guest, Room room, Date checkIn, Date checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Date getCheckInDate() { return checkInDate;}
    public void setCheckInDate(Date date) { checkInDate = date; }

    public Date getCheckOutDate() { return checkOutDate;}
    public void setCheckOutDate(Date date) { checkOutDate = date; }
}