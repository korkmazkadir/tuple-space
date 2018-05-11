# tuple-space
Simple tuple space implementation with java RMI

Tuple Space Client Library provides 4 function for it is users : 

1. byte[] read(String key)
2. void write(String key, Object obj)
3. byte[] take(String key)
4. byte[] waitToTake(String key, Duration waitDuration)


read used to read value of an object with key from tuple space. It will return null if there is no object.

write is used to write an object to tuple space with key. It will override previous object if there is an object with key.

take is removes and returns object from tuple space with key. If there is no object, it will return null.

waitToTake will return the object if it is available. Otherwise it will block the calling thread and waits for the object.
It can return null also. This case have to be handled by client code.

