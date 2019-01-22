
package model;

import java.awt.Color;

public class Cell {

    private int state; //state of the cell
    private static final Color colorScheme[] = {Color.WHITE, //color sequence
        Color.BLACK, Color.BLUE, Color.green, 
        Color.magenta, Color.cyan, Color.RED, 
        Color.lightGray, Color.ORANGE, Color.PINK, Color.YELLOW};

    public Cell() {
        this.state = 0;
    }
    
    public void resetCell(){
        this.state = 0;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Color getColor() {
        return colorScheme[state];
    }
    
}
