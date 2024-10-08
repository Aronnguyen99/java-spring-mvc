package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // >> tao thuc the --> de co the gen ra table trong mysql
@Table(name = "users")
public class User {
    @Id // ==> khi tao 1 Entity thi can co 1 ID de xac dinh
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;

    // roleID
    // User -- many ==> one --- Role ( Nhieu User co 1 Role)
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    // User -- one ==> many -- Order (1 User cos nhieu Order)
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", email=" + email
                + ", password=" + password
                + ", fullName=" + fullName
                + ", address=" + address
                + ", phone=" + phone
                + ", avatar=" + avatar + "]";
    }

}
