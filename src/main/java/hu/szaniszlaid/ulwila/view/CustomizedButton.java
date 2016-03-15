package hu.szaniszlaid.ulwila.view;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomizedButton extends JButton {
	
	private ImageIcon image;
	private ImageIcon imageRollover;
	private ImageIcon imagePressed;
	
	
	
	public CustomizedButton(URL imgURL, URL imgRolloverURL, URL imgPressedURL) {
		super();
		this.image = new ImageIcon(imgURL);
		this.imageRollover = new ImageIcon(imgRolloverURL);
		this.imagePressed = new ImageIcon(imgPressedURL);
		setIcon(image);
		
		setRolloverIcon(imageRollover);
		setPressedIcon(imagePressed);

		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
	}



	public static class CustomizedButtonBuilder {
		private URL imgURL;
		private URL imgRolloverURL;
		private URL imgPressedURL;
		
		public CustomizedButtonBuilder imgUrl(String imagePath) {
			this.imgURL = getClass().getResource(imagePath);
			return this;
		}
		
		public CustomizedButtonBuilder imgRolloverURL(String imageRolloverPath) {
			this.imgRolloverURL = getClass().getResource(imageRolloverPath);
			return this;
		}
		
		public CustomizedButtonBuilder imgPressedURL(String imagePressedPath) {
			this.imgPressedURL = getClass().getResource(imagePressedPath);
			return this;
		}
		
		public CustomizedButton create() {
			return new CustomizedButton(imgURL, imgRolloverURL, imgPressedURL);
		}
	}

}
