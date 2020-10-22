package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.NandGate;
import br.pro.hashi.ensino.desagil.aps.model.NotGate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.LinkedList;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends FixedPanel implements MouseListener, ItemListener {

    // A ideia é que essa componente gráfica represente
    // um componente especifico. Esse componente que
    // está sendo representada é guardado como atributo.
    private final Gate gate;
    private final int entradas;
    private Color color;
    private final Image image;
    // A classe JTextField representa uma checkbox.
    //checkboxes de entrada
    private final JCheckBox[] in_Box;
    private final JCheckBox outBox;

    //construtor
    public GateView(Gate gate) {
        super(490, 350);
        this.gate = gate;
        this.entradas =  gate.getInputSize();
        // inicializacao checkbox
        in_Box = new JCheckBox[gate.getInputSize()];
        for (int i = 0;i < entradas;i ++){in_Box[i] = new JCheckBox(); }
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
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Colocamos todas componentes aqui no contêiner.
        add(inLabel);
        for (int i = 0;i< entradas;i++){
            add(in_Box[i], 20, (int) (140 + 45* Math.pow(-1,i)), 25, 25);
        }
        add(outLabel);
        add(outBox,445, 135,50,50);

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);
        color = Color.BLACK;
        // Um campo de texto tem uma lista de observadores que
        // reagem quando o usuário dá Enter depois de digitar.
        // Usamos o método addActionListener para adicionar esta
        // instância de GateView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ItemListener" lá em cima.
        for (int i = 0;i< entradas;i++){in_Box[i].addItemListener( this); }

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
        for (int i = 0;i < entradas;i ++){signal[i] = new Switch();}

        for (int i = 0;i < entradas;i ++){
            if (in_Box[i].isSelected()) {signal[i].turnOn();}
            else {signal[i].turnOff();}
            gate.connect(i,signal[i]);
        }

        outBox.setSelected(gate.read());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 40, 40, 400, 221, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(445, 200, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
//    private void update() {}

}