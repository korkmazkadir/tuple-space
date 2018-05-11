/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespace.service;

import com.kadirkorkmaz.tuplespacecommon.TupleSpace;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadir Korkmaz
 */
public class TupleSpaceService implements TupleSpace {

    private static final Logger LOGGER = Logger.getLogger(TupleSpaceService.class.getName());

    private final Map<String, byte[]> repository;

    private final Map<String, Integer> waiterMap;

    private final Object lock;

    public TupleSpaceService() {
        repository = new LinkedHashMap<>();
        lock = new Object();
        waiterMap = new LinkedHashMap<>();
    }

    @Override
    public byte[] read(String key) throws RemoteException {
        synchronized (lock) {
            LOGGER.log(Level.INFO, "Reading --> {0}", key);
            return repository.get(key);
        }
    }

    @Override
    public void write(String key, byte[] data) throws RemoteException {
        synchronized (lock) {
            LOGGER.log(Level.INFO, "Writing --> {0}", key);
            repository.put(key, data);
            Integer counter = waiterMap.get(key);
            if (counter != null) {
                lock.notifyAll();
            }
        }
    }

    @Override
    public byte[] take(String key) throws RemoteException {
        synchronized (lock) {
            LOGGER.log(Level.INFO, "Taking --> {0}", key);
            return repository.remove(key);
        }
    }

    @Override
    public byte[] waitToTake(String key, Duration waitDuration) throws RemoteException {
        synchronized (lock) {
            if (repository.containsKey(key) == false) {
                addToWaitingList(key);
                while (repository.containsKey(key) == false) {
                    try {
                        Integer counter = waiterMap.get(key);
                        LOGGER.log(Level.INFO, "Waiting --> {0}", key);
                        lock.wait();
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                    }
                }
                removeFromWaitingList(key);
            }

            LOGGER.log(Level.INFO, "(waitToTake) Taking --> {0}", key);
            return repository.remove(key);
        }
    }

    private void addToWaitingList(String key) {
        Integer counter = waiterMap.get(key);
        if (counter == null) {
            counter = 0;
        }
        counter++;
        waiterMap.put(key, counter);
    }

    private void removeFromWaitingList(String key) {
        Integer counter = waiterMap.get(key);
        if (counter != null) {
            counter = counter - 1;
            if (counter == 0) {
                waiterMap.remove(key);
            }
        }
    }

}
