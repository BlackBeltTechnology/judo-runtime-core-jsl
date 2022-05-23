package hu.blackbelt.judo.runtime.core.bootstrap.jsl;


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

	public static TransformationContext loadAndTransformJslModels(String modelName, String dialect, URI...modelUris) {
	    JslParser parser = new JslParser();
	    

    	JslDslModel model = parser.getModelFromStreamSources(
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
    	
    	//if (!model.getName().equals(modelName)) {
    	//	throw new IllegalStateException("Model name not match: " + modelName + " " + model.getName());
    	//}

    	
		JslDefaultWorkflow defaultWorkflow;
		
		defaultWorkflow = new JslDefaultWorkflow(
				DefaultWorkflowSetupParameters.defaultWorkflowSetupParameters()
						.jslModel(model)
						.dialectList(ImmutableList.of(dialect))
						.modelName(model.getName())
		);
		try {
			WorkReport workReport = defaultWorkflow.startDefaultWorkflow();
			if (workReport.getStatus() == WorkStatus.FAILED) {
				new File("target/test-classes/loader-test").mkdirs();
				saveModels(defaultWorkflow.getTransformationContext(), new File("target/test-classes/loader-test"), ImmutableList.of("hsqldb"));
				throw new RuntimeException(workReport.toString(), workReport.getError());
			}
		} catch (Exception e) {
			new File("target/test-classes/loader-test").mkdirs();
			saveModels(defaultWorkflow.getTransformationContext(), new File("target/test-classes/loader-test"), ImmutableList.of("hsqldb"));
			throw e;
		}

    	return defaultWorkflow.getTransformationContext();
	}

}
