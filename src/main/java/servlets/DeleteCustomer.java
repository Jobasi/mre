package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.CustomerDAO;
import entities.Customer;

/**
 * Servlet implementation class DeleteCustomer
 */
public class DeleteCustomer extends HttpServlet {
	private CustomerDAO customerDAO; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomer() {
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
		Customer customer = customerDAO.findCustomerById(Long.parseLong(request.getParameter("id")));
        customerDAO.delete(customer);
        response.sendRedirect("/mre/customers.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print("<h1>Nice Try!!</h1>");
        printWriter.flush();
	}

}
