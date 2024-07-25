package lk.ijse.dep12.relationship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`user`")
@ToString(exclude = "orders")
public class User implements Serializable {
    @Id
    @Column(name = "user_name")
    private String userName;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private List<Order> orders = new ArrayList<>();

    public User(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public User(String userName, String name, String password, List<Order> orders) {
        if (orders != null && !orders.isEmpty()){
            orders.stream().filter(od->od.getUser()==null).forEach(order -> order.setUser(this));
        }
        if(orders != null && !orders.isEmpty()){
            orders.forEach(order -> {
                if (order.getUser() != this)
                    throw new IllegalStateException("The order is already assigned to  %s user.".formatted(order.getUser()) );
            });
        }
            this.userName = userName;
        this.name = name;
        this.password = password;
        this.orders = orders;
    }
}
