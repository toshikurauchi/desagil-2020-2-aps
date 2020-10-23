package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {

    private final Gate gate;
    private final JCheckBox[] entradasField;
    private final JCheckBox saidaField;
    private final Switch[] switches;

    // Adição dos novos atributos
    private Color color;
    private final Image image;

    public GateView(Gate gate) {
        // Largura e altura da janela fixas
        super(245, 346);

        this.gate = gate;

        //inputs
        entradasField = new JCheckBox[gate.getInputSize()];
        switches = new Switch[gate.getInputSize()];
        saidaField = new JCheckBox();

        //labels
        JLabel entradaLabel = new JLabel("Entrada");
        JLabel saidaLabel = new JLabel("Saida");

        // add(entradaLabel);

        // posição e o tamanho de cada componente
        add(entradaLabel, 10, 10, 75, 25);


        for (int i = 0; i < entradasField.length; i++) {
            switches[i] = new Switch();
            gate.connect(i, switches[i]);

            entradasField[i] = new JCheckBox();
            add(entradasField[i], 85, 10, 150, 25);
            entradasField[i].addActionListener(this);
        }
        // padrão layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        add(saidaLabel, 10, 311, 75, 25);
        add(saidaField, 85, 311, 120, 25);

        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        saidaField.setEnabled(false);

        addMouseListener(this);
        update();
    }

    private void update() {
        for (int i = 0; i < entradasField.length; i++) {
            if (entradasField[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        boolean estadoOutput = gate.read();
        saidaField.setSelected(estadoOutput);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 80, 221, 221, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(210, 311, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}

    // Implementar checkbox no lugar .. A classe JTextField representa um campo de texto.

