package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import helper.CustomerBuilder;

/*@NamedQueries({
    @NamedQuery(
            name = "findCustomerByEmail",
            query = "from Customer c where c.email = :email"
    )
})*/
@Entity
@XmlRootElement
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private Long phoneNumber;
    
    public Customer(){
        super();
    }

    public Customer(CustomerBuilder customerBuilder) {
        super();
        this.firstName = customerBuilder.getFirstName();
        this.lastName = customerBuilder.getLastName();
        this.email = customerBuilder.getEmail();
        this.phoneNumber = customerBuilder.getPhoneNumber();
    }



    private Long getPersonId() {
        return personId;
    }

    public Long notAGooIdea() {
		return getPersonId();
	}

    public void setPersonId(Long personId) {
        this.personId = personId;
    }



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public Long getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", personId=" + "Nice Try!!!" +
                '}';
    }

}
