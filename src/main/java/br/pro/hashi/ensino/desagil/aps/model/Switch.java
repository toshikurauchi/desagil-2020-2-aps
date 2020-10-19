package br.pro.hashi.ensino.desagil.aps.model;

public class Switch implements Emitter {
    private boolean signal;

    public Switch() {
        signal = false;
    }

    //será chamado quando a checkbox correspondente for marcada
    public void turnOn() {
        signal = true;
    }

    //vai ser chamado quando a checkbox correspondente for desmarcada.
    public void turnOff() {
        signal = false;
    }

    @Override
    public boolean read() {
        return signal;
    }
}