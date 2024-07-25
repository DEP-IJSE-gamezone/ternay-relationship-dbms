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

public class ManyToOneDemo3 {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();

                User nimantha = new User("abcd", "Nimantha", "1234");
                User udara = new User("udara", "Udara arjun", "1234");

                Item i001 = new Item("I001", "Rice Cooker", 10, new BigDecimal("40356.98"));
                Item i002 = new Item("I002", "Gas Cooker", 28, new BigDecimal("20000.98"));
                Item i003 = new Item("I003", "Oven", 5, new BigDecimal("19000.98"));

                Customer c001 = em.find(Customer.class, "C001");
                Customer c002 = em.find(Customer.class, "C002");

                Order od001 = new Order("OD001", Date.valueOf(LocalDate.now()), c001, nimantha);
                Order od002 = new Order("OD002", Date.valueOf(LocalDate.now()), c001, udara);
                Order od003 = new Order("OD003", Date.valueOf(LocalDate.now()), c002, udara);

                List.of(nimantha,udara,i001,i002,i003,od001,od002,od003).forEach(em::persist);
                tx.commit();
            }catch (Throwable e){
                e.printStackTrace();
                tx.rollback();
            }
        }

    }
}
