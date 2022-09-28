package hu.blackbelt.judo.runtime.core.bootstrap.jsl;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
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

import com.google.common.collect.ImmutableList;
import hu.blackbelt.judo.tatami.core.workflow.work.TransformationContext;
import org.junit.jupiter.api.Test;

import java.io.File;

import static hu.blackbelt.judo.tatami.jsl.workflow.DefaultWorkflowSave.saveModels;

class JSLTransformationModelLoaderTest {

    @Test
    void loadAndTransformJslModels() {
        File target = new File("target/test-classes/loader-test");
        target.mkdirs();

        TransformationContext context = JSLTransformationModelLoader.loadAndTransformJslModels("test2::SalesModel2", "hsqldb",
                target,
                new File("src/test/resources/models/sample.jsl").toURI(),
                new File("src/test/resources/models/sample2.jsl").toURI()
        );

        saveModels(context, target, ImmutableList.of("hsqldb"));
    }
}
