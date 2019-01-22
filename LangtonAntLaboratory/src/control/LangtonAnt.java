package control;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.SimulationUniverse;
import model.WorldType;

public class LangtonAnt {

    //TODO more jokes on "buggy" code
    
    static SimulationUniverse simulationUniverse;
    static HomeWindow homeWindow;
    static boolean verbose = false;

    public static void main(String[] args) {
        if (verbose == true) {
            System.out.println("Verbose run");
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("v")) {
                    verbose = true;
                    System.out.println("Verbose run");
                }
            }
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        simulationUniverse = SimulationUniverse.getInstance(10, 100);
        simulationUniverse.getField().setWorldType(WorldType.CYCLIC);
        homeWindow = new HomeWindow(simulationUniverse);

    }

}
