package normal.gui;


import global.Category;
import global.Database;
import global.Month;
import global.Worker;
import global.gui.ErrorMessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;

import logic.BackUpAndRestore;
import logic.DateFormats;
import logic.SpecialCharacterDispatcher;
import logic.TextFieldHandeler;
import normal.gui.alloperationspanel.AllOperationsPanel_AllOperationsTableModel;
import normal.gui.alloperationspanel.AllOperationsPanel_ManualPanel_ViewButtonListener;
import normal.gui.alloperationspanel.Operations_SortByListener;
import normal.gui.dailyoperations.DailyOperationsPanel_AddOperationButtonListener;
import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableKeyListener;
import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableModel;
import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableMouseListener;
import normal.gui.itemspanel.Item;
import normal.gui.itemspanel.ItemsPanel_ItemsTableModel;
import normal.gui.itemspanel.ItemsPanel_ManualPanel_ViewButtonListener;
import normal.gui.itemspanel.ItemsSortByListener;

import org.jdesktop.swingx.autocomplete.Configurator;

import root.gui.RootUserSwitchingDialog;


/**
 *
 * @author Khaled
 */
public class NormalUserGUIFrame extends javax.swing.JFrame {

	
	private static final long serialVersionUID = -2511859348897938551L;
	private SpecialCharacterDispatcher specialCharacterispatcher;

	
	public NormalUserGUIFrame() {
        initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        backUpAndRestore = new BackUpAndRestore(this);
        items = new ArrayList<Item>();
        database = new Database(this);
        ToolBar = new javax.swing.JPanel();
        ToolBar_SearchButton = new javax.swing.JButton();
        ToolBar_ItemTextField = new javax.swing.JTextField();
        ToolBar_ItemLabel = new JLabel();
        TabbedPane = new javax.swing.JTabbedPane();
        DailyOperationPanel = new javax.swing.JPanel();
        DailyOperationPanel_ManualPanel = new javax.swing.JPanel();
        DailyOperationPanel_ManualPanel_ItemsComboBox = new javax.swing.JComboBox<>();
        DailyOperationPanel_ItemLabel = new javax.swing.JLabel();
        DailyOperationPanel_ManualPanel_PaidRadioButton = new JRadioButton();
        DailyOperationPanel_ManualPanel_ReturnedRadioButton = new JRadioButton();
        buttonGroup = new ButtonGroup();
        DailyOperationPanel_AmountLabel = new javax.swing.JLabel();
        DailyOperationPanel_NotesLabel = new javax.swing.JLabel();
        DailyOperationPanel_SellerLabel = new javax.swing.JLabel(); 
        DailyOperationPanel_ManualPanel_NotesTextField = new javax.swing.JTextField();
        DailyOperationPanel_ManualPanel_SellerComboBox = new javax.swing.JComboBox<Worker>();
        DailyOperationPanel_ManualPanel_AmountTextField = new javax.swing.JTextField();
        DailyOperationPanel_ManualPanel_AddOperationButton = new javax.swing.JButton();
        DailyOperationPanel_DailyOperationTablePanel = new javax.swing.JPanel();
        ScrollPane1 = new javax.swing.JScrollPane();
        DailyOperationPanel_DailyOperationTable = new javax.swing.JTable();
        dailyOperationsPanel_DailyOperationsTableModel = new DailyOperationsPanel_DailyOperationsTableModel(database);
        AllOperationsPanel = new javax.swing.JPanel();
        AllOperationsPanel_ManualPanel = new javax.swing.JPanel();
        AllOperationsPanel_ManualPanel_DateOfOperationsLabel = new javax.swing.JLabel();
        AllOperationsPanel_ManualPanel_MonthLabel = new javax.swing.JLabel();
        AllOperationsPanel_ManualPanel_YearLabel = new javax.swing.JLabel();
        AllOperationsPanel_ManualPanel_DayLabel = new javax.swing.JLabel();
        AllOperationsPanel_ManualPanel_MonthComboBox = new javax.swing.JComboBox();
        AllOperationsPanel_ManualPanel_DaySpinner = new javax.swing.JSpinner();
        AllOperationsPanel_ManualPanel_YearSpinner = new javax.swing.JSpinner();
        AllOperationsPanel_ManualPanel_ViewButton = new javax.swing.JButton();
        AllOperationsPanel_AllOperationsTablePanel = new javax.swing.JPanel();
        AllOperationsPanel_ManualPanel_SellerLabel = new JLabel();
        AllOperationPanel_ManualPanel_SellerComboBox = new JComboBox<Worker>();
        ScrollPane2 = new javax.swing.JScrollPane();
        AllOperationsPanel_AllOperationsTable = new javax.swing.JTable();
        allOperationsPanel_AllOperationsTableModel = new AllOperationsPanel_AllOperationsTableModel(database);
        allOperationsPanel_ManualPanel_ViewButtonListener = new AllOperationsPanel_ManualPanel_ViewButtonListener(this, AllOperationsPanel_AllOperationsTablePanel, allOperationsPanel_AllOperationsTableModel, AllOperationsPanel_ManualPanel_MonthComboBox, AllOperationsPanel_ManualPanel_DaySpinner, AllOperationsPanel_ManualPanel_YearSpinner, AllOperationPanel_ManualPanel_SellerComboBox);
        operations_SortByListener = new Operations_SortByListener(this, TabbedPane, getDailyOperationsPanel_DailyOperationsTableModel(), allOperationsPanel_AllOperationsTableModel, getDailyOperationPanel_DailyOperationTable(), AllOperationsPanel_AllOperationsTable);
        ItemsPanel = new javax.swing.JPanel();
        ItemsPanel_ManualPanel = new javax.swing.JPanel();
        ItemsPanel_ManualPanel_ViewButton = new javax.swing.JButton();
        ItemsPanel_ManualPanel_CategoryLabel = new javax.swing.JLabel();
        ItemsPanel_ManualPanel_CategoryComboBox = new javax.swing.JComboBox<>();
        ItemsPanel_ItemsTablePanel = new javax.swing.JPanel();
        ScrollPane3 = new javax.swing.JScrollPane();
        ItemsPanel_ItemsTable = new javax.swing.JTable();
        itemsPanel_ItemsTableModel = new ItemsPanel_ItemsTableModel(database);
        itemsPanel_ManualPanel_ViewButtonListener = new ItemsPanel_ManualPanel_ViewButtonListener(this, ItemsPanel_ItemsTablePanel, itemsPanel_ItemsTableModel, ItemsPanel_ManualPanel_CategoryComboBox);
        itemsSortByListener = new ItemsSortByListener(this, ItemsPanel_ItemsTable, itemsPanel_ItemsTableModel);
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        FileMenu_ImportDatabaseToFile = new javax.swing.JMenuItem();
        FileMenu_ExportDatabaseFromFile = new javax.swing.JMenuItem();
        MenuItemSeparator1 = new javax.swing.JPopupMenu.Separator();
        FileMenu_Exit = new javax.swing.JMenuItem();
        PreferencesMenu = new javax.swing.JMenu();
        PreferencesMenu_SwitchToRootUser = new javax.swing.JMenuItem();
        PreferencesMenu_Refresh = new javax.swing.JMenuItem();
        searchButtonListener = new SearchButtonListener(this, TabbedPane, ToolBar_ItemTextField, getDailyOperationsPanel_DailyOperationsTableModel(), itemsPanel_ManualPanel_ViewButtonListener, AllOperationsPanel_ManualPanel_MonthComboBox, AllOperationsPanel_ManualPanel_YearSpinner,allOperationsPanel_AllOperationsTableModel, AllOperationsPanel_AllOperationsTablePanel,DailyOperationPanel_DailyOperationTablePanel, AllOperationPanel_ManualPanel_SellerComboBox, DailyOperationPanel_ManualPanel_SellerComboBox);
        dailyOperationsPanel_AddOperationButtonListener = new DailyOperationsPanel_AddOperationButtonListener(this, DailyOperationPanel_ManualPanel_ItemsComboBox, DailyOperationPanel_ManualPanel_AmountTextField, DailyOperationPanel_ManualPanel_PaidRadioButton, DailyOperationPanel_ManualPanel_ReturnedRadioButton, DailyOperationPanel_ManualPanel_NotesTextField, getDailyOperationsPanel_DailyOperationsTableModel(), getDailyOperationPanel_DailyOperationTable(), DailyOperationPanel_ManualPanel_SellerComboBox);
        specialCharacterispatcher = new SpecialCharacterDispatcher(this, TabbedPane, DailyOperationPanel, DailyOperationPanel_ManualPanel_ItemsComboBox);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sky Tech");
        setName("Normal user GUI"); 
        
        removeEnterKeyBinding(DailyOperationPanel_DailyOperationTable);
        
//////////////////////////////////////////////////////////////////////////////////////////////////
        database.connect();
/////////////////////////////////////////////////////////////////////////////////////////////////
        
        

        ToolBar.setLayout(new java.awt.GridBagLayout());

        ToolBar_SearchButton.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ToolBar.add(ToolBar_SearchButton, gridBagConstraints);

        ToolBar_ItemLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ToolBar_ItemLabel.setText("Item :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ToolBar.add(ToolBar_ItemLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ToolBar.add(ToolBar_ItemTextField, gridBagConstraints);
        
        TabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Normal user", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));

        DailyOperationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Daily operations", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        DailyOperationPanel_ManualPanel.setLayout(new java.awt.GridBagLayout());

        DailyOperationPanel_ItemLabel.setText("Item :");
        DailyOperationPanel_ItemLabel.setDisplayedMnemonic(KeyEvent.VK_I);
        DailyOperationPanel_ItemLabel.setLabelFor(DailyOperationPanel_ManualPanel_ItemsComboBox);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ItemLabel, gridBagConstraints);

        DailyOperationPanel_ManualPanel_PaidRadioButton.setText("Paid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_PaidRadioButton, gridBagConstraints);

        DailyOperationPanel_ManualPanel_ReturnedRadioButton.setText("Returned");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_ReturnedRadioButton, gridBagConstraints);

        DailyOperationPanel_ManualPanel_PaidRadioButton.setSelected(true);
        buttonGroup.add(DailyOperationPanel_ManualPanel_PaidRadioButton);
        buttonGroup.add(DailyOperationPanel_ManualPanel_ReturnedRadioButton);
        
        DailyOperationPanel_AmountLabel.setText("Amount :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_AmountLabel, gridBagConstraints);

        DailyOperationPanel_NotesLabel.setText("Notes :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_NotesLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_NotesTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_AmountTextField, gridBagConstraints);

        DailyOperationPanel_SellerLabel.setText("Seller :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_SellerLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_SellerComboBox, gridBagConstraints);
        
        DailyOperationPanel_ManualPanel_AddOperationButton.setText("Add operation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_AddOperationButton, gridBagConstraints);
        DailyOperationPanel_ManualPanel_AddOperationButton.setMnemonic(KeyEvent.VK_A);
        
        loadItemComboBox();
        loadSellerComboBox();
        
        Configurator.enableAutoCompletion(DailyOperationPanel_ManualPanel_ItemsComboBox);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        DailyOperationPanel_ManualPanel.add(DailyOperationPanel_ManualPanel_ItemsComboBox, gridBagConstraints);
        
        DailyOperationPanel_DailyOperationTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(new Date()), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        getDailyOperationPanel_DailyOperationTable().setModel(getDailyOperationsPanel_DailyOperationsTableModel());
        ScrollPane1.setViewportView(getDailyOperationPanel_DailyOperationTable());
        if (getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumnCount() > 0) {
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(0).setPreferredWidth(65);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(0).setMaxWidth(65);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(1).setPreferredWidth(270);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(1).setMaxWidth(290);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(2).setPreferredWidth(65);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(2).setMaxWidth(65);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(3).setPreferredWidth(220);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(3).setMaxWidth(220);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(4).setPreferredWidth(50);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(4).setMaxWidth(50);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(5).setPreferredWidth(50);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(5).setMaxWidth(50);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(6).setPreferredWidth(220);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(6).setMaxWidth(220);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(7).setPreferredWidth(100);
        	getDailyOperationPanel_DailyOperationTable().getColumnModel().getColumn(7).setMaxWidth(100);
        }
        
        
///////////////////////////////////////////////////////////////////////////////////////////////////////      
        try {
			dailyOperationsPanel_DailyOperationsTableModel.loadFromDatabase(null, "");
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(this, e.getMessage());
			e.printStackTrace();
		}
        getDailyOperationPanel_DailyOperationTable().addKeyListener(new DailyOperationsPanel_DailyOperationsTableKeyListener(this, getDailyOperationsPanel_DailyOperationsTableModel(), getDailyOperationPanel_DailyOperationTable(),DailyOperationPanel_ManualPanel_ItemsComboBox));
        getDailyOperationPanel_DailyOperationTable().addMouseListener(new DailyOperationsPanel_DailyOperationsTableMouseListener(this, getDailyOperationsPanel_DailyOperationsTableModel(), getDailyOperationPanel_DailyOperationTable(),DailyOperationPanel_ManualPanel_ItemsComboBox));
        DailyOperationPanel_ManualPanel_AddOperationButton.addActionListener(dailyOperationsPanel_AddOperationButtonListener);
        getDailyOperationPanel_DailyOperationTable().getTableHeader().addMouseListener(operations_SortByListener);
        DailyOperationPanel_ManualPanel_AmountTextField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			dailyOperationsPanel_AddOperationButtonListener.addOperation();
        		}
        	}
		});
        TextFieldHandeler.orientationHandeler((JComponent) DailyOperationPanel_ManualPanel_ItemsComboBox.getEditor().getEditorComponent());
        
        DailyOperationPanel_ManualPanel_NotesTextField.addKeyListener(DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners()[DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners().length - 1]);
        TextFieldHandeler.orientationHandeler(DailyOperationPanel_ManualPanel_NotesTextField);
        
        DailyOperationPanel_ManualPanel_PaidRadioButton.addKeyListener(DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners()[DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners().length - 1]);
        DailyOperationPanel_ManualPanel_ReturnedRadioButton.addKeyListener(DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners()[DailyOperationPanel_ManualPanel_AmountTextField.getKeyListeners().length - 1]);
//////////////////////////////////////////////////////////////////////////////////////////////////////

        
        javax.swing.GroupLayout DailyOperationPanel_DailyOperationTablePanelLayout = new javax.swing.GroupLayout(DailyOperationPanel_DailyOperationTablePanel);
        DailyOperationPanel_DailyOperationTablePanel.setLayout(DailyOperationPanel_DailyOperationTablePanelLayout);
        DailyOperationPanel_DailyOperationTablePanelLayout.setHorizontalGroup(
            DailyOperationPanel_DailyOperationTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DailyOperationPanel_DailyOperationTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane1)
                .addContainerGap())
        );
        DailyOperationPanel_DailyOperationTablePanelLayout.setVerticalGroup(
            DailyOperationPanel_DailyOperationTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DailyOperationPanel_DailyOperationTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout DailyOperationPanelLayout = new javax.swing.GroupLayout(DailyOperationPanel);
        DailyOperationPanel.setLayout(DailyOperationPanelLayout);
        DailyOperationPanelLayout.setHorizontalGroup(
            DailyOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DailyOperationPanel_ManualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DailyOperationPanel_DailyOperationTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DailyOperationPanelLayout.setVerticalGroup(
            DailyOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DailyOperationPanelLayout.createSequentialGroup()
                .addComponent(DailyOperationPanel_ManualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DailyOperationPanel_DailyOperationTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Daily operations", DailyOperationPanel);

        AllOperationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "All operations", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        AllOperationsPanel_ManualPanel.setLayout(new java.awt.GridBagLayout());

        AllOperationsPanel_ManualPanel_DateOfOperationsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AllOperationsPanel_ManualPanel_DateOfOperationsLabel.setText("Date of operations  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_DateOfOperationsLabel, gridBagConstraints);

        AllOperationsPanel_ManualPanel_MonthLabel.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_MonthLabel, gridBagConstraints);

        AllOperationsPanel_ManualPanel_YearLabel.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_YearLabel, gridBagConstraints);

        AllOperationsPanel_ManualPanel_DayLabel.setText("Day");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 5, 5);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_DayLabel, gridBagConstraints);

        AllOperationsPanel_ManualPanel_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_MonthComboBox, gridBagConstraints);

        AllOperationsPanel_ManualPanel_DaySpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        AllOperationsPanel_ManualPanel_DaySpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_DaySpinner, gridBagConstraints);

        AllOperationsPanel_ManualPanel_YearSpinner.setModel(new javax.swing.SpinnerNumberModel(2015, 2015, 2150, 1));
        AllOperationsPanel_ManualPanel_YearSpinner.setEditor(new JSpinner.NumberEditor(AllOperationsPanel_ManualPanel_YearSpinner,"#"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_YearSpinner, gridBagConstraints);

        AllOperationsPanel_ManualPanel_SellerLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AllOperationsPanel_ManualPanel_SellerLabel.setText("Seller  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_SellerLabel, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        AllOperationsPanel_ManualPanel.add(AllOperationPanel_ManualPanel_SellerComboBox, gridBagConstraints);
        
        AllOperationsPanel_ManualPanel_ViewButton.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        AllOperationsPanel_ManualPanel.add(AllOperationsPanel_ManualPanel_ViewButton, gridBagConstraints);

        AllOperationsPanel_AllOperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        AllOperationsPanel_AllOperationsTable.setModel(allOperationsPanel_AllOperationsTableModel);
        ScrollPane2.setViewportView(AllOperationsPanel_AllOperationsTable);
        if (AllOperationsPanel_AllOperationsTable.getColumnModel().getColumnCount() > 0) {
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(0).setPreferredWidth(65);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(0).setMaxWidth(65);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(1).setPreferredWidth(270);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(1).setMaxWidth(290);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(2).setPreferredWidth(65);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(2).setMaxWidth(65);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(3).setMaxWidth(220);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(4).setMaxWidth(50);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(5).setMaxWidth(50);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(6).setPreferredWidth(220);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(6).setMaxWidth(220);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        	AllOperationsPanel_AllOperationsTable.getColumnModel().getColumn(7).setMaxWidth(100);
        }
        
        
///////////////////////////////////////////////////////////////////////////////////////////////////////      
//        AllOperationsPanel_AllOperationsTable.addMouseListener(new AllOperationsPanel_AllOperationsTableMousListener(this, allOperationsPanel_AllOperationsTableModel, AllOperationsPanel_AllOperationsTable));
//        AllOperationsPanel_AllOperationsTable.addKeyListener(new AllOperationsPanel_AllOperationsTableKeyListener(this, allOperationsPanel_AllOperationsTableModel, AllOperationsPanel_AllOperationsTable));
        AllOperationsPanel_ManualPanel_ViewButton.addActionListener(allOperationsPanel_ManualPanel_ViewButtonListener);
        AllOperationsPanel_AllOperationsTable.getTableHeader().addMouseListener(operations_SortByListener);
//////////////////////////////////////////////////////////////////////////////////////////////////////

        javax.swing.GroupLayout AllOperationsPanel_AllOperationsTablePanelLayout = new javax.swing.GroupLayout(AllOperationsPanel_AllOperationsTablePanel);
        AllOperationsPanel_AllOperationsTablePanel.setLayout(AllOperationsPanel_AllOperationsTablePanelLayout);
        AllOperationsPanel_AllOperationsTablePanelLayout.setHorizontalGroup(
            AllOperationsPanel_AllOperationsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllOperationsPanel_AllOperationsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane2)
                .addContainerGap())
        );
        AllOperationsPanel_AllOperationsTablePanelLayout.setVerticalGroup(
            AllOperationsPanel_AllOperationsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllOperationsPanel_AllOperationsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout AllOperationsPanelLayout = new javax.swing.GroupLayout(AllOperationsPanel);
        AllOperationsPanel.setLayout(AllOperationsPanelLayout);
        AllOperationsPanelLayout.setHorizontalGroup(
            AllOperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AllOperationsPanel_AllOperationsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AllOperationsPanel_ManualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AllOperationsPanelLayout.setVerticalGroup(
            AllOperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllOperationsPanelLayout.createSequentialGroup()
                .addComponent(AllOperationsPanel_ManualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AllOperationsPanel_AllOperationsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("All operations", AllOperationsPanel);

        ItemsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ItemsPanel_ManualPanel.setLayout(new java.awt.GridBagLayout());

        ItemsPanel_ManualPanel_ViewButton.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_ViewButton, gridBagConstraints);
        

        ItemsPanel_ManualPanel_CategoryLabel.setText("Category :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CategoryLabel, gridBagConstraints);

        ItemsPanel_ManualPanel_CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"all", "electric", "computer", "mobile" , "maintenance" , "other" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CategoryComboBox, gridBagConstraints);

        ItemsPanel_ItemsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ItemsPanel_ItemsTable.setModel(itemsPanel_ItemsTableModel);
        ScrollPane3.setViewportView(ItemsPanel_ItemsTable);

        
        
        
//////////////////////////////////////////////////////////////////////////////////////////////////////
        ItemsPanel_ManualPanel_ViewButton.addActionListener(itemsPanel_ManualPanel_ViewButtonListener);
        ItemsPanel_ItemsTable.getTableHeader().addMouseListener(itemsSortByListener);
        ItemsPanel_ItemsTable.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if(e.getButton() == MouseEvent.BUTTON3){
        			ItemsPanel_ItemsTable.getSelectionModel().clearSelection();
        		}
        	}
		});
        
//////////////////////////////////////////////////////////////////////////////////////////////////////

        
        
        
        
        javax.swing.GroupLayout ItemsPanel_ItemsTablePanelLayout = new javax.swing.GroupLayout(ItemsPanel_ItemsTablePanel);
        ItemsPanel_ItemsTablePanel.setLayout(ItemsPanel_ItemsTablePanelLayout);
        ItemsPanel_ItemsTablePanelLayout.setHorizontalGroup(
            ItemsPanel_ItemsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ItemsPanel_ItemsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane3)
                .addContainerGap())
        );
        ItemsPanel_ItemsTablePanelLayout.setVerticalGroup(
            ItemsPanel_ItemsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemsPanel_ItemsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ItemsPanelLayout = new javax.swing.GroupLayout(ItemsPanel);
        ItemsPanel.setLayout(ItemsPanelLayout);
        ItemsPanelLayout.setHorizontalGroup(
            ItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ItemsPanel_ManualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ItemsPanel_ItemsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ItemsPanelLayout.setVerticalGroup(
            ItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemsPanelLayout.createSequentialGroup()
                .addComponent(ItemsPanel_ManualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ItemsPanel_ItemsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Items", ItemsPanel);
        
////////////////////////////////////////////////////////////////////////////////////////////////
        ToolBar_SearchButton.addActionListener(searchButtonListener);
        ToolBar_ItemTextField.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			searchButtonListener.startSearching();
        		}
        	}
		});
        TextFieldHandeler.orientationHandeler(ToolBar_ItemTextField);
////////////////////////////////////////////////////////////////////////////////////////////////        

        FileMenu.setText("File");

        FileMenu_ImportDatabaseToFile.setText("Save database to file");
        FileMenu.add(FileMenu_ImportDatabaseToFile);

//        FileMenu_ExportDatabaseFromFile.setText("Load database from file");
//        FileMenu.add(FileMenu_ExportDatabaseFromFile);
        FileMenu.add(MenuItemSeparator1);

//        FileMenu_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        FileMenu_Exit.setText("Exit");
        FileMenu.add(FileMenu_Exit);

        
/////////////////////////////////////////////////////////////////////////////////////////
        FileMenu_Exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(NormalUserGUIFrame.this, "Do you want to save data to file before exit?", "Save to file and exit", JOptionPane.YES_NO_CANCEL_OPTION);
        		if(option == JOptionPane.YES_OPTION){
        			saveToDateNamedFile();
        			System.exit(0);
        		}if(option == JOptionPane.NO_OPTION){
        			System.exit(0);
        		}else {}
        	}
        });
        
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		int option = JOptionPane.showConfirmDialog(NormalUserGUIFrame.this, "Do you want to save data to file before exit?", "Save to file and exit", JOptionPane.YES_NO_CANCEL_OPTION);
        		if(option == JOptionPane.YES_OPTION){
        			saveToDateNamedFile();
        			System.exit(0);
        		}if(option == JOptionPane.NO_OPTION){
        			System.exit(0);
        		}else {}
        	}
		});
        
        FileMenu_ExportDatabaseFromFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				backUpAndRestore.loadFromFile();
			}
		});
        
        FileMenu_ImportDatabaseToFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backUpAndRestore.saveToFile();
			}
		});
/////////////////////////////////////////////////////////////////////////////////////////        
        
        
        
        
        MenuBar.add(FileMenu);

        PreferencesMenu.setText("Preferences");

        PreferencesMenu_SwitchToRootUser.setText("Switch to root user");
        PreferencesMenu.add(PreferencesMenu_SwitchToRootUser);
        
        PreferencesMenu_Refresh.setText("Refresh");
        PreferencesMenu.add(PreferencesMenu_Refresh);
        PreferencesMenu_Refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        MenuBar.add(PreferencesMenu);

        setJMenuBar(MenuBar);
        
        
/////////////////////////////////////////////////////////////////////////////////
        PreferencesMenu_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
        
        PreferencesMenu_SwitchToRootUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RootUserSwitchingDialog rootUserOptionsDialog = new RootUserSwitchingDialog(NormalUserGUIFrame.this, specialCharacterispatcher);
		        Dimension dimension = NormalUserGUIFrame.this.getSize();
		        rootUserOptionsDialog.setLocation(dimension.width/2-rootUserOptionsDialog.getSize().width/2, dimension.height/2-rootUserOptionsDialog.getSize().height/2);
		        rootUserOptionsDialog.setVisible(true);
			}
		});
/////////////////////////////////////////////////////////////////////////////////////
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPane))
        );

        pack();
        
        
///////////////////////////////////////////////////////////////////////////
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(specialCharacterispatcher);     
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.gc();
///////////////////////////////////////////////////////////////////////////
        
        
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSellerComboBox() {
		try {
			DailyOperationPanel_ManualPanel_SellerComboBox.setModel(new javax.swing.DefaultComboBoxModel(getAllSellers()));
			AllOperationPanel_ManualPanel_SellerComboBox.setModel(new javax.swing.DefaultComboBoxModel(getAllSellers()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Worker[] getAllSellers() throws SQLException {
		String sql = "SELECT worker_id, worker_name FROM SkyTech.workers ORDER BY worker_name";
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql);
		
		ArrayList<Worker> workersNames = new ArrayList<Worker>();
		
		while(results.next()){
			int wokerId = results.getInt("worker_id");
			String storedWorkerName = results.getString("worker_name");
			workersNames.add(new Worker(wokerId, storedWorkerName));
		}
		
		Worker[] workersNamesArray = new Worker[workersNames.size()];  
		workersNames.toArray(workersNamesArray);
		
		results.close();
		statement.close();
		
		return workersNamesArray;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadItemComboBox() {
		try {
        	DailyOperationPanel_ManualPanel_ItemsComboBox.setModel(new javax.swing.DefaultComboBoxModel(getAllItems()));
        } catch (SQLException e2) {
        	ErrorMessage.showErrorMessage(this, e2.getMessage());
        	e2.printStackTrace();
        }
	}

	private void removeEnterKeyBinding(JTable table) {
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		    table.getActionMap().put("Enter", new AbstractAction() {
				private static final long serialVersionUID = -3773386847187833207L;
		        public void actionPerformed(ActionEvent ae) {
		            
		        }
		    });
		}

    private Item[] getAllItems() throws SQLException{
		String sql = "SELECT item_id, name, buying_price, amount, category FROM SkyTech.items ORDER BY name";
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql);
		
		while(results.next()){
			int itemId = results.getInt("item_id");
			String name = results.getString("name");
			double price = results.getDouble("buying_price");
			int amount = results.getInt("amount");
			Category category = Category.valueOf(results.getString("category"));
			
			items.add(new Item(itemId, name, price, amount, category));
		}
		
		Item[] itemsArray = new Item[items.size()];  
		items.toArray(itemsArray);
		
		results.close();
		statement.close();
		
		return itemsArray;
    }
    
    public void refresh() {
		database.connect();
		
		try {
			dailyOperationsPanel_DailyOperationsTableModel.loadFromDatabase(null, "");
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(this, e1.getMessage());
			e1.printStackTrace();
		}
		DailyOperationPanel_DailyOperationTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(new Date()), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
		
		allOperationsPanel_AllOperationsTableModel.operations.clear();
		allOperationsPanel_AllOperationsTableModel.setLastSQL(null);
		allOperationsPanel_AllOperationsTableModel.fireTableDataChanged();
		AllOperationsPanel_AllOperationsTablePanel.setBorder(javax.swing.BorderFactory.
				createTitledBorder(null, "Date", javax.swing.border.TitledBorder.CENTER, 
						javax.swing.border.TitledBorder.ABOVE_TOP, 
						new java.awt.Font("Tahoma", 1, 12)));
		
		itemsPanel_ItemsTableModel.items.clear();
		itemsPanel_ItemsTableModel.setLastSQL(null);
		itemsPanel_ItemsTableModel.fireTableDataChanged();
		ItemsPanel_ItemsTablePanel.setBorder(javax.swing.BorderFactory.
				createTitledBorder(null, "Category", javax.swing.border.TitledBorder.CENTER, 
				javax.swing.border.TitledBorder.ABOVE_TOP, 
				new java.awt.Font("Tahoma", 1, 12)));
		
	}
    
    private void saveToDateNamedFile() {
		try {
			File file = new File(DateFormats.getSimpleDateFormat(new Date())+".sql");
			backUpAndRestore.saveToFileNamed(file).waitFor();
			Toolkit.getDefaultToolkit().beep();
		} catch (IOException e1) {
			ErrorMessage.showErrorMessage(NormalUserGUIFrame.this, e1.getMessage());
			e1.printStackTrace();
		} catch (InterruptedException e) {
			ErrorMessage.showErrorMessage(NormalUserGUIFrame.this, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
    	
    	
//////////////////////////////////////////////////////////////////////////////////////////////////    	
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2:00"));
//////////////////////////////////////////////////////////////////////////////////////////////////
		
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIDefaults defaults = javax.swing.UIManager.getLookAndFeelDefaults();
    	            defaults.put("Table.gridColor", new Color (214,217,223));
    	            defaults.put("Table.showGrid", true);
    	            defaults.put("Table.intercellSpacing", new Dimension(1, 1));
    	            defaults.put("TableHeader.font", new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 14));
    	            defaults.put("Table.font", new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                    defaults.put("Table.rowHeight", 24);
                    defaults.put("defaultFont", new Font(Font.SANS_SERIF, Font.PLAIN, 14));
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NormalUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NormalUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NormalUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NormalUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NormalUserGUIFrame().setVisible(true);
            }
        });
    }

    public javax.swing.JTable getDailyOperationPanel_DailyOperationTable() {
		return DailyOperationPanel_DailyOperationTable;
	}

	public DailyOperationsPanel_DailyOperationsTableModel getDailyOperationsPanel_DailyOperationsTableModel() {
		return dailyOperationsPanel_DailyOperationsTableModel;
	}

    private ItemsSortByListener itemsSortByListener;
    private BackUpAndRestore backUpAndRestore;
    private ArrayList<Item> items;
    private javax.swing.JPanel AllOperationsPanel;
    private javax.swing.JTable AllOperationsPanel_AllOperationsTable;
    private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
    private javax.swing.JPanel AllOperationsPanel_AllOperationsTablePanel;
    private javax.swing.JPanel AllOperationsPanel_ManualPanel;
    private javax.swing.JLabel AllOperationsPanel_ManualPanel_DateOfOperationsLabel;
    private javax.swing.JLabel AllOperationsPanel_ManualPanel_DayLabel;
    private javax.swing.JSpinner AllOperationsPanel_ManualPanel_DaySpinner;
    private javax.swing.JComboBox<Month> AllOperationsPanel_ManualPanel_MonthComboBox;
    private javax.swing.JLabel AllOperationsPanel_ManualPanel_MonthLabel;
    private javax.swing.JButton AllOperationsPanel_ManualPanel_ViewButton;
    private javax.swing.JLabel AllOperationsPanel_ManualPanel_YearLabel;
    private javax.swing.JSpinner AllOperationsPanel_ManualPanel_YearSpinner;
    private JLabel AllOperationsPanel_ManualPanel_SellerLabel;
    private JComboBox<Worker> AllOperationPanel_ManualPanel_SellerComboBox;
    private AllOperationsPanel_ManualPanel_ViewButtonListener allOperationsPanel_ManualPanel_ViewButtonListener;
    private javax.swing.JPanel DailyOperationPanel;
    private javax.swing.JTable DailyOperationPanel_DailyOperationTable;
    private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
    private javax.swing.JPanel DailyOperationPanel_DailyOperationTablePanel;
    private javax.swing.JLabel DailyOperationPanel_ItemLabel;
    private javax.swing.JLabel DailyOperationPanel_AmountLabel;
    private javax.swing.JLabel DailyOperationPanel_SellerLabel;
    private javax.swing.JPanel DailyOperationPanel_ManualPanel;
    private javax.swing.JButton DailyOperationPanel_ManualPanel_AddOperationButton;
    private javax.swing.JTextField DailyOperationPanel_ManualPanel_AmountTextField;
    private javax.swing.JTextField DailyOperationPanel_ManualPanel_NotesTextField;
    private ButtonGroup buttonGroup;
    private javax.swing.JRadioButton DailyOperationPanel_ManualPanel_PaidRadioButton;
    private javax.swing.JRadioButton DailyOperationPanel_ManualPanel_ReturnedRadioButton;
    private javax.swing.JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox;
    private javax.swing.JComboBox<Worker> DailyOperationPanel_ManualPanel_SellerComboBox;
    private javax.swing.JLabel DailyOperationPanel_NotesLabel;
    private Operations_SortByListener operations_SortByListener;
    private Database database;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem FileMenu_Exit;
    private javax.swing.JMenuItem FileMenu_ExportDatabaseFromFile;
    private javax.swing.JMenuItem FileMenu_ImportDatabaseToFile;
    private javax.swing.JPanel ItemsPanel;
    private javax.swing.JTable ItemsPanel_ItemsTable;
    private javax.swing.JPanel ItemsPanel_ItemsTablePanel;
    private ItemsPanel_ItemsTableModel itemsPanel_ItemsTableModel;
    private javax.swing.JPanel ItemsPanel_ManualPanel;
    private javax.swing.JComboBox<Category> ItemsPanel_ManualPanel_CategoryComboBox;
    private javax.swing.JLabel ItemsPanel_ManualPanel_CategoryLabel;
    private javax.swing.JButton ItemsPanel_ManualPanel_ViewButton;
    private ItemsPanel_ManualPanel_ViewButtonListener itemsPanel_ManualPanel_ViewButtonListener;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JPopupMenu.Separator MenuItemSeparator1;
    private javax.swing.JMenu PreferencesMenu;
    private javax.swing.JScrollPane ScrollPane1;
    private javax.swing.JScrollPane ScrollPane2;
    private javax.swing.JScrollPane ScrollPane3;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JButton ToolBar_SearchButton;
    private javax.swing.JTextField ToolBar_ItemTextField;
    private javax.swing.JLabel ToolBar_ItemLabel;
    private javax.swing.JMenuItem PreferencesMenu_SwitchToRootUser;
    private javax.swing.JMenuItem PreferencesMenu_Refresh;
    private SearchButtonListener searchButtonListener;
    private DailyOperationsPanel_AddOperationButtonListener dailyOperationsPanel_AddOperationButtonListener;
}
