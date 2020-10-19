package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private final JCheckBox in0Box;
    private final JCheckBox in1Box;
    //checkbox de saida
    private final JCheckBox outBox;

    //construtor
    public GateView(Gate gate) {
        this.gate = gate;

        // inicializacao checkbox
        in0Box = new JCheckBox();
        in1Box = new JCheckBox();
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
        add(in0Box);
        add(in1Box);
        add(outLabel);
        add(outBox);

        // Um campo de texto tem uma lista de observadores que
        // reagem quando o usuário dá Enter depois de digitar.
        // Usamos o método addActionListener para adicionar esta
        // instância de GateView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ItemListener" lá em cima.
        in0Box.addItemListener(this);
        in1Box.addItemListener(this);

        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.
        outBox.setEnabled(false);

        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        //update();
    }

    //ajeitar essa parte, nao consegui desenvolver
    // link para ajudar-->https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    public void itemStateChanged(ItemEvent e) {

        Switch signal0 = new Switch();
        Switch signal1 = new Switch();
        Object source = e.getItemSelectable();

        if (source == in0Box) {
            signal0.turnOn();
        }
        else if (source == in1Box){signal1.turnOn();}

        if (e.getStateChange() == ItemEvent.DESELECTED){
            if (source == in0Box) { signal0.turnOff();            }
            else if (source == in1Box){signal1.turnOff();}
        }

        
//        update();
    }
//    @Override
//    public void actionPerformed(ActionEvent event) {
//        update();
//    }

    private void update() {
        boolean sig1;
        boolean sig2;

    }

}