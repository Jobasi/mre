package restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import dataaccess.CustomerDAO;
import entities.Customer;
import helper.CustomerBuilder;

@Component
@Path(value="/customer")
public class CustomerService {
	private CustomerDAO customerDAO; 
	private static ApplicationContext context;
	
	public CustomerService() {
		//this.customerDAO = new CustomerDAO();
		System.out.println(getClass()+" Triggered");
	}
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
		System.out.println(getClass()+ "Called");
		System.out.println(this.findCustomer("3"));
	}
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Customer findCustomer(@PathParam("id") String id){
		return customerDAO.findCustomerById(Long.valueOf(id));
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getAllCustomers(){
		if(customerDAO == null){
			System.out.println(customerDAO);
			try {
				customerDAO = context.getBean(CustomerDAO.class);
			} catch (Exception e) {
				System.out.println(customerDAO);
				System.out.println(e);
			}
			
			System.out.println(customerDAO);
		}
		return customerDAO.fetchAllCustomers();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Customer createCustomer(JAXBElement<Customer> cus){
		
		Customer customer = new CustomerBuilder(cus.getValue().getFirstName(), cus.getValue().getLastName())
				.withEmail(cus.getValue().getEmail())
				.withPhone(cus.getValue().getPhoneNumber())
				.build();
		
		customerDAO.save(customer);
		
		//Long customerId = customerDAO.save(customer);
		
		return customerDAO.findCustomerById(3L);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCustomer(@FormParam("id") String val){
		customerDAO.delete(customerDAO.findCustomerById(Long.parseLong(val)));
		return  "Successful";
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_XML)	
	public Customer updateCustomer(JAXBElement<Customer> cus, @PathParam("id") String id){
		cus.getValue().setPersonId(Long.valueOf(id));
		customerDAO.updateCustomer(cus.getValue());
		return customerDAO.findCustomerById(Long.valueOf(id));
	}
	
	
}
