/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespaceclient;

import com.kadirkorkmaz.tuplespacecommon.TupleSpaceConnection;
import com.kadirkorkmaz.tuplespacecommon.client.TupleSpaceClient;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Kadir Korkmaz
 */
public class TupleSpaceConnector {

    private TupleSpaceConnector() {
    }

    public static TupleSpaceConnection GetConnection(String host, int portNumber) throws RemoteException, NotBoundException {
        TupleSpaceClient client = new TupleSpaceClient(host, portNumber);
        client.connect();
        return client;
    }

}
