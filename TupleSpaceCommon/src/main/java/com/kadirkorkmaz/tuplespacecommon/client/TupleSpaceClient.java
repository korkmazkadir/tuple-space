/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespacecommon.client;

import com.kadirkorkmaz.tuplespacecommon.TupleSpace;
import com.kadirkorkmaz.tuplespacecommon.TupleSpaceConnection;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Kadir Korkmaz
 */
public class TupleSpaceClient implements TupleSpaceConnection {

    private static final Logger LOGGER = Logger.getLogger(TupleSpaceClient.class.getName());

    private final String HOST;
    private final int PORT_NUMBER;

    private TupleSpace tupleSpace;

    public TupleSpaceClient(String HOST, int PORT_NUMBER) {
        this.HOST = HOST;
        this.PORT_NUMBER = PORT_NUMBER;
    }

    public void connect() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
        tupleSpace = (TupleSpace) registry.lookup("tuple-space-service");
    }

    @Override
    public byte[] read(String key) {
        try {
            return tupleSpace.read(key);
        } catch (RemoteException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }

        return null;
    }

    @Override
    public void write(String key, Object obj) {
        try {
            byte[] data = SerializationUtils.serialize((Serializable) obj);
            tupleSpace.write(key, data);
        } catch (RemoteException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public byte[] take(String key) {
        try {
            return tupleSpace.take(key);
        } catch (RemoteException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }

        return null;
    }

    @Override
    public byte[] waitToTake(String key, Duration waitDuration) {
        try {
            return tupleSpace.waitToTake(key, waitDuration);
        } catch (RemoteException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }

        return null;
    }

}
