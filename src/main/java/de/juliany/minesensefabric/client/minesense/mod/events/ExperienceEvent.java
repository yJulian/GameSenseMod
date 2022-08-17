package de.juliany.minesensefabric.client.minesense.mod.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.event.PercentEvent;

public class ExperienceEvent extends PercentEvent {
    @Override
    public String getEvent() {
        return "EXPERIENCE";
    }

    public void update(int experienceProgress) {
        super.update(experienceProgress);
    }

    @Override
    public JsonObject getKeyboardHandler() {
        JsonObject object = new JsonObject();

        object.add("device-type", new JsonPrimitive("keyboard"));
        object.add("zone", new JsonPrimitive("function-keys"));
        object.add("mode", new JsonPrimitive("percent"));

        JsonObject yellow = new JsonObject();
        yellow.add("red", new JsonPrimitive(255));
        yellow.add("green", new JsonPrimitive(255));
        yellow.add("blue", new JsonPrimitive(0));

        JsonObject color = new JsonObject();

        object.add("color", yellow);

        return object;
    }
}
