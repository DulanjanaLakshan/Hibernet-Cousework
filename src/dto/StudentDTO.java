package dto;

public class StudentDTO {
       private String id;
       private String name;
       private String NIC;
       private String contact;
       private String email;
       private String address;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, String NIC, String contact, String email, String address) {
        this.setId(id);
        this.setName(name);
        this.setNIC(NIC);
        this.setContact(contact);
        this.setEmail(email);
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
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", NIC='" + NIC + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
