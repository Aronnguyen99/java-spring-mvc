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

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalPrice;

    // userID
    // Order -- many ==> one --- User ( Nhieu Order thuoc 1 User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Order -- one ==> many -- OrderDetail ( 1 don hang co nhieu chi tiet don hang)
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", totalPrice=" + totalPrice + "]";
    }

}
