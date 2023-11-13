package br.ucsal.jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Sprite {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}

	protected void carregarImagem(String imageName, int width, int height) {
		try{
			Image imagem  = ImageIO.read(new File(imageName));


			Image imagemRedimensionada = imagem.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon novaImagem = new ImageIcon(imagemRedimensionada);
			image = novaImagem.getImage();

		}catch (Exception e){
			e.printStackTrace();
		}


	}

	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
