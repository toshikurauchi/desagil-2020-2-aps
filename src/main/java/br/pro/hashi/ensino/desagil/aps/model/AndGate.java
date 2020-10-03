package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate[] nand;

    public AndGate() {
        super("AND", 2);

        nand = new NandGate[2];
    }

    @Override
    public boolean read() {
        return nand[1].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand[0].connect(0, emitter);
        nand[0].connect(1, emitter);
        nand[1].connect(0, nand[0]);
        nand[1].connect(1, nand[0]);
    }

}
