package br.pro.hashi.ensino.desagil.aps.model;

//especifica um modelo para fontes ligáveis e desligáveis
//sao apenas emissores
//simulador "pluga" essas fontes (signal emitido) nas portas lógicas para controlar a entrada
public class Switch implements Emitter {
    private boolean signal;

    public Switch() {
        signal = false; //signal default é false
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