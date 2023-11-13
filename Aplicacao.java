package br.ucsal.jogo;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Aplicacao extends JFrame {

	private static final long serialVersionUID = 1L;

	public Aplicacao() {
		initUI();
	}

	private void initUI() {
		add(new Jogo());
		pack();
		setTitle("Joguinho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Aplicacao aplicacao = new Aplicacao();
				aplicacao.setVisible(true);
			}
		});
	}
}
