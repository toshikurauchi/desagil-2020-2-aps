package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nand0In;
    private final NandGate nand1In;
    private final NandGate nandOut;

    //construtor
    //devemos criar os nands e conecta-los
    public OrGate() {
        super("OR", 2);
        nand0In = new NandGate();
        nand1In = new NandGate();
        nandOut = new NandGate();

        nandOut.connect(0, nand0In);
        nandOut.connect(1, nand1In);
    }

    @Override
    public boolean read() {
        return nandOut.read();
    }


    // switch--> cada case pega o valor que está sendo passado no switch e compara
    // com cada um dos casos que estão em sua estrutura
    //The switch expression is evaluated once.
    //The value of the expression is compared with the values of each case.
    //If there is a match, the associated block of code is executed.
    //If there is no match, the default code block is executed.
    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0: //caso o index seja 0
                nand0In.connect(0, emitter);
                nand0In.connect(1, emitter);
                break;
            case 1: //caso o index seja 1
                nand1In.connect(0, emitter);
                nand1In.connect(1, emitter);
                break;
            default:
                //se nao for nenhum nem outro ta fora do range
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}
