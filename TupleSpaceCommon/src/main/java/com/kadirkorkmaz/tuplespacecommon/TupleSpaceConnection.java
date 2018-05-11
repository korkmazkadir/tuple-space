/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadirkorkmaz.tuplespacecommon;

import java.time.Duration;

/**
 *
 * @author Kadir Korkmaz
 */
public interface TupleSpaceConnection {

    /*Return the object related to key*/
    public byte[] read(String key);

    /*Saves object to space with key */
    public void write(String key, Object obj);

    /*Removes object from space with key*/
    public byte[] take(String key);

    /*Removes object from space with key, if object is not available waits for it*/
    public byte[] waitToTake(String key, Duration waitDuration);
}
