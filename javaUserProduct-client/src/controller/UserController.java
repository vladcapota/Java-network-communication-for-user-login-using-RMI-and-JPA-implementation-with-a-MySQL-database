/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UserDto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import webservice.UserLoginWebService;

/**
 *
 * @author Vlad
 */
public class UserController {
    
    static Registry registry;
    static UserLoginWebService ulws;
    
    private UserController(){
        
        try{
            
            registry = LocateRegistry.getRegistry("localhost", 4321);
            ulws = (UserLoginWebService) registry.lookup("registry");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    private static final class SingletonHolder{
        private static final UserController INSTANCE = new UserController();
    }
    
    public static UserController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    
    public boolean register (String username, String password) throws RemoteException{
        
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        return ulws.registerUser(userDto);
        
    }
    
    public boolean login (String username, String password) throws RemoteException{
        
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        return ulws.login(userDto);
        
    }
    
}
