/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import service.ProductService;
import service.UserLoginService;

/**
 *
 * @author Vlad
 */
public class Server {
    
    public static void main(String[] args) {
        try{
            Registry userRegistry = LocateRegistry.createRegistry(4321);
            userRegistry.rebind("registry", new UserLoginService());
            Registry productRegistry = LocateRegistry.createRegistry(4322);
            productRegistry.rebind("registry", new ProductService());
        }catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
    
}
