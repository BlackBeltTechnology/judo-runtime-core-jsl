package hu.blackbelt.judo.runtime.core.guice.jsl;

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
import hu.blackbelt.judo.meta.asm.runtime.AsmModel;
import hu.blackbelt.judo.meta.expression.runtime.ExpressionModel;
import hu.blackbelt.judo.meta.query.runtime.QueryModel;
import hu.blackbelt.judo.tatami.asm2rdbms.Asm2RdbmsWork;
import hu.blackbelt.judo.tatami.core.workflow.work.TransformationContext;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static hu.blackbelt.judo.tatami.jsl.workflow.DefaultWorkflowSave.saveModels;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JSLTransformationModelLoaderTest {

    private static final String MODEL_NAME = "test2::SalesModel2";

    @Test
    void loadAndTransformJslModels() {
        File target = new File("target/test-classes/loader-test");
        target.mkdirs();

        TransformationContext context = JSLTransformationModelLoader.loadAndTransformJslModels(MODEL_NAME, "hsqldb",
                target,
                new File("src/test/resources/models/sample.jsl").toURI(),
                new File("src/test/resources/models/sample2.jsl").toURI()
        );

        saveModels(context, target, ImmutableList.of("hsqldb"));
    }

    /**
     * Test plan TP-49: the asm2query stage is off by default, so the loader's context holds no query model and the saved
     * output has no query file, while the other models are present as before. See change: persisted-query-model.
     */
    @Test
    void asm2QueryStageIsOffByDefault() {
        final File target = new File("target/test-classes/loader-test-no-query");
        target.mkdirs();
        // a file left by an earlier run must not make the assertion pass or fail by accident
        final File[] leftovers = target.listFiles((dir, name) -> name.endsWith("-query.model"));
        if (leftovers != null) {
            for (File leftover : leftovers) {
                assertTrue(leftover.delete(), "Could not delete " + leftover);
            }
        }

        final TransformationContext context = JSLTransformationModelLoader.loadAndTransformJslModels(MODEL_NAME, "hsqldb",
                target,
                new File("src/test/resources/models/sample.jsl").toURI(),
                new File("src/test/resources/models/sample2.jsl").toURI()
        );

        final Optional<QueryModel> queryModel = context.getByClass(QueryModel.class);
        assertFalse(queryModel.isPresent(), "The loader's context must not contain a QueryModel");
        assertTrue(context.getByClass(AsmModel.class).isPresent(), "The ASM model is present as before");
        assertTrue(context.getByClass(ExpressionModel.class).isPresent(), "The expression model is present as before");
        assertTrue(Asm2RdbmsWork.getRdbmsModel(context, "hsqldb").isPresent(), "The RDBMS model is present as before");

        saveModels(context, target, ImmutableList.of("hsqldb"));

        final String[] queryFiles = target.list((dir, name) -> name.endsWith("-query.model"));
        assertEquals(0, queryFiles == null ? 0 : queryFiles.length, "No <name>-query.model is written");
        final String[] asmFiles = target.list((dir, name) -> name.endsWith("-asm.model"));
        assertEquals(1, asmFiles == null ? 0 : asmFiles.length, "The ASM model file is written");
    }
}
