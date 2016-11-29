package start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Customer;

public class App {

	private static final String PERSISTENCE_UNIT_NAME = "customer";
    private static EntityManagerFactory factory;

	public static void main(String[] args) {
		
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select c from Customer c");
        @SuppressWarnings("unchecked")
		List<Customer> customerList = q.getResultList();
        for (Customer customer : customerList) {
                System.out.println(customer);
        }
        System.out.println("Size: " + customerList.size());

        // create new Customer
        em.getTransaction().begin();
        Customer customer = new Customer();
        customer.setFirstName("Some");
        customer.setLastName("Guy");
        customer.setEmail("exmple@eaxmple.com");
        customer.setPhoneNumber(171718181L);
        em.persist(customer);
        em.getTransaction().commit();

        em.close();
		
		
	}

}
