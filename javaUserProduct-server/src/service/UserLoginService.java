/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import dto.UserDto;
import entities.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import webservice.UserLoginWebService;

/**
 *
 * @author Vlad
 */
public class UserLoginService extends UnicastRemoteObject implements UserLoginWebService{
    
    private EntityManagerFactory emf;
    
    public UserLoginService() throws RemoteException{
        emf = Persistence.createEntityManagerFactory("javaUserProduct-serverPU");
    }

    @Override
    public boolean registerUser(UserDto userDto) throws RemoteException {
        
        EntityManager em = emf.createEntityManager();
        UserDao userDao = new UserDao(em);
        
        User user = userDao.findUserbyUser(userDto.getUsername());
        
        if(user == null){
            
            addUser(userDto);
            
            return true;
            
        }else{
            return false;
        }

    }

    @Override
    public boolean login(UserDto userDto) throws RemoteException {
        
        EntityManager em = emf.createEntityManager();
        UserDao userDao = new UserDao(em);

        User user = userDao.findUserbyUser(userDto.getUsername());
        
        if(user != null){
            if(user.getPassword().equals(userDto.getPassword())){
                return true;
            }
            return true;
        }
        
        return false;
    }
    
    @Override
    public void addUser(UserDto userDto) throws RemoteException{
        
        EntityManager em = emf.createEntityManager();
        UserDao userDao = new UserDao(em);
        
        em.getTransaction().begin();
        
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userDao.addUser(user);
        
        em.getTransaction().commit();
        
    }
    
    
    
}
