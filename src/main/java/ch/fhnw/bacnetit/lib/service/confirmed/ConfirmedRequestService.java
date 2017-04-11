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
package ch.fhnw.bacnetit.lib.service.confirmed;

import ch.fhnw.bacnetit.lib.deviceobjects.Service;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.lib.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.lib.encoding.type.constructed.ServicesSupported;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.lib.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.lib.encoding.util.ByteQueue;

abstract public class ConfirmedRequestService extends Service {
    private static final long serialVersionUID = -7443765811741238314L;

    public static final byte SERVICE_ID = 0;

    public static void checkConfirmedRequestService(
            final ServicesSupported services, final byte type)
            throws BACnetException {
        if (type == AcknowledgeAlarmRequest.TYPE_ID
                && services.isAcknowledgeAlarm()) {
            return;
        }
        if (type == ConfirmedCovNotificationRequest.TYPE_ID
                && services.isConfirmedCovNotification()) {
            return;
        }
        if (type == ConfirmedEventNotificationRequest.TYPE_ID
                && services.isConfirmedEventNotification()) {
            return;
        }
        if (type == GetAlarmSummaryRequest.TYPE_ID
                && services.isGetAlarmSummary()) {
            return;
        }
        if (type == GetEnrollmentSummaryRequest.TYPE_ID
                && services.isGetEnrollmentSummary()) {
            return;
        }
        if (type == SubscribeCOVRequest.TYPE_ID && services.isSubscribeCov()) {
            return;
        }
        if (type == AtomicReadFileRequest.TYPE_ID
                && services.isAtomicReadFile()) {
            return;
        }
        if (type == AtomicWriteFileRequest.TYPE_ID
                && services.isAtomicWriteFile()) {
            return;
        }
        if (type == AddListElementRequest.TYPE_ID
                && services.isAddListElement()) {
            return;
        }
        if (type == RemoveListElementRequest.TYPE_ID
                && services.isRemoveListElement()) {
            return;
        }
        if (type == CreateObjectRequest.TYPE_ID && services.isCreateObject()) {
            return;
        }
        if (type == DeleteObjectRequest.TYPE_ID && services.isDeleteObject()) {
            return;
        }
        if (type == ReadPropertyRequest.TYPE_ID && services.isReadProperty()) {
            return;
        }
        if (type == ReadPropertyConditionalRequest.TYPE_ID
                && services.isReadPropertyConditional()) {
            return;
        }
        if (type == ReadPropertyMultipleRequest.TYPE_ID
                && services.isReadPropertyMultiple()) {
            return;
        }
        if (type == WritePropertyRequest.TYPE_ID
                && services.isWriteProperty()) {
            return;
        }
        if (type == WritePropertyMultipleRequest.TYPE_ID
                && services.isWritePropertyMultiple()) {
            return;
        }
        if (type == DeviceCommunicationControlRequest.TYPE_ID
                && services.isDeviceCommunicationControl()) {
            return;
        }
        if (type == ConfirmedPrivateTransferRequest.TYPE_ID
                && services.isConfirmedPrivateTransfer()) {
            return;
        }
        if (type == ConfirmedTextMessageRequest.TYPE_ID
                && services.isConfirmedTextMessage()) {
            return;
        }
        if (type == ReinitializeDeviceRequest.TYPE_ID
                && services.isReinitializeDevice()) {
            return;
        }
        if (type == VtOpenRequest.TYPE_ID && services.isVtOpen()) {
            return;
        }
        if (type == VtCloseRequest.TYPE_ID && services.isVtClose()) {
            return;
        }
        if (type == VtDataRequest.TYPE_ID && services.isVtData()) {
            return;
        }
        if (type == AuthenticateRequest.TYPE_ID && services.isAuthenticate()) {
            return;
        }
        if (type == RequestKeyRequest.TYPE_ID && services.isRequestKey()) {
            return;
        }
        if (type == ReadRangeRequest.TYPE_ID && services.isReadRange()) {
            return;
        }
        if (type == LifeSafetyOperationRequest.TYPE_ID
                && services.isLifeSafetyOperation()) {
            return;
        }
        if (type == SubscribeCOVPropertyRequest.TYPE_ID
                && services.isSubscribeCovProperty()) {
            return;
        }
        if (type == GetEventInformation.TYPE_ID
                && services.isGetEventInformation()) {
            return;
        }

        throw new BACnetErrorException(ErrorClass.device,
                ErrorCode.serviceRequestDenied);
    }

    public static ConfirmedRequestService createConfirmedRequestService(
            final byte type, final ByteQueue queue) throws BACnetException {
        if (type == AcknowledgeAlarmRequest.TYPE_ID) {
            return new AcknowledgeAlarmRequest(queue);
        }
        if (type == ConfirmedCovNotificationRequest.TYPE_ID) {
            return new ConfirmedCovNotificationRequest(queue);
        }
        if (type == ConfirmedEventNotificationRequest.TYPE_ID) {
            return new ConfirmedEventNotificationRequest(queue);
        }
        if (type == GetAlarmSummaryRequest.TYPE_ID) {
            return new GetAlarmSummaryRequest(queue);
        }
        if (type == GetEnrollmentSummaryRequest.TYPE_ID) {
            return new GetEnrollmentSummaryRequest(queue);
        }
        if (type == SubscribeCOVRequest.TYPE_ID) {
            return new SubscribeCOVRequest(queue);
        }
        if (type == AtomicReadFileRequest.TYPE_ID) {
            return new AtomicReadFileRequest(queue);
        }
        if (type == AtomicWriteFileRequest.TYPE_ID) {
            return new AtomicWriteFileRequest(queue);
        }
        if (type == AddListElementRequest.TYPE_ID) {
            return new AddListElementRequest(queue);
        }
        if (type == RemoveListElementRequest.TYPE_ID) {
            return new RemoveListElementRequest(queue);
        }
        if (type == CreateObjectRequest.TYPE_ID) {
            return new CreateObjectRequest(queue);
        }
        if (type == DeleteObjectRequest.TYPE_ID) {
            return new DeleteObjectRequest(queue);
        }
        if (type == ReadPropertyRequest.TYPE_ID) {
            return new ReadPropertyRequest(queue);
        }
        if (type == ReadPropertyConditionalRequest.TYPE_ID) {
            return new ReadPropertyConditionalRequest(queue);
        }
        if (type == ReadPropertyMultipleRequest.TYPE_ID) {
            return new ReadPropertyMultipleRequest(queue);
        }
        if (type == WritePropertyRequest.TYPE_ID) {
            return new WritePropertyRequest(queue);
        }
        if (type == WritePropertyMultipleRequest.TYPE_ID) {
            return new WritePropertyMultipleRequest(queue);
        }
        if (type == DeviceCommunicationControlRequest.TYPE_ID) {
            return new DeviceCommunicationControlRequest(queue);
        }
        if (type == ConfirmedPrivateTransferRequest.TYPE_ID) {
            return new ConfirmedPrivateTransferRequest(queue);
        }
        if (type == ConfirmedTextMessageRequest.TYPE_ID) {
            return new ConfirmedTextMessageRequest(queue);
        }
        if (type == ReinitializeDeviceRequest.TYPE_ID) {
            return new ReinitializeDeviceRequest(queue);
        }
        if (type == VtOpenRequest.TYPE_ID) {
            return new VtOpenRequest(queue);
        }
        if (type == VtCloseRequest.TYPE_ID) {
            return new VtCloseRequest(queue);
        }
        if (type == VtDataRequest.TYPE_ID) {
            return new VtDataRequest(queue);
        }
        if (type == AuthenticateRequest.TYPE_ID) {
            return new AuthenticateRequest(queue);
        }
        if (type == RequestKeyRequest.TYPE_ID) {
            return new RequestKeyRequest(queue);
        }
        if (type == ReadRangeRequest.TYPE_ID) {
            return new ReadRangeRequest(queue);
        }
        if (type == LifeSafetyOperationRequest.TYPE_ID) {
            return new LifeSafetyOperationRequest(queue);
        }
        if (type == SubscribeCOVPropertyRequest.TYPE_ID) {
            return new SubscribeCOVPropertyRequest(queue);
        }
        if (type == GetEventInformation.TYPE_ID) {
            return new GetEventInformation(queue);
        }

        throw new BACnetErrorException(ErrorClass.device,
                ErrorCode.serviceRequestDenied);
    }

    // abstract public AcknowledgementService handle(LocalDevice localDevice,
    // Address from, OctetString linkService)
    // throws BACnetException;
}
