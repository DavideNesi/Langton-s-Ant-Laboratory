package view;

import javax.swing.*;
import model.SimulationUniverse;

final public class FieldDisplayAreaWrapper extends JPanel {

    FieldDisplayArea fieldDisplayArea;
    int defaultSize = 800; //default display area dimensions
    SimulationUniverse su;
    int xPanelOffset = 0;
    int yPanelOffset = 0;

    public FieldDisplayAreaWrapper(SimulationUniverse sun, int size) {
        setLayout(null);
        su = sun;
        defaultSize = size;
        fieldDisplayArea = new FieldDisplayArea(sun, defaultSize);
        add(fieldDisplayArea);
        centerFieldInPanel();
    }

    public FieldDisplayAreaWrapper(SimulationUniverse sun) {
        setLayout(null);
        su = sun;
        fieldDisplayArea = new FieldDisplayArea(sun, defaultSize);
        add(fieldDisplayArea);
        centerFieldInPanel();
    }

    public void centerFieldInPanel() {
        int coord = 0;
        if (su.getField().getDimension() < defaultSize / fieldDisplayArea.getSingleCellDimension()) {
            coord = (defaultSize / 2) - (fieldDisplayArea.getSingleCellDimension() * su.getField().getDimension()) / 2;
        }
        fieldDisplayArea.setBounds(coord + xPanelOffset+fieldDisplayArea.getSingleCellDimension()/2, coord + yPanelOffset+fieldDisplayArea.getSingleCellDimension()/2, fieldDisplayArea.getSingleCellDimension() * su.getField().getDimension() + 1, fieldDisplayArea.getSingleCellDimension() * su.getField().getDimension() + 1);
    }

    public void updateFieldDisplayArea() {
        fieldDisplayArea.calculateBaseCellDimension();
        fieldDisplayArea.repaint();
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    public SimulationUniverse getSu() {
        return su;
    }

    public int getyOffset() {
        return fieldDisplayArea.getyOffset();
    }

    public void setyOffset(int yOffset) {
        fieldDisplayArea.setyOffset(yOffset);
    }

    public int getxOffset() {
        return fieldDisplayArea.getxOffset();
    }

    public void setxOffset(int xOffset) {
        fieldDisplayArea.setxOffset(xOffset);
    }

    public int getCellSizeOffest() {
        return fieldDisplayArea.getCellSizeOffest();
    }

    public void setCellSizeOffest(int cellSizeOffest) {
        fieldDisplayArea.setCellSizeOffest(cellSizeOffest);
    }

    public int getBaseCellDimension() {
        return fieldDisplayArea.calculateBaseCellDimension();
    }

    public int getCellDimension() {
        return fieldDisplayArea.getSingleCellDimension();
    }

    public void setDrawCellFrameRule(int drawCellFrameRule) {
        fieldDisplayArea.setDrawCellFrameRule(drawCellFrameRule);
    }

    public int getDrawCellFrameRule() {
        return fieldDisplayArea.getDrawCellFrameRule();
    }

    public int getMode() {
        return fieldDisplayArea.getMode();
    }

    public void setMode(int mode) {
        fieldDisplayArea.setMode(mode);
    }

}
