public class Guest {
    private String name;
    private String email;

    private Date checkInDate;

    public Guest(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return email; }
    public void setContact(String contact) { this.email = email; }

    public String toString() {
        return "Name: " + name;
    }
}