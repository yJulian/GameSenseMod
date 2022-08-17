package de.juliany.minesensefabric.client.minesense.sse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WindowsServerDiscovery extends ServerDiscovery {

    private static final String PATH = System.getenv("PROGRAMDATA") + "/SteelSeries/SteelSeries Engine 3/coreProps.json";

    @Override
    protected JsonObject getData() throws IOException {
        return JsonParser.parseString(Files.readString(new File(PATH).toPath())).getAsJsonObject();
    }
}
