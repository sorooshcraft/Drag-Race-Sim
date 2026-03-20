package ui;

import model.Car;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VisualPanel extends JPanel {
    private BufferedImage splashImage;
    private BufferedImage car1Img;
    private BufferedImage car2Img;

    private boolean isRacing;
    private Timer raceTimer;
    private double car1X;
    private double car2X;
    private String name1;
    private String name2;
    private Runnable onFinishCallback;

    private double elapsedTime;
    private double visualDuration1;
    private double visualDuration2;
    private int countdownStep;

    private final int startX = 100;
    private final int countdownDuration = 2000;
    private int finishX;

    public VisualPanel() {
        setBackground(Color.DARK_GRAY);
        splashImage = loadSingleImage("splash.jpg");
    }

    public void stopRaceEarly() {
        if (raceTimer != null) {
            raceTimer.stop();
        }
        isRacing = false;
        repaint();
    }

    public void animateRace(Car c1, String i1, double t1, Car c2, String i2, double t2, Runnable onFin) {
        setupAnimationVariables(c1, t1, c2, t2, onFin);
        loadCarImages(i1, i2);
        startRaceTimer();
    }

    private void loadCarImages(String path1, String path2) {
        this.car1Img = loadSingleImage(path1);
        this.car2Img = loadSingleImage(path2);
    }

    private BufferedImage loadSingleImage(String filename) {
        if (filename == null || filename.equals("None (Fallback)")) {
            return null;
        }
        try {
            return ImageIO.read(new File("./data/" + filename));
        } catch (Exception e) {
            return null;
        }
    }

    private void setupAnimationVariables(Car c1, double t1, Car c2, double t2, Runnable onFin) {
        this.name1 = c1.getName();
        this.name2 = (c2 != null) ? c2.getName() : null;
        this.onFinishCallback = onFin;
        this.finishX = getWidth() - 100;
        this.elapsedTime = 0;
        this.countdownStep = 0;
        this.car1X = startX;
        this.car2X = startX;
        this.visualDuration1 = t1 * 1000.0;
        this.visualDuration2 = (t2 > 0) ? t2 * 1000.0 : 0.0;
    }

    private void startRaceTimer() {
        isRacing = true;
        if (raceTimer != null && raceTimer.isRunning()) {
            raceTimer.stop();
        }
        raceTimer = new Timer(16, e -> handleRaceTick(16));
        raceTimer.start();
    }

    private void handleRaceTick(int tickRate) {
        elapsedTime += tickRate;
        if (elapsedTime < countdownDuration) {
            countdownStep = (int) (elapsedTime / 500);
        } else {
            countdownStep = 4;
            updateCarPositions();
        }
        repaint();
    }

    private void updateCarPositions() {
        double raceTime = elapsedTime - countdownDuration;
        double t1 = Math.min(1.0, raceTime / visualDuration1);
        double t2 = (visualDuration2 > 0) ? Math.min(1.0, raceTime / visualDuration2) : 1.0;

        car1X = startX + (finishX - startX) * (t1 * t1);
        if (name2 != null) {
            car2X = startX + (finishX - startX) * (t2 * t2);
        }

        if (t1 >= 1.0 && (name2 == null || t2 >= 1.0)) {
            raceTimer.stop();
            isRacing = false;
            if (onFinishCallback != null) {
                onFinishCallback.run();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isRacing) {
            drawTrack(g);
            drawChristmasTree(g);
            drawCar(g, (int) car1X, 60, Color.RED, name1, car1Img);
            if (name2 != null) {
                drawCar(g, (int) car2X, 150, Color.CYAN, name2, car2Img);
            }
            drawCountdownOverlay(g);
        } else if (splashImage != null) {
            drawSplash(g);
        } else {
            drawFallbackSplash(g);
        }
    }

    private void drawCountdownOverlay(Graphics g) {
        String text = getOverlayText();
        if (text.isEmpty()) {
            return;
        }

        g.setFont(new Font("Arial", Font.BOLD, 120));
        FontMetrics fm = g.getFontMetrics();
        int tx = (getWidth() - fm.stringWidth(text)) / 2;
        int ty = (getHeight() + fm.getAscent()) / 2 - 50;

        g.setColor(text.equals("GO!") ? Color.GREEN : Color.YELLOW);
        g.drawString(text, tx, ty);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }

    private String getOverlayText() {
        if (countdownStep == 1) {
            return "3";
        }
        if (countdownStep == 2) {
            return "2";
        }
        if (countdownStep == 3) {
            return "1";
        }
        if (countdownStep == 4 && elapsedTime - countdownDuration < 1000) {
            return "GO!";
        }
        return "";
    }

    private void drawChristmasTree(Graphics g) {
        int tx = startX - 60;
        g.setColor(Color.BLACK);
        g.fillRect(tx, 30, 30, 180);

        drawLight(g, tx + 5, 40, Color.WHITE, countdownStep >= 0);
        drawLight(g, tx + 5, 75, Color.YELLOW, countdownStep >= 1);
        drawLight(g, tx + 5, 110, Color.YELLOW, countdownStep >= 2);
        drawLight(g, tx + 5, 145, Color.YELLOW, countdownStep >= 3);
        drawLight(g, tx + 5, 180, Color.GREEN, countdownStep >= 4);
    }

    private void drawLight(Graphics g, int x, int y, Color c, boolean isOn) {
        g.setColor(isOn ? c : Color.DARK_GRAY);
        g.fillOval(x, y, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
    }

    private void drawTrack(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(startX + 65, 0, startX + 65, getHeight());

        int fx = getWidth() - 100;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(fx + 65, 0, fx + 65, getHeight());
        g2.setStroke(new BasicStroke(1));

        if (name2 != null) {
            for (int i = 0; i < getWidth(); i += 40) {
                g.drawLine(i, getHeight() / 2, i + 20, getHeight() / 2);
            }
        }
    }

    private void drawCar(Graphics g, int x, int y, Color c, String name, BufferedImage img) {
        if (img != null) {
            int maxWidth = 100;
            int maxHeight = 50;
            double widthScale = (double) maxWidth / img.getWidth();
            double heightScale = (double) maxHeight / img.getHeight();
            double scale = Math.min(widthScale, heightScale);
            int drawWidth = (int) (img.getWidth() * scale);
            int drawHeight = (int) (img.getHeight() * scale);
            int drawY = y - (drawHeight / 2);
            g.drawImage(img, x, drawY, drawWidth, drawHeight, null);
        } else {
            drawFallbackGraphic(g, x, y, c);
        }
        g.setColor(Color.WHITE);
        g.drawString(name, x, y - 30);
    }

    private void drawFallbackGraphic(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 70, 20);
        g.fillRect(x + 15, y - 10, 35, 10);
        g.setColor(Color.BLACK);
        g.fillOval(x + 5, y + 10, 20, 20);
        g.fillOval(x + 45, y + 10, 20, 20);
    }

    private void drawSplash(Graphics g) {
        if (splashImage == null) {
            return;
        }
        double scale = Math.min((double) getWidth() / splashImage.getWidth(),
                (double) getHeight() / splashImage.getHeight());
        int sw = (int) (splashImage.getWidth() * scale);
        int sh = (int) (splashImage.getHeight() * scale);
        int sx = (getWidth() - sw) / 2;
        int sy = (getHeight() - sh) / 2;
        g.drawImage(splashImage, sx, sy, sw, sh, this);
    }

    private void drawFallbackSplash(Graphics g) {
        int cx = getWidth() / 2 - 100;
        int cy = getHeight() / 2;
        drawFallbackGraphic(g, cx, cy, Color.RED);
        g.setColor(Color.WHITE);
        g.drawString("Add splash.jpg to ./data/ for custom image!", 10, 20);
    }
}