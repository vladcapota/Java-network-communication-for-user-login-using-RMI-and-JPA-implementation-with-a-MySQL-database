/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import dto.UserDto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Vlad
 */
public interface UserLoginWebService extends Remote{
    
    public boolean registerUser(UserDto userDto) throws RemoteException;
    
    public boolean login(UserDto userDto) throws RemoteException;
    
    public void addUser(UserDto userDto) throws RemoteException;
    
}
