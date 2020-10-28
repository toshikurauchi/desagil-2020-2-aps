package br.pro.hashi.ensino.desagil.aps.model;

//representa qualquer elemento do simulador que pode emitir um sinal digital
public interface Emitter {
    //Emitter é uma interface, logo ela não declara atributos nem métodos
    //especificam o que um objeto faz, não o que ele é; (não declara atributo)
    //especificam o que um objeto faz, não como ele faz. (assinatura sem implementação)

    boolean read(); //Estou definindo a assinatura ("casca") do método read()
    //quem implementa Emitter é que deve definir o método
    //Neste caso as subclasses de gatr que deverao definir este método
    //O métod read() irá devolver um booleano correspondente ao sinal emitido
}
