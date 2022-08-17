package de.juliany.minesensefabric.client.minesense.sse.heartbeat;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;

public class SSEHeartBeat extends Thread {

    private final long timeout;
    private final SteelSeriesEngine api;
    private boolean running = true;
    private boolean alive = true;

    public SSEHeartBeat(long timeout, SteelSeriesEngine engine) {
        this.timeout = timeout;
        this.api = engine;

        this.setDaemon(true);
        this.setName("SteelSeries HeartBeat");
    }

    public void setRunning(boolean running) {
        this.running = running;

        if (running && !alive) {
            start();
        }
    }

    @Override
    public void run() {
        try {
            alive = true;
            while (running) {
                JsonObject heartBeatPacket = new JsonObject();

                heartBeatPacket.add("game", new JsonPrimitive(SteelSeriesEngine.GAME_NAME));

                this.api.send("game_heartbeat", heartBeatPacket);

                Thread.sleep(this.timeout);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            alive = false;
        }
    }
}
