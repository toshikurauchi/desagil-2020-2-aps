package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nand;

    //construtor
    public OrGate() {
        super("OR", 2);
        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return !(nand.read()); //Or = not Nand
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        nand.connect(inputIndex, emitter);
    }
}
