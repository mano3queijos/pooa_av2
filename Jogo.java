package br.ucsal.jogo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Jogo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Nave nave;
	private ArrayList<Inimigo> aliens;
	private boolean ingame;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 400;
	private final int B_HEIGHT = 300;
	private final int DELAY = 15;

	private final int[][] pos = {
			{2380, 29}, {2500, 59}, {1380, 89},
			{780, 109}, {580, 139}, {680, 239},
			{790, 259}, {760, 50}, {790, 150},
			{980, 209}, {560, 45}, {510, 70},
			{930, 159}, {590, 80}, {530, 60},
			{940, 59}, {990, 30}, {920, 200},
			{900, 259}, {660, 50}, {540, 90},
			{810, 220}, {860, 20}, {740, 180},
			{820, 128}, {490, 170}, {700, 30}
	};

	public Jogo() {
		initJogo();
	}

	private void initJogo() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		nave = new Nave(ICRAFT_X, ICRAFT_Y);
		initAliens();
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void initAliens() {
		aliens = new ArrayList<>();
		for (int[] p : pos) {
			aliens.add(new Inimigo(p[0], p[1]));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (ingame) {
			drawObjects(g);
		} else {
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void drawObjects(Graphics g) {
		if (nave.isVisible()) {
			g.drawImage(nave.getImage(), nave.getX(), nave.getY(),
					this);
		}
		ArrayList<Missil> ms = nave.getMissiles();
		for (Missil m : ms) {
			if (m.isVisible()) {
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}
		for (Inimigo a : aliens) {
			if (a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}
		g.setColor(Color.WHITE);
		g.drawString("Inimigos restantes: " + aliens.size(), 5, 15);
	}

	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		updateNave();
		updateMissils();
		updateInimigos();
		checkCollisions();
		repaint();
	}

	private void inGame() {
		if (!ingame) {
			timer.stop();
		}
	}

	private void updateNave() {
		if (nave.isVisible()) {
			nave.move();
		}
	}

	private void updateMissils() {
		ArrayList<Missil> ms = nave.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missil m = ms.get(i);

			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}

	private void updateInimigos() {
		if (aliens.isEmpty()) {
			ingame = false;
			return;
		}

		for (int i = 0; i < aliens.size(); i++) {
			Inimigo a = aliens.get(i);
			if (a.isVisible()) {
				a.move();
			} else {
				aliens.remove(i);
			}
		}
	}

	public void checkCollisions() {
		Rectangle r3 = nave.getBounds();
		for (Inimigo alien : aliens) {
			Rectangle r2 = alien.getBounds();
			if (r3.intersects(r2)) {
				nave.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		ArrayList<Missil> ms = nave.getMissiles();
		for (Missil m : ms) {
			Rectangle r1 = m.getBounds();
			for (Inimigo alien : aliens) {
				Rectangle r2 = alien.getBounds();
				if (r1.intersects(r2)) {
					m.setVisible(false);

					alien.setVisible(false);
				}
			}
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}
	}
}

