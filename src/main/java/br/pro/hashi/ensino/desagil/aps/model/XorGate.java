package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nand;
    //construtor
    public XorGate() {
        super("XOR", 2);
        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return !(!(nand.read()&&nand.read()) && nand.read()); //Or = not Nand
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        nand.connect(inputIndex, emitter);
    }
}
}
