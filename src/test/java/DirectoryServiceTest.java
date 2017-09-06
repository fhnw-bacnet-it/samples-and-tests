
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

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.junit.Test;

import ch.fhnw.bacnetit.ase.application.configuration.api.DiscoveryConfig;
import ch.fhnw.bacnetit.ase.encoding.api.BACnetEID;
import ch.fhnw.bacnetit.ase.network.directory.api.DirectoryService;
import ch.fhnw.bacnetit.directorybinding.dnssd.api.DNSSD;

public class DirectoryServiceTest {

    public static final String DUMMY_DEVICE_URL = "ws://127.0.0.1:8080";
    public static final String DUMMY_DNS_URL = "0.0.0.0";
    public DiscoveryConfig discoveryConfig = new DiscoveryConfig("DNSSD",
            DUMMY_DNS_URL, "itb.bacnet.ch.", "bds._sub._bacnet._tcp.",
            "bds._sub._bacnet._tcp.", "bds._sub._bacnet._tcp.", false);
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
    public void testFindBds() {
        try {
            DirectoryService.init();
            final DirectoryService directory = DirectoryService.getInstance();
            directory.setDNSBinding(new DNSSD(discoveryConfig));
            directory.getBds();
        } catch (final UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
