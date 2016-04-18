package hu.szaniszlaid.ulwila.view;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomizedButton extends JButton {

	private ImageIcon image;
	private ImageIcon imageRollover;
	private ImageIcon imagePressed;

	private CustomizedButton(URL imgURL, URL imgRolloverURL, URL imgPressedURL, String Tooltip) {
		super();
		if (imgURL != null) {
			this.image = new ImageIcon(imgURL);
			setIcon(image);
			
			setBorderPainted(false);
			setFocusPainted(false);
			setContentAreaFilled(false);
			setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		}
		if (imgRolloverURL != null) {
			this.imageRollover = new ImageIcon(imgRolloverURL);
			setRolloverIcon(imageRollover);
		}
		if (imgPressedURL != null) {
			this.imagePressed = new ImageIcon(imgPressedURL);
			setPressedIcon(imagePressed);

		}
		if (Tooltip != null) {
			setToolTipText(Tooltip);
		}


	}

	public static class CustomizedButtonBuilder {
		private URL imgURL;
		private URL imgRolloverURL;
		private URL imgPressedURL;
		private String toolTip;

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
		
		public CustomizedButtonBuilder toolTip(String toolTip) {
			this.toolTip = toolTip;
			return this;
		}

		public CustomizedButton create() {
			return new CustomizedButton(imgURL, imgRolloverURL, imgPressedURL, toolTip);
		}
	}

}
