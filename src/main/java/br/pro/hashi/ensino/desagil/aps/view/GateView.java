package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.NandGate;
import br.pro.hashi.ensino.desagil.aps.model.NotGate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends JPanel implements ItemListener, ActionListener {

    // A ideia é que essa componente gráfica represente
    // um componente especifico. Esse componente que
    // está sendo representada é guardado como atributo.
    private final Gate gate;

    // A classe JTextField representa uma checkbox.
    //checkboxes de entrada
    private final JCheckBox[] in_Box;
    //checkbox de saida
    private final JCheckBox outBox;

    //construtor
    public GateView(Gate gate) {
        this.gate = gate;
        // inicializacao checkbox
        in_Box = new JCheckBox[gate.getInputSize()];
        for (int i = 0;i < gate.getInputSize();i ++){in_Box[i] = new JCheckBox(); }
        outBox = new JCheckBox();



        // A classe JLabel representa um rótulo, ou seja,
        // um texto não-editável que queremos colocar na
        // interface para identificar alguma coisa. Não
        // precisa ser atributo, pois não precisamos mais
        // mexer nesses objetos depois de criar e adicionar.
        JLabel inLabel = new JLabel("Entrada:");
        JLabel outLabel = new JLabel("Saída:");


        // Um JPanel tem um layout, ou seja, um padrão para
        // organizar as componentes dentro dele. A linha abaixo
        // estabelece um dos padrões mais simples: simplesmente
        // colocar uma componente debaixo da outra, alinhadas.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Colocamos todas componentes aqui no contêiner.
        add(inLabel);
        for (int i = 0;i< gate.getInputSize();i++){add(in_Box[i]);}
        add(outLabel);
        add(outBox);

        // Um campo de texto tem uma lista de observadores que
        // reagem quando o usuário dá Enter depois de digitar.
        // Usamos o método addActionListener para adicionar esta
        // instância de GateView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ItemListener" lá em cima.
        for (int i = 0;i< gate.getInputSize();i++){ in_Box[i].addItemListener(this); }

        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.
        outBox.setEnabled(false);
        if ((gate instanceof NotGate) || (gate instanceof NandGate)  ){
            outBox.setSelected(true);
        }
        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        //update();
    }

    //ajeitar essa parte, nao consegui desenvolver
    // link para ajudar-->https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    public void itemStateChanged(ItemEvent e) {

        Switch[] signal = new Switch[gate.getInputSize()];
        for (int i = 0;i < gate.getInputSize();i ++){signal[i] = new Switch();}

        for (int i = 0;i < gate.getInputSize();i ++){
            if (in_Box[i].isSelected()) {signal[i].turnOn();}
            else {signal[i].turnOff();}
        }

        for (int i = 0;i < gate.getInputSize();i ++){
            gate.connect(i,signal[i]);
        }

        outBox.setSelected(gate.read());
//        gate.connect(1,signal1);
//        System.out.println(signal0.read());
//        System.out.println(signal1.read());
//        System.out.println(gate.read());
//        System.out.println(" ");
//        update();
    }
    @Override
    public void actionPerformed(ActionEvent event) {
//        update();
    }

//    private void update() {}

}