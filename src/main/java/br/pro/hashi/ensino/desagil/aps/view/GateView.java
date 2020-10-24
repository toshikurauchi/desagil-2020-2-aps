package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {

    private final JCheckBox[] entradasField;
    private final Light saidaField;
    private final Switch[] switches;
    private final Image image;
    // Adição dos novos atributos
    private Color color;

    public GateView(Gate gate) {
        // Largura e altura da janela fixas
        super();

        //inputs
        entradasField = new JCheckBox[gate.getInputSize()];
        switches = new Switch[gate.getInputSize()];
        saidaField = new Light(255, 0, 0);

        for (int i = 0; i < entradasField.length; i++) {
            switches[i] = new Switch();
            gate.connect(i, switches[i]);

            entradasField[i] = new JCheckBox();
            entradasField[i].addActionListener(this);
        }

        if (entradasField.length > 1) {
            add(entradasField[0], 40, 48);
            add(entradasField[1], 40, 48 + 76);
        } else {
            add(entradasField[0], 40, 85);
        }

        saidaField.connect(0, gate);

        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

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
        repaint();
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
        if (x >= 360 && x < 380 && y >= 90 && y < 110) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);
            saidaField.setColor(color);
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
        g.drawImage(image, 60, 20, 300, 160, this);

        // Desenha um circulo cheio.
        g.setColor(saidaField.getColor());
        g.fillOval(360, 90, 20, 20);

        // g.fillRect(210, 311, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}

// Implementar checkbox no lugar .. A classe JTextField representa um campo de texto.

