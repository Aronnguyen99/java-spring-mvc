package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // >> tao thuc the --> de co the gen ra table trong mysql
public class User {
    @Id // ==> khi tao 1 Entity thi can co 1 ID de xac dinh
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;

    public User() {

    }

    public User(long id, String email, String password, String fullName, String address, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", email=" + email
                + ", password=" + password
                + ", fullName=" + fullName
                + ", address=" + address
                + ", phone=" + phone + "]";
    }

    // void setID(long id){
    // this.id = id;
    // }

    // long getId(){
    // return this.id;
    // }

}
