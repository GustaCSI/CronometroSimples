package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro extends JFrame {

    private JLabel tempoLabel;
    private JButton startBtn, pauseBtn, resetBtn;

    private Timer timer;
    private int centesimos = 0;

    public Cronometro() {
        setTitle("Cronômetro Simples");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo preto e fonte branca
        JPanel painel = new JPanel();
        painel.setBackground(Color.BLACK);
        painel.setLayout(new BorderLayout());

        tempoLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        tempoLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        tempoLabel.setForeground(Color.WHITE);
        painel.add(tempoLabel, BorderLayout.CENTER);

        JPanel botoesPainel = new JPanel();
        botoesPainel.setBackground(Color.BLACK);

        startBtn = new JButton("Start");
        pauseBtn = new JButton("Pause");
        resetBtn = new JButton("Reset");

        botoesPainel.add(startBtn);
        botoesPainel.add(pauseBtn);
        botoesPainel.add(resetBtn);

        painel.add(botoesPainel, BorderLayout.SOUTH);

        add(painel);

        // Timer disparando a cada 10ms (centésimos de segundo)
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centesimos++;
                atualizarLabel();
            }
        });

        startBtn.addActionListener(e -> timer.start());

        pauseBtn.addActionListener(e -> timer.stop());

        resetBtn.addActionListener(e -> {
            timer.stop();
            centesimos = 0;
            atualizarLabel();
        });
    }

    private void atualizarLabel() {
        int minutos = (centesimos / 100) / 60;
        int segundos = (centesimos / 100) % 60;
        int cent = centesimos % 100;

        String texto = String.format("%02d:%02d:%02d", minutos, segundos, cent);
        tempoLabel.setText(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Cronometro().setVisible(true);
        });
    }
}
