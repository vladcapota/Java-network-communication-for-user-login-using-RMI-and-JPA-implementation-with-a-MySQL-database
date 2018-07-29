/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import dto.ProductDto;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Vlad
 */
public interface ProductWebService extends Remote{
    
    public void addProduct(ProductDto productDto) throws RemoteException;
    
    public List<ProductDto> getProducts() throws RemoteException;
    
    public void deleteProduct(ProductDto productDto) throws RemoteException;
    
}
