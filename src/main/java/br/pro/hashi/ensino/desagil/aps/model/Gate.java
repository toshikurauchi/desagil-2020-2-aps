package br.pro.hashi.ensino.desagil.aps.model;

//apenas especifica um modelo genérico para portas lógicas
//emissor e receptor
//deixo para as subclasses de Gate honrarem o contrato com as interfaces
public abstract class Gate implements Emitter, Receiver {
    private final String name; //representa o nome
    private final int inputSize; // representa a quantidadede pinos de entradas

    protected Gate(String name, int inputSize) {
        this.name = name;
        this.inputSize = inputSize;
    }

    // Esse toString é um método especial que o Java usa quando
    // precisa de uma representação textual do objeto. Por exemplo,
    // quando você passa o objeto para o System.out.println. Uma
    // implementação padrão existe, mas aqui estamos reescrevendo.
    public String toString() {
        return name;
    }

    //retorna o numero de entradas do gate
    public int getInputSize() {
        return inputSize;
    }
}
