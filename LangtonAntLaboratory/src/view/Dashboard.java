/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.inputFilter.LRFilter;
import view.inputFilter.NumericFilter;
import view.inputFilter.NumericCharFilter;
import control.HomeWindow;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author admin
 */
public class Dashboard extends javax.swing.JPanel {

    HomeWindow hw;

    public Dashboard(HomeWindow h) {
        hw = h;
        initComponents();
        ((AbstractDocument) nameTextField.getDocument()).setDocumentFilter(new NumericCharFilter());
        ((AbstractDocument) dnaTextField.getDocument()).setDocumentFilter(new LRFilter());
        ((AbstractDocument) coordxTextField.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) coordyTextField.getDocument()).setDocumentFilter(new NumericFilter());

        //set all filters
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }
        });
        dnaTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }
        });
        coordxTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }
        });
        coordyTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hw.somethingChangedInAnt();
            }
        });
        nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nameTextField.getText().length() >= 8) {
                    e.consume();
                }
            }
        });
        dnaTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (dnaTextField.getText().length() > 10) {
                    e.consume();
                }
            }
        });
        coordxTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (coordxTextField.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        coordyTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (coordyTextField.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        

    }

    public void addListenersToControllClass(ActionListener lis, ChangeListener clis) {
        //add all listeners to create action when something is performed on an object
        playButton.addActionListener(lis);
        stepButton.addActionListener(lis);
        pauseButton.addActionListener(lis);
        clearButton.addActionListener(lis);
        playButton.setActionCommand("playPressed");
        stepButton.setActionCommand("stepPressed");
        pauseButton.setActionCommand("pausePressed");
        clearButton.setActionCommand("clearPressed");

        speedSlider.addChangeListener(clis);

        zoomInButton.addActionListener(lis);
        zoomOutButton.addActionListener(lis);
        zoomCenter.addActionListener(lis);
        zoomInButton.setActionCommand("zoomInPressed");
        zoomOutButton.setActionCommand("zoomOutPressed");
        zoomCenter.setActionCommand("zoomCenterPressed");
        leftButton.addActionListener(lis);
        rightButton.addActionListener(lis);
        upButton.addActionListener(lis);
        downButton.addActionListener(lis);
        offsetCenter.addActionListener(lis);
        leftButton.setActionCommand("leftPressed");
        rightButton.setActionCommand("rightPressed");
        upButton.setActionCommand("upPressed");
        downButton.setActionCommand("downPressed");
        offsetCenter.setActionCommand("centerPressed");

        antSelectionComboBox.addActionListener(lis);
        colorSelectionComboBox.addActionListener(lis);
        antBehaviourComboBox.addActionListener(lis);
        speedComboBox.addActionListener(lis);
        movementComboBox.addActionListener(lis);
        orientationComboBox.addActionListener(lis);
        saveButton.addActionListener(lis);
        deleteButton.addActionListener(lis);
        antSelectionComboBox.setActionCommand("antSelected");
        
        antBehaviourComboBox.setActionCommand("somethingChangedInAnt");
        colorSelectionComboBox.setActionCommand("somethingChangedInAnt");
        dnaTextField.setActionCommand("somethingChangedInAnt");
        speedComboBox.setActionCommand("somethingChangedInAnt");
        movementComboBox.setActionCommand("somethingChangedInAnt");
        orientationComboBox.setActionCommand("somethingChangedInAnt");
        
        saveButton.setActionCommand("savePressed");
        deleteButton.setActionCommand("deletePressed");
        
        worldTypeComboBox.addActionListener(lis);
        gridSizeComboBox.addActionListener(lis);
        cellFrameComboBox.addActionListener(lis);
        modeComboBox.addActionListener(lis);
        worldTypeComboBox.setActionCommand("somethingChangedInField");
        gridSizeComboBox.setActionCommand("somethingChangedInField");
        cellFrameComboBox.setActionCommand("somethingChangedInField");
        modeComboBox.setActionCommand("somethingChangedInField");
    }

    //default methods
    public void updateStepsCounterLabel(int ticks) {
        currentTickLabel.setText(Integer.toString(ticks));
    }

    public void updateStepsPerSecondLabel(String ticksPerSeconds) {
        ticksPerSecondCounterLabel.setText(ticksPerSeconds);
    }

    public JPanel getAntsPanel() {
        return antsPanel;
    }

    public void setAntsPanel(JPanel antsPanel) {
        this.antsPanel = antsPanel;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    public JLabel getCurrentTickLabel() {
        return currentTickLabel;
    }

    public void setCurrentTickLabel(JLabel currentTickLabel) {
        this.currentTickLabel = currentTickLabel;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public void setDownButton(JButton downButton) {
        this.downButton = downButton;
    }

    public JPanel getFieldPanel() {
        return fieldPanel;
    }

    public void setFieldPanel(JPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    public JTabbedPane getjTabbedPane1() {
        return jTabbedPane1;
    }

    public void setjTabbedPane1(JTabbedPane jTabbedPane1) {
        this.jTabbedPane1 = jTabbedPane1;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(JButton leftButton) {
        this.leftButton = leftButton;
    }

    public JButton getOffsetCenter() {
        return offsetCenter;
    }

    public void setOffsetCenter(JButton offsetCenter) {
        this.offsetCenter = offsetCenter;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(JButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public void setRightButton(JButton rightButton) {
        this.rightButton = rightButton;
    }

    public JLabel getSpeedLabel() {
        return speedLabel;
    }

    public void setSpeedLabel(JLabel speedLabel) {
        this.speedLabel = speedLabel;
    }

    public JLabel getSpeedLabel3() {
        return speedLabel3;
    }

    public void setSpeedLabel3(JLabel speedLabel3) {
        this.speedLabel3 = speedLabel3;
    }

    public JSlider getSpeedSlider() {
        return speedSlider;
    }

    public void setSpeedSlider(JSlider speedSlider) {
        this.speedSlider = speedSlider;
    }

    public JButton getStepButton() {
        return stepButton;
    }

    public void setStepButton(JButton stepButton) {
        this.stepButton = stepButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    public JLabel getTicksPerSecondCounterLabel() {
        return ticksPerSecondCounterLabel;
    }

    public void setTicksPerSecondCounterLabel(JLabel ticksPerSecondCounterLabel) {
        this.ticksPerSecondCounterLabel = ticksPerSecondCounterLabel;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public void setUpButton(JButton upButton) {
        this.upButton = upButton;
    }

    public JButton getZoomCenter() {
        return zoomCenter;
    }

    public void setZoomCenter(JButton zoomCenter) {
        this.zoomCenter = zoomCenter;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public void setZoomInButton(JButton zoomInButton) {
        this.zoomInButton = zoomInButton;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public void setZoomOutButton(JButton zoomOutButton) {
        this.zoomOutButton = zoomOutButton;
    }

    public JComboBox<String> getAntBehaviourComboBox() {
        return antBehaviourComboBox;
    }

    public void setAntBehaviourComboBox(JComboBox<String> antBehaviourComboBox) {
        this.antBehaviourComboBox = antBehaviourComboBox;
    }

    public JComboBox<String> getAntSelectionComboBox() {
        return antSelectionComboBox;
    }

    public void setAntSelectionComboBox(JComboBox<String> antSelectionComboBox) {
        this.antSelectionComboBox = antSelectionComboBox;
    }

    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    public void setButtonGroup1(ButtonGroup buttonGroup1) {
        this.buttonGroup1 = buttonGroup1;
    }

    public JComboBox<String> getColorSelectionComboBox() {
        return colorSelectionComboBox;
    }

    public void setColorSelectionComboBox(JComboBox<String> colorSelectionComboBox) {
        this.colorSelectionComboBox = colorSelectionComboBox;
    }

    public JTextField getCoordxTextField() {
        return coordxTextField;
    }

    public void setCoordxTextField(JTextField coordxTextField) {
        this.coordxTextField = coordxTextField;
    }

    public JTextField getCoordyTextField() {
        return coordyTextField;
    }

    public void setCoordyTextField(JTextField coordyTextField) {
        this.coordyTextField = coordyTextField;
    }

    public JTextField getDnaTextField() {
        return dnaTextField;
    }

    public void setDnaTextField(JTextField dnaTextField) {
        this.dnaTextField = dnaTextField;
    }

    public JComboBox<String> getGridSizeComboBox() {
        return gridSizeComboBox;
    }

    public void setGridSizeComboBox(JComboBox<String> gridSizeComboBox) {
        this.gridSizeComboBox = gridSizeComboBox;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel10() {
        return jLabel10;
    }

    public void setjLabel10(JLabel jLabel10) {
        this.jLabel10 = jLabel10;
    }

    public JLabel getjLabel13() {
        return jLabel13;
    }

    public void setjLabel13(JLabel jLabel13) {
        this.jLabel13 = jLabel13;
    }

    public JLabel getjLabel14() {
        return jLabel14;
    }

    public void setjLabel14(JLabel jLabel14) {
        this.jLabel14 = jLabel14;
    }

    public JLabel getjLabel15() {
        return jLabel15;
    }

    public void setjLabel15(JLabel jLabel15) {
        this.jLabel15 = jLabel15;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    public JLabel getjLabel7() {
        return jLabel7;
    }

    public void setjLabel7(JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }

    public JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    public JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }

    public JComboBox<String> getMovementComboBox() {
        return movementComboBox;
    }

    public void setMovementComboBox(JComboBox<String> movementComboBox) {
        this.movementComboBox = movementComboBox;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JComboBox<String> getOrientationComboBox() {
        return orientationComboBox;
    }

    public void setOrientationComboBox(JComboBox<String> orientationComboBox) {
        this.orientationComboBox = orientationComboBox;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JComboBox<String> getSpeedComboBox() {
        return speedComboBox;
    }

    public void setSpeedComboBox(JComboBox<String> speedComboBox) {
        this.speedComboBox = speedComboBox;
    }

    public JComboBox<String> getWorldTypeComboBox() {
        return worldTypeComboBox;
    }

    public void setWorldTypeComboBox(JComboBox<String> worldTypeComboBox) {
        this.worldTypeComboBox = worldTypeComboBox;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JComboBox<String> getCellFrameComboBox() {
        return cellFrameComboBox;
    }

    public void setCellFrameComboBox(JComboBox<String> cellFrameComboBox) {
        this.cellFrameComboBox = cellFrameComboBox;
    }

    public JComboBox<String> getModeComboBox() {
        return modeComboBox;
    }

    public void setModeComboBox(JComboBox<String> modeComboBox) {
        this.modeComboBox = modeComboBox;
    }
    
    

    //automatic created code by swing editor
  /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        antsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dnaTextField = new javax.swing.JTextField();
        antSelectionComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        colorSelectionComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        speedComboBox = new javax.swing.JComboBox<>();
        movementComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        antBehaviourComboBox = new javax.swing.JComboBox<>();
        coordxTextField = new javax.swing.JTextField();
        coordyTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        orientationComboBox = new javax.swing.JComboBox<>();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        fieldPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        worldTypeComboBox = new javax.swing.JComboBox<>();
        gridSizeComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cellFrameComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        modeComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pauseButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        speedLabel = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();
        leftButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        zoomInButton = new javax.swing.JButton();
        zoomOutButton = new javax.swing.JButton();
        offsetCenter = new javax.swing.JButton();
        currentTickLabel = new javax.swing.JLabel();
        speedLabel3 = new javax.swing.JLabel();
        ticksPerSecondCounterLabel = new javax.swing.JLabel();
        zoomCenter = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 800));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Select ant:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Ant DNA:");

        dnaTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        dnaTextField.setText("LR");
        dnaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dnaTextFieldActionPerformed(evt);
            }
        });

        antSelectionComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        antSelectionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antSelectionComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Color:");

        colorSelectionComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        colorSelectionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Green", "Yellow", "Pink", "Magenta", "Cyan", "Blue" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Speed:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Movement lenght:");

        speedComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        speedComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        speedComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedComboBoxActionPerformed(evt);
            }
        });

        movementComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        movementComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Strarting point:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Ant behaviour:");

        antBehaviourComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        antBehaviourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNA", "Random" }));

        coordxTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        coordxTextField.setText("0");
        coordxTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coordxTextFieldActionPerformed(evt);
            }
        });

        coordyTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        coordyTextField.setText("0");
        coordyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coordyTextFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText(";");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Name:");

        nameTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nameTextField.setText("name");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("Orientation:");

        orientationComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        orientationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Up", "Right", "Down", "Left" }));
        orientationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientationComboBoxActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        deleteButton.setText("DELETE ANT");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout antsPanelLayout = new javax.swing.GroupLayout(antsPanel);
        antsPanel.setLayout(antsPanelLayout);
        antsPanelLayout.setHorizontalGroup(
            antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(antsPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(antsPanelLayout.createSequentialGroup()
                        .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, antsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(dnaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(movementComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(orientationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(antSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(coordxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(coordyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(antsPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(antBehaviourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(colorSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(antsPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(deleteButton)
                        .addGap(28, 28, 28)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        antsPanelLayout.setVerticalGroup(
            antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, antsPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(antSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(colorSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(antBehaviourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dnaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(movementComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(coordxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coordyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(orientationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(antsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(deleteButton))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("       Ants       ", antsPanel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("World type:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Field size:");

        worldTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        worldTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finite", "Cyclic" }));
        worldTypeComboBox.setSelectedIndex(1);

        gridSizeComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        gridSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "50", "100", "200" }));
        gridSizeComboBox.setSelectedIndex(2);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Draw cell frame:");

        cellFrameComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cellFrameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Always", "Best", "Never" }));
        cellFrameComboBox.setSelectedIndex(1);
        cellFrameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellFrameComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Mode:");

        modeComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard", "Jamaica", "Pride" }));
        modeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("WARNING");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Changing Field options changes Field");

        javax.swing.GroupLayout fieldPanelLayout = new javax.swing.GroupLayout(fieldPanel);
        fieldPanel.setLayout(fieldPanelLayout);
        fieldPanelLayout.setHorizontalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addGroup(fieldPanelLayout.createSequentialGroup()
                            .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel15))
                            .addGap(56, 56, 56)
                            .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cellFrameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(worldTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gridSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel12))
                .addGap(33, 33, 33))
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(worldTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(gridSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cellFrameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("       Field       ", fieldPanel);

        pauseButton.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        speedLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        speedLabel.setText("FPS");

        speedSlider.setMaximum(8);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setValue(4);

        leftButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        leftButton.setText("◄");

        upButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        upButton.setText("▲");
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        rightButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rightButton.setText("►");
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });

        downButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        downButton.setText("▼");
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        zoomInButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zoomInButton.setText("Zoom IN");
        zoomInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInButtonActionPerformed(evt);
            }
        });

        zoomOutButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zoomOutButton.setText("Zoom OUT");
        zoomOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutButtonActionPerformed(evt);
            }
        });

        offsetCenter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        offsetCenter.setText("O");
        offsetCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offsetCenterActionPerformed(evt);
            }
        });

        currentTickLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentTickLabel.setText("0");

        speedLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        speedLabel3.setText("Current tick:");

        ticksPerSecondCounterLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ticksPerSecondCounterLabel.setText("10");

        zoomCenter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zoomCenter.setText("O");
        zoomCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomCenterActionPerformed(evt);
            }
        });

        stepButton.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        playButton.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        playButton.setText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speedLabel3)
                        .addGap(128, 128, 128)
                        .addComponent(currentTickLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(downButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(offsetCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rightButton)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(zoomCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(zoomOutButton)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(zoomInButton)
                                .addGap(8, 8, 8))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ticksPerSecondCounterLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseButton)
                    .addComponent(clearButton)
                    .addComponent(stepButton)
                    .addComponent(playButton))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(speedSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticksPerSecondCounterLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speedLabel3)
                    .addComponent(currentTickLabel))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zoomInButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rightButton)
                        .addComponent(zoomCenter))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(offsetCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downButton)
                    .addComponent(zoomOutButton))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
       
    }                                           

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    }                                           

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
    }                                          

    private void offsetCenterActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
    }                                            

    private void zoomOutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
    }                                             

    private void zoomInButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            

    private void zoomCenterActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
    }                                        

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    }                                           

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    private void dnaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            

    private void coordxTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                
    }                                               

    private void coordyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                
    }                                               

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    private void speedComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    private void antSelectionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    }                                                    

    private void cellFrameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    }                                                 

    private void orientationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    }                                                   

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            

    private void modeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            


    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> antBehaviourComboBox;
    private javax.swing.JComboBox<String> antSelectionComboBox;
    private javax.swing.JPanel antsPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cellFrameComboBox;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> colorSelectionComboBox;
    private javax.swing.JTextField coordxTextField;
    private javax.swing.JTextField coordyTextField;
    private javax.swing.JLabel currentTickLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField dnaTextField;
    private javax.swing.JButton downButton;
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JComboBox<String> gridSizeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton leftButton;
    private javax.swing.JComboBox<String> modeComboBox;
    private javax.swing.JComboBox<String> movementComboBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton offsetCenter;
    private javax.swing.JComboBox<String> orientationComboBox;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> speedComboBox;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JLabel speedLabel3;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton stepButton;
    private javax.swing.JLabel ticksPerSecondCounterLabel;
    private javax.swing.JButton upButton;
    private javax.swing.JComboBox<String> worldTypeComboBox;
    private javax.swing.JButton zoomCenter;
    private javax.swing.JButton zoomInButton;
    private javax.swing.JButton zoomOutButton;
    // End of variables declaration                   
}
