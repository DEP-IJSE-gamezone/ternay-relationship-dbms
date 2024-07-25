package lk.ijse.dep12.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.entity.*;
import lk.ijse.dep12.relationship.jpa.JpaUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ManyToOneDemo4 {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                User nimantha = em.find(User.class, "abcd");

                Customer c001 = em.find(Customer.class, "C001");
                Customer c002 = em.find(Customer.class, "C002");

                Order od001 = em.find(Order.class, "OD001");
                Order od002 = em.find(Order.class, "OD002");

                Item i001 = em.find(Item.class, "I001");
                OrderItem orderItem = new OrderItem(od001, i001, new BigDecimal("798209"), 10);

                em.persist(orderItem);
//                System.out.println(nimantha.getOrders());
//               System.out.println(c001.getOrders());
//                System.out.println(od001.getCustomer());
//                System.out.println(od001.getUser());

                tx.commit();
            } catch (Throwable e) {
                e.printStackTrace();
                tx.rollback();
            }
        }

    }
}
