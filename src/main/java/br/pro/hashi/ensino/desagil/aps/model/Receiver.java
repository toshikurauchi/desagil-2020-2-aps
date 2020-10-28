package br.pro.hashi.ensino.desagil.aps.model;

//representa qualquer elemento do simulador que pode receber um sinal digital
public interface Receiver {
    //Receiver tbm é uma interface, logo ela não declara atributos nem métodos
    //Define o método connect
    void connect(int inputIndex, Emitter emitter);
    //connect --> irá "plugar" um objeto emissor responsável por um sinal recebido
    //recebe um valor int correspondente ao pino em que seráplugado e
    // o objeto emissor que será plugado (correspondente ao sinal emitido por cada uma das entradas)
}
