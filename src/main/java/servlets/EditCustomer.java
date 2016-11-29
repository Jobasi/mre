package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.CustomerDAO;
import entities.Customer;
import helper.Validator;

/**
 * Servlet implementation class EditCustomer
 */
public class EditCustomer extends HttpServlet {
	private CustomerDAO customerDAO; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomer() {
        super();
        //this.customerDAO = new CustomerDAO();
    }
    
    public void setCustomerDAO(CustomerDAO customerDAO) {
 		this.customerDAO = customerDAO;
 		System.out.println(getClass()+ " Called");
 	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
        Long customerId = Long.parseLong(pathInfo.substring(1));
        Customer customer = customerDAO.findCustomerById(customerId);

        request.setAttribute("first_name", customer.getFirstName());
        request.setAttribute("last_name", customer.getLastName());
        request.setAttribute("email", customer.getEmail());
        request.setAttribute("phone", customer.getPhoneNumber());
        request.setAttribute("cid", customer.notAGooIdea());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/edit.jsp");
        requestDispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Long cid = Long.parseLong(String.valueOf(request.getParameter("cid")));
       
        if(Validator.validateEmail(email)){
            Customer customer = customerDAO.findCustomerById(cid);

            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPhoneNumber(Long.parseLong(phone));

            customerDAO.updateCustomer(customer);

            response.sendRedirect("/mre/customers.jsp");
        } else {
            request.setAttribute("first_name", request.getParameter("first_name"));
            request.setAttribute("last_name", request.getParameter("last_name"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("phone", request.getParameter("phone"));
            request.setAttribute("error", "Please enter a valid email!");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/mre/create.jsp");
            requestDispatcher.forward(request, response);
        }
	}

}
