/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespacetest;

import com.kadirkorkmaz.tuplespaceclient.TupleSpaceConnector;
import com.kadirkorkmaz.tuplespacecommon.TupleSpaceConnection;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Kadir Korkmaz
 */
public class AppProducer {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        System.setProperty("java.security.policy","security.policy");
        
        TupleSpaceConnection connection = TupleSpaceConnector.GetConnection("167.99.217.243", 80);

        String key = "count";

        String keyConsumerReady = "consumer-ready";

        Integer countValue = 0;
        while (connection.waitToTake(keyConsumerReady, null) != null) {
            connection.write(key, countValue++);
        }

    }

}
