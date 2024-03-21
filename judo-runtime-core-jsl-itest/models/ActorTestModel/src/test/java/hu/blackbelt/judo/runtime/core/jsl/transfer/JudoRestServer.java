package hu.blackbelt.judo.runtime.core.jsl.transfer;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transport.http_jetty.ServerEngine;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

@Slf4j
public class JudoRestServer {

    final Server server;

    @Builder
    @Getter
    public static class RestServerContext {
        @NonNull
        Application application;

        @Builder.Default
        String protocol = "http";

        @Builder.Default
        Integer port = 8181;

        @Builder.Default
        String address = "localhost";

        @Builder.Default
        String contextPath = "api";
    }

    public static JudoRestServer createServer(RestServerContext context) {
        return new JudoRestServer(context);
    }

    public JudoRestServer(RestServerContext context) {
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

        //ActorApplicationConfigImpl application = new ActorApplicationConfigImpl(context.getDispatcher());

        /*
        ActorImpl actor = new ActorImpl();
        actor.__bindDispatcher(dispatcher);
        application.set_actortestmodel_actortestmodel_ActorImpl(actor);

        UserTransferImpl userTransfer = new UserTransferImpl();
        userTransfer.__bindDispatcher(dispatcher);
        application.set_actortestmodel_actortestmodel_UserTransferImpl(userTransfer);
        */

        final Set<Class<?>> classes = context.getApplication().getClasses();
        final Set<Object> singletons = context.getApplication().getSingletons();

        if ((classes == null || classes.isEmpty()) && (singletons == null || singletons.isEmpty())) {
            throw new RuntimeException("No resource classes found, do not start JAX-RS application");
        }

        final RuntimeDelegate delegate = RuntimeDelegate.getInstance();
        //final JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();

        JAXRSServerFactoryBean serverFactory = delegate.createEndpoint(context.getApplication(), JAXRSServerFactoryBean.class);

//        final Map<String, Object> properties = application.getProperties();
//        final String applicationPath = properties != null ? (String) properties.get(APPLICATION_PATH) : null;
//        if (applicationPath != null) {
//            serverFactory.setAddress(applicationPath);
//        } else if (!application.getClass().isAnnotationPresent(ApplicationPath.class)) {
//            log.warn("No @ApplicationPath found on component, service.id = " + applicationId);
//        }
//        serverFactory.setAddress("http://localhost:8181/api/ActorTest");
        serverFactory.setAddress(context.getProtocol() + "://" + context.getAddress() + ":" + Integer.toString(context.getPort()) + "/" + context.getContextPath() + serverFactory.getAddress());
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

        serverFactory.setStart(false);
        server = serverFactory.create();

        server.start();



        /*
        ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            Resource res = Resource.newClassPathResource("web/index.html");
            resourceHandler.setBaseResource(res);
        } finally {
            Thread.currentThread().setContextClassLoader(oldCl);
        }

         */
    }

    public void addStaticContent(String path, String pathInJar) {
        // Add custom handler
        Destination dest = server.getDestination();
        JettyHTTPDestination jettyDestination = JettyHTTPDestination.class.cast(dest);
        ServerEngine engine = jettyDestination.getEngine();
        JettyHTTPServerEngine serverEngine = JettyHTTPServerEngine.class.cast(engine);
        org.eclipse.jetty.server.Server httpServer = serverEngine.getServer();

        URL f = this.getClass().getClassLoader().getResource(pathInJar);
        if (f == null) {
            throw new RuntimeException("Unable to find resource directory");
        }

        // Resolve file to directory
        URI webPath = null;
        try {
            webPath = f.toURI().resolve("./").normalize();
            ContextHandlerCollection serverHandler = (ContextHandlerCollection) httpServer.getHandler();
            ContextHandler contextHandler = new ContextHandler();
            contextHandler.setContextPath(path);
            ResourceHandler resourceHandler = new ResourceHandler();
            Resource res = Resource.newResource(webPath);
            resourceHandler.setBaseResource(res);
            contextHandler.setHandler(resourceHandler);
            serverHandler.addHandler(contextHandler);
            resourceHandler.start();
            contextHandler.start();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Path added " + path + " resolved: " + webPath);

    }

    public void stop() {
        if (server != null && server.isStarted()) {
            server.destroy();
        }
    }
}
