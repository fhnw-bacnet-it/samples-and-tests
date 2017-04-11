
/*******************************************************************************
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2017 University of Applied Sciences and Arts,
 * Northwestern Switzerland FHNW,
 * Institute of Mobile and Distributed Systems.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.orglicenses.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import org.junit.Test;

import ch.fhnw.bacnetit.stack.application.configuration.DiscoveryConfig;
import ch.fhnw.bacnetit.stack.encoding.BACnetEID;
import ch.fhnw.bacnetit.stack.network.directory.DirectoryBindingType;
import ch.fhnw.bacnetit.stack.network.directory.DirectoryService;
import ch.fhnw.bacnetit.stack.network.directory.bindings.DNSSD;

public class DirectoryServiceTest {

    public static final String DUMMY_DEVICE_URL = "ws://127.0.0.1:8080";
    public static final String DUMMY_DNS_URL = "86.119.39.127";
    public DiscoveryConfig discoveryConfig = new DiscoveryConfig(
            DirectoryBindingType.DNSSD.name(), DUMMY_DNS_URL, "itb.bacnet.ch.",
            "bds._sub._bacnet._tcp.", "bds._sub._bacnet._tcp.",
            "bds._sub._bacnet._tcp.", false);
    // public ConnectionConfig chConfig = new ConnectionConfig("testChannel",
    // "ws",8080, 1);
    // public DeviceConfig devConfig = new DeviceConfig("1234", true, false);

    @Test
    public void testRegister() {
        try {
            final DirectoryService service = DirectoryService.getInstance();
            service.register(new BACnetEID(1234), new URI(DUMMY_DEVICE_URL),
                    false, false);

            assertEquals(DUMMY_DEVICE_URL,
                    service.resolve(new BACnetEID(1234)).toString()); // find
                                                                      // created
                                                                      // entry
        } catch (URISyntaxException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDnsResolve() {
        try {
            final BACnetEID dummyEid = new BACnetEID(2001);
            final URI dummyUrl = new URI(DUMMY_DEVICE_URL);
            final DNSSD service = new DNSSD(discoveryConfig);
            service.register(dummyEid, dummyUrl, false);
            Thread.sleep(100);

            assertEquals(dummyUrl, service.resolve(dummyEid));
        } catch (URISyntaxException | UnknownHostException
                | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDnsDelete() {
        try {
            final BACnetEID dummyEid = new BACnetEID(2001);
            final URI dummyUrl = new URI(DUMMY_DEVICE_URL);
            final DNSSD service = new DNSSD(discoveryConfig);
            service.register(dummyEid, dummyUrl, false);
            service.delete(dummyEid); // delete entry from DNS

            assertNull(service.resolve(dummyEid));
        } catch (URISyntaxException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCacheFile() {
        try {
            File cacheFile = new File(DirectoryService.CACHE_FILE_NAME);
            if (cacheFile.exists()) { // ensure no cache file is present
                if (!cacheFile.delete()) {
                    throw new Exception("File couldn't be deleted");
                }
            }

            final BACnetEID eid1 = new BACnetEID(2001);
            final BACnetEID eid2 = new BACnetEID(2002);
            final BACnetEID eid3 = new BACnetEID(2003);
            final URI dummyUrl = new URI(DUMMY_DEVICE_URL);

            DirectoryService service = DirectoryService.getInstance();
            service.register(eid1, dummyUrl, false, false);
            service.register(eid2, dummyUrl, false, false);
            service.register(eid3, dummyUrl, false, false);

            final LinkedList<BACnetEID> eids = new LinkedList<>();
            eids.add(eid1);
            eids.add(eid2);
            eids.add(eid3);
            service.prepareForShutdown(eids); // force file write
            service = null;

            cacheFile = new File(DirectoryService.CACHE_FILE_NAME);
            assertTrue(cacheFile.exists());

            service = DirectoryService.getInstance(); // file is read, no DNS is
                                                      // added
            assertEquals(dummyUrl, service.resolve(eid1));
            assertEquals(dummyUrl, service.resolve(eid2));
            assertEquals(dummyUrl, service.resolve(eid3));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindBds() {
        try {
            DirectoryService.init();
            final DirectoryService directory = DirectoryService.getInstance();
            directory.setDns(discoveryConfig);
            directory.getBds();
        } catch (final UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
