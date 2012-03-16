package GUI;

import rvm.*;
import rvm.VirtualMachine;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Ernestas Prisakaru OS GUI
 * @version 1.0
 */ 

public class Gui {
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
    ButtonGroup rbGroup;
    JRadioButton rbChar;
    JRadioButton rbNum;
    
    JTextArea taOutput;
    JScrollPane spOutput;
    JTextField tfInput;
    
    JFileChooser fc;
    
    private void initProject() throws FileNotFoundException {
        rm = new RM();
        vm = rm.startNewVM("src/program1");
    }

    public Gui() throws FileNotFoundException {
        frame = new JFrame();   
        initProject();
        initUI();
    }
    
    public final void initUI() {
        frame.setTitle("OS");
        //frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.add(createMainPage());
        frame.setVisible(true);
    }

    public static void main(String[] arg) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException err) {
            System.out.println(err);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui graphical = null;
                try {
                    graphical = new Gui();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
                graphical.frame.setVisible(true);
            }
        });
    }
 
// main page panel
public JPanel createMainPage() {
        JPanel panel = new JPanel( new GridBagLayout() );
        GridBagConstraints c1 = new GridBagConstraints();

        prepareRealMemoryModel();
        prepareVirtualMemoryModel();
        prepareStackModel();


        //panel.add(stackList);
        stackList.setSelectedIndex(-1);

        loadFile = new JButton("Load...");
        loadFile.setPreferredSize(new Dimension(20, 70));
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
        step.setPreferredSize(new Dimension(20, 70));
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // stepint
            }
        });
        
        run = new JButton("Run>>");
        run.setPreferredSize(new Dimension(20, 70));
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // stepint
            }
        });

        java.awt.GridBagConstraints gridBagConstraints;
        // ====== deliojimas
        spRealMemoryTable = new JScrollPane();
        spRealMemoryTable.setViewportView(realMemoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 769;
        gridBagConstraints.ipady = 234;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 9, 11, 10);
        panel.add(spRealMemoryTable, gridBagConstraints);

        spStackList = new JScrollPane();
        spStackList.setViewportView(stackList);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.ipady = 275;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 0);
        panel.add(spStackList, gridBagConstraints);

        
        step.setText("Step>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 0);
        panel.add(step, gridBagConstraints);

        loadFile.setText("Load ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        panel.add(loadFile, gridBagConstraints);

        spVirtualMemoryTable = new JScrollPane();
        spVirtualMemoryTable.setViewportView(virtualMemoryTable);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 553;
        gridBagConstraints.ipady = 256;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        panel.add(spVirtualMemoryTable, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        panel.add(run, gridBagConstraints);

        tfInput = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 553;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(tfInput, gridBagConstraints);

        taOutput = new JTextArea();
        spOutput = new JScrollPane();
        taOutput.setColumns(20);
        taOutput.setRows(5);
        spOutput.setViewportView(taOutput);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 553;
        gridBagConstraints.ipady = 73;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(spOutput, gridBagConstraints);

        panelMemoryRepresentation = new JPanel();
        panelMemoryRepresentation.setBorder(javax.swing.BorderFactory.createTitledBorder("Representation"));
        panelMemoryRepresentation.setLayout(new java.awt.GridBagLayout());

        rbChar = new JRadioButton();
        rbChar.setText("Char");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 12, 0, 30);
        panelMemoryRepresentation.add(rbChar, gridBagConstraints);

        rbNum = new JRadioButton();
        rbNum.setText("Num");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 14, 0);
        panelMemoryRepresentation.add(rbNum, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = -12;
        gridBagConstraints.ipady = -4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 10);
        panel.add(panelMemoryRepresentation, gridBagConstraints);

        lbRegR = new JLabel();
        lbRegR.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 6, 0, 0);
        panel.add(lbRegR, gridBagConstraints);

        lbRegSF = new JLabel();
        lbRegSF.setText("SF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegSF, gridBagConstraints);

        lbRegIP = new JLabel();
        lbRegIP.setText("IP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegIP, gridBagConstraints);

        lbRegSP = new JLabel();
        lbRegSP.setText("SP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegSP, gridBagConstraints);
        
        tfRegR = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        panel.add(tfRegR, gridBagConstraints);
        
        tfRegSF = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(tfRegSF, gridBagConstraints);
        
        tfRegIP = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(tfRegIP, gridBagConstraints);
        
        tfRegSP = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(tfRegSP, gridBagConstraints);

        lbRegDS = new JLabel();
        lbRegDS.setText("DS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 6, 0, 0);
        panel.add(lbRegDS, gridBagConstraints);

        lbRegCS = new JLabel();
        lbRegCS.setText("CS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegCS, gridBagConstraints);

        lbRegSS = new JLabel();
        lbRegSS.setText("SS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegSS, gridBagConstraints);

        lbRegES = new JLabel();
        lbRegES.setText("ES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 0, 0);
        panel.add(lbRegES, gridBagConstraints);
        
        tfRegDS = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 21, 0, 10);
        panel.add(tfRegDS, gridBagConstraints);
        
        tfRegCS = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 21, 0, 10);
        panel.add(tfRegCS, gridBagConstraints);
        
        tfRegSS = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 21, 0, 10);
        panel.add(tfRegSS, gridBagConstraints);
        
        tfRegES = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 21, 0, 10);
        panel.add(tfRegES, gridBagConstraints);

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
        MemoryTableCellRenderer renderer= new MemoryTableCellRenderer();
        try {
            realMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
        } catch (ClassNotFoundException ex) {
            System.out.println("blet");
        }
        updateRealMemoryModel();
        realMemoryTable.setVisible(true);
    }
  
    private void updateRealMemoryModel() {
        Word[] mem = rm.getMemoryGui();
        for (int i=0; i < rvm.Constants.MEMORY_SIZE; i++) {
            int col = i % 0x10;
            int row = i / 0x10;
            if (mem[i] == null) {
                mem[i] = new Word(0x0);
            }
            realMemoryModel.setValueAt(mem[i].toString(), row, col+1);
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
        MemoryTableCellRenderer renderer= new MemoryTableCellRenderer();
        try {
            virtualMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
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
            virtualMemoryModel.setValueAt(vmem[i].toString(), row, col+1);
        }
    }
    
    private void prepareStackModel () {
        stackModel = new DefaultListModel();
        stackList = new JList();
        stackList.setVisible(true);

        updateStackModel();
        stackList.setModel(stackModel);
    }
  
    private void updateStackModel() {
        
    }

}






