package br.pro.hashi.ensino.desagil.aps.model;

public class NotGate extends Gate {
    private final NandGate nand;

    public NotGate() {
        super("NOT", 1);

        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return nand.read();
    }


    //inputIndex só pode ser 0, pois inputSize = 1
    // Se outro valor for recebido, o método lança uma IndexOutOfBoundsException
    // Senão, simplesmente chamo o metodo connect da instancia de nand para conectar o parametro input index ao parametro emitter
    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand.connect(0, emitter);
        nand.connect(1, emitter);
    }
}
