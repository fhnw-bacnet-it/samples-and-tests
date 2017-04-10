/*******************************************************************************
 * Copyright (C) 2016 The Java BACnetITB Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.fhnw.bacnetit.lib.service.unconfirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.BACnetObjectIdentifier;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.Segmentation;
import ch.fhnw.bacnetit.lib.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

public class IAmRequest extends UnconfirmedRequestService {
    private static final long serialVersionUID = -5896735458454994754L;

    public static final byte TYPE_ID = 0;

    private final BACnetObjectIdentifier iAmDeviceIdentifier;

    private final UnsignedInteger maxAPDULengthAccepted;

    private final Segmentation segmentationSupported;

    private final UnsignedInteger vendorId;

    public IAmRequest(final BACnetObjectIdentifier iamDeviceIdentifier,
            final UnsignedInteger maxAPDULengthAccepted,
            final Segmentation segmentationSupported,
            final UnsignedInteger vendorId) {
        this.iAmDeviceIdentifier = iamDeviceIdentifier;
        this.maxAPDULengthAccepted = maxAPDULengthAccepted;
        this.segmentationSupported = segmentationSupported;
        this.vendorId = vendorId;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    // @Override
    // public void handle(final LocalDevice localDevice, final Address from,
    // final OctetString linkService) {
    // if (!ObjectType.device.equals(iAmDeviceIdentifier.getObjectType())) {
    // LOG.warn("Received IAm from an object that is not a device.");
    // return;
    // }
    //
    // // Make sure we're not hearing from ourselves.
    // int myDoi = localDevice.getConfiguration().getInstanceId();
    // int remoteDoi = iAmDeviceIdentifier.getInstanceNumber();
    // if (remoteDoi == myDoi) {
    // boolean isLocalLoopback = false;
    // // Get my bacnet address and compare the addresses
    // for (final Address addr : localDevice.getAllLocalAddresses()) {
    // if (addr.getMacAddress().equals(from.getMacAddress()))
    // // This is a local address, so ignore.
    // isLocalLoopback = true;
    // return;
    // }
    // if(!isLocalLoopback){
    // LOG.warn("Another instance with my device instance ID (" +
    // iAmDeviceIdentifier.getInstanceNumber() +") found! Local: " +
    // localDevice.getAllLocalAddresses()[0].getMacAddress() + " Remote: " +
    // from.getMacAddress()
    // + ". " + Thread.currentThread());
    // }
    // }
    //
    // // Register the device in the list of known devices.
    // final RemoteDevice rd = localDevice.getRemoteDeviceCreate(remoteDoi,
    // from, linkService);
    // rd.setMaxAPDULengthAccepted(maxAPDULengthAccepted.intValue());
    // rd.setSegmentationSupported(segmentationSupported);
    // rd.setVendorId(vendorId.intValue());
    //
    // // Fire the appropriate event.
    // localDevice.getEventHandler().fireIAmReceived(rd);
    // }

    @Override
    public void write(final ByteQueue queue) {
        writeContextTag(queue, SERVICE_ID, true);
        writeContextTag(queue, TYPE_ID, true);
        write(queue, iAmDeviceIdentifier);
        write(queue, maxAPDULengthAccepted);
        write(queue, segmentationSupported);
        write(queue, vendorId);
        writeContextTag(queue, TYPE_ID, false);
        writeContextTag(queue, SERVICE_ID, false);
    }

    IAmRequest(final ByteQueue queue) throws BACnetException {
        iAmDeviceIdentifier = read(queue, BACnetObjectIdentifier.class);
        maxAPDULengthAccepted = read(queue, UnsignedInteger.class);
        segmentationSupported = read(queue, Segmentation.class);
        vendorId = read(queue, UnsignedInteger.class);
    }

    public BACnetObjectIdentifier getIAmDeviceIdentifier() {
        return iAmDeviceIdentifier;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((iAmDeviceIdentifier == null) ? 0
                : iAmDeviceIdentifier.hashCode());
        result = PRIME * result + ((maxAPDULengthAccepted == null) ? 0
                : maxAPDULengthAccepted.hashCode());
        result = PRIME * result + ((segmentationSupported == null) ? 0
                : segmentationSupported.hashCode());
        result = PRIME * result
                + ((vendorId == null) ? 0 : vendorId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IAmRequest other = (IAmRequest) obj;
        if (iAmDeviceIdentifier == null) {
            if (other.iAmDeviceIdentifier != null) {
                return false;
            }
        } else if (!iAmDeviceIdentifier.equals(other.iAmDeviceIdentifier)) {
            return false;
        }
        if (maxAPDULengthAccepted == null) {
            if (other.maxAPDULengthAccepted != null) {
                return false;
            }
        } else if (!maxAPDULengthAccepted.equals(other.maxAPDULengthAccepted)) {
            return false;
        }
        if (segmentationSupported == null) {
            if (other.segmentationSupported != null) {
                return false;
            }
        } else if (!segmentationSupported.equals(other.segmentationSupported)) {
            return false;
        }
        if (vendorId == null) {
            if (other.vendorId != null) {
                return false;
            }
        } else if (!vendorId.equals(other.vendorId)) {
            return false;
        }
        return true;
    }
}
