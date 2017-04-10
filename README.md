# BACnet/IT java implementation
## Download
1. Create an new empty directory "BACnetIT" and make it the current directory
2. Get the sources  
Stack project: ```git clone https://github.com/fhnw-BACnet-IT/Stack.git```  
Binding project: ```git clone https://github.com/fhnw-BACnet-IT/Binding.git```  
Misc project: ```git clone https://github.com/fhnw-BACnet-IT/Misc.git```


## Build
1. Ensure JAVA 8 is installed
2. Make BACnetIT/Misc the current directory
3. Build all project using gradle wrapper: ```./gradlew build```


## Example 1

### Description / Story:  
Run two stacks on localhost.  
Each stack simulates 2 BACnet/IT devices.  
Let device 1 of stack 1 (device1inStack1) send a WriteProperty Request to device 2 on stack 2 (device2inStack2).

### Preparation
Ensure the builded jars are in java class path.

#### Setup stack 1 on localhost at port 8080

Create an instance of the ConnectionFactory class

```java
ConnectionFactory connectionFactory1 = new ConnectionFactory();
```  
Add a Transport Binding for outgoing and incoming communication  

```java
int port1 = 8080;
connectionFactory1.addConnectionClient("ws", new WSConnectionClientFactory());
connectionFactory1.addConnectionServer("ws", new WSConnectionServerFactory(port1));
```
Create an instance of the Channel class

```java
Channel channel1 = new Channel();
```
Implement the ChannelListener interface for each simulated BACnet device and the stacks network port object. Passing a keystore configuration to the network port object to identify the stack.

```java
BACnetEID device1inStack1 = new BACnetEID(1001);
BACnetEID device2inStack1 = new BACnetEID(1002);
KeystoreConfig keystoreConfig1 = new KeystoreConfig([PATH-TO-KEYSTORE],
        [PWD], "operationaldevcert");
NetworkPortObj npo1 = new NetworkPortObj("ws", 8080, keystoreConfig1);


channel1.registerChannelListener(new ChannelListener(device1inStack1) {
            @Override
            public void onIndication(
                    final T_UnitDataIndication tUnitDataIndication,
                    final ChannelHandlerContext ctx) {
                System.out.println(this.eid.getIdentifierAsString()
                        + " got an indication" + tUnitDataIndication.getData());
            }

            @Override
            public void onError(final String cause) {
                System.err.println(cause);
            }

            @Override
            public URI getURIfromNPO() {
                return npo1.getUri();
            }
});

channel1.registerChannelListener(new ChannelListener(device2inStack1) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo1.getUri();
                    }
});
```

Implement the BACnetEntityListener interface to handle Control Messages on application level

```java
BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {

                    @Override
                    public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                        DirectoryService.getInstance().register(eid, remoteUri, false, true);
                    }
                    @Override
                    public void onRemoteRemove(final BACnetEID eid) {
                        // TODO Auto-generated method stub
                    }
                    @Override
                    public void onLocalRequested(final BACnetEID eid) {
                        // TODO Auto-generated method stub
                    }

};
channel1.setEntityListener(bacNetEntityHandler);
```

Start the channel passing the connection factory instance containing the transport bindings

```java
channel1.initializeAndStart(connectionFactory1);
```


#### Setup stack 2 on localhost at port 9090

Create an instance of the ConnectionFactory class

```java
ConnectionFactory connectionFactory2 = new ConnectionFactory();
```  
Add a Transport Binding for outgoing and incoming communincation  

```java
int port2 = 9090;
connectionFactory2.addConnectionClient("ws", new WSConnectionClientFactory());
connectionFactory2.addConnectionServer("ws", new WSConnectionServerFactory(port2));
```
Create an instance of the Channel class

```java
Channel channel2 = new Channel();
```
Implement the ChannelListener interface for each simulated BACnet device and the stacks network port object. Passing a keystore configuration to the network port object to identify the stack.

```java
BACnetEID device1inStack2 = new BACnetEID(2001);
BACnetEID device2inStack2 = new BACnetEID(2002);
KeystoreConfig keystoreConfig2 = new KeystoreConfig([PATH-TO-KEYSTORE],
        [PWD], "operationaldevcert");
NetworkPortObj npo2 = new NetworkPortObj("ws", 9090, keystoreConfig2);

channel2.registerChannelListener(new ChannelListener(device1inStack2) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo2.getUri();
                    }
});

channel2.registerChannelListener(new ChannelListener(device2inStack2) {
                    @Override
                    public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                        System.out.println(this.eid.getIdentifierAsString()
                                + " got an indication" + tUnitDataIndication.getData());
                    }

                    @Override
                    public void onError(final String cause) {
                        System.err.println(cause);
                    }

                    @Override
                    public URI getURIfromNPO() {
                        return npo2.getUri();
                    }
});
```

Implement the BACnetEntityListener interface to handle Control Messages on application level

```java
BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener() {

                    @Override
                    public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                        DirectoryService.getInstance().register(eid, remoteUri, false, true);
                    }
                    @Override
                    public void onRemoteRemove(final BACnetEID eid) {
                        // TODO Auto-generated method stub
                    }
                    @Override
                    public void onLocalRequested(final BACnetEID eid) {
                        // TODO Auto-generated method stub
                    }

};
channel2.setEntityListener(bacNetEntityHandler2);
```

Start the channel passing the connection factory instance containing transport bindings

```java
channel2.initializeAndStart(connectionFactory2);
```

#### Start the directory service
```java
final DiscoveryConfig ds = new DiscoveryConfig(
                    DirectoryBindingType.DNSSD.name(), [DNSIP],
                    "itb.bacnet.ch.", "bds._sub._bacnet._tcp.",
                    "dev._sub._bacnet._tcp.", "obj._sub._bacnet._tcp.", false);

try {
                DirectoryService.init();
                DirectoryService.getInstance().setDns(ds);

} catch (final Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
}
```


#### Send a ReadPropertyRequest from device1inStack1 to device2inStack2

Create an instance of ReadProperty class

```java
ReadPropertyRequest readRequest = new ReadPropertyRequest(
            new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
            BACnetPropertyIdentifier.presentValue
);
```
Got the byte sequence from the confirmed BACnet Service (readRequest)

```java
ByteQueue byteQueue = new ByteQueue();
readRequest.write(byteQueue);
```
Create an instance of TPDU class

```java
TPDU tpdu = new TPDU(device1inStack1, device2inStack2, byteQueue.popAll());
```

Create an instance of T_UnitDataRequest class and pass the tpdu

```java
T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(new URI("ws://localhost:9090"), tpdu, 1, true, null);
```

Pass the unitDataRequest down to the channel

```Java
channel1.doRequest(unitDataRequest);
```

device2inStack2 should get an indication from device1inStack1.


#### Complete code example

```java
ConnectionFactory connectionFactory1 = new ConnectionFactory();

int port1 = 8080;
connectionFactory1.addConnectionClient("ws", new WSConnectionClientFactory());
connectionFactory1.addConnectionServer("ws", new WSConnectionServerFactory(port1));

Channel channel1 = new Channel();

BACnetEID device1inStack1 = new BACnetEID(1001);
BACnetEID device2inStack1 = new BACnetEID(1002);
    
KeystoreConfig keystoreConfig1 = new KeystoreConfig([PATH-TO-KEYSTORE],
        [PWD], "operationaldevcert");
NetworkPortObj npo1 = new NetworkPortObj("ws", 8080, keystoreConfig1);

    
channel1.registerChannelListener(new ChannelListener(device1inStack1) {
    @Override
    public void onIndication(
            final T_UnitDataIndication tUnitDataIndication,
            final ChannelHandlerContext ctx) {
        System.out.println(this.eid.getIdentifierAsString()
                + " got an indication" + tUnitDataIndication.getData());
    }

    @Override
    public void onError(final String cause) {
        System.err.println(cause);
    }

    @Override
    public URI getURIfromNPO() {
        return npo1.getUri();
    }
});

 channel1.registerChannelListener(new ChannelListener(device2inStack1) {
            @Override
            public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                System.out.println(this.eid.getIdentifierAsString()
                        + " got an indication" + tUnitDataIndication.getData());
            }

            @Override
            public void onError(final String cause) {
                System.err.println(cause);
            }

            @Override
            public URI getURIfromNPO() {
                return npo1.getUri();
            }
});


BACnetEntityListener bacNetEntityHandler = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                DirectoryService.getInstance().register(eid, remoteUri, false, true);
            }
            @Override
            public void onRemoteRemove(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onLocalRequested(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }

        };
channel1.setEntityListener(bacNetEntityHandler);

channel1.initializeAndStart(connectionFactory1);

ConnectionFactory connectionFactory2 = new ConnectionFactory();

int port2 = 9090;
connectionFactory2.addConnectionClient("ws", new WSConnectionClientFactory());
connectionFactory2.addConnectionServer("ws", new WSConnectionServerFactory(port2));

Channel channel2 = new Channel();

BACnetEID device1inStack2 = new BACnetEID(2001);
BACnetEID device2inStack2 = new BACnetEID(2002);
KeystoreConfig keystoreConfig2 = new KeystoreConfig([PATH-TO-KEYSTORE],
        [PWD], "operationaldevcert");
NetworkPortObj npo2 = new NetworkPortObj("ws", 9090, keystoreConfig2);

channel2.registerChannelListener(new ChannelListener(device1inStack2) {
            @Override
            public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                System.out.println(this.eid.getIdentifierAsString()
                        + " got an indication" + tUnitDataIndication.getData());
            }

            @Override
            public void onError(final String cause) {
                System.err.println(cause);
            }

            @Override
            public URI getURIfromNPO() {
                return npo2.getUri();
            }
});

 channel2.registerChannelListener(new ChannelListener(device2inStack2) {
            @Override
            public void onIndication(T_UnitDataIndication tUnitDataIndication,ChannelHandlerContext ctx) {
                System.out.println(this.eid.getIdentifierAsString()
                        + " got an indication" + tUnitDataIndication.getData());
            }

            @Override
            public void onError(final String cause) {
                System.err.println(cause);
            }

            @Override
            public URI getURIfromNPO() {
                return npo2.getUri();
            }
});

BACnetEntityListener bacNetEntityHandler2 = new BACnetEntityListener() {

            @Override
            public void onRemoteAdded(final BACnetEID eid, final URI remoteUri) {
                DirectoryService.getInstance().register(eid, remoteUri, false, true);
            }
            @Override
            public void onRemoteRemove(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onLocalRequested(final BACnetEID eid) {
                // TODO Auto-generated method stub
            }

        };
channel2.setEntityListener(bacNetEntityHandler2);

channel2.initializeAndStart(connectionFactory2);


final DiscoveryConfig ds = new DiscoveryConfig(
            DirectoryBindingType.DNSSD.name(), "86.119.39.127",
            "itb.bacnet.ch.", "bds._sub._bacnet._tcp.",
            "dev._sub._bacnet._tcp.", "obj._sub._bacnet._tcp.", false);

    try {
        DirectoryService.init();
        DirectoryService.getInstance().setDns(ds);

    } catch (final Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    

ReadPropertyRequest readRequest = new ReadPropertyRequest(
    new BACnetObjectIdentifier(BACnetObjectType.analogValue, 1),
                                BACnetPropertyIdentifier.presentValue
                                );
                                        
        ByteQueue byteQueue = new ByteQueue();
  readRequest.write(byteQueue);
TPDU tpdu = new TPDU(device1inStack1, device2inStack2, byteQueue.popAll());

T_UnitDataRequest unitDataRequest = new T_UnitDataRequest(new URI("ws://localhost:9090"), tpdu, 1, true, null);

channel1.doRequest(unitDataRequest);
```

## Example 2
### Description / Story: 
Add two transport bindings; websocket and websocket secure.

```java
// Define key- and truststore
KeystoreConfig keystoreConfig = new KeystoreConfig([PATH-TO-KEYSTORE],
        [PWD], "operationaldevcert");
TruststoreConfig truststoreConfig = new TruststoreConfig([PATH-TO-TRUSTSTORE],
        [PWD], [TRUSTLIST]...);

// Build the connection factory
ConnectionFactory connectionFactory = new ConnectionFactory();

// Outgoing websocket secure transport binding (wss://)
connectionFactory.addConnectionClient("wss",
        new WSSConnectionClientFactory(keystoreConfig, truststoreConfig));
// Incoming websocket secure transport binding (wss://)
connectionFactory.addConnectionServer("wss",
        new WSSConnectionServerFactory([PORT], keystoreConfig, truststoreConfig));

// Outgoing websocket transport binding (ws://)    
connectionFactory.addConnectionClient("ws", new WSConnectionClientFactory());
// Incoming websocket transport binding (ws://)
connectionFactory.addConnectionServer("ws", new WSConnectionServerFactory([PORT]));
```

## Example 3
### Description / Story: 
Send a message between two simulated BACnet/IT devices using the Directory Service.

// TODO
- DNS available  
- Choose a Directory Binding  
- Register BDS  
- Register Device  
- Resolve Device  
- Send message
        
