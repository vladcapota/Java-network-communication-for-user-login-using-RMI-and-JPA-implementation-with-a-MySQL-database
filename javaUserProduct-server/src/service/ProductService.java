/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDao;
import dto.ProductDto;
import entities.Product;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import webservice.ProductWebService;

/**
 *
 * @author Vlad
 */
public class ProductService extends UnicastRemoteObject implements ProductWebService{
    
    private EntityManagerFactory emf;
    
    public ProductService() throws RemoteException{
        emf = Persistence.createEntityManagerFactory("javaUserProduct-serverPU");
    }

    @Override
    public void addProduct(ProductDto productDto) throws RemoteException {
        
        EntityManager em = emf.createEntityManager();
        ProductDao productDao = new ProductDao(em);
        
        em.getTransaction().begin();
        
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productDao.addProduct(product);
        
        em.getTransaction().commit();
    }

    @Override
    public List<ProductDto> getProducts() throws RemoteException {
        
        EntityManager em = emf.createEntityManager();
        ProductDao productDao = new ProductDao(em);
        
        return productDao.getProducts().stream().map(Product::fromProduct).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(ProductDto productDto) throws RemoteException {
        
        EntityManager em = emf.createEntityManager();
        ProductDao productDao = new ProductDao(em);
        
        em.getTransaction().begin();
        
        Product product = productDao.findProduct(productDto.getId());
        productDao.deleteProduct(product);
        
        em.getTransaction().commit();
    }
    
    
    
}
