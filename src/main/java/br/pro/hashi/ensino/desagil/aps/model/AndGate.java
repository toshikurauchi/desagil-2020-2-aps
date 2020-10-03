package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate[] nands;

    public AndGate() {
        super("AND", 2);

        nands = new NandGate[2];
        for(int i=0;i<nands.length;i++){
            nands[i] = new NandGate();
        }
    }

    @Override
    public boolean read() {
        return nands[1].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nands[0].connect(inputIndex, emitter);
        nands[1].connect(inputIndex, nands[0]);
    }

}