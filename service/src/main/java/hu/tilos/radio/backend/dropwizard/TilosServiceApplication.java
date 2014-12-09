package hu.tilos.radio.backend.dropwizard;

import hu.tilos.radio.backend.Smoketest;
import hu.tilos.radio.backend.controller.ShowController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class TilosServiceApplication extends Application<TilosServiceConfig> {

    public static void main(String[] args) throws Exception {
        new TilosServiceApplication().run(args);

        System.out.println(com.fasterxml.jackson.annotation.ObjectIdResolver.class);
    }

    @Override
    public void initialize(Bootstrap<TilosServiceConfig> bootstrap) {

    }

    @Override
    public void run(TilosServiceConfig configuration, Environment environment) throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        environment.jersey().register(container.instance().select(Smoketest.class).get());
        environment.jersey().register(container.instance().select(ShowController.class).get());
    }
}
