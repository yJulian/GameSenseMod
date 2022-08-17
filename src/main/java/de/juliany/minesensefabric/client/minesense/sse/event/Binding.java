package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonArray;

public interface Binding {

    JsonArray getHandlers();

}
