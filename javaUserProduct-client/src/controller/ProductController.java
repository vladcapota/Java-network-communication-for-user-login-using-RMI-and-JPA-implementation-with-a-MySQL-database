/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ProductDto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import webservice.ProductWebService;

/**
 *
 * @author Vlad
 */
public class ProductController {
    
    static Registry registry;
    static ProductWebService pws;
    
    private ProductController(){
        
        try{
            registry = LocateRegistry.getRegistry("localhost", 4322);
            pws = (ProductWebService) registry.lookup("registry");
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    private static final class SingletonHolder{
        private static final ProductController INSTANCE = new ProductController();
    }
    
    public static ProductController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    
    public void addProduct(String name, double price) throws RemoteException{
        
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        pws.addProduct(productDto);
        
    }
    
    public List<ProductDto> getProducts() throws RemoteException{
        
        return pws.getProducts();
        
    }
    
    public void deleteProduct(ProductDto productDto) throws RemoteException{
        
        pws.deleteProduct(productDto);
        
    }
}
