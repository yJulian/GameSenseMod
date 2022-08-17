package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class IntegerEvent extends BoundedEvent {
    protected int value;

    @Override
    public JsonObject getData() {
        JsonObject data = new JsonObject();

        data.add("value", new JsonPrimitive(value));

        return data;
    }

    public void update(int value) {
        if (this.value != value) {
            setValue(value);
            update();
            System.out.println("update");
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
