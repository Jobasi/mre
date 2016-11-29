package dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import helper.Validator;
import entities.Customer;
import exception.EmailException;

public class CustomerDAO {

	private static final String PERSISTENCE_UNIT_NAME = "customer";
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    
	 public CustomerDAO() {
		 this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 this.entityManager = this.factory.createEntityManager();
		 System.out.println(getClass()+ "Triggered!!");
	 }
	 

	    private Validator validator;

	    public void setValidator(Validator validator) {
	        this.validator = validator;
	        }
	
	    public void save(Customer customer){
	    	entityManager.getTransaction().begin();
	    	entityManager.persist(customer);
	    	entityManager.getTransaction().commit();
	    	entityManager.close();
	    }
	    @SuppressWarnings("unchecked")
	    public List<Customer> fetchAllCustomers(){
	    	entityManager.getTransaction().begin();
	        Query query = entityManager.createQuery("SELECT c FROM Customer c");
	        List<Customer> customer = (List<Customer>) query.getResultList(); 
	        entityManager.close();
	        return customer;
	    }

	    public Customer findCustomerById(long id){
	    	entityManager.getTransaction().begin();
	        Customer customer = entityManager.find(Customer.class, id); 
	        entityManager.close();
	        return customer;

	    }

	    public void updateCustomer(Customer customer){
	
	    }

	    public void delete(Customer customer){
	    	entityManager.getTransaction().begin();
	    	entityManager.remove(customer);
	    	entityManager.getTransaction().commit();
	    	entityManager.close(); 
	    }

	    public Customer findCustomerByEmail(String email){
	    	entityManager.getTransaction().begin();
	    	Query query = entityManager.createQuery("SELECT c FROM Customer WHERE c.email = :email");
	    	Customer customer = (Customer)query.getSingleResult();
	    	entityManager.close();
	    	return customer;
	    }
}
