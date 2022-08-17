package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class BoundedEvent extends Event implements Binding {

    private final JsonArray elements = new JsonArray();

    public final void addHandler(JsonObject obj) {
        if (obj != null)
            this.elements.add(obj);
    }

    public BoundedEvent() {
        registerAdditionalHandlers();

        addHandler(getOledHandler());
        addHandler(getKeyboardHandler());
        addHandler(getHeadsetHandler());
    }

    /**
     * Method to override to register new handlers
     */
    public void registerAdditionalHandlers() {

    }

    public JsonArray getHandlers() {
        return elements;
    }

    public JsonObject getOledHandler() {
        return null;
    }

    public JsonObject getKeyboardHandler() {
        return null;
    }

    public JsonObject getHeadsetHandler() {
        return null;
    }

}
