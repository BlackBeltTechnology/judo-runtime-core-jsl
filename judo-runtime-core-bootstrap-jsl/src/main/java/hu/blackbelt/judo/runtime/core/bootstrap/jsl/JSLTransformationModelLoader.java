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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import hu.blackbelt.judo.meta.jsl.jsldsl.runtime.JslDslModel;
import hu.blackbelt.judo.meta.jsl.runtime.JslParser;
import hu.blackbelt.judo.meta.jsl.runtime.JslStreamSource;
import hu.blackbelt.judo.tatami.core.workflow.work.TransformationContext;
import hu.blackbelt.judo.tatami.core.workflow.work.WorkReport;
import hu.blackbelt.judo.tatami.core.workflow.work.WorkStatus;
import hu.blackbelt.judo.tatami.jsl.workflow.DefaultWorkflowSetupParameters;
import hu.blackbelt.judo.tatami.jsl.workflow.JslDefaultWorkflow;

import static hu.blackbelt.judo.tatami.jsl.workflow.DefaultWorkflowSave.saveModels;

public class JSLTransformationModelLoader {

    public static TransformationContext loadAndTransformJslModels(JslDslModel model, String dialect, File outputDirectory) {
        JslDefaultWorkflow defaultWorkflow;

        defaultWorkflow = new JslDefaultWorkflow(
                DefaultWorkflowSetupParameters.defaultWorkflowSetupParameters()
                        .jslModel(model)
                        .dialectList(ImmutableList.of(dialect))
                        .generateGuice(true)
                        .modelName(model.getName())
        );
        try {
            WorkReport workReport = defaultWorkflow.startDefaultWorkflow();
            if (workReport.getStatus() == WorkStatus.FAILED) {
                outputDirectory.mkdirs();
                saveModels(defaultWorkflow.getTransformationContext(), outputDirectory, ImmutableList.of(dialect));
                throw new RuntimeException(workReport.toString(), workReport.getError());
            }
        } catch (Exception e) {
            outputDirectory.mkdirs();
            saveModels(defaultWorkflow.getTransformationContext(), outputDirectory, ImmutableList.of(dialect));
            throw e;
        }

        return defaultWorkflow.getTransformationContext();
    }

    public static TransformationContext loadAndTransformJslModels(String modelName, String dialect, File outputDirectory, URI...modelUris) {
        JslDslModel model = JslParser.getModelFromStreamSources(
                modelName,
                Arrays.asList(modelUris).stream()
                        .map(s -> (URI) s)
                        .map(s -> {
                            try {
                                return new JslStreamSource(s.toURL().openStream(), org.eclipse.emf.common.util.URI.createURI("platform:/" + s.getPath()));
                            } catch (IOException e) {
                                throw new RuntimeException("Could not open stream: " + s.toString());
                            }
                        })
                        .collect(Collectors.toList()));
        return loadAndTransformJslModels(model, dialect, outputDirectory);
    }
}
