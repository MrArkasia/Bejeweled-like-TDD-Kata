package com.wemanity.kata.tdd.bejeweledlike.view;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.DiamondColor;
import com.wemanity.kata.tdd.bejeweledlike.models.Score;
import com.wemanity.kata.tdd.bejeweledlike.rules.GameBoardRules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameView extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private static final int BOX_SIZE = 32;

    private final GameBoardRules gameBoardRules;
    private final int nbBox;

    private int selectedX = -1;
    private int selectedY = -1;
    private int swappedX = -1;
    private int swappedY = -1;

    Image buffer;

    public GameView(int size) {
        this.gameBoardRules = new GameBoardRules(size);
        this.nbBox = size;
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
        while (gameBoardRules.fill()) ;
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

        if (buffer == null) buffer = createImage(nbBox * BOX_SIZE + 5, nbBox * BOX_SIZE + 25);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // background
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, BOX_SIZE * nbBox, BOX_SIZE * nbBox); //getWidth(), getHeight());

        // display the empty grid
        g.setColor(Color.BLACK);
        for (int i = 0; i < nbBox + 1; i++) {
            g.drawLine(BOX_SIZE * i + 1, 1, BOX_SIZE * i + 1, nbBox * BOX_SIZE + 1 + 1);
            g.drawLine(1, BOX_SIZE * i + 1, nbBox * BOX_SIZE + 1 + 1, BOX_SIZE * i + 1);
        }

        // show the first box selected
        if (selectedX != -1 && selectedY != -1) {
            g.setColor(Color.ORANGE);
            g.fillRect(selectedX * BOX_SIZE + 1 + 1, selectedY * BOX_SIZE + 1 + 1, BOX_SIZE - 1, BOX_SIZE - 1);
        }

        // display the second selected box
        if (swappedX != -1 && swappedY != -1) {
            g.setColor(Color.YELLOW);
            g.fillRect(swappedX * BOX_SIZE + 1 + 1, swappedY * BOX_SIZE + 1 + 1, BOX_SIZE - 1, BOX_SIZE - 1);
        }

        // display the contents of the grid
        for (int i = 0; i < nbBox; i++) {
            for (int j = 0; j < nbBox; j++) {
                final int boxValue = gameBoardRules.getGameBoard().getValue(new Coordinates(i, j));
                final Color color = DiamondColor.getColor(boxValue);
                g.setColor(color);
                g.fillOval(BOX_SIZE * i + 3 + 1, BOX_SIZE * j + 3 + 1, 27, 27);
            }
        }
        g2.drawImage(buffer, 0, 0, null);
        g2.drawString("Score : " + Score.getInstance().getValue(), 5, nbBox * BOX_SIZE + 15);
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
        selectedX = (e.getX() / BOX_SIZE);
        selectedY = (e.getY() / BOX_SIZE);
        System.out.println("selectedX " + selectedX + " selectedY " + selectedY);
        rePaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (selectedX != -1 && selectedY != -1) {
            swappedX = (e.getX() / BOX_SIZE);
            swappedY = (e.getY() / BOX_SIZE);
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
