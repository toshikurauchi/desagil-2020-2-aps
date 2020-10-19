package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nandIn;
    private final NandGate nand0Mid;
    private final NandGate nand1Mid;
    private final NandGate nandOut;

    //construtor
    public XorGate() {
        super("XOR", 2);
        nandIn = new NandGate();
        nand0Mid = new NandGate();
        nand1Mid = new NandGate();
        nandOut = new NandGate();


        nand0Mid.connect(1,nandIn);
        nand1Mid.connect(0,nandIn);
        nandOut.connect(0,nand0Mid);
        nandOut.connect(1,nand1Mid);
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
            case 0: //caso o index seja 0
                //When JavaScript reaches a break keyword, it breaks out of the switch block.
                nand0Mid.connect(0, emitter);
                nandIn.connect(0, emitter);
                break;
            case 1: //caso o index seja 1
                nand1Mid.connect(1, emitter);
                nandIn.connect(1, emitter);
                break;
            default:
                //se nao for nenhum nem outro ta fora do range
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}

