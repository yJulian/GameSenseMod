package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;

import java.util.Objects;

public abstract class Event {

    public abstract String getEvent();
    public abstract JsonObject getData();

    public JsonObject getRegisterData() {
        JsonObject request = new JsonObject();

        request.add("game", new JsonPrimitive(SteelSeriesEngine.GAME_NAME));
        request.add("event", new JsonPrimitive(getEvent()));

        return request;
    }

    public void update() {
        JsonObject request = new JsonObject();

        request.add("game", new JsonPrimitive(SteelSeriesEngine.GAME_NAME));
        request.add("event", new JsonPrimitive(getEvent()));
        request.add("data", getData());

        SteelSeriesEngine.getInstance().send("game_event", request.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEvent());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event event))
            return false;

        return event.getEvent().equals(getEvent());
    }
}
