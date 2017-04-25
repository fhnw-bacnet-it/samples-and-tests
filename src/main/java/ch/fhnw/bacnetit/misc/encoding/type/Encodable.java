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
package ch.fhnw.bacnetit.misc.encoding.type;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import ch.fhnw.bacnetit.misc.deviceobjects.BACnetObjectFactory;
import ch.fhnw.bacnetit.misc.deviceobjects.BACnetObjectType;
import ch.fhnw.bacnetit.misc.deviceobjects.BACnetProperty;
import ch.fhnw.bacnetit.misc.deviceobjects.BACnetPropertyIdentifier;
import ch.fhnw.bacnetit.misc.deviceobjects.VendorServiceKey;
import ch.fhnw.bacnetit.misc.encoding.base.BACnetUtils;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetErrorException;
import ch.fhnw.bacnetit.misc.encoding.exception.BACnetException;
import ch.fhnw.bacnetit.misc.encoding.exception.ReflectionException;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.Choice;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.Sequence;
import ch.fhnw.bacnetit.misc.encoding.type.constructed.SequenceOf;
import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorClass;
import ch.fhnw.bacnetit.misc.encoding.type.enumerated.ErrorCode;
import ch.fhnw.bacnetit.misc.encoding.type.eventParameter.EventParameter;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.Null;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.Primitive;
import ch.fhnw.bacnetit.misc.encoding.type.primitive.UnsignedInteger;
import ch.fhnw.bacnetit.misc.encoding.util.ByteQueue;

//import com.serotonin.bacnet4j.event.ExceptionDispatch;

abstract public class Encodable implements Serializable {
    private static final long serialVersionUID = -4378016931626697698L;

    abstract public void write(ByteQueue queue);

    abstract public void write(ByteQueue queue, int contextId);

    @Override
    public String toString() {
        return "Encodable(" + getClass().getName() + ")";
    }

    protected static void popTagData(final ByteQueue queue,
            final TagData tagData) {
        peekTagData(queue, tagData);
        queue.pop(tagData.tagLength);
    }

    protected static void peekTagData(final ByteQueue queue,
            final TagData tagData) {
        int peekIndex = 0;
        final byte b = queue.peek(peekIndex++);
        tagData.tagNumber = (b & 0xff) >> 4;
        tagData.contextSpecific = (b & 8) != 0;
        tagData.length = (b & 7);

        if (tagData.tagNumber == 0xf) {
            // Extended tag.
            tagData.tagNumber = BACnetUtils.toInt(queue.peek(peekIndex++));
        }

        if (tagData.length == 5) {
            tagData.length = BACnetUtils.toInt(queue.peek(peekIndex++));
            if (tagData.length == 254) {
                tagData.length = (BACnetUtils
                        .toInt(queue.peek(peekIndex++)) << 8)
                        | BACnetUtils.toInt(queue.peek(peekIndex++));
            } else if (tagData.length == 255) {
                tagData.length = (BACnetUtils
                        .toLong(queue.peek(peekIndex++)) << 24)
                        | (BACnetUtils.toLong(queue.peek(peekIndex++)) << 16)
                        | (BACnetUtils.toLong(queue.peek(peekIndex++)) << 8)
                        | BACnetUtils.toLong(queue.peek(peekIndex++));
            }
        }

        tagData.tagLength = peekIndex;
    }

    protected static int peekTagNumber(final ByteQueue queue) {
        if (queue.size() == 0) {
            return -1;
        }

        // Take a peek at the tag number.
        int tagNumber = (queue.peek(0) & 0xff) >> 4;
        if (tagNumber == 15) {
            tagNumber = queue.peek(1) & 0xff;
        }
        return tagNumber;
    }

    //
    // Write context tags for base types.
    public void writeContextTag(final ByteQueue queue, final int contextId,
            final boolean start) {
        if (contextId <= 14) {
            queue.push((contextId << 4) | (start ? 0xe : 0xf));
        } else {
            queue.push(start ? 0xfe : 0xff);
            queue.push(contextId);
        }
    }

    //
    // Read start tags.
    protected static int readStart(final ByteQueue queue) {
        if (queue.size() == 0) {
            return -1;
        }

        final int b = queue.peek(0) & 0xff;
        if ((b & 0xf) != 0xe) {
            return -1;
        }
        if ((b & 0xf0) == 0xf0) {
            return queue.peek(1);
        }
        return b >> 4;
    }

    protected static int popStart(final ByteQueue queue) {
        final int contextId = readStart(queue);
        if (contextId != -1) {
            queue.pop();
            if (contextId > 14) {
                queue.pop();
            }
        }
        return contextId;
    }

    protected static void popStart(final ByteQueue queue, final int contextId)
            throws BACnetErrorException {
        if (popStart(queue) != contextId) {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.missingRequiredParameter);
        }
    }

    //
    // Read end tags.
    protected static int readEnd(final ByteQueue queue) {
        if (queue.size() == 0) {
            return -1;
        }
        final int b = queue.peek(0) & 0xff;
        if ((b & 0xf) != 0xf) {
            return -1;
        }
        if ((b & 0xf0) == 0xf0) {
            return queue.peek(1);
        }
        return b >> 4;
    }

    protected static void popEnd(final ByteQueue queue, final int contextId)
            throws BACnetErrorException {
        if (readEnd(queue) != contextId) {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.missingRequiredParameter);
        }
        queue.pop();
        if (contextId > 14) {
            queue.pop();
        }
    }

    private static boolean matchContextId(final ByteQueue queue,
            final int contextId) {
        return peekTagNumber(queue) == contextId;
    }

    protected static boolean matchStartTag(final ByteQueue queue,
            final int contextId) {
        return matchContextId(queue, contextId) && (queue.peek(0) & 0xf) == 0xe;
    }

    protected static boolean matchEndTag(final ByteQueue queue,
            final int contextId) {
        return matchContextId(queue, contextId) && (queue.peek(0) & 0xf) == 0xf;
    }

    protected static boolean matchNonEndTag(final ByteQueue queue,
            final int contextId) {
        return matchContextId(queue, contextId) && (queue.peek(0) & 0xf) != 0xf;
    }

    //
    // Basic read and write. Pretty trivial.
    protected static void write(final ByteQueue queue, final Encodable type) {
        type.write(queue);
    }

    @SuppressWarnings("unchecked")
    protected static <T extends Encodable> T read(final ByteQueue queue,
            final Class<T> clazz) throws BACnetException {
        if (clazz == Primitive.class) {
            return (T) Primitive.createPrimitive(queue);
        }

        try {
            return clazz.getConstructor(new Class[] { ByteQueue.class })
                    .newInstance(new Object[] { queue });
        } catch (final NoSuchMethodException e) {
            // Check if this is an EventParameter
            if (clazz == EventParameter.class) {
                return (T) EventParameter.createEventParameter(queue);
            }
            throw new BACnetException(e);
        } catch (final InvocationTargetException e) {
            // Check if there is a wrapped BACnet exception
            if (e.getCause() instanceof BACnetException) {
                throw (BACnetException) e.getCause();
            }
            throw new ReflectionException(e);
        } catch (final Exception e) {
            throw new BACnetException(e);
        }
    }

    //
    // Read and write with context id.
    protected static <T extends Encodable> T read(final ByteQueue queue,
            final Class<T> clazz, final int contextId) throws BACnetException {
        if (!matchNonEndTag(queue, contextId)) {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.missingRequiredParameter);
        }

        if (Primitive.class.isAssignableFrom(clazz)) {
            return read(queue, clazz);
        }
        return readWrapped(queue, clazz, contextId);
    }

    protected static void write(final ByteQueue queue, final Encodable type,
            final int contextId) {
        type.write(queue, contextId);
    }

    //
    // Optional read and write.
    protected static void writeOptional(final ByteQueue queue,
            final Encodable type) {
        if (type == null) {
            return;
        }
        write(queue, type);
    }

    protected static void writeOptional(final ByteQueue queue,
            final Encodable type, final int contextId) {
        if (type == null) {
            return;
        }
        write(queue, type, contextId);
    }

    protected static <T extends Encodable> T readOptional(final ByteQueue queue,
            final Class<T> clazz, final int contextId) throws BACnetException {
        if (!matchNonEndTag(queue, contextId)) {
            return null;
        }
        return read(queue, clazz, contextId);
    }

    //
    // Read lists
    protected static <T extends Encodable> SequenceOf<T> readSequenceOf(
            final ByteQueue queue, final Class<T> clazz)
            throws BACnetException {
        return new SequenceOf<T>(queue, clazz);
    }

    protected static <T extends Encodable> SequenceOf<T> readSequenceOf(
            final ByteQueue queue, final int count, final Class<T> clazz)
            throws BACnetException {
        return new SequenceOf<T>(queue, count, clazz);
    }

    protected static <T extends Encodable> SequenceOf<T> readSequenceOf(
            final ByteQueue queue, final Class<T> clazz, final int contextId)
            throws BACnetException {
        popStart(queue, contextId);
        final SequenceOf<T> result = new SequenceOf<T>(queue, clazz, contextId);
        popEnd(queue, contextId);
        return result;
    }

    protected static <T extends Encodable> T readSequenceType(
            final ByteQueue queue, final Class<T> clazz, final int contextId)
            throws BACnetException {
        popStart(queue, contextId);
        T result;
        try {
            result = clazz
                    .getConstructor(
                            new Class[] { ByteQueue.class, Integer.TYPE })
                    .newInstance(new Object[] { queue, contextId });
        } catch (final Exception e) {
            throw new BACnetException(e);
        }
        popEnd(queue, contextId);
        return result;
    }

    protected static SequenceOf<Choice> readSequenceOfChoice(
            final ByteQueue queue,
            final List<Class<? extends Encodable>> classes, final int contextId)
            throws BACnetException {
        popStart(queue, contextId);
        final SequenceOf<Choice> result = new SequenceOf<Choice>();
        while (readEnd(queue) != contextId) {
            result.add(new Choice(queue, classes));
        }
        popEnd(queue, contextId);
        return result;
    }

    protected static <T extends Encodable> SequenceOf<T> readOptionalSequenceOf(
            final ByteQueue queue, final Class<T> clazz, final int contextId)
            throws BACnetException {
        if (readStart(queue) != contextId) {
            return null;
        }
        return readSequenceOf(queue, clazz, contextId);
    }

    // Read and write encodable
    protected static void writeEncodable(final ByteQueue queue,
            final Encodable type, final int contextId) {
        if (Primitive.class.isAssignableFrom(type.getClass())) {
            ((Primitive) type).writeEncodable(queue, contextId);
        } else {
            type.write(queue, contextId);
        }
    }

    protected static Encodable readEncodable(final ByteQueue queue,
            final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final UnsignedInteger propertyArrayIndex, final int contextId)
            throws BACnetException {
        // A property array index of 0 indicates a request for the length of an
        // array.
        if (propertyArrayIndex != null && propertyArrayIndex.intValue() == 0) {
            return readWrapped(queue, UnsignedInteger.class, contextId);
        }

        if (!matchNonEndTag(queue, contextId)) {
            throw new BACnetErrorException(ErrorClass.property,
                    ErrorCode.missingRequiredParameter);
        }

        final BACnetProperty def = BACnetObjectFactory
                .getPropertyTypeDefinition(objectType, propertyIdentifier);
        if (def == null) {
            return new AmbiguousValue(queue, contextId);
        }

        if (BACnetObjectFactory.isCommandable(objectType, propertyIdentifier)) {
            // If the object is commandable, it could be set to Null, so we need
            // to treat it as ambiguous.
            final AmbiguousValue amb = new AmbiguousValue(queue, contextId);

            if (amb.isNull()) {
                return new Null();
            }

            // Try converting to the definition value.
            return amb.convertTo(def.getClazz());
        }

        if (propertyArrayIndex != null) {
            if (!def.isSequence()
                    && !SequenceOf.class.isAssignableFrom(def.getClazz())) {
                throw new BACnetErrorException(ErrorClass.property,
                        ErrorCode.propertyIsNotAList);
            }
            if (SequenceOf.class.isAssignableFrom(def.getClazz())) {
                return readWrapped(queue, def.getInnerType(), contextId);
            }
        } else {
            if (def.isSequence()) {
                return readSequenceOf(queue, def.getClazz(), contextId);
            }
            if (SequenceOf.class.isAssignableFrom(def.getClazz())) {
                return readSequenceType(queue, def.getClazz(), contextId);
            }
        }

        return readWrapped(queue, def.getClazz(), contextId);
    }

    protected static Encodable readOptionalEncodable(final ByteQueue queue,
            final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final int contextId) throws BACnetException {
        if (readStart(queue) != contextId) {
            return null;
        }
        return readEncodable(queue, objectType, propertyIdentifier, null,
                contextId);
    }

    protected static SequenceOf<? extends Encodable> readSequenceOfEncodable(
            final ByteQueue queue, final BACnetObjectType objectType,
            final BACnetPropertyIdentifier propertyIdentifier,
            final int contextId) throws BACnetException {
        final BACnetProperty def = BACnetObjectFactory
                .getPropertyTypeDefinition(objectType, propertyIdentifier);
        if (def == null) {
            return readSequenceOf(queue, AmbiguousValue.class, contextId);
        }
        return readSequenceOf(queue, def.getClazz(), contextId);
    }

    // Read vendor-specific
    protected static Sequence readVendorSpecific(final ByteQueue queue,
            final UnsignedInteger vendorId, final UnsignedInteger serviceNumber,
            final Map<VendorServiceKey, SequenceDefinition> resolutions,
            final int contextId) throws BACnetException {
        if (readStart(queue) != contextId) {
            return null;
        }

        final VendorServiceKey key = new VendorServiceKey(vendorId,
                serviceNumber);
        final SequenceDefinition def = resolutions.get(key);
        if (def == null) {
            // ExceptionDispatch.fireUnimplementedVendorService(vendorId,
            // serviceNumber, queue);
            return null;
        }

        return new Sequence(def, queue, contextId);
    }

    private static <T extends Encodable> T readWrapped(final ByteQueue queue,
            final Class<T> clazz, final int contextId) throws BACnetException {
        popStart(queue, contextId);
        final T result = read(queue, clazz);
        popEnd(queue, contextId);
        return result;
    }
}
