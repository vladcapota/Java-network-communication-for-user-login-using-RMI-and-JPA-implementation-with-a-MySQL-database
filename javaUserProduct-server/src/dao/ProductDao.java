/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vlad
 */
public class ProductDao {
    
    private EntityManager em;
    
    public ProductDao (EntityManager em){
        this.em = em;
    }
    
    public void addProduct(Product product){
        em.persist(product);
    }
    
    public List<Product> getProducts(){
        String sql = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(sql, Product.class);
        return query.getResultList();
    }
    
    public Product findProduct(int id){
        return em.find(Product.class, id);
    }
    
    public void deleteProduct(Product product){
        em.remove(product);
    }
    
}
