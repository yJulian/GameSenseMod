package de.juliany.minesensefabric.client.minesense.mod.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.event.FloatEvent;

public class HealthEvent extends FloatEvent {

    @Override
    public String getEvent() {
        return "HEALTH";
    }

    public void update(float health) {
        if (value != health) {
            super.update(health);
        }
    }

    @Override
    public JsonObject getKeyboardHandler() {
        JsonObject object = new JsonObject();

        object.add("device-type", new JsonPrimitive("keyboard"));
        object.add("zone", new JsonPrimitive("number-keys"));
        object.add("mode", new JsonPrimitive("percent"));

        JsonObject gradient = new JsonObject();
        JsonObject zero = new JsonObject();
        zero.add("red", new JsonPrimitive(255));
        zero.add("green", new JsonPrimitive(0));
        zero.add("blue", new JsonPrimitive(0));
        JsonObject hundred = new JsonObject();
        hundred.add("red", new JsonPrimitive(0));
        hundred.add("green", new JsonPrimitive(255));
        hundred.add("blue", new JsonPrimitive(0));
        gradient.add("zero", zero);
        gradient.add("hundred", hundred);

        JsonObject color = new JsonObject();
        color.add("gradient", gradient);

        object.add("color", color);

        return object;
    }
}
