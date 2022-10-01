package view.tm;

public class StudentTm {
     private String id;
     private String name;
     private String NIC;
     private String Contact;
     private String Email;
     private String address;

    public StudentTm() {
    }

    public StudentTm(String id, String name, String NIC, String contact, String email, String address) {
        this.setId(id);
        this.setName(name);
        this.setNIC(NIC);
        setContact(contact);
        setEmail(email);
        this.setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Email='" + Email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
