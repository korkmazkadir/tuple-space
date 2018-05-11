/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespace;

import com.kadirkorkmaz.tuplespace.service.TupleSpaceService;
import com.kadirkorkmaz.tuplespacecommon.TupleSpace;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadir Korkmaz
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException {
        
        System.setProperty("java.rmi.server.hostname","167.99.217.243");
        System.setProperty("java.security.policy","security.policy");
        
        Runtime.getRuntime().exec("rmiregistry 80");
        LocateRegistry.createRegistry(80);

        try {
            TupleSpaceService service = new TupleSpaceService();
            TupleSpace spaceStub = (TupleSpace) UnicastRemoteObject.exportObject(service, 0);

            Registry registry = LocateRegistry.getRegistry(80);
            registry.bind("tuple-space-service", spaceStub);

            System.out.println("Tuple space service is running...");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}
