package webserver;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import enums.MessageType;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import utils.Console;
import utils.Output;

import java.nio.charset.StandardCharsets;
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
        Console.print("Received " + input.substring(0, 1).toUpperCase() + input.substring(1), MessageType.INFO);
        switch (input) {
            case "fullon":
                Output.Output(PinState.HIGH, 500);
                break;
            case "blackout":

                Output.Output(PinState.HIGH, 100);
                break;
            default:
                Console.print("Received invalid input from WebService!", MessageType.ERROR);
                break;
        }
    }
}
