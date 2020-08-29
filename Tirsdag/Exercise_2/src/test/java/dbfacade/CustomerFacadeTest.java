/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import Entities.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathias
 */
public class CustomerFacadeTest {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
    
    public CustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        
    }

    @AfterEach
    public void tearDown() throws Exception {
        
    }
    

    /**
     * Test of findById method, of class CustomerFacade.
     */
    @Test
    public void testFindById() {
        Customer customer = cf.findById(1);
        String exp = "Kobe";
        assertEquals(exp,customer.getFirstName());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        Customer customer = new Customer("test", "test");
        cf.addCustomer(customer);
        String lastName = "test";
        List<Customer> resultList = cf.findByLastName(lastName);
        Customer result = null;
        for (Customer c : resultList) {
            if(c.equals(customer)){
                result = customer;
            }
        }
        cf.removeCustomer("test");
        assertEquals(lastName, result.getLastName());
        
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        Long result = cf.getNumberOfCustomers();
        Long exp = 5L;
        assertEquals(exp, result);
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @Test
    public void testAllCustomers() {
        List<Customer> testList = cf.allCustomers();
       int result = testList.size();
       int exp = 5;
       assertEquals(exp, result);
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        Customer customer = new Customer("testadd", "testadd");
        Customer result = cf.addCustomer(customer);
        cf.removeCustomer("testadd");
        assertTrue(result != null);
//        String lastName = "testadd";
//        List<Customer> resultList = cf.allCustomers();
//        Customer result = null;
//        for (Customer c : resultList) {
//            if(c.equals(customer)){
//                result = customer;
//            }
//        }
//        cf.removeCustomer("testadd");
//        assertEquals(customer, result);
        
    }
    
}
