package de.juliany.minesensefabric.client.minesense.sse.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.juliany.minesensefabric.client.minesense.sse.SteelSeriesEngine;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumericValueEvent<T extends Number> extends Event {

    private final String event;
    private T value;

    private T minValue;
    private T maxValue;

    private int iconId = 0;
    private boolean valueOptional = false;

    private final DecimalFormat format = new DecimalFormat("#.##");

    public NumericValueEvent(String event, T initialValue) {
        this.event = event;
        this.value = initialValue;
    }

    public NumericValueEvent(String event, T initialValue, T minValue, T maxValue) {
        this.event = event;
        this.value = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public JsonObject getData() {
        JsonObject data = new JsonObject();

        data.add("value", new JsonPrimitive(format.format(getCurrentValue())));

        return data;
    }

    @Override
    public JsonObject getRegisterData() {
        JsonObject request = new JsonObject();

        request.add("game", new JsonPrimitive(SteelSeriesEngine.GAME_NAME));
        request.add("event", new JsonPrimitive(getEvent()));

        if (minValue != null)
            request.add("min_value", new JsonPrimitive(getMinValue()));
        if (maxValue != null)
            request.add("max_value", new JsonPrimitive(getMaxValue()));

        request.add("icon_id", new JsonPrimitive(iconId));
        request.add("value_optional", new JsonPrimitive(valueOptional));

        return request;
    }

    public int getIcon() {
        return iconId;
    }

    public boolean isOptional() {
        return valueOptional;
    }

    public T getCurrentValue() {
        return value;
    }

    public final T getMinValue() {
        return minValue;
    }

    public final T getMaxValue() {
        return maxValue;
    }

    public void update(T value) {
        this.value = value;
    }

}
