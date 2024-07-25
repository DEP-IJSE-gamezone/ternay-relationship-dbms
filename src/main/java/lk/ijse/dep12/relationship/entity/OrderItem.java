package lk.ijse.dep12.relationship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
@IdClass(OrderItemPK.class)
public class OrderItem implements Serializable {
    @Id
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @ManyToOne
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_code",referencedColumnName = "code")
    private Item item;
    private BigDecimal price;
    private int discount;
}
