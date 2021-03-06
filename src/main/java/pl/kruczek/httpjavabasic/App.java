package pl.kruczek.httpjavabasic;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import pl.kruczek.httpjavabasic.config.DBFacade;

public class App {

    public static void main(String[] args) throws Exception {

        WebAppContext webContext = new WebAppContext();
        webContext.setResourceBase("src/main/webapp");
        webContext.setContextPath("/");
        webContext.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration(),
                new JettyWebXmlConfiguration()
        });
        webContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        Server server = new Server(8888);
        server.setHandler(webContext);

        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
            @Override
            public void lifeCycleStopped(LifeCycle event) {
                DBFacade.close();
            }
        });
        server.start();
        server.join();
    }
}
