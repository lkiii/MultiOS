package GUI;

import rvm.*;
import rvm.VirtualMachine;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author  Ernestas Prisakaru OS GUI
 * @version 1.0
 */ 

public class Gui1 {
    private JFrame frame;
    RM rm = null;
    VirtualMachine vm = null;
    
    //- Main page
    private JButton loadFile; // load source code
    private JButton run; // run a program
    private JButton step; // make a step 
    
    private JList stackList; // stack
    private JTable realMemoryTable; // real memory
    private JTable virtualMemoryTable;
    
    private DefaultListModel stackModel; // stack values
    private MemoryTableModel realMemoryModel; // real memory words
    private MemoryTableModel virtualMemoryModel; // virtual memory words
    
    private MemoryTableCellRenderer realMemoryCell; 
    private MemoryTableCellRenderer virtualMemoryCell;
    
    private JScrollPane spStackList; 
    private JScrollPane spRealMemoryTable;
    private JScrollPane spVirtualMemoryTable;
    
    // registru reiksmes
    JTextField tfRegR;
    JTextField tfRegSF;
    JTextField tfRegIP;
    JTextField tfRegSP;
    JTextField tfRegDS;
    JTextField tfRegCS;
    JTextField tfRegSS;
    JTextField tfRegES;
    
    // registrai
    JLabel lbRegR;
    JLabel lbRegSF;    
    JLabel lbRegIP;
    JLabel lbRegSP;
    JLabel lbRegDS;
    JLabel lbRegCS;
    JLabel lbRegSS;
    JLabel lbRegES;
    
    //TODO grupe
    JPanel panelMemoryRepresentation;
    private boolean charRepresentation = true;
    ButtonGroup rbGroup;
    JRadioButton rbChar;
    JRadioButton rbNum;
    
    JTextArea taOutput;
    JScrollPane spOutput;
    JTextField tfInput;
    
    JFileChooser fc;
    boolean isLoaded = false;
    
    private void initProject() throws FileNotFoundException {
        rm = new RM();
        vm = rm.startNewVM("src/program1");
    }

    public Gui1() throws FileNotFoundException {
        frame = new JFrame();   
        initProject();
        initUI();
    }
    
    public final void initUI() {
        frame.setTitle("OS");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.add(createMainPage());
        frame.setVisible(true);
    }

    public static void main(String[] arg) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException err) {
            System.out.println(err);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui1 graphical = null;
                try {
                    graphical = new Gui1();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Gui1.class.getName()).log(Level.SEVERE, null, ex);
                }
                graphical.frame.setVisible(true);
            }
        });
    }
    
private void makeStep() {
    virtualMemoryCell.setNextInstructionAddress(vm.getCS() + vm.getIP());
    updateRealMemoryModel();
    updateVirtualMemoryModel();
    updateStackModel();
    tfRegR.setText(String.valueOf(vm.getR()));
    tfRegSF.setText(String.valueOf(vm.getSF()));
    tfRegIP.setText(String.valueOf(vm.getIP()));
    tfRegSP.setText(String.valueOf(vm.getSP()));
    tfRegDS.setText(String.valueOf(vm.getDS()));
    tfRegCS.setText(String.valueOf(vm.getCS()));
    tfRegSS.setText(String.valueOf(vm.getSS()));
}
 
// main page panel
public JPanel createMainPage() {
        JPanel panel = new JPanel();
        GridBagConstraints c1 = new GridBagConstraints();

        prepareRealMemoryModel();
        prepareVirtualMemoryModel();
        prepareStackModel();


        //panel.add(stackList);
        stackList.setSelectedIndex(-1);

        loadFile = new JButton("Load...");
        //loadFile.setPreferredSize(new Dimension(20, 70));
        fc = new JFileChooser();
        fc.setFileFilter(new SourceFileFilter());
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int returnVal = fc.showOpenDialog(frame);
              if (returnVal == JFileChooser.APPROVE_OPTION) {
                  File file = fc.getSelectedFile();
              } 
           }
        });

        step = new JButton("Step>");
        //.setPreferredSize(new Dimension(20, 70));
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.step();
                makeStep();
            }
        });
        
        run = new JButton("Run>>");
        //run.setPreferredSize(new Dimension(20, 70));
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // runint
            }
        });
        
        spRealMemoryTable = new JScrollPane();
        //realMemoryTable = new JTable();
        
        spVirtualMemoryTable = new JScrollPane();
        //virtualMemoryTable = new JTable();
        
        spOutput = new JScrollPane();
        taOutput = new JTextArea();
        
        tfInput = new JTextField();

        spStackList = new JScrollPane();
        
        panelMemoryRepresentation = new JPanel();
        rbChar = new JRadioButton();
        rbNum = new JRadioButton();
        
        rbChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbChar.isSelected()) {
                    setCharRepresentation(true);
                    updateRealMemoryModel();
                    updateVirtualMemoryModel();
                    updateStackModel();
                }
            }
        });
        
        rbNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbNum.isSelected()) {
                    setCharRepresentation(false);
                    updateRealMemoryModel();
                    updateVirtualMemoryModel();
                    updateStackModel();
                }
            }
        });
        
        // regai
        lbRegR = new JLabel();
        lbRegSF = new JLabel();
        lbRegIP = new JLabel();
        lbRegSP = new JLabel();
        
        tfRegR = new JTextField(7);
        tfRegSF = new JTextField(7);
        tfRegIP = new JTextField(7);
        tfRegSP = new JTextField(7);
        
        
        lbRegDS = new JLabel();
        lbRegCS = new JLabel();
        lbRegSS = new JLabel();
        lbRegES = new JLabel();
        
        tfRegDS = new JTextField(7);
        tfRegCS = new JTextField(7);
        tfRegSS = new JTextField(7);
        tfRegES = new JTextField(7);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        spRealMemoryTable.setViewportView(realMemoryTable);
        spVirtualMemoryTable.setViewportView(virtualMemoryTable);
        spStackList.setViewportView(stackList);

        taOutput.setColumns(20);
        taOutput.setRows(5);
        spOutput.setViewportView(taOutput);


        loadFile.setText("Load...");
        step.setText("Step>");
        run.setText("Run>>");

        panelMemoryRepresentation.setBorder(BorderFactory.createTitledBorder("Memory representation"));

        rbGroup = new ButtonGroup();
        rbChar.setText("Char");
        rbNum.setText("Num");
        rbGroup.add(rbChar);
        rbGroup.add(rbNum);

        GroupLayout panelLayout = new GroupLayout(panelMemoryRepresentation);
        panelMemoryRepresentation.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(rbNum)
                    .addComponent(rbChar))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbChar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbNum))
        );

        lbRegR.setText("R");
        lbRegSF.setText("SF");
        lbRegIP.setText("IP");
        lbRegSP.setText("SP");

        lbRegDS.setText("DS");
        lbRegCS.setText("CS");
        lbRegSS.setText("SS");
        lbRegES.setText("ES");

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(tfInput)
                            .addComponent(spOutput)
                            .addComponent(spVirtualMemoryTable, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spStackList, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loadFile, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .addComponent(run, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(step, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbRegR)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfRegR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRegDS)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfRegDS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbRegSF)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfRegSF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRegCS)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfRegCS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbRegIP)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfRegIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbRegSP)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRegSP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbRegSS)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRegSS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbRegES)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRegES, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                            .addComponent(panelMemoryRepresentation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(spRealMemoryTable, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spVirtualMemoryTable, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spOutput, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRegR)
                            .addComponent(tfRegR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRegDS)
                            .addComponent(tfRegDS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRegSF)
                            .addComponent(tfRegSF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRegCS)
                            .addComponent(tfRegCS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRegIP)
                            .addComponent(tfRegIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRegSS)
                            .addComponent(tfRegSS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRegSP)
                            .addComponent(lbRegES)
                            .addComponent(tfRegES, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfRegSP))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMemoryRepresentation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(step)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(run)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loadFile))
                            .addComponent(spStackList, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spRealMemoryTable, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        //frame.pack();
        return panel;
}
 
    private void prepareRealMemoryModel () {
        int rows = Constants.MEMORY_SIZE / Constants.BLOCK_SIZE;
        realMemoryModel = new MemoryTableModel(0x11, rows); // eiluciu nulis poto iterpsim
        realMemoryTable = new JTable(realMemoryModel); 
        realMemoryTable.getTableHeader().setReorderingAllowed(false);
        realMemoryTable.getTableHeader().setResizingAllowed(false);
        realMemoryTable.setDragEnabled(false);
        realMemoryTable.setSelectionForeground(Color.black);
        realMemoryCell = new MemoryTableCellRenderer();
        try {
            realMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), realMemoryCell);
        } catch (ClassNotFoundException ex) {
            System.out.println("blet");
        }
        updateRealMemoryModel();
        realMemoryTable.setVisible(true);
    }
  
    private void updateRealMemoryModel() {
        realMemoryTable.removeAll();
        realMemoryTable.repaint();
        Word[] mem = rm.getMemoryGui();
        for (int i=0; i < rvm.Constants.MEMORY_SIZE; i++) {
            int col = i % 0x10;
            int row = i / 0x10;
            if (mem[i] == null) {
                mem[i] = new Word(0x0);
            }
            if (charRepresentation) {
                realMemoryModel.setValueAt(mem[i].toString(), row, col+1);
            } else {
                realMemoryModel.setValueAt(mem[i].toInt(), row, col+1);
            }
        }
    }
    
    private void prepareVirtualMemoryModel () {
        int rows = vm.getMemory().getSize() / Constants.BLOCK_SIZE + 1 + 1;
        virtualMemoryModel = new MemoryTableModel(0x11, rows);
        virtualMemoryTable = new JTable(virtualMemoryModel);
        virtualMemoryTable.getTableHeader().setReorderingAllowed(false);
        virtualMemoryTable.getTableHeader().setResizingAllowed(false);
        virtualMemoryTable.setDragEnabled(false);
        virtualMemoryTable.setSelectionForeground(Color.black);
        virtualMemoryCell = new MemoryTableCellRenderer();
        try {
            virtualMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), virtualMemoryCell);
        } catch (ClassNotFoundException ex) {
            System.out.println("blet");
        }
        updateVirtualMemoryModel();
        virtualMemoryTable.setVisible(true);
    }      
    
    private void updateVirtualMemoryModel() {
        virtualMemoryTable.removeAll();
        virtualMemoryTable.repaint();
        Word[] vmem = vm.getMemory().getVirtualMemoryGui();
        for (int i=0; i < vm.getMemory().getSize(); i++) {
            int col = i % 0x10;
            int row = i / 0x10;
            if (vmem[i] == null) {
                vmem[i] = new Word(0x0);
            }
            if (charRepresentation) {
                virtualMemoryModel.setValueAt(vmem[i].toString(), row, col+1);
            } else {
                virtualMemoryModel.setValueAt(vmem[i].toInt(), row, col+1);
            }
        }
    }
    
    private void prepareStackModel () {
        stackModel = new DefaultListModel();
        stackList = new JList(stackModel);
        stackList.setVisible(true);

        updateStackModel();
    }
  
    private void updateStackModel() {
        if (vm != null) {
            stackModel.clear();
            int index = 0;
            System.out.println(vm.getSS() + " " + vm.getSP());
            for (int i=0; i< vm.getSP(); i++) {
                if (charRepresentation) {
                    stackModel.add(index, vm.getMemory().getVirtualMemoryGui()[vm.getSS()+i].toString());
                } else {
                    stackModel.add(index, vm.getMemory().getVirtualMemoryGui()[vm.getSS()+i].toInt());
                }
                index++;
            }
            stackList.repaint();
        }
    }
    
    private void setCharRepresentation(boolean charRep) {
        charRepresentation = charRep;
    }

}






