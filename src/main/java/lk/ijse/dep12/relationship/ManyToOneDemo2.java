package lk.ijse.dep12.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.entity.Customer;
import lk.ijse.dep12.relationship.entity.CustomerContact;
import lk.ijse.dep12.relationship.jpa.JpaUtil;

import java.util.List;

public class ManyToOneDemo2 {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();
                Customer dilini = em.find(Customer.class, "C001");
                CustomerContact dasunContact = em.find(CustomerContact.class, "089-243434");

                System.out.println(dilini.getId());
                System.out.println(dasunContact.getCustomer().getAddress());
                dasunContact.getCustomer().setAddress("Jaffna");



                tx.commit();
            }catch (Throwable e){
                e.printStackTrace();
                tx.rollback();
            }
        }

    }
}
