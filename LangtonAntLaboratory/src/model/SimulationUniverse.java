package model;

import java.awt.Color;
import java.util.ArrayList;

public final class SimulationUniverse {

    private static SimulationUniverse single_instance = null; //singleton
    private Field field;
    private static ArrayList<Ant> ants;
    private int ticksPerMinute;
    private SimulationStatus state;
    private int ticks = 0; //current tick

    private SimulationUniverse(int ticksPerMinute, int size) {
        setState(SimulationStatus.NEW);
        this.field = Field.getInstance(size);
        this.ticksPerMinute = ticksPerMinute;
        ants = new ArrayList<>();
        setState(SimulationStatus.PAUSED);
    }

    public static SimulationUniverse getInstance(int ticksPerMinute, int size) {
        if (single_instance == null) {
            single_instance = new SimulationUniverse(ticksPerMinute, size);
        }
        return single_instance;
    }

    public void resetSimulation() {
        ticks = 0;
        field.resetField();
        for (int i = 0; i < ants.size(); i++) {
            ants.get(i).resetPosition();
        }
    }

    public void addAnt(int x, int y, String dna) {
        ants.add(new Ant(field, x, y, dna));
    }

    public void addAnt(int x, int y, String dna, String name, boolean live) {
        ants.add(new Ant(field, x, y, dna, name, live));
    }

    public Color[][] getColorsMap() {
        return field.getCellMapRepresentation();
    }

    public boolean tick() {
        if (state == SimulationStatus.RUNNING) {
            for (int i = 0; i < ants.size(); i++) {
                if (!ants.get(i).move()) {
                    return true;
                }
            }
        }
        ticks++;
        return false;
    }

    public void deleteAllNotMovingAnts() {
        for (int i = 0; i < ants.size(); i++) {
            if (!ants.get(i).isInsideField()) {
                ants.remove(i);
            }
        }
    }

    public void deleteAllAnts() {
        for (int i = 0; i < ants.size(); i++) {
            ants.remove(i);
        }
    }

    public Ant getNotMovingAnt() {
        for (int i = 0; i < ants.size(); i++) {
            if (!ants.get(i).isInsideField()) {
                return ants.get(i);
            }
        }
        return null;
    }

    public void addAnt(Ant t) {
        ants.add(t);
    }

    public Ant getAntByName(String name) {
        for (int i = 0; i < ants.size(); i++) {
            if (ants.get(i).getName().equalsIgnoreCase(name)) {
                return ants.get(i);
            }
        }
        return null;
    }

    public void deleteAntByName(String name) {
        for (int i = 0; i < ants.size(); i++) {
            if (ants.get(i).getName().equalsIgnoreCase(name)) {
                ants.remove(i);
            }
        }
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public static ArrayList<Ant> getAnts() {
        return ants;
    }

    public static void setAnts(ArrayList<Ant> ants) {
        SimulationUniverse.ants = ants;
    }

    public int getTicksPerMinute() {
        return ticksPerMinute;
    }

    public void setTicksPerMinute(int ticksPerMinute) {
        this.ticksPerMinute = ticksPerMinute;
    }

    public SimulationStatus getState() {
        return state;
    }

    public void setState(SimulationStatus state) {
        this.state = state;
    }

    public int getAntsCount() {
        return ants.size();
    }

    public int getActiveAntsCount() {
        int res = 0;
        for (int i = 0; i < ants.size(); i++) {
            if (ants.get(i).isInsideField()) {
                res++;
            }
        }
        return res;
    }

    public Ant getSpecificAnt(int idex) {
        return ants.get(idex);
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setAllAntsNotHighligted() {
        for (int i = 0; i < ants.size(); i++) {
            ants.get(i).setHighlighted(false);
        }
    }

    @Override
    public String toString() {
        return "SimulationUniverse{" + "field=" + field.toString() + ", ticksPerMinute=" + ticksPerMinute + ", state=" + state + ", ticks=" + ticks + '}';
    }

}
