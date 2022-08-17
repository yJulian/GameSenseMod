package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class StringEvent extends BoundedEvent {

    protected String value;

    @Override
    public JsonObject getData() {
        JsonObject data = new JsonObject();

        data.add("value", new JsonPrimitive(value));

        return data;
    }

    public void update(String value) {
        setValue(value);
        update();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
