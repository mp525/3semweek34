/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import Entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mathias
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    
    }
    
    public static void main(String[] args) {
         emf = Persistence.createEntityManagerFactory("pu");
         CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
         Customer c1 = new Customer("bigboy", "mcgee");
         Customer c2 = new Customer("sans", "sans");
         
         System.out.println(facade.findById(1).toString());
         System.out.println(facade.findByLastName("mcgee"));
         System.out.println(facade.getNumberOfCustomers());
         System.out.println(facade.allCustomers());
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer findById(long id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer cus = em.find(Customer.class,id);
            return cus;
        }finally {
            em.close();
        }
    }
    public List<Customer> findByLastName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer from Customer customer where customer.lastName = :name",Customer.class);
            return query.setParameter("name", name).getResultList();
        }finally {
            em.close();
        } 
    }
    public Long getNumberOfCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            
            TypedQuery<Long> query = 
                       em.createQuery("Select count(c) from Customer c",Long.class);
            return query.getSingleResult();
        }finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
        
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer from Customer customer",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
        
    }
    
    public Customer addCustomer(Customer customer){
        
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public void removeCustomer(String firstName){
        
         EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
             int query = 
                       em.createQuery("Delete FROM Customer where firstName = '" + firstName +"'").executeUpdate();
            em.getTransaction().commit();
            
        }finally {
            em.close();
        }
        
    }
}

    
    
    

