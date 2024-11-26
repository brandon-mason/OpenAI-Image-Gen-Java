package ui.panels;

import java.awt.*;

import javax.swing.*;

import java.net.MalformedURLException;
import java.net.URL;

public class GenImagePanel extends JPanel {
    private ImageIcon genImage;

    public void startLoading() {
        try {
            // Load gif from resources directory
            URL loaderUrl = getClass().getClassLoader().getResource("resources/Loader.gif");
            if (loaderUrl == null) {
                throw new IllegalArgumentException("Gif not found in resources");
            }
            this.genImage = new ImageIcon(loaderUrl);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            if(url == null) {
                throw new MalformedURLException("URL does not point to an image.");
            }
            this.genImage = new ImageIcon(url);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (genImage != null) {
            g.drawImage(genImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
