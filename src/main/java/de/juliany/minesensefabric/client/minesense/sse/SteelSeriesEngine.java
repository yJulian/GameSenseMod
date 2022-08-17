package de.juliany.minesensefabric.client.minesense.sse;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.event.Event;
import de.juliany.minesensefabric.client.minesense.sse.event.EventUpdater;
import de.juliany.minesensefabric.client.minesense.sse.heartbeat.SSEHeartBeat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class SteelSeriesEngine {

    private static SteelSeriesEngine instance;

    public static final String GAME_NAME = "MINE_SENSE";
    private static final String DISPLAY_NAME = "MineSense";
    private static final String DEVELOPER = "Juliany";
    private static final long SSE_TIMEOUT = 5000;

    private final ServerDiscovery discovery;

    private boolean registered;

    private SSEHeartBeat heartBeat;
    private EventUpdater eventUpdater;

    public static SteelSeriesEngine getInstance() {
        if (instance == null) {
            instance = new SteelSeriesEngine();
        }
        return instance;
    }

    private SteelSeriesEngine() {
        this.discovery = ServerDiscovery.discover();
        this.registered = false;
    }

    public void register() {
        if (registered) {
            throw new UnsupportedOperationException("Cannot register application twice!");
        }

        JsonObject registerRequest = new JsonObject();

        registerRequest.add("game", new JsonPrimitive(GAME_NAME));
        registerRequest.add("game_display_name", new JsonPrimitive(DISPLAY_NAME));
        registerRequest.add("developer", new JsonPrimitive(DEVELOPER));
        registerRequest.add("deinitialize_timer_length_ms", new JsonPrimitive(SSE_TIMEOUT));

        send("game_metadata", registerRequest);

        this.heartBeat = new SSEHeartBeat(SSE_TIMEOUT - 1000, this);
        this.heartBeat.start();

        this.eventUpdater = new EventUpdater(this);

        this.registered = true;
    }

    public void registerEvent(Event event) {
        this.eventUpdater.registerEvent(event);
    }

    public void stop() {
        JsonObject stopRequest = new JsonObject();

        stopRequest.add("game", new JsonPrimitive(GAME_NAME));

        send("stop_game", stopRequest);
    }

    public void remove() {
        JsonObject removeRequest = new JsonObject();

        removeRequest.add("game", new JsonPrimitive(GAME_NAME));

        send("remove_game", removeRequest);
    }

    public void send(String endpoint, Object obj) {
        try {
            URL url = new URL("http://%s/%s".formatted(discovery.getAddress(), endpoint));
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);

            byte[] out = obj.toString().getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
