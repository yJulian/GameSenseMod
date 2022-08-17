package de.juliany.minesensefabric.client.minesense.sse;

import com.google.gson.JsonObject;

import java.io.IOException;

public abstract class ServerDiscovery {

    String getAddress() {
        try {
            return getData().get("address").getAsString();
        } catch (IOException e) {
            return null;
        }
    }

    protected abstract JsonObject getData() throws IOException;

    public static ServerDiscovery discover() {
        return new WindowsServerDiscovery();
    }


}
