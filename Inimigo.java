package br.ucsal.jogo;

public class Inimigo extends Sprite {
	private final int INITIAL_X = 400;

	public Inimigo(int x, int y) {
		super(x, y);
		initInimigo();
	}

	private void initInimigo() {
		carregarImagem("C:\\Users\\emanu\\pooa_av2\\imagens\\alien.png", 10, 10);
		getImageDimensions();
	}

	public void move() {
		if (x < 0) {
			x = INITIAL_X;
		}
		x -= 1;
	}
}
