package de.juliany.minesensefabric.client.minesense.mod.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.event.StringEvent;
import de.juliany.minesensefabric.util.LanguageUtil;
import net.minecraft.util.Identifier;

public class BiomeEvent extends StringEvent {

    private Identifier identifier;

    public void update(Identifier identifier){
        if (this.identifier != identifier) {
            this.identifier = identifier;
            update(LanguageUtil.translate("biome", identifier));
        }
    }

    @Override
    public JsonObject getOledHandler() {
        JsonObject req = new JsonObject();

        req.add("device-type", new JsonPrimitive("screened"));
        req.add("zone", new JsonPrimitive("one"));
        req.add("mode", new JsonPrimitive("screen"));

        JsonArray datas = new JsonArray();

        JsonObject data = new JsonObject();
        data.add("has-text", new JsonPrimitive(true));
        data.add("prefix", new JsonPrimitive("Biome: "));

        datas.add(data);

        req.add("datas", datas);

        return req;
    }

    @Override
    public String getEvent() {
        return "BIOME";
    }
}
