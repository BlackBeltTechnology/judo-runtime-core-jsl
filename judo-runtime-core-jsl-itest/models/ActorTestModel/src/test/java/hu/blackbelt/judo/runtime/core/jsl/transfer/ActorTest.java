package hu.blackbelt.judo.runtime.core.jsl.transfer;


import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import hu.blackbelt.judo.psm.generator.rest.test.impl.actortestmodel.actortestmodel.ActorApplicationConfigImpl;
import hu.blackbelt.judo.psm.generator.rest.test.impl.rest.actortestmodel.actortestmodel.ActorImpl;
import hu.blackbelt.judo.psm.generator.rest.test.impl.rest.actortestmodel.actortestmodel.UserTransferImpl;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ActorTestModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Set;


@Slf4j
public class ActorTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("ActorTestModel", new ActorTestModelDaoModules());

    private Server server;
    @Inject
    Dispatcher dispatcher;

    @BeforeEach
    void setup() {
        /*
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();

        sf.setResourceClasses(CustomerService.class);
        sf.setResourceProvider(CustomerService.class, new SingletonResourceProvider(new CustomerService()));
        sf.setAddress("http://localhost:9000/");
        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
        sf.create();
        */

        ActorApplicationConfigImpl application = new ActorApplicationConfigImpl();

        ActorImpl actor = new ActorImpl();
        actor.__bindDispatcher(dispatcher);
        application.set_actortestmodel_actortestmodel_ActorImpl(actor);

        UserTransferImpl userTransfer = new UserTransferImpl();
        userTransfer.__bindDispatcher(dispatcher);
        application.set_actortestmodel_actortestmodel_UserTransferImpl(userTransfer);

        final Set<Class<?>> classes = application.getClasses();
        final Set<Object> singletons = application.getSingletons();

        if ((classes == null || classes.isEmpty()) && (singletons == null || singletons.isEmpty())) {
            log.warn("No resource classes found, do not start JAX-RS application");
            return;
        }

        final RuntimeDelegate delegate = RuntimeDelegate.getInstance();
        //final JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();

        JAXRSServerFactoryBean serverFactory = delegate.createEndpoint(application, JAXRSServerFactoryBean.class);

//        final Map<String, Object> properties = application.getProperties();
//        final String applicationPath = properties != null ? (String) properties.get(APPLICATION_PATH) : null;
//        if (applicationPath != null) {
//            serverFactory.setAddress(applicationPath);
//        } else if (!application.getClass().isAnnotationPresent(ApplicationPath.class)) {
//            log.warn("No @ApplicationPath found on component, service.id = " + applicationId);
//        }
//        serverFactory.setAddress("http://localhost:8181/api/ActorTest");
        serverFactory.setAddress("http://localhost:8181/api" + serverFactory.getAddress());
        log.info("Server URL: " + serverFactory.getAddress());

//        final CxfContext cxfContext;
//        if (properties != null && properties.containsKey(BasicApplication.CONTEXT_PROPERTY_KEY)) {
//            cxfContext = (CxfContext) properties.get(BasicApplication.CONTEXT_PROPERTY_KEY);
//        } else if (properties != null && properties.containsKey(CONTEXT_KEY)) {
//            final Object ctx = properties.get(CONTEXT_KEY);
//            cxfContext = (ctx instanceof CxfContext) ? (CxfContext) ctx : null;
//            cxfContext.getBus().setExtension(new BundleDelegatingClassLoader(applicationBundle), ClassLoader.class);
//        } else {
//            cxfContext = null;
//        }
//        if (cxfContext != null) {
//            serverFactory.setBus(cxfContext.getBus());
//
//            if (log.isTraceEnabled()) {
//                log.trace("IN interceptors: {}", cxfContext.getInInterceptors());
//                log.trace("OUT interceptors: {}", cxfContext.getOutInterceptors());
//                log.trace("FAULT interceptors: {}", cxfContext.getFaultInterceptors());
//            }

//            serverFactory.setInInterceptors(cxfContext.getInInterceptors());
//            serverFactory.setOutInterceptors(cxfContext.getOutInterceptors());
//            serverFactory.setOutFaultInterceptors(cxfContext.getFaultInterceptors());
//        }

        /*
        final List<Object> _providers;
        if (providers == null) {
            _providers = applicationProviders.containsKey(applicationId) ? applicationProviders.get(applicationId) : providers;
        } else {
            _providers = providers;
        }
        serverFactory.setProviders(_providers);
        applicationProviders.put(applicationId, _providers);
        */

        final Server server = serverFactory.create();
        server.start();
    }

    @AfterEach
    public void tearDown() {
        if (server != null && server.isStarted()) {
            server.destroy();
        }
    }

    @Test
    void testAbstractEntitiesHaveNoCreateMethods() throws InterruptedException {
        Thread.sleep(100000);
    }


}
