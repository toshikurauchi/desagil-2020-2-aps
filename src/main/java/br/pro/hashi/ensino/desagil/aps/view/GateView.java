package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    private final Gate gate;
    private final JCheckBox[] entradasField;
    private final JCheckBox saidaField;

    public GateView(Gate gate) {
        this.gate = gate;
        //inputs
        entradasField = new JCheckBox[gate.getInputSize()];
        saidaField = new JCheckBox();
        //labels
        JLabel entradaLabel = new JLabel("Entrada");
        JLabel saidaLabel = new JLabel("Saida");

        add(entradaLabel);

        for (int i = 0; i < entradasField.length; i++) {
            entradasField[i] = new JCheckBox();
            add(entradasField[i]);
            entradasField[i].addActionListener(this);
        }
        // padrão layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(saidaLabel);
        add(saidaField);

        saidaField.setEnabled(false);

        update();
    }

    private void update() {
        for (int i = 0; i < entradasField.length; i++) {
            Switch switchEntrada = new Switch();
            if (entradasField[i].isSelected()) {
                switchEntrada.turnOn();
            } else {
                switchEntrada.turnOff();
            }
            gate.connect(i, switchEntrada);
        }
        boolean estadoOutput = gate.read();
        saidaField.setSelected(estadoOutput);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        update();
    }

    // Implementar checkbox no lugar .. A classe JTextField representa um campo de texto.

}
