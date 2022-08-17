package de.juliany.minesensefabric.client.minesense;

import de.juliany.minesensefabric.client.minesense.mod.events.BiomeEvent;
import de.juliany.minesensefabric.client.minesense.mod.events.ExperienceEvent;
import de.juliany.minesensefabric.client.minesense.mod.events.HealthEvent;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;

public class SteelSeriesListener implements ClientTickEvents.EndWorldTick {

    private final BiomeEvent biomeEvent;
    private final HealthEvent healthEvent;
    private final ExperienceEvent experienceEvent;

    public SteelSeriesListener() {
        SteelSeriesEngine.getInstance().registerEvent(this.biomeEvent = new BiomeEvent());
        SteelSeriesEngine.getInstance().registerEvent(this.healthEvent = new HealthEvent());
        SteelSeriesEngine.getInstance().registerEvent(this.experienceEvent = new ExperienceEvent());
    }

    @Override
    public void onEndTick(ClientWorld world) {
        Entity entity = MinecraftClient.getInstance().getCameraEntity();

        if (entity == null)
            return;

        // Health update
        if (entity instanceof LivingEntity livingEntity) {
            this.healthEvent.update(livingEntity.getHealth() / livingEntity.getMaxHealth() * 100);

            if (entity instanceof ClientPlayerEntity player) {
                this.experienceEvent.update((int) (player.experienceProgress*100));
            }
        }

        // Biome update
        RegistryEntry<Biome> biomeEntry = world.getBiome(entity.getCameraBlockPos());
        if (biomeEntry.getKey().isPresent()) {
            biomeEvent.update(biomeEntry.getKey().get().getValue());
        }
    }
}
