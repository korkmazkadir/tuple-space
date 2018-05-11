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
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Kadir Korkmaz
 */
public class AppConsumer {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        TupleSpaceConnection connection = TupleSpaceConnector.GetConnection("127.0.0.1", 2020);

        String key = "count";

        String keyConsumerReady = "consumer-ready";
        Boolean consumerReady = true;

        connection.write(keyConsumerReady, consumerReady);

        Integer countValue;
        byte[] result;
        while ((result = connection.waitToTake(key, null)) != null) {
            countValue = SerializationUtils.deserialize(result);
            System.out.print( countValue + " --> ");
            for (int i = 0; i <= countValue; i++) {
                System.out.print(i + ", ");
            }
            System.out.println("");
            connection.write(keyConsumerReady, consumerReady);
        }

    }

}
