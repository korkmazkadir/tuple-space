/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespaceclient;

import com.kadirkorkmaz.tuplespacecommon.TupleSpaceConnection;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Kadir Korkmaz
 */
public class TestApplication2 {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        TupleSpaceConnection connection = TupleSpaceConnector.GetConnection("127.0.0.1", 2020);
        
        String key = "test-key";
        String message = "Hey ben buradayım, ya sen neredesin?";
        connection.write(key,message);
        
    }
}