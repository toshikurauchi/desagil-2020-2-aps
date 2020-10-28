package br.pro.hashi.ensino.desagil.aps.model;

public class NandGate extends Gate {
    private final Emitter[] emitters; //esse vetor irá armazenar os emissores plugados na porta

    public NandGate() {
        super("NAND", 2);

        emitters = new Emitter[2]; //estou criando um vetor e nao emissores em si com tamanho correspondente ao inputsize da minha porta
    }


    //deve definir esse método para cumprir o contrato estabelecido por Emitter
    //lê o sinal recebido em cada pino de entrada
    // usa os operadores lógicos ! e && para fazer, literalmente, um "not and" sobre esses sinais
    // um sinal é simplesmente false se não há emissor "plugado" no pino.
    @Override
    public boolean read() {
        boolean a = (emitters[0] != null && emitters[0].read());
        boolean b = (emitters[1] != null && emitters[1].read());
        return !(a && b);
    }


    //deve definir esse método para cumprir o contrato estabelecido por Receiver
    //inputIndex só pode ser 0 ou 1, pois inputSize = 2.
    // Se outro valor for recebido, o método lança uma IndexOutOfBoundsException
    // Senão, simplesmente armazena emitter[inputIndex] em emitters.
    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        emitters[inputIndex] = emitter;
    }
}
