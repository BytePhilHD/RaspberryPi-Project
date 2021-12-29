package webserver;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

import java.util.ArrayList;

public class WebService {

    public static void boot() {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start();

        app.ws("/mainws", ws -> {
            ws.onMessage(ctx -> {
                String message = ctx.message().replace("?", "");
            });
        });
    }
}
