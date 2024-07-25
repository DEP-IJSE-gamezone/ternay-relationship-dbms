package lk.ijse.dep12.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.entity.Customer;
import lk.ijse.dep12.relationship.entity.CustomerContact;
import lk.ijse.dep12.relationship.jpa.JpaUtil;

import java.util.List;

public class ManyToOneDemo1 {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();
                Customer dilini = new Customer("C001", "Dilini", "Mathara");
                Customer dasun = new Customer("C002", "Dasun", "Colombo");
                Customer tarindu = new Customer("C003", "Tharindu", "Galle");

                CustomerContact contact1 = new CustomerContact("011-243434", dilini);
                CustomerContact contact2 = new CustomerContact("071-243434", dilini);
                CustomerContact contact11 = new CustomerContact("089-243434", dasun);

                List.of(dilini,dasun,tarindu,contact1,contact2,contact11).forEach(em::persist);

                tx.commit();
            }catch (Throwable e){
                e.printStackTrace();
                tx.rollback();
            }
        }

    }
}
