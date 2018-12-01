package com.wemanity.kata.tdd.bejeweledlike.view;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.DiamondColor;
import com.wemanity.kata.tdd.bejeweledlike.rules.GameBoardRules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameView extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private final GameBoardRules gameBoardRules;
    private final int gridSize;

    private int selectedX = -1;
    private int selectedY = -1;
    private int swappedX = -1;
    private int swappedY = -1;

    Image buffer;

    public GameView(int size) {
        gameBoardRules = new GameBoardRules(size);
        gridSize = size;
    }

    /**
     * Main loop
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (!gameBoardRules.fill()) {
                gameBoardRules.removeAlignments();
            }
            rePaint();
        }
    }

    /**
     * Initialization: mouse events and main loop
     */
    public void init() {
        new Thread(this).start();
        // fill the grid for the first time
        while (gameBoardRules.fill());
        while (gameBoardRules.removeAlignments()) {
            gameBoardRules.fill();
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Display routine: double buffering
     */
    @Override
    public void paint(Graphics g2) {

        super.paint(g2);

        if (buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, 32 * gridSize, 32 * gridSize); //getWidth(), getHeight());

        // afficher la grille vide
        g.setColor(Color.BLACK);
        for (int i = 0; i < gridSize + 1; i++) {
            g.drawLine(32 * i + 1, 1, 32 * i + 1, gridSize * 32 + 1 + 1);
            g.drawLine(1, 32 * i + 1, gridSize * 32 + 1 + 1, 32 * i + 1);
        }

        // afficher la première case sélectionnée
        if (selectedX != -1 && selectedY != -1) {
            g.setColor(Color.ORANGE);
            g.fillRect(selectedX * 32 + 1 + 1, selectedY * 32 + 1 + 1, 31, 31);
        }

        // afficher la deuxième case sélectionnée
        if (swappedX != -1 && swappedY != -1) {
            g.setColor(Color.YELLOW);
            g.fillRect(swappedX * 32 + 1 + 1, swappedY * 32 + 1 + 1, 31, 31);
        }

        // afficher le contenu de la grille
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                final int boxValue = gameBoardRules.getGameBoard().getValue(new Coordinates(i, j));
                final Color color = DiamondColor.getColor(boxValue);
                g.setColor(color);
                g.fillOval(32 * i + 3 + 1, 32 * j + 3 + 1, 27, 27);
            }
        }
        g2.drawImage(buffer, 0, 0, null);
    }

    /**
     * Avoid flickering
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void rePaint() {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // press the mouse button: retrieve the coordinates of the first box
        selectedX = (e.getX() / 32);
        selectedY = (e.getY() / 32);
        System.out.println("selectedX " + selectedX + " selectedY " + selectedY);
        rePaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (selectedX != -1 && selectedY != -1) {
            swappedX = (e.getX() / 32);
            swappedY = (e.getY() / 32);
            // if the exchange is invalid, we hide the second box
            if (!(gameBoardRules.isValidSwap(new Coordinates(selectedX, selectedY), new Coordinates(swappedX, swappedY)))) {
                swappedX = swappedY = -1;
            }
        }
        rePaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //when you release the mouse you have to make the exchange and hide the boxes
        if (selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
            gameBoardRules.swap(new Coordinates(selectedX, selectedY), new Coordinates(swappedX, swappedY));
        }
        selectedX = selectedY = swappedX = swappedY = -1;
        rePaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}
