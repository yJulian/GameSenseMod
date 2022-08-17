package de.juliany.minesensefabric.client;

import de.juliany.minesensefabric.client.minesense.SteelSeriesListener;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Environment(EnvType.CLIENT)
public class MineSenseFabricClient implements ClientModInitializer {

    private ScheduledExecutorService ses;

    @Override
    public void onInitializeClient() {
        SteelSeriesEngine engine = SteelSeriesEngine.getInstance();
        engine.remove();
        engine.register();
        SteelSeriesListener SSEListener = new SteelSeriesListener();

        ClientTickEvents.END_WORLD_TICK.register(new SteelSeriesListener());
    }
}
