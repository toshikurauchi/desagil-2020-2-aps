package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends FixedPanel implements MouseListener, ItemListener {

    private final int entradas; //vai me retornar o numero de entradas do meu objeto
    private final Switch[] switches;

    private final JCheckBox[] inBox; //inBox é uma lista de checkboxes
    private final Light outColor; //outColor é um sinal colorido

    private Color color;
    private final Image image;


    //construtor
    public GateView(Gate gate) {

        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.
        super(490, 350);

        //atribuo o parametro gate ao meu atributo
        // A ideia é que essa componente gráfica represente
        // um componente especifico. Esse componente que
        // está sendo representada é guardado como atributo.

        this.entradas = gate.getInputSize(); //vai me retornar o numero de entradas que eu tenho

        //Vou criar um vetor com o numero de pinos de entrada do gate
        //e armazenar uma nova checkbox em inbox[inputIndex]
        inBox = new JCheckBox[gate.getInputSize()];
        for (int i = 0; i < entradas; i++) {
            inBox[i] = new JCheckBox();
        }

        //crio uma saida de cor True default vermelha
        outColor = new Light(255, 0, 0);
        // Inicializamos o atributo de cor a partir da cor do sinal de saida
        color = outColor.getColor();


        //Vou criar um vetor com o numero de pinos de entrada do gate
        //e armazenar um novo switch em switches[inputIndex]
        //Lembrando que simulador "pluga" essas fontes especificadas pelo switch
        //nas portas lógicas para controlar a entrada (como vou fazer o update)
        //para isso preciso conectar ao emissor
        switches = new Switch[gate.getInputSize()];
        for (int i = 0; i < entradas; i++) {
            switches[i] = new Switch();
            gate.connect(i, switches[i]);
        }


        // Adicionando os componentes ao meu painel

        // Como subclasse de FixedPanel, agora podemos definir a
        // posição e o tamanho de cada componente ao adicioná-la.
        // Colocamos todas componentes aqui no contêiner.
        // o resto dos meus componentes serao desenhados em paintComponent

        //add inBox
        //for para add entrada conforme o numero de entradas na lista inBox
        for (int i = 0; i < entradas; i++) {
            if (entradas > 1) {
                add(inBox[i], 80, (int) (137 + 30* Math.pow(-1, i)), 25, 25);
            } else {
                add(inBox[i], 80, 137, 25, 25);
            }
        }

        //fazendo o carregamento da imagem
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        //atribuo o emitter gate recebido como parametro ao meu atributo emitter definido em Light que será a minha
        //conecto a minha saida ao sinal emtido
        outColor.connect(0, gate);

        // Um checkbox tem uma lista de observadores que
        // reagem quando o usuário clica.
        // Usamos o método addItemListener para adicionar esta
        // instância de GateView, ou seja "this", nessa
        // lista. Só que addItemListener espera receber um objeto
        // do tipo ItemListener como parâmetro. É por isso que
        // adicionamos o "implements ItemListener" lá em cima.
        //Aqui, vou addItemListener para cada uma das checkboxes de entrada
        for (int i = 0; i < entradas; i++) {
            inBox[i].addItemListener(this);
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
        update();
    }


    //Irá atualizar o valor do campo a partir do meu inBox
    public void update() {
        //loop que vai atualizar o sinal emitido nas portas lógicas
        for (int i = 0; i < entradas; i++) {
            //se a checkbow estiver selecionada signal=True, senao signal=False
            if (inBox[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        //com os sinais atualizados, posso recolorir meu outbox
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Descobre em qual posição o clique ocorreu.
        int x = e.getX();
        int y = e.getY();

        // Se o clique foi dentro do circulo colorido...
        //330, 137

        if (Math.pow((x-330-12.5), 2) + Math.pow((137+12.5-y), 2) <= Math.pow(12.5, 2) && outColor.isOn()) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color); //poderia lancar uma excecao se escolher preto
            outColor.setColor(color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
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
        g.drawImage(image, 40, 80, 400, 140, this);

        // Desenha um circulo cheio.
        g.setColor(outColor.getColor());
        g.fillOval(330, 137, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}