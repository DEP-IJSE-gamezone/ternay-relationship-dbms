package lk.ijse.dep12.relationship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
@ToString(exclude = "orders")
public class Customer implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;


    @OneToMany(mappedBy = "customer")
    @Setter(AccessLevel.NONE)
    private List<Order> orders=new ArrayList<>();

    public Customer(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Customer(String id, String name, String address, List<Order> orders) {
        if(orders !=null && !orders.isEmpty()){
            orders.stream().filter(od->od.getCustomer() ==null).forEach(order -> order.setCustomer(this));
        }
        if(orders !=null && !orders.isEmpty()){
            orders.forEach(od->{
                if (od.getCustomer() != this)
                    throw new IllegalStateException("%s Order is already associated with the another customer.".formatted(od.getId()));
            });
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }
}
