/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespacecommon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.Duration;

/**
 *
 * @author Kadir Korkmaz
 */
public interface TupleSpace extends Remote{
    
    /*Return the object related to key*/
    public byte[] read(String key) throws RemoteException;
    
    /*Saves object to space with key */
    public void write(String key, byte[] data) throws RemoteException;

    /*Removes object from space with key*/
    public byte[] take(String key) throws RemoteException;
    
    /*Removes object from space with key, if object is not available waits for it*/
    public byte[] waitToTake(String key, Duration waitDuration) throws RemoteException;
    
}
