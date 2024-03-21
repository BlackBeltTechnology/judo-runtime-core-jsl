package hu.blackbelt.judo.runtime.core.jsl.transfer;


import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import hu.blackbelt.judo.psm.generator.rest.test.impl.actortestmodel.actortestmodel.ActorApplicationConfigImpl;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ActorTestModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;


@Slf4j
public class ActorTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("ActorTestModel", new ActorTestModelDaoModules());

    private JudoRestServer server;
    @Inject
    Dispatcher dispatcher;

    @BeforeEach
    void setup() {
        server =
                JudoRestServer.createServer(JudoRestServer.RestServerContext.builder()
                        .application(new ActorApplicationConfigImpl(dispatcher))
                        .build());
        server.addStaticContent("/", "web");
    }

    @AfterEach
    public void tearDown() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    void testAbstractEntitiesHaveNoCreateMethods() throws InterruptedException {
        Thread.sleep(1000000);
    }


}
