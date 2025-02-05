package hu.blackbelt.judo.runtime.core.jsl.entity;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.fault1.Fault1Exception;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.fault2.Fault2Exception;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedfaulttransfer.MappedFaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedfaulttransfer.MappedFaultTransferOperation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedinputparameter.MappedInputParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedoutputparameter.MappedOutputParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedtransfer.MappedTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.mappedtransfer.MappedTransferOperation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.unmappedfaulttransfer.UnmappedFaultTransferOperation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.unmappedinputparameter.UnmappedInputParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.unmappedoutputparameter.UnmappedOutputParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operationmodel.operationmodel.unmappedtransfer.UnmappedTransferOperation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.OperationModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
public class OperationModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("OperationModel", new OperationModelDaoModules());

    @Test
    public void testGeneratedOperationsInUnmappedTransferObject() {

        Class<UnmappedTransferOperation> clazz = UnmappedTransferOperation.class;

        // static action
        hasStaticMethod("staticVoidAction", clazz, null, void.class);
        hasStaticMethod("staticVoidActionWithUnmappedInput", clazz, UnmappedInputParameter.class, void.class);
        hasStaticMethod("staticVoidActionWithMappedInput", clazz, MappedInputParameter.class, void.class);
        hasStaticMethod("staticUnmappedOutputAction", clazz, null, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputAction", clazz, null, MappedOutputParameter.class);
        hasStaticMethod("staticUnmappedOutputActionWithUnmappedInput", clazz, UnmappedInputParameter.class, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputActionWithUnmappedInput", clazz, UnmappedInputParameter.class, MappedOutputParameter.class);
        hasStaticMethod("staticUnmappedOutputActionWithMappedInput", clazz, MappedInputParameter.class, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputActionWithMappedInput", clazz, MappedInputParameter.class, MappedOutputParameter.class);

    }

    static <C, I, O> void hasStaticMethod(String name, Class<C> clazz, Class<I> inputType, Class<O> returnType) {

        Method method = assertDoesNotThrow(() -> {
                if (inputType != null) {
                    return clazz.getMethod(name, inputType);
                }
                return clazz.getMethod(name);
                }
        );
        if(inputType !=null) {
            assertEquals(1, method.getParameters().length);
            assertEquals(inputType, method.getParameters()[0].getType());
        } else {
            assertEquals(0, method.getParameters().length);
        }
        assertEquals(returnType, method.getReturnType());

    }

    @Test
    public void testGeneratedOperationsInMappedTransferObject() {

        Class<MappedTransferOperation> clazz = MappedTransferOperation.class;

        // static actions
        hasStaticMethod("staticVoidAction", clazz, null, void.class);
        hasStaticMethod("staticVoidActionWithUnmappedInput", clazz, UnmappedInputParameter.class, void.class);
        hasStaticMethod("staticVoidActionWithMappedInput", clazz, MappedInputParameter.class, void.class);
        hasStaticMethod("staticUnmappedOutputAction", clazz, null, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputAction", clazz, null, MappedOutputParameter.class);
        hasStaticMethod("staticUnmappedOutputActionWithUnmappedInput", clazz, UnmappedInputParameter.class, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputActionWithUnmappedInput", clazz, UnmappedInputParameter.class, MappedOutputParameter.class);
        hasStaticMethod("staticUnmappedOutputActionWithMappedInput", clazz, MappedInputParameter.class, UnmappedOutputParameter.class);
        hasStaticMethod("staticMappedOutputActionWithMappedInput", clazz, MappedInputParameter.class, MappedOutputParameter.class);

        // instance actions
        hasInstanceMethod("voidAction", clazz, MappedTransfer.class, null, void.class);
        hasInstanceMethod("voidActionWithUnmappedInput", clazz, MappedTransfer.class, UnmappedInputParameter.class, void.class);
        hasInstanceMethod("voidActionWithMappedInput", clazz, MappedTransfer.class, MappedInputParameter.class, void.class);
        hasInstanceMethod("unmappedOutputAction", clazz, MappedTransfer.class, null, UnmappedOutputParameter.class);
        hasInstanceMethod("mappedOutputAction", clazz, MappedTransfer.class, null, MappedOutputParameter.class);
        hasInstanceMethod("unmappedOutputActionWithUnmappedInput", clazz, MappedTransfer.class, UnmappedInputParameter.class, UnmappedOutputParameter.class);
        hasInstanceMethod("mappedOutputActionWithUnmappedInput", clazz, MappedTransfer.class, UnmappedInputParameter.class, MappedOutputParameter.class);
        hasInstanceMethod("unmappedOutputActionWithMappedInput", clazz, MappedTransfer.class, MappedInputParameter.class, UnmappedOutputParameter.class);
        hasInstanceMethod("mappedOutputActionWithMappedInput", clazz, MappedTransfer.class, MappedInputParameter.class, MappedOutputParameter.class);

    }

    static <C, T, I, O> void hasInstanceMethod(String name, Class<C> clazz, Class<T> thiz, Class<I> inputType, Class<O> returnType) {

        Method method = assertDoesNotThrow(() -> {
                    if (inputType != null) {
                        return clazz.getMethod(name, thiz, inputType);
                    }
                    return clazz.getMethod(name, thiz);
                }
        );
        if(inputType !=null) {
            assertEquals(2, method.getParameters().length);
            assertEquals(thiz, method.getParameters()[0].getType());
            assertEquals(inputType, method.getParameters()[1].getType());
        } else {
            assertEquals(1, method.getParameters().length);
            assertEquals(thiz, method.getParameters()[0].getType());
        }
        assertEquals(returnType, method.getReturnType());

    }

    @Test
    public void testGeneratedOperationsWithFaults() {

        // Mapped
        hasFaultMethod("faults", MappedFaultTransferOperation.class, MappedFaultTransfer.class, List.of(Fault1Exception.class, Fault2Exception.class));
        hasFaultMethod("staticFaults", MappedFaultTransferOperation.class, null, List.of(Fault1Exception.class, Fault2Exception.class));

        // Unmapped
        hasFaultMethod("staticFaults", UnmappedFaultTransferOperation.class, null, List.of(Fault1Exception.class, Fault2Exception.class));


    }

    static <C, T> void hasFaultMethod(String name, Class<C> clazz, Class<T> thiz, Collection<Class<?>> exceptionClasses) {

        Method method = assertDoesNotThrow(() -> {
                    if (thiz != null) {
                        return clazz.getMethod(name, thiz);
                    }
                    return clazz.getMethod(name);
                }
        );

        if(thiz !=null) {
            assertEquals(1, method.getParameters().length);
            assertEquals(thiz, method.getParameters()[0].getType());
        } else {
            assertEquals(0, method.getParameters().length);
        }
        assertEquals(void.class, method.getReturnType());

        Set<Class<?>> classSet = Arrays.stream(method.getExceptionTypes()).collect(Collectors.toSet());

        assertEquals(exceptionClasses.size(), classSet.size());
        assertTrue(classSet.containsAll(exceptionClasses));

    }

}
