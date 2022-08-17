package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;

public class EventUpdater {

    private final SteelSeriesEngine sse;

    public EventUpdater(SteelSeriesEngine sse) {
        this.sse = sse;
    }


    public void registerEvent(Event event) {
        sse.send("register_game_event", event.getRegisterData());

        if (event instanceof Binding binding) {
            JsonObject data = event.getRegisterData();
            data.add("handlers", binding.getHandlers());
            sse.send("bind_game_event", data);
        }
    }

}
