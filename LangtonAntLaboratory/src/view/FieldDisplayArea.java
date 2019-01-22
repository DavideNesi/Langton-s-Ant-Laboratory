package view;

import javax.swing.*;
import java.awt.*;
import javafx.scene.shape.Circle;
import model.Ant;
import model.SimulationUniverse;

final class FieldDisplayArea extends JPanel {

    private int singleCellDimension;
    private SimulationUniverse su;
    private int simulationPanelSize;

    private int yOffset = 0;
    private int xOffset = 0;
    private int cellSizeOffest = 0;

    private int drawCellFrameRule = 1;

    private int mode = 0;
    private static final Color colorScheme1[] = {new Color(2, 153, 84), new Color(2, 153, 84), new Color(242, 245, 0), new Color(242, 245, 0), new Color(1, 1, 1), new Color(1, 1, 1), new Color(242, 245, 0), new Color(242, 245, 0), new Color(2, 153, 84), new Color(2, 153, 84)};
    private static final Color colorScheme2[] = {new Color(247, 0, 0), new Color(247, 0, 0), new Color(247, 148, 0), new Color(247, 148, 0), new Color(247, 247, 0), new Color(247, 247, 0), new Color(0, 148, 0), new Color(0, 148, 0), new Color(0, 0, 247), new Color(0, 0, 247), new Color(0, 198, 148), new Color(0, 198, 148)};

    FieldDisplayArea(SimulationUniverse s, int sps) {
        su = s;
        simulationPanelSize = sps;
        calculateCellDimension();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculateCellDimension();
        Graphics2D g2 = (Graphics2D) g;
        drawFieldAndAnts(g2);
        setBackground(new Color(225, 226, 225));
    }

    public void drawFieldAndAnts(Graphics2D g2) {

        //Draw all field squares
        for (int i = 0; i < su.getField().getDimension(); i++) {
            for (int j = 0; j < su.getField().getDimension(); j++) {

                if (su.getField().getCellState(i, j) != 0) {
                    g2.setColor(su.getField().getCellColor(i, j));
                } else {
                    switch (mode) {
                        case 1:
                            g2.setColor(colorScheme1[(i + j + su.getTicks()) % colorScheme1.length]);
                            break;
                        case 2:
                            g2.setColor(colorScheme2[(i + j + su.getTicks()) % colorScheme2.length]);
                            break;
                        default:
                            g2.setColor(su.getField().getCellColor(i, j));
                    }
                }
                Rectangle rectangle = new Rectangle(xOffset + i * singleCellDimension, yOffset + j * singleCellDimension, singleCellDimension, singleCellDimension);
                g2.fill(rectangle);

                if (drawCellFraming()) {
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.draw(rectangle);
                }
            }
        }

        //draw all ants
        for (int i = 0; i < su.getAntsCount(); i++) {
            Ant a = su.getSpecificAnt(i);
            int antXpos = a.getX() * singleCellDimension;
            int antYpos = a.getY() * singleCellDimension;
            Rectangle rectangle = new Rectangle(xOffset + antXpos, yOffset + antYpos, singleCellDimension, singleCellDimension);
            g2.setColor(a.getColor());
            g2.fill(rectangle);
            if (drawCellFraming()) {
                g2.setColor(Color.GRAY);
            }
            if (drawCellFraming()) {
                g2.draw(rectangle);
            }

            g2.setColor(Color.BLACK);
            Polygon triangle = new Polygon(new int[]{antXpos + (singleCellDimension / 2) + 1, antXpos, antXpos + singleCellDimension}, new int[]{antYpos + singleCellDimension, antYpos + (singleCellDimension / 2), antYpos + (singleCellDimension / 2)}, 3);

            //Draw simple ant representation (needed for orientation)
            if (singleCellDimension > 5) {
                switch (a.getOrientation()) {
                    case 0:
                        g2.drawLine(antXpos + (singleCellDimension / 2) + 1 + xOffset, antYpos + yOffset, antXpos + (singleCellDimension / 2) + 1 + xOffset, antYpos + singleCellDimension + yOffset);
                        triangle = new Polygon(new int[]{xOffset + antXpos + (singleCellDimension / 2) + 1, xOffset + antXpos, xOffset + antXpos + singleCellDimension}, new int[]{yOffset + antYpos, yOffset + antYpos + (singleCellDimension / 2), yOffset + antYpos + (singleCellDimension / 2)}, 3);
                        break;
                    case 1:
                        g2.drawLine(xOffset + antXpos, yOffset + antYpos + (singleCellDimension / 2) + 1, xOffset + antXpos + singleCellDimension, yOffset + antYpos + (singleCellDimension / 2) + 1);
                        triangle = new Polygon(new int[]{xOffset + antXpos + (singleCellDimension / 2) + 1, xOffset + antXpos + singleCellDimension, xOffset + antXpos + (singleCellDimension / 2) + 1}, new int[]{yOffset + antYpos, yOffset + antYpos + singleCellDimension / 2 + 1, yOffset + antYpos + singleCellDimension}, 3);
                        break;
                    case 2:
                        g2.drawLine(xOffset + antXpos + (singleCellDimension / 2) + 1, yOffset + antYpos, xOffset + antXpos + (singleCellDimension / 2) + 1, yOffset + antYpos + singleCellDimension);
                        triangle = new Polygon(new int[]{xOffset + antXpos + (singleCellDimension / 2) + 1, xOffset + antXpos, xOffset + antXpos + singleCellDimension}, new int[]{yOffset + antYpos + singleCellDimension, yOffset + antYpos + (singleCellDimension / 2), yOffset + antYpos + (singleCellDimension / 2)}, 3);
                        break;
                    case 3:
                        g2.drawLine(xOffset + antXpos, yOffset + antYpos + (singleCellDimension / 2) + 1, xOffset + antXpos + singleCellDimension, yOffset + antYpos + (singleCellDimension / 2) + 1);
                        triangle = new Polygon(new int[]{xOffset + antXpos + (singleCellDimension / 2) + 1, xOffset + antXpos, xOffset + antXpos + (singleCellDimension / 2) + 1}, new int[]{yOffset + antYpos, yOffset + antYpos + singleCellDimension / 2 + 1, yOffset + antYpos + singleCellDimension}, 3);
                        break;
                    default:
                        break;
                }
                g2.fill(triangle);
            }

            //Draw ant selection circle
            if (a.isHighlighted()) {
                g2.setColor(Color.RED);
                g2.drawOval(antXpos - singleCellDimension + xOffset, antYpos - singleCellDimension + yOffset, 3 * singleCellDimension, 3 * singleCellDimension);
            }
        }
    }

    public void calculateCellDimension() {
        singleCellDimension = (int) Math.floor((double) simulationPanelSize / su.getField().getDimension());
        singleCellDimension += cellSizeOffest;
    }

    public int calculateBaseCellDimension() {
        int tmp = (int) Math.floor((double) simulationPanelSize / su.getField().getDimension());
        return tmp;
    }

    public int getSingleCellDimension() {
        return singleCellDimension;
    }

    public void setSingleCellDimension(int singleCellDimension) {
        this.singleCellDimension = singleCellDimension;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getCellSizeOffest() {
        return cellSizeOffest;
    }

    public void setCellSizeOffest(int cellSizeOffest) {
        this.cellSizeOffest = cellSizeOffest;
    }

    public boolean drawCellFraming() {
        switch (drawCellFrameRule) {
            case 0:
                return true;
            case 1:
                return singleCellDimension >= 6;
            default:
                return false;
        }
    }

    public int getDrawCellFrameRule() {
        return drawCellFrameRule;
    }

    public void setDrawCellFrameRule(int drawCellFrameRule) {
        this.drawCellFrameRule = drawCellFrameRule;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
