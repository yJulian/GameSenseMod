package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class FloatEvent extends BoundedEvent {

    protected float value;

    @Override
    public JsonObject getData() {
        JsonObject data = new JsonObject();

        data.add("value", new JsonPrimitive(value));

        return data;
    }

    public void update(float value) {
        setValue(value);
        update();
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
