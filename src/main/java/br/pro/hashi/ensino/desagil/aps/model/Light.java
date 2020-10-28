package br.pro.hashi.ensino.desagil.aps.model;

import java.awt.*;

//especifica um modelo para luzes indicadoras
//simulador "pluga" as portas lógicas nessas luzes para visualizar a saída
public class Light implements Receiver {
    private Color color; //atributo que define a cor
    private Emitter emitter; //

    public Light(int r, int g, int b) {
        color = new Color(r, g, b);
        emitter = null;
    }

    //ele ja me retorna qual a cor de saida
    public Color getColor() {
        if (emitter != null && emitter.read()) { //se emitter for diferente de null e emitter.read()=True eu retorno a cor definida
            return color; // indica saida = 1
        }
        return Color.BLACK; // se emitter=null ou emitter.read()=False eu retorno a cor preta -->indica saida = 0
    }

    //metodo usado para mudar a cor
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        this.emitter = emitter;
    }
    //como Switch esta diretamente relacionado com a saida, se eu receber um valor de inputIndex != 0 eu devo lançar uma exceção
    //se for = 0 eu apenas atribuo o emitter recebido como parametro ao meu atributo emitter definido em Light

    public boolean isOn(){
        return emitter.read();
    }
}
