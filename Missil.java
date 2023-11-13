package br.ucsal.jogo;

public class Missil extends Sprite {

	private final int BOARD_WIDTH = 390;
	private final int MISSILE_SPEED = 2;

	public Missil(int x, int y) {
		super(x, y);
		initMissil();
	}
	private void initMissil() {
		carregarImagem("C:\\Users\\emanu\\pooa_av2\\imagens\\missil.png", 10, 10);
		getImageDimensions();
	}
	public void move() {
		x += MISSILE_SPEED;
		if (x > BOARD_WIDTH) {
			visible = false;
		}
	}
}