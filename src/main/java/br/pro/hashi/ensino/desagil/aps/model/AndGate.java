package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nandIn;
    private final NandGate nandOut;

    public AndGate() {
        super("AND", 2);

        nandIn = new NandGate();
        nandOut = new NandGate();

        //conectando o nandin no nandout
        nandOut.connect(0, nandIn);
        nandOut.connect(1, nandIn);
    }

    @Override
    public boolean read() {
        return nandOut.read();
    }

    // switch--> cada case pega o valor que está sendo passado no switch e compara
    // com cada um dos casos que estão em sua estrutura
    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:
                nandIn.connect(0, emitter);
                break;
            case 1:
                nandIn.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}
