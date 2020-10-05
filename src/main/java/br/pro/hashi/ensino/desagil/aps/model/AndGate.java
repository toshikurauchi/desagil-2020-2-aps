package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;

    protected AndGate() {
        super("And", 2);
        this.nand = new NandGate();
    }

    @Override
    public boolean read() {
        return !(nand.read());
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        nand.connect(inputIndex, emitter);
    }
}
