package model;

import java.awt.Color;
import java.util.ArrayList;

public class Field {

    private static Field single_instance = null; //singleton
    private static ArrayList<ArrayList<Cell>> cellMap;
    private static int actualSize = 100;
    public WorldType worldType = WorldType.FINITE;

    private Field(int s) {
        actualSize = s;
        Field.cellMap = new ArrayList<>();
        for (int i = 0; i < actualSize; i++) {
            ArrayList<Cell> tmp = new ArrayList<>();
            for (int j = 0; j < actualSize; j++) {
                tmp.add(new Cell());
            }
            cellMap.add(tmp);
        }
    }

    public static Field getInstance(int s) {
        if (single_instance == null) {
            single_instance = new Field(s);
        }
        return single_instance;
    }
    
    public void resetField() {      
        for (int i = 0; i < actualSize; i++) {        
            for (int j = 0; j < actualSize; j++) {
                cellMap.get(i).get(j).resetCell();
            }     
        }
    }

    public static void rebuildField() {
        Field.cellMap = new ArrayList<>();
        for (int i = 0; i < actualSize; i++) {
            ArrayList<Cell> tmp = new ArrayList<>();
            for (int j = 0; j < actualSize; j++) {
                tmp.add(new Cell());
            }
            cellMap.add(tmp);
        }
    }
 
    public int changeCellState(int x, int y, int newState) {
        cellMap.get(x).get(y).setState(newState);
        return 0;
    }

    public int getCellState(int x, int y) {
        if (!areCoordinateValid(x, y)) {
            return -1;
        }
        return cellMap.get(x).get(y).getState();
    }

    public Color getCellColor(int x, int y) {
        return cellMap.get(x).get(y).getColor();
    }

    public boolean areCoordinateValid(int x, int y) {
        return x < cellMap.size() && x >= 0 && y < cellMap.size() && y >= 0;
    }

    public Color[][] getCellMapRepresentation() {
        Color[][] grid = new Color[cellMap.size()][cellMap.get(0).size()];
        for (int i = 0; i < cellMap.size(); i++) {
            for (int j = 0; j < cellMap.get(0).size(); j++) {
                grid[i][j] = cellMap.get(i).get(j).getColor();
            }
        }
        return grid;
    }

    public int getDimension() {
        return cellMap.size();
    }

    public static int getActualSize() {
        return actualSize;
    }

    public static void setActualSize(int actualSize) {
        Field.actualSize = actualSize;
        rebuildField();
    }

    public WorldType getWorldType() {
        return worldType;
    }

    public void setWorldType(WorldType worldType) {
        this.worldType = worldType;
    }

    @Override
    public String toString() {
        return "Field{" + "worldType=" + worldType + '}';
    }

}
