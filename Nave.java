package br.ucsal.jogo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Nave extends Sprite {
	private int dx;
	private int dy;
	private ArrayList<Missil> missiles;

	public Nave(int x, int y) {
		super(x, y);
		initNave();
	}

	private void initNave() {
		missiles = new ArrayList<Missil>();
		carregarImagem("imagens/nave.png", 100, 100 );
		getImageDimensions();
	}

	public void move() {
		x += dx;
		y += dy;
	}

	public ArrayList<Missil> getMissiles() {
		return missiles;
	}

	public void atira() {
		missiles.add(new Missil(x + width, y + height / 2));
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			atira();
		}
		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -1;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}
