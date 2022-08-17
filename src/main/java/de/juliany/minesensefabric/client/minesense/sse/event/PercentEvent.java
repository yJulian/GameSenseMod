package de.juliany.minesensefabric.client.minesense.sse.event;

public abstract class PercentEvent extends IntegerEvent {

    @Override
    public void setValue(int value) {
        if (value < 0 || value > 100)
            throw new IllegalArgumentException("Not a percent value.");
        super.setValue(value);
    }
}
