package control;

import static control.LangtonAnt.verbose;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Ant;
import model.SimulationStatus;
import model.SimulationUniverse;
import model.WorldType;
import view.Dashboard;
import view.FieldDisplayAreaWrapper;
import view.TopPanel;

public final class HomeWindow extends JFrame implements ActionListener, ChangeListener {

    Timer timer; //used to manage the ticks
    FieldDisplayAreaWrapper simulationDisplay;
    Dashboard controlPanel;
    TopPanel topPanel;
    SimulationUniverse su;
    private static final int timesValues[] = {1000, 700, 400, 200, 100, 50, 25, 10, 1};
    private static final String fps[] = {"1", "1.5", "2.5", "5", "10", "20", "40", "100", "1000"};
    private final static int maxZoomOffset = 15, zoomStep = 1;
    private int zoomCounter = 0;

    public HomeWindow(SimulationUniverse s) {
        if (verbose) {
            System.out.println("Home window created");
        }

        su = s;

        //creating the whole window
        topPanel = new TopPanel();
        controlPanel = new Dashboard(this);
        simulationDisplay = new FieldDisplayAreaWrapper(su);

        topPanel.setPreferredSize(new Dimension(simulationDisplay.getDefaultSize() + 400, 100));
        controlPanel.setPreferredSize(new Dimension(400, 800));
        simulationDisplay.setPreferredSize(new Dimension(simulationDisplay.getDefaultSize(), simulationDisplay.getDefaultSize()));

        topPanel.setBackground(new Color(200, 200, 200));
        controlPanel.setBackground(new Color(237, 240, 242));
        simulationDisplay.setBackground(new Color(225, 226, 225));

        add(topPanel, BorderLayout.PAGE_START);
        add(controlPanel, BorderLayout.LINE_START);
        add(simulationDisplay, BorderLayout.CENTER);

        controlPanel.addListenersToControllClass(this, this);

        populateAntSelectionComboBox();

        timer = new Timer(timesValues[4], this);
        timer.setActionCommand("timer");

        controlPanel.getPauseButton().setEnabled(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    //list of action performed on dashboard that triggers event in this control class
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        switch (id) {
            case "stepPressed":
                oneTick();
                break;
            case "timer":
                tick();
                break;
            case "pausePressed":
                pause();
                break;
            case "playPressed":
                play();
                break;
            case "clearPressed":
                clear();
                break;
            case "zoomInPressed":
                zoomIn();
                break;
            case "zoomOutPressed":
                zoomOut();
                break;
            case "zoomCenterPressed":
                zoomCenter();
                break;
            case "leftPressed":
                left();
                break;
            case "rightPressed":
                right();
                break;
            case "upPressed":
                up();
                break;
            case "downPressed":
                down();
                break;
            case "centerPressed":
                center();
                break;
            case "antSelected":
                antSelected();
                break;
            case "savePressed":
                savePressed();
                break;
            case "somethingChangedInAnt":
                somethingChangedInAnt();
                break;
            case "deletePressed":
                deletePressed();
                break;
            case "somethingChangedInField":
                somethingChangedInField();
                break;
            default:
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int index = (int) source.getValue();
            timer.setDelay(timesValues[index]);
            controlPanel.updateStepsPerSecondLabel(fps[index]);
        }
    }

    //method called after the step button is pressed
    public void oneTick() {
    if (su.getActiveAntsCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add and save at least one Ant");
            return;
        }
        su.setState(SimulationStatus.RUNNING);
        if (su.tick()) {
            pause();
            JOptionPane.showMessageDialog(this, "One ant just hit the border");
        }
        su.setState(SimulationStatus.PAUSED);
        simulationDisplay.updateFieldDisplayArea();
        controlPanel.updateStepsCounterLabel(su.getTicks());
    }

    //every tick of the simulation ants are checked to see if they hit the display borders
    public void tick() {
        if (su.tick()) {
            pause();
            JOptionPane.showMessageDialog(this, "One ant just hit the border");
        }
        simulationDisplay.updateFieldDisplayArea();
        controlPanel.updateStepsCounterLabel(su.getTicks());
    }

    //pause the simulation
    public void pause() {
        timer.stop();
        su.setState(SimulationStatus.PAUSED);
        controlPanel.getPlayButton().setEnabled(true);
        controlPanel.getStepButton().setEnabled(true);
        controlPanel.getClearButton().setEnabled(true);
        controlPanel.getPauseButton().setEnabled(false);

        controlPanel.getWorldTypeComboBox().setEnabled(true);
        controlPanel.getGridSizeComboBox().setEnabled(true);
        controlPanel.getCellFrameComboBox().setEnabled(true);
        controlPanel.getModeComboBox().setEnabled(true);
    }

    public void play() {
        if (su.getActiveAntsCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add and save at least one Ant");
            return;
        }
        timer.start();
        su.setState(SimulationStatus.RUNNING);
        controlPanel.getStepButton().setEnabled(false);
        controlPanel.getClearButton().setEnabled(false);
        controlPanel.getPlayButton().setEnabled(false);
        controlPanel.getPauseButton().setEnabled(true);

        controlPanel.getWorldTypeComboBox().setEnabled(false);
        controlPanel.getGridSizeComboBox().setEnabled(false);
        controlPanel.getCellFrameComboBox().setEnabled(false);
        controlPanel.getModeComboBox().setEnabled(false);
    }

    public void clear() {
        su.setState(SimulationStatus.PAUSED);
        su.resetSimulation();
        simulationDisplay.updateFieldDisplayArea();
        controlPanel.updateStepsCounterLabel(su.getTicks());
    }

    //move the displayed simulation left
    public void left() {
        int currentFieldPixelSize = simulationDisplay.getCellDimension() * su.getField().getDimension();
        int maxPossibleOffset = -(currentFieldPixelSize / 10) * 8; //going right the offset is negative, so possible offsets are bigger than this value
        int oneTenthOfField = currentFieldPixelSize / 10;

        if ((simulationDisplay.getxOffset() - oneTenthOfField) > maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2)) {
            simulationDisplay.setxOffset(simulationDisplay.getxOffset() - oneTenthOfField);
            if ((simulationDisplay.getxOffset() - oneTenthOfField) <= maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2)) {
                controlPanel.getLeftButton().setEnabled(false);
            }
        } else {
            controlPanel.getLeftButton().setEnabled(false);
        }
        controlPanel.getRightButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

    //move the displayed simulation right
    public void right() {
        int currentFieldPixelSize = simulationDisplay.getCellDimension() * su.getField().getDimension();
        int maxPossibleOffset = (currentFieldPixelSize / 10) * 8; //going left the offset is positive, so possible offsets are lower than this value
        int oneTenthOfField = currentFieldPixelSize / 10;
        if ((oneTenthOfField + simulationDisplay.getxOffset()) < (maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2))) {
            simulationDisplay.setxOffset(oneTenthOfField + simulationDisplay.getxOffset());
            if ((oneTenthOfField + simulationDisplay.getxOffset()) >= (maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2))) {
                controlPanel.getRightButton().setEnabled(false);
            }
        } else {
            controlPanel.getRightButton().setEnabled(false);
        }
        controlPanel.getLeftButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

     //move the displayed simulation up
    public void up() {
        int currentFieldPixelSize = simulationDisplay.getCellDimension() * su.getField().getDimension();
        int maxPossibleOffset = -(currentFieldPixelSize / 10) * 8; //going right the offset is negative, so possible offsets are bigger than this value
        int oneTenthOfField = currentFieldPixelSize / 10;
        if ((simulationDisplay.getyOffset() - oneTenthOfField) > maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2)) {
            simulationDisplay.setyOffset(simulationDisplay.getyOffset() - oneTenthOfField);
            if ((simulationDisplay.getyOffset() - oneTenthOfField) <= maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2)) {
                controlPanel.getUpButton().setEnabled(false);
            }
        } else {
            controlPanel.getUpButton().setEnabled(false);
        }
        controlPanel.getDownButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

    //move the displayed simulation down
    public void down() {
        int currentFieldPixelSize = simulationDisplay.getCellDimension() * su.getField().getDimension();
        int maxPossibleOffset = (currentFieldPixelSize / 10) * 8; //going left the offset is positive, so possible offsets are lower than this value
        int oneTenthOfField = currentFieldPixelSize / 10;
        if ((oneTenthOfField + simulationDisplay.getyOffset()) < (maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2))) {
            simulationDisplay.setyOffset(oneTenthOfField + simulationDisplay.getyOffset());
            if ((oneTenthOfField + simulationDisplay.getyOffset()) >= (maxPossibleOffset + (400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2))) {
                controlPanel.getDownButton().setEnabled(false);
            }
        } else {
            controlPanel.getDownButton().setEnabled(false);
        }
        controlPanel.getUpButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

    //center the displayed simulation field in the center of the display area
    public void center() {
        if (verbose) {
            System.out.println("Center pressed");
        }
        controlPanel.getLeftButton().setEnabled(true);
        controlPanel.getRightButton().setEnabled(true);
        controlPanel.getUpButton().setEnabled(true);
        controlPanel.getDownButton().setEnabled(true);
        simulationDisplay.setyOffset(400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2);
        simulationDisplay.setxOffset(400 - simulationDisplay.getCellDimension() * su.getField().getDimension() / 2);
        simulationDisplay.updateFieldDisplayArea();
    }

    //zooming method taking care of repositioning
    public void zoomIn() {
        if (verbose) {
            System.out.println("ZoomIn pressed");
        }
        int desideredZoom = simulationDisplay.getCellSizeOffest() + zoomStep;
        if (desideredZoom > -simulationDisplay.getBaseCellDimension() / 2 && desideredZoom < maxZoomOffset) {
            simulationDisplay.setCellSizeOffest(desideredZoom);
            simulationDisplay.setxOffset(simulationDisplay.getxOffset() - su.getField().getDimension() / 2); //Rigth
            simulationDisplay.setyOffset(simulationDisplay.getyOffset() - su.getField().getDimension() / 2); //Down
            zoomCounter--;
            if(!((desideredZoom+ zoomStep) > -simulationDisplay.getBaseCellDimension() / 2 && (desideredZoom+ zoomStep) < maxZoomOffset)){
                controlPanel.getZoomInButton().setEnabled(false);
            }
        } else {
            controlPanel.getZoomInButton().setEnabled(false);
        }

        int spaceRequired = su.getField().getDimension() * simulationDisplay.getCellDimension();
        if (Math.abs(simulationDisplay.getxOffset()) - spaceRequired >= -100) {
            if (simulationDisplay.getxOffset() < 0) {
                simulationDisplay.setxOffset(simulationDisplay.getxOffset() + 50);
            } else {
                simulationDisplay.setxOffset(simulationDisplay.getxOffset() - 50);
            }
        }
        if (Math.abs(simulationDisplay.getyOffset()) - spaceRequired >= -100) {
            if (simulationDisplay.getyOffset() < 0) {
                simulationDisplay.setyOffset(simulationDisplay.getyOffset() + 50);
            } else {
                simulationDisplay.setyOffset(simulationDisplay.getyOffset() - 50);
            }
        }
        controlPanel.getZoomOutButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

    public void zoomOut() {
        if (verbose) {
            System.out.println("ZoomOut pressed");
        }
        int desideredZoom = simulationDisplay.getCellSizeOffest() - zoomStep;
        if (desideredZoom > -simulationDisplay.getBaseCellDimension() / 2 && desideredZoom < maxZoomOffset) {
            simulationDisplay.setCellSizeOffest(desideredZoom);
            simulationDisplay.setxOffset(simulationDisplay.getxOffset() + su.getField().getDimension() / 2); //Left
            simulationDisplay.setyOffset(simulationDisplay.getyOffset() + su.getField().getDimension() / 2); //Up
            zoomCounter++;
            if (!((desideredZoom - 1) > -simulationDisplay.getBaseCellDimension() / 2 && (desideredZoom - 1) < maxZoomOffset)) {
                controlPanel.getZoomOutButton().setEnabled(false);
            }
        } else {
            controlPanel.getZoomOutButton().setEnabled(false);
        }

        int spaceRequired = su.getField().getDimension() * simulationDisplay.getCellDimension();
        if (Math.abs(simulationDisplay.getxOffset()) - spaceRequired >= -100) {
            if (simulationDisplay.getxOffset() < 0) {
                simulationDisplay.setxOffset(simulationDisplay.getxOffset() + 50);
            } else {
                simulationDisplay.setxOffset(simulationDisplay.getxOffset() - 50);
            }
        }
        if (Math.abs(simulationDisplay.getyOffset()) - spaceRequired >= -100) {
            if (simulationDisplay.getyOffset() < 0) {
                simulationDisplay.setyOffset(simulationDisplay.getyOffset() + 50);
            } else {
                simulationDisplay.setyOffset(simulationDisplay.getyOffset() - 50);
            }
        }
        controlPanel.getZoomInButton().setEnabled(true);
        simulationDisplay.updateFieldDisplayArea();
    }

    public void zoomCenter() {
        if (verbose) {
            System.out.println("ZoomCenter pressed");
        }
        if (zoomCounter != 0) {
            controlPanel.getZoomInButton().setEnabled(true);
            controlPanel.getZoomOutButton().setEnabled(true);
            int sizeOffset = simulationDisplay.getCellSizeOffest();
            for (int i = 0; i < Math.abs(sizeOffset); i++) {
                if (sizeOffset < 0) {
                    int desideredZoom = simulationDisplay.getCellSizeOffest() + zoomStep;
                    if (desideredZoom > -simulationDisplay.getBaseCellDimension() / 2 && desideredZoom < maxZoomOffset) {
                        simulationDisplay.setCellSizeOffest(desideredZoom);
                        simulationDisplay.setxOffset(simulationDisplay.getxOffset() - su.getField().getDimension() / 2); //Rigth
                        simulationDisplay.setyOffset(simulationDisplay.getyOffset() - su.getField().getDimension() / 2); //Down
                        zoomCounter--;
                    } else {
                        controlPanel.getZoomInButton().setEnabled(false);
                    }

                    int spaceRequired = su.getField().getDimension() * simulationDisplay.getCellDimension();
                    if (Math.abs(simulationDisplay.getxOffset()) - spaceRequired >= -100) {
                        if (simulationDisplay.getxOffset() < 0) {
                            simulationDisplay.setxOffset(simulationDisplay.getxOffset() + 50);
                        } else {
                            simulationDisplay.setxOffset(simulationDisplay.getxOffset() - 50);
                        }
                    }
                    if (Math.abs(simulationDisplay.getyOffset()) - spaceRequired >= -100) {
                        if (simulationDisplay.getyOffset() < 0) {
                            simulationDisplay.setyOffset(simulationDisplay.getyOffset() + 50);
                        } else {
                            simulationDisplay.setyOffset(simulationDisplay.getyOffset() - 50);
                        }
                    }
                } else if (sizeOffset > 0) {
                    int desideredZoom = simulationDisplay.getCellSizeOffest() - zoomStep;
                    if (desideredZoom > -simulationDisplay.getBaseCellDimension() / 2 && desideredZoom < maxZoomOffset) {
                        simulationDisplay.setCellSizeOffest(desideredZoom);
                        simulationDisplay.setxOffset(simulationDisplay.getxOffset() + su.getField().getDimension() / 2); //Left
                        simulationDisplay.setyOffset(simulationDisplay.getyOffset() + su.getField().getDimension() / 2); //Up
                        zoomCounter++;
                    } else {
                        controlPanel.getZoomOutButton().setEnabled(false);
                    }

                    int spaceRequired = su.getField().getDimension() * simulationDisplay.getCellDimension();
                    if (Math.abs(simulationDisplay.getxOffset()) - spaceRequired >= -100) {
                        if (simulationDisplay.getxOffset() < 0) {
                            simulationDisplay.setxOffset(simulationDisplay.getxOffset() + 50);
                        } else {
                            simulationDisplay.setxOffset(simulationDisplay.getxOffset() - 50);
                        }
                    }
                    if (Math.abs(simulationDisplay.getyOffset()) - spaceRequired >= -100) {
                        if (simulationDisplay.getyOffset() < 0) {
                            simulationDisplay.setyOffset(simulationDisplay.getyOffset() + 50);
                        } else {
                            simulationDisplay.setyOffset(simulationDisplay.getyOffset() - 50);
                        }
                    }
                }
            }
            simulationDisplay.updateFieldDisplayArea();
        }
    }

    
    public void antSelected() {
        if (controlPanel.getAntSelectionComboBox().getItemCount() != 0) {
            String selectedAntName = controlPanel.getAntSelectionComboBox().getSelectedItem().toString();
            if (verbose) {
                System.out.println("Ant selected:-" + selectedAntName + "-");
            }
            su.deleteAllNotMovingAnts();
            if (selectedAntName.equalsIgnoreCase("None")) {
                // disable all the input fields
                controlPanel.getNameTextField().setEnabled(false);
                controlPanel.getColorSelectionComboBox().setEnabled(false);
                controlPanel.getAntBehaviourComboBox().setEnabled(false);
                controlPanel.getDnaTextField().setEnabled(false);
                controlPanel.getSpeedComboBox().setEnabled(false);
                controlPanel.getMovementComboBox().setEnabled(false);
                controlPanel.getCoordxTextField().setEnabled(false);
                controlPanel.getCoordyTextField().setEnabled(false);
                controlPanel.getOrientationComboBox().setEnabled(false);
                controlPanel.getSaveButton().setEnabled(false);
                controlPanel.getDeleteButton().setEnabled(false);
                su.setAllAntsNotHighligted();
            } else if (selectedAntName.equalsIgnoreCase("new ant")) {
                // set all fields to default settigns and create an ant not moving
                controlPanel.getNameTextField().setEnabled(true);
                controlPanel.getColorSelectionComboBox().setEnabled(true);
                controlPanel.getAntBehaviourComboBox().setEnabled(true);
                controlPanel.getDnaTextField().setEnabled(true);
                controlPanel.getSpeedComboBox().setEnabled(true);
                controlPanel.getMovementComboBox().setEnabled(true);
                controlPanel.getCoordxTextField().setEnabled(true);
                controlPanel.getCoordyTextField().setEnabled(true);
                controlPanel.getOrientationComboBox().setEnabled(true);
                controlPanel.getSaveButton().setEnabled(true);
                controlPanel.getDeleteButton().setEnabled(false);

                String tmpAntName = "Ant" + su.getAntsCount();
                controlPanel.getNameTextField().setText(tmpAntName);
                controlPanel.getCoordxTextField().setText("" + (int) su.getField().getDimension() / 2);
                controlPanel.getCoordyTextField().setText("" + (int) su.getField().getDimension() / 2);
                su.addAnt((int) su.getField().getDimension() / 2, su.getField().getDimension() / 2, controlPanel.getDnaTextField().getText(), tmpAntName, false);

                controlPanel.getCoordxTextField().setEnabled(true);
                controlPanel.getCoordyTextField().setEnabled(true);
            } else {

                //selected an ant: display all the info about it 
                controlPanel.getNameTextField().setEnabled(false);
                controlPanel.getColorSelectionComboBox().setEnabled(true);
                controlPanel.getAntBehaviourComboBox().setEnabled(true);
                controlPanel.getDnaTextField().setEnabled(true);
                controlPanel.getSpeedComboBox().setEnabled(true);
                controlPanel.getMovementComboBox().setEnabled(true);
                controlPanel.getCoordxTextField().setEnabled(true);
                controlPanel.getCoordyTextField().setEnabled(true);
                controlPanel.getOrientationComboBox().setEnabled(true);
                controlPanel.getSaveButton().setEnabled(true);
                controlPanel.getDeleteButton().setEnabled(true);

                Ant tmpAnt = su.getAntByName(selectedAntName);
                //System.out.println(tmpAnt.toString());
                controlPanel.getCoordxTextField().setText(tmpAnt.getStartingX() + "");
                controlPanel.getCoordyTextField().setText(tmpAnt.getStartingY() + "");
                controlPanel.getNameTextField().setText(tmpAnt.getName());
                controlPanel.getColorSelectionComboBox().setSelectedIndex(tmpAnt.getColorIndex());
                controlPanel.getAntBehaviourComboBox().setSelectedIndex(tmpAnt.getAntBehaviour());
                controlPanel.getDnaTextField().setText(tmpAnt.getDNA());
                controlPanel.getSpeedComboBox().setSelectedIndex(tmpAnt.getSpeed() - 1);
                controlPanel.getMovementComboBox().setSelectedIndex(tmpAnt.getMovementLength() - 1);
                controlPanel.getOrientationComboBox().setSelectedIndex(tmpAnt.getOrientation());

                //controlPanel.getCoordxTextField().setEnabled(false);
                //controlPanel.getCoordyTextField().setEnabled(false);
            }
            simulationDisplay.updateFieldDisplayArea();
        }
    }

    public void somethingChangedInAnt() {
        String selectionString = controlPanel.getAntSelectionComboBox().getSelectedItem().toString();
        if (controlPanel.getAntBehaviourComboBox().getSelectedIndex() == 0) {
            controlPanel.getDnaTextField().setEnabled(true);
        } else {
            controlPanel.getDnaTextField().setEnabled(false);
        }
        if (selectionString.equalsIgnoreCase("new ant")) {
            Ant tmpAnt = su.getNotMovingAnt();
            su.setAllAntsNotHighligted();
            if (tmpAnt != null) {
                safeAntParameterSetting(tmpAnt);
                tmpAnt.setHighlighted(true);
            }
        } else if (selectionString.equalsIgnoreCase("none")) {
            su.setAllAntsNotHighligted();
        } else {
            Ant tmpAnt = su.getAntByName(selectionString);
            su.setAllAntsNotHighligted();
            tmpAnt.setHighlighted(true);
            //safeAntParameterSetting(tmpAnt);
        }
        simulationDisplay.updateFieldDisplayArea();
    }

    public void somethingChangedInField() {
        center();
        su.deleteAllAnts();
        populateAntSelectionComboBox();
        int worldTypeSelected = controlPanel.getWorldTypeComboBox().getSelectedIndex();
        if (worldTypeSelected == 0) {
            su.getField().setWorldType(WorldType.FINITE);
        } else {
            su.getField().setWorldType(WorldType.CYCLIC);
        }

        int gridSizeSelected = Integer.parseInt(controlPanel.getGridSizeComboBox().getSelectedItem().toString().trim());
        su.getField().setActualSize(gridSizeSelected);

        int drawCellRuleSelected = controlPanel.getCellFrameComboBox().getSelectedIndex();
        simulationDisplay.setDrawCellFrameRule(drawCellRuleSelected);

        int modeSelected = controlPanel.getModeComboBox().getSelectedIndex();
        simulationDisplay.setMode(modeSelected);

        controlPanel.getAntSelectionComboBox().setSelectedIndex(0);

        zoomCenter();

        simulationDisplay.updateFieldDisplayArea();
    }

    public void savePressed() {

        int wantedYcoord = Integer.parseInt(controlPanel.getCoordyTextField().getText());
        int wantedXcoord = Integer.parseInt(controlPanel.getCoordxTextField().getText());

        if (wantedXcoord >= simulationDisplay.getSu().getField().getDimension() || wantedYcoord >= simulationDisplay.getSu().getField().getDimension()) {
            JOptionPane.showMessageDialog(this, "Inserted coordinates are not valid");
        } else {

            String selectedAntNameOrOldAnt = controlPanel.getAntSelectionComboBox().getSelectedItem().toString();
            if (selectedAntNameOrOldAnt.equalsIgnoreCase("new ant")) {

                if (su.getAntByName(controlPanel.getNameTextField().getText()) != null) {
                    Ant tmpAnt = su.getAntByName(controlPanel.getNameTextField().getText());
                    if (!tmpAnt.isInsideField()) {
                        safeAntParameterSetting(tmpAnt);
                        tmpAnt.setInsideField(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Name already taken");
                        return;
                    }

                } else {
                    Ant tmpAnt = su.getNotMovingAnt();
                    tmpAnt.setName(controlPanel.getNameTextField().getText());
                    safeAntParameterSetting(tmpAnt);
                    tmpAnt.setInsideField(true);
                }

                populateAntSelectionComboBox();
                controlPanel.getAntSelectionComboBox().setSelectedIndex(su.getAntsCount() + 1);
                antSelected();
            } else {
                Ant tmpAnt = su.getAntByName(selectedAntNameOrOldAnt);
                safeAntParameterSetting(tmpAnt);
            }
            simulationDisplay.updateFieldDisplayArea();
        }
    }

    public void deletePressed() {
        String selectedAntNameOrOldAnt = controlPanel.getAntSelectionComboBox().getSelectedItem().toString();
        if (selectedAntNameOrOldAnt.equalsIgnoreCase("new ant")) {
            su.deleteAllNotMovingAnts();
        } else {
            su.deleteAntByName(selectedAntNameOrOldAnt);
        }
        simulationDisplay.updateFieldDisplayArea();
        populateAntSelectionComboBox();
        controlPanel.getAntSelectionComboBox().setSelectedIndex(su.getAntsCount() + 1);
        antSelected();
        pause();
    }

    public int getTimerDelayMillis() {
        return timer.getDelay();
    }

    public void setTimerDelayMillis(int timerDelayMillis) {
        timer.setDelay(timerDelayMillis);
    }

    public void populateAntSelectionComboBox() {
        controlPanel.getAntSelectionComboBox().removeAllItems();
        controlPanel.getAntSelectionComboBox().addItem("None");
        controlPanel.getAntSelectionComboBox().addItem("New Ant");
        for (int i = 0; i < su.getAntsCount(); i++) {
            controlPanel.getAntSelectionComboBox().addItem(su.getSpecificAnt(i).getName());
        }
    }

    public void addNewAntToAntComboBox(String name) {
        controlPanel.getAntSelectionComboBox().addItem(name);
    }

    public void safeAntParameterSetting(Ant tmpAnt) {

        tmpAnt.setColorIndex(controlPanel.getColorSelectionComboBox().getSelectedIndex());
        tmpAnt.setAntBehaviour(controlPanel.getAntBehaviourComboBox().getSelectedIndex());
        if (controlPanel.getDnaTextField().getText().toUpperCase().length() > 0) {
            tmpAnt.setDNA(controlPanel.getDnaTextField().getText().toUpperCase());
        }
        tmpAnt.setSpeed(controlPanel.getSpeedComboBox().getSelectedIndex() + 1);
        tmpAnt.setMovementLength(controlPanel.getMovementComboBox().getSelectedIndex() + 1);

        if (controlPanel.getCoordxTextField().getText().length() > 0) {

            int wantedXcoord = 0;

            try {
                wantedXcoord = Integer.parseInt(controlPanel.getCoordxTextField().getText());
            } catch (Exception e) {
            }

            if (wantedXcoord >= simulationDisplay.getSu().getField().getDimension()) {
                JOptionPane.showMessageDialog(this, "Inserted coordinates are not valid");
            } else {

                if (wantedXcoord >= simulationDisplay.getSu().getField().getDimension()) {
                    wantedXcoord = simulationDisplay.getSu().getField().getDimension() - 1;
                }
                tmpAnt.setStartingX(wantedXcoord);
                if (!tmpAnt.isInsideField()) {
                    tmpAnt.setX(wantedXcoord);
                }
            }
        }

        if (controlPanel.getCoordyTextField().getText().length() > 0) {

            int wantedYcoord = 0;

            try {
                wantedYcoord = Integer.parseInt(controlPanel.getCoordyTextField().getText());
            } catch (Exception e) {
            }

            if (wantedYcoord >= simulationDisplay.getSu().getField().getDimension()) {
                JOptionPane.showMessageDialog(this, "Inserted coordinates are not valid");
            } else {

                if (wantedYcoord >= simulationDisplay.getSu().getField().getDimension()) {
                    wantedYcoord = simulationDisplay.getSu().getField().getDimension() - 1;
                }
                tmpAnt.setStartingY(wantedYcoord);
                if (!tmpAnt.isInsideField()) {
                    tmpAnt.setY(wantedYcoord);
                }
            }
        }

        tmpAnt.setOrientation(controlPanel.getOrientationComboBox().getSelectedIndex());

        simulationDisplay.updateFieldDisplayArea();
    }

}
