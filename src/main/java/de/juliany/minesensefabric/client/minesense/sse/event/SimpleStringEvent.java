package de.juliany.minesensefabric.client.minesense.sse.event;

public class SimpleStringEvent extends StringEvent {

    private final String event;

    public SimpleStringEvent(String event) {
        this.event = event;
    }

    @Override
    public String getEvent() {
        return this.event;
    }

}
