package model;

import java.awt.Color;
import java.security.SecureRandom;

public class Ant {

    private int orientation; //0=up, 1=right, 2=down, 3=left  //actual orientation
    private int x,y; //actual position
    private int startingX, startingY, startingOrientation; //starting position and orientation 
    private Field assignedField; //field where ants live, the field the ant can modify
    private String DNA; 
    private int speed; //number of steps the ant can make in a universe tick time frame
    private int movementLength; //number of cells the ant can trave in a single step
    private int antColorIndex; //ant color
    private boolean insideField; //if ant is able to join the field and start to move
    private String name;
    private static Color colorScheme[] = {Color.RED, Color.GREEN, Color.YELLOW, Color.pink, Color.MAGENTA, Color.CYAN, Color.BLUE};
    private int antBehaviour; //0dna,1random 
    private boolean highlighted = true; //true only if ant is selected

    public Ant(Field f, int xi, int yi, String dna, String n) {
        DNA = dna;
        startingOrientation = orientation = 0;
        startingX = x = xi;
        startingY = y = yi;
        assignedField = f;
        speed = 1;
        movementLength = 1;
        antColorIndex = 0;
        insideField = true;
        name = n;
        antBehaviour = 0;
    }

    public Ant(Field f, int xi, int yi, String dna, String n, boolean live) {
        DNA = dna;
        startingOrientation = orientation = 0;
        startingX = x = xi;
        startingY = y = yi;
        assignedField = f;
        speed = 1;
        movementLength = 1;
        antColorIndex = 0;
        insideField = live;
        name = n;
        antBehaviour = 0;
    }

    public Ant(Field f, int xi, int yi, String n) {
        DNA = "LR";
        startingOrientation = orientation = 0;
        startingX = x = xi;
        startingY = y = yi;
        assignedField = f;
        speed = 1;
        movementLength = 1;
        antColorIndex = 0;
        insideField = true;
        name = n;
        antBehaviour = 0;
    }

    public void resetPosition() {
        orientation = startingOrientation;
        x = startingX;
        y = startingY;
    }

    public boolean move() {
        if (insideField) {
            int currentCellState = assignedField.getCellState(x, y);
            boolean movementResult;
            int counter = speed;
            while (counter > 0) {
                if (antBehaviour == 0) {
                    int normalizedCellState = currentCellState % DNA.length();
                    char operationToPerform = DNA.charAt(normalizedCellState);
                    switch (operationToPerform) {
                        case 'L':
                            assignedField.changeCellState(x, y, (currentCellState + 1) % DNA.length());
                            turnLeft();
                            break;
                        case 'R':
                            assignedField.changeCellState(x, y, (currentCellState + 1) % DNA.length());
                            turnRight();
                            break;
                        default:
                            return false;
                    }
                } else {
                    SecureRandom random = new SecureRandom();
                    assignedField.changeCellState(x, y, Math.abs(random.nextInt()) % 11);
                    int desideredOrientation = Math.abs(random.nextInt()) % 4;
                    setOrientation(desideredOrientation);
                }
                movementResult = moveForward();
                if (!movementResult) {
                    return false;
                }
                counter--;
            }
        }
        return true;
    }

    public void turnLeft() {
        orientation = (orientation - 1) % 4;
        if (orientation < 0) {
            orientation += 4;
        }
    }

    public void turnRight() {
        orientation = (orientation + 1) % 4;
        if (orientation < 0) {
            orientation += 4;
        }
    }

    private boolean moveForward() {
        int counter = movementLength;
        while (counter > 0) {
            int desideredY = y;
            int desideredX = x;
            switch (orientation) {
                case 0:
                    desideredY = y - 1;
                    break;
                case 1:
                    desideredX = x + 1;
                    break;
                case 2:
                    desideredY = y + 1;
                    break;
                case 3:
                    desideredX = x - 1;
                    break;
                default:
                    break;
            }
            if (assignedField.getWorldType() == WorldType.FINITE) {
                if (assignedField.areCoordinateValid(desideredX, desideredY)) {
                    y = desideredY;
                    x = desideredX;
                } else {
                    return false;
                }
            } else if (assignedField.getWorldType() == WorldType.CYCLIC) {
                y = desideredY % assignedField.getDimension();
                if (y < 0) {
                    y += assignedField.getDimension();
                }
                x = desideredX % assignedField.getDimension();
                if (x < 0) {
                    x += assignedField.getDimension();
                }
            }

            counter--;
        }
        return true;
    }

    public Color getColorAtIndex(int i) {
        return colorScheme[i];
    }

    public int getAntBehaviour() {
        return antBehaviour;
    }

    public void setAntBehaviour(int antBehaviour) {
        this.antBehaviour = antBehaviour;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Field getAssignedField() {
        return assignedField;
    }

    public void setAssignedField(Field assignedField) {
        this.assignedField = assignedField;
    }

    public String getDNA() {
        return DNA;
    }

    public void setDNA(String DNA) {
        this.DNA = DNA;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMovementLength() {
        return movementLength;
    }

    public void setMovementLength(int movementLength) {
        this.movementLength = movementLength;
    }

    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int startingX) {
        this.startingX = startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }

    public int getStartingOrientation() {
        return startingOrientation;
    }

    public void setStartingOrientation(int startingOrientation) {
        this.startingOrientation = startingOrientation;
    }

    public Color getColor() {
        return colorScheme[antColorIndex];
    }

    public int getColorIndex() {
        return antColorIndex;
    }

    public void setColorIndex(int antColor) {
        this.antColorIndex = antColor;
    }

    public boolean isInsideField() {
        return insideField;
    }

    public void setInsideField(boolean insideField) {
        this.insideField = insideField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public String toString() {
        return "Ant{" + "orientation=" + orientation + ", x=" + x + ", y=" + y + ", startingX=" + startingX + ", startingY=" + startingY + ", startingOrientation=" + startingOrientation + ", assignedField=" + assignedField + ", DNA=" + DNA + ", speed=" + speed + ", movementLength=" + movementLength + ", antColorIndex=" + antColorIndex + ", insideField=" + insideField + ", name=" + name + ", antBehaviour=" + antBehaviour + '}';
    }

}
