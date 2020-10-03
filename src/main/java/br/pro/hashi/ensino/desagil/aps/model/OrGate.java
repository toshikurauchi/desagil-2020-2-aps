package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate[] nands;

    public OrGate()
    {
        super("OrGate", 2);
        nands = new NandGate[3];
    }

    @Override
    public boolean read() {
        return nands[2].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nands[0].connect(0, emitter);
        nands[0].connect(1, emitter);
        nands[1].connect(0, emitter);
        nands[2].connect(1, emitter);

        nands[2].connect(0, nands[0]);
        nands[2].connect(1, nands[1]);
    }
}
