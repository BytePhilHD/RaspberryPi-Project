package webserver;

import enums.MessageType;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import utils.Console;

import java.util.ArrayList;

public class WebService {

    public static void boot() {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start();

        app.ws("/mainws", ws -> {
            ws.onConnect(ctx -> {
                Console.print("Client connected with IP " + ctx.session.getRemoteAddress() + " and SessionID " + ctx.getSessionId(), MessageType.INFO);
            });
            ws.onClose(ctx -> {
                Console.print("Client disconnected with IP " + ctx.session.getRemoteAddress() + " and SessionID " + ctx.getSessionId(), MessageType.INFO);
            });
            ws.onMessage(ctx -> {
                handleInput(ctx.message());
            });
        });
    }

    private static void handleInput(String input) {
        switch (input) {
            case "fullon":
                Console.print("DMX - Send out Full On", MessageType.INFO);
                break;
            case "blackout":
                Console.print("DMX - Send out Blackout", MessageType.INFO);
                break;
            default:
                Console.print("DMX - Received invalid input from WebService!", MessageType.ERROR);
                break;
        }
    }
}
