package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

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
    private final Image image;
    // A classe JCheckBox representa uma checkbox.
    //checkboxes de entrada
    private final JCheckBox[] inBox; //in_Box é uma lista
    private final Light out_Box;
    private Color color_on;
    private Color color_off;

    //construtor
    public GateView(Gate gate) {
        super(490, 350);
        this.gate = gate;
        this.entradas = gate.getInputSize();
        // inicializacao checkbox
        inBox = new JCheckBox[gate.getInputSize()];
        for (int i = 0; i < entradas; i++) {
            inBox[i] = new JCheckBox();
        }//crio uma checkbox para cada entrada
        out_Box = new Light(0, 0, 0);//crio uma saida de cor vermelha


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
//        add(inLabel);
        //for para add entrada conforme o numero de entradas na lista inBox
        for (int i = 0; i < entradas; i++) {
            if (entradas > 1) {
                add(inBox[i], 20, (int) (140 + 45 * Math.pow(-1, i)), 25, 25);
            } else {
                add(inBox[i], 20, (int) (140), 25, 25);
            }
        }
//        add(outLabel);
        //add(out_Box,445, 135,50,50);

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        out_Box.connect(0, gate);
        color_off = Color.BLACK;
        color_on = Color.RED;
        // Um campo de texto tem uma lista de observadores que
        // reagem quando o usuário clica.
        // Usamos o método addActionListener para adicionar esta
        // instância de GateView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ItemListener" lá em cima.
        for (int i = 0; i < entradas; i++) {
            inBox[i].addItemListener(this);
        }

        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.
        //outBox.setEnabled(false);

        if ((gate instanceof NotGate) || (gate instanceof NandGate)) {
            out_Box.setColor(color_on);
            repaint();
        }

        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
        addMouseListener(this);

        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        //update();
    }

    // link para ajudar-->https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    public void itemStateChanged(ItemEvent e) {

        Switch[] signal = new Switch[gate.getInputSize()];
        for (int i = 0; i < entradas; i++) {
            signal[i] = new Switch();
        }

        for (int i = 0; i < entradas; i++) {
            if (inBox[i].isSelected()) {
                signal[i].turnOn();
            } else {
                signal[i].turnOff();
            }
            gate.connect(i, signal[i]);
        }
        if (gate.read()) {
            out_Box.setColor(color_on);
        } else {
            out_Box.setColor((color_off));
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Descobre em qual posição o clique ocorreu.
        int x = e.getX();
        int y = e.getY();

        //445, 300, 25, 25
        // Se o clique foi dentro do quadrado colorido...
        //Math.pow((x - 247), 2) + Math.pow((y - 105), 2) <= 225
        //x >= 445 && x < 470 && y >= 140 && y < 165
        if (Math.pow((445 - x), 2) + Math.pow((140 - y), 2) <= Math.pow(25, 2) && out_Box.getColor()!= Color.BLACK) {

            // ...então abrimos a janela seletora de cor...
            color_on = JColorChooser.showDialog(this, null, color_on);
            out_Box.setColor(color_on);
            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
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

        // Desenha um circulo cheio.
        g.setColor(out_Box.getColor());
        g.fillOval(445, 140, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
//    private void update() {}

}