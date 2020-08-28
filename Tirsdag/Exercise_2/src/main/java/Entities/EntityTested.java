/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mathias
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Customer c1 = new Customer("Kim", "Larsen");
        Customer c2 = new Customer("Kurt", "Hansen");
        Customer c3 = new Customer("Kobe", "Jensen");
        
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
    }
    
}
