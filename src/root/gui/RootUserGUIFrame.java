package root.gui;


import global.Category;
import global.Database;
import global.Month;
import global.Worker;
import global.gui.ErrorMessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;

import logic.BackUpAndRestore;
import logic.DateFormats;
import logic.SpecialCharacterDispatcher;
import logic.TextFieldHandeler;
import normal.gui.NormalUserGUIFrame;
import root.gui.itemspanel.ItemsPanel_ItemsTableKeyListener;
import root.gui.itemspanel.ItemsPanel_ItemsTableMouseListener;
import root.gui.itemspanel.RootItemPanelTableModel;
import root.gui.itemspanel.RootItemSortByListener;
import root.gui.itemspanel.RootItemsPanel_ManualPanel_ViewButtonListener;
import root.gui.operations.OperationsPanel_ViewButtonListener;
import root.gui.operations.OperationsTableKeyListener;
import root.gui.operations.OperationsTableMouseListener;
import root.gui.operations.RootOperationsSortByListener;
import root.gui.operations.RootOperationsTableModel;
import root.gui.profitspanel.ProfitsPanel_ViewButtonListener;
import root.gui.profitspanel.ProfitsSortByListener;
import root.gui.profitspanel.ProfitsTableModel;

/**
 *
 * @author Khaled
 */
public class RootUserGUIFrame extends javax.swing.JFrame {

    
	private static final long serialVersionUID = 5429216761090914319L;
	private RootItemSortByListener rootItemSortByListener;
	private ProfitsSortByListener profitsSortByListener;
	private RootOperationsSortByListener rootOperationsSortByListener;
	private SpecialCharacterDispatcher specialCharacterDispatcher;
	
    public RootUserGUIFrame() {
        initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        backUpAndRestore = new BackUpAndRestore(this);
        database = new Database(this);
        TabbedPane = new javax.swing.JTabbedPane();
        ItemsPanel = new javax.swing.JPanel();
        ItemsPanel_ManualPanel = new javax.swing.JPanel();
        ItemsPanel_ManualPanel_ViewButton = new javax.swing.JButton();
        ItemsPanel_ManualPanel_CategoryComboBox = new javax.swing.JComboBox<>();
        ItemsPanel_ManualPanel_CategoryLabel = new javax.swing.JLabel();
        ItemsPanel_ManualPanel_CapitalLabel = new javax.swing.JLabel();
        ItemsPanel_ManualPanel_CapitalTextField = new javax.swing.JTextField();
        ItemsPanel_ManualPanel_AddItemButton = new javax.swing.JButton();
        ItemsPanel_ManualPanel_AddToItemButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        ItemsPanel_ItemsTablePanel = new javax.swing.JPanel();
        ScrollPane1 = new javax.swing.JScrollPane();
        ItemsPanel_ItemsTable = new javax.swing.JTable();
        rootItemPanelTableModel = new RootItemPanelTableModel(database, ItemsPanel_ManualPanel_CapitalTextField);
        rootItemsPanel_ManualPanel_ViewButtonListener = new RootItemsPanel_ManualPanel_ViewButtonListener(this, ItemsPanel_ItemsTablePanel, rootItemPanelTableModel, ItemsPanel_ManualPanel_CategoryComboBox);
        rootItemSortByListener = new RootItemSortByListener(this, rootItemPanelTableModel, ItemsPanel_ItemsTable);
        ProfitsPanel = new javax.swing.JPanel();
        ProfitsPanel_ManualPanel = new javax.swing.JPanel();
        ProfitsPanel_ManualPanel_DateOfOperationsLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_MonthLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_YearLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_DayLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_MonthComboBox = new javax.swing.JComboBox<>();
        ProfitsPanel_ManualPanel_DaySpinner = new javax.swing.JSpinner();
        ProfitsPanel_ManualPanel_YearSpinner = new javax.swing.JSpinner();
        ProfitsPanel_ManualPanel_CategoryLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_ViewButton = new javax.swing.JButton();
        ProfitsPanel_ManualPanel_CategoryComboBox = new javax.swing.JComboBox<>();
        ProfitsPanel_ManualPanel_ProfitsLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_SellersLabel = new javax.swing.JLabel();
        ProfitsPanel_ManualPanel_ProfitsTextFiled = new javax.swing.JTextField();
        ProfitsPanel_ManualPanel_SellerComboBox = new JComboBox<Worker>();
        ProfitsPanel_ProfitsTablePanel = new javax.swing.JPanel();
        profitsTableModel = new ProfitsTableModel(database, ProfitsPanel_ManualPanel_ProfitsTextFiled);
        ScrollPane2 = new javax.swing.JScrollPane();
        ProfitsPanel_ProfitsTable = new javax.swing.JTable();
        profitsSortByListener = new ProfitsSortByListener(this, ProfitsPanel_ProfitsTable, profitsTableModel);
        OperationsPanel = new javax.swing.JPanel();
        OperationsPanel_ManualPanel = new javax.swing.JPanel();
        OperationsPanel_ManualPanel_DateOfOperations = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_MonthLabel = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_YearLabel = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_DayLabel = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_MonthComboBox = new javax.swing.JComboBox<>();
        OperationsPanel_ManualPanel_DaySpinner = new javax.swing.JSpinner();
        OperationsPanel_ManualPanel_YearSpinner = new javax.swing.JSpinner();
        OperationsPanel_ManualPanel_ViewButton = new javax.swing.JButton();
        OperationsPanel_ManualPanel_CategoryLabel = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_CategoryComboBox = new javax.swing.JComboBox<>();
        OperationsPanel_ManualPanel_IncomeLabel = new javax.swing.JLabel();
        OperationsPanel_ManualPanel_IncomeTextField = new javax.swing.JTextField();
        OperationsPanel_ManualPanel_SellersLabel = new JLabel();
        OperationsPanel_ManualPanel_SellerComboBox = new JComboBox<Worker>();
        OperationsPanel_OperationsTablePanel = new javax.swing.JPanel();
        ScrollPane3 = new javax.swing.JScrollPane();
        OperationsPanel_OperationsTable = new javax.swing.JTable();
        operationsTableModel = new RootOperationsTableModel(database, OperationsPanel_ManualPanel_IncomeTextField);
        rootOperationsSortByListener = new RootOperationsSortByListener(this, OperationsPanel_OperationsTable, operationsTableModel);
        ToolBar = new javax.swing.JPanel();
        ToolBar_SearchButton = new javax.swing.JButton();
        ToolBar_ItemLabel = new javax.swing.JLabel();
        ToolBar_ItemTextField = new javax.swing.JTextField();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        FileMenu_ImportDatabaseToFile = new javax.swing.JMenuItem();
        FileMenu_ExportDatabaseFromFile = new javax.swing.JMenuItem();
        MenuItemSeparator1 = new javax.swing.JPopupMenu.Separator();
        FileMenu_Exit = new javax.swing.JMenuItem();
        PreferencesMenu = new javax.swing.JMenu();
        PreferencesMenu_SwitchToNormalUser = new javax.swing.JMenuItem();
        PreferencesMenu_Refresh = new javax.swing.JMenuItem();
        PreferencesMenu_ClearOperations = new javax.swing.JMenuItem();
        rootSearchButtonListener = new RootSearchButtonListener(this, TabbedPane, ToolBar_ItemTextField, OperationsPanel_ManualPanel_MonthComboBox, OperationsPanel_ManualPanel_YearSpinner, operationsTableModel, OperationsPanel_OperationsTablePanel, OperationsPanel_ManualPanel_CategoryComboBox, ProfitsPanel_ManualPanel_DaySpinner, ProfitsPanel_ManualPanel_MonthComboBox, ProfitsPanel_ManualPanel_YearSpinner, ProfitsPanel_ManualPanel_CategoryComboBox, ProfitsPanel_ProfitsTablePanel, profitsTableModel, rootItemsPanel_ManualPanel_ViewButtonListener, OperationsPanel_ManualPanel_SellerComboBox, ProfitsPanel_ManualPanel_SellerComboBox);
        specialCharacterDispatcher = new SpecialCharacterDispatcher(this, TabbedPane, ItemsPanel, ToolBar_ItemTextField);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sky Tech");
        setName("Root User GUI");
        
//////////////////////////////////////////////////////////////////////////////////////////////////
        database.connect();
/////////////////////////////////////////////////////////////////////////////////////////////////        

        TabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Root user", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ItemsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 5, 0};
        ItemsPanel_ManualPanel.setLayout(jPanel4Layout);

        ItemsPanel_ManualPanel_ViewButton.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_ViewButton, gridBagConstraints);

        ItemsPanel_ManualPanel_CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"all", "electric", "computer", "mobile" , "maintenance" , "other" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CategoryComboBox, gridBagConstraints);

        ItemsPanel_ManualPanel_CategoryLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemsPanel_ManualPanel_CategoryLabel.setText("Category :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CategoryLabel, gridBagConstraints);

        ItemsPanel_ManualPanel_CapitalLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ItemsPanel_ManualPanel_CapitalLabel.setText("Avaliable capital :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CapitalLabel, gridBagConstraints);

        ItemsPanel_ManualPanel_CapitalTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_CapitalTextField, gridBagConstraints);
        ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(0));

        ItemsPanel_ManualPanel_AddItemButton.setText("Add Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_AddItemButton, gridBagConstraints);
        ItemsPanel_ManualPanel_AddItemButton.setMnemonic(KeyEvent.VK_A);
        
        ItemsPanel_ManualPanel_AddToItemButton.setText("Add to Item (Space)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ItemsPanel_ManualPanel.add(ItemsPanel_ManualPanel_AddToItemButton, gridBagConstraints);
        ItemsPanel_ManualPanel_AddToItemButton.setMnemonic(KeyEvent.VK_I);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        ItemsPanel_ManualPanel.add(jSeparator1, gridBagConstraints);

        ItemsPanel_ItemsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ItemsPanel_ItemsTable.setModel(rootItemPanelTableModel);
        ScrollPane1.setViewportView(ItemsPanel_ItemsTable);
        if (ItemsPanel_ItemsTable.getColumnModel().getColumnCount() > 0) {
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(0).setPreferredWidth(55);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(0).setMaxWidth(55);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(1).setPreferredWidth(240);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(1).setMaxWidth(290);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(2).setPreferredWidth(105);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(2).setMaxWidth(105);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(3).setPreferredWidth(105);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(3).setMaxWidth(105);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(4).setPreferredWidth(65);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(4).setMaxWidth(65);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(5).setMaxWidth(90);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(6).setPreferredWidth(220);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(6).setMaxWidth(220);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(7).setPreferredWidth(220);
        	ItemsPanel_ItemsTable.getColumnModel().getColumn(7).setMaxWidth(220);
        }
        
        
        
////////////////////////////////////////////////////////////////////////////////////////////////////        
        ItemsPanel_ManualPanel_ViewButton.addActionListener(rootItemsPanel_ManualPanel_ViewButtonListener);
        ItemsPanel_ManualPanel_AddItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRootItem();
			}
		});
        ItemsPanel_ManualPanel_AddToItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAmountToRootItem();
			}
		});
        ItemsPanel_ItemsTable.addMouseListener(new ItemsPanel_ItemsTableMouseListener(this, rootItemPanelTableModel, ItemsPanel_ItemsTable));
        ItemsPanel_ItemsTable.getTableHeader().addMouseListener(rootItemSortByListener);
        ItemsPanel_ItemsTable.addKeyListener(new ItemsPanel_ItemsTableKeyListener(this, rootItemPanelTableModel, ItemsPanel_ItemsTable));
///////////////////////////////////////////////////////////////////////////////////////////////////       
        
        
        
        javax.swing.GroupLayout ItemsPanel_ItemsTablePanelLayout = new javax.swing.GroupLayout(ItemsPanel_ItemsTablePanel);
        ItemsPanel_ItemsTablePanel.setLayout(ItemsPanel_ItemsTablePanelLayout);
        ItemsPanel_ItemsTablePanelLayout.setHorizontalGroup(
            ItemsPanel_ItemsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemsPanel_ItemsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane1)
                .addContainerGap())
        );
        ItemsPanel_ItemsTablePanelLayout.setVerticalGroup(
            ItemsPanel_ItemsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemsPanel_ItemsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
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

        ProfitsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items profits", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ProfitsPanel_ManualPanel.setLayout(new java.awt.GridBagLayout());

        ProfitsPanel_ManualPanel_DateOfOperationsLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ProfitsPanel_ManualPanel_DateOfOperationsLabel.setText("Date of operations  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_DateOfOperationsLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_MonthLabel.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_MonthLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_YearLabel.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_YearLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_DayLabel.setText("Day");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_DayLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_MonthComboBox, gridBagConstraints);

        ProfitsPanel_ManualPanel_DaySpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        ProfitsPanel_ManualPanel_DaySpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_DaySpinner, gridBagConstraints);

        ProfitsPanel_ManualPanel_YearSpinner.setModel(new javax.swing.SpinnerNumberModel(2015, 2015, 2150, 1));
        ProfitsPanel_ManualPanel_YearSpinner.setEditor(new JSpinner.NumberEditor(ProfitsPanel_ManualPanel_YearSpinner, "#"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_YearSpinner, gridBagConstraints);

        ProfitsPanel_ManualPanel_CategoryLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        ProfitsPanel_ManualPanel_CategoryLabel.setText("Category :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_CategoryLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_ViewButton.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_ViewButton, gridBagConstraints);

        ProfitsPanel_ManualPanel_CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"all", "electric", "computer", "mobile" , "maintenance" , "other" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_CategoryComboBox, gridBagConstraints);
        

        ProfitsPanel_ManualPanel_SellersLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        ProfitsPanel_ManualPanel_SellersLabel.setText("Sellers :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_SellersLabel, gridBagConstraints);
        
        loadSellerComboBox();
        
        ProfitsPanel_ManualPanel_SellerComboBox.setPrototypeDisplayValue(new Worker(0, "00000000"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_SellerComboBox, gridBagConstraints);
        
        
        ProfitsPanel_ManualPanel_ProfitsLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        ProfitsPanel_ManualPanel_ProfitsLabel.setText("Total profits :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_ProfitsLabel, gridBagConstraints);

        ProfitsPanel_ManualPanel_ProfitsTextFiled.setEditable(false);
        ProfitsPanel_ManualPanel_ProfitsTextFiled.setText(Double.toString(0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ProfitsPanel_ManualPanel.add(ProfitsPanel_ManualPanel_ProfitsTextFiled, gridBagConstraints);

        ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date - Category", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        ProfitsPanel_ProfitsTable.setModel(profitsTableModel);
        ScrollPane2.setViewportView(ProfitsPanel_ProfitsTable);
        
        
//////////////////////////////////////////////////////////////////////////////////////////////
        ProfitsPanel_ManualPanel_ViewButton.addActionListener(new ProfitsPanel_ViewButtonListener(this, ProfitsPanel_ManualPanel_MonthComboBox, ProfitsPanel_ManualPanel_CategoryComboBox, profitsTableModel, ProfitsPanel_ManualPanel_DaySpinner, ProfitsPanel_ManualPanel_YearSpinner, ProfitsPanel_ProfitsTablePanel, ProfitsPanel_ManualPanel_SellerComboBox));
        ProfitsPanel_ProfitsTable.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if(e.getButton() == MouseEvent.BUTTON3){
        			ProfitsPanel_ProfitsTable.getSelectionModel().clearSelection();
        		}
        	}
		});
        ProfitsPanel_ProfitsTable.getTableHeader().addMouseListener(profitsSortByListener);
/////////////////////////////////////////////////////////////////////////////////////////////        
        
        
        javax.swing.GroupLayout ProfitsPanel_ProfitsTablePanelLayout = new javax.swing.GroupLayout(ProfitsPanel_ProfitsTablePanel);
        ProfitsPanel_ProfitsTablePanel.setLayout(ProfitsPanel_ProfitsTablePanelLayout);
        ProfitsPanel_ProfitsTablePanelLayout.setHorizontalGroup(
            ProfitsPanel_ProfitsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfitsPanel_ProfitsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane2)
                .addContainerGap())
        );
        ProfitsPanel_ProfitsTablePanelLayout.setVerticalGroup(
            ProfitsPanel_ProfitsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfitsPanel_ProfitsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ProfitsPanelLayout = new javax.swing.GroupLayout(ProfitsPanel);
        ProfitsPanel.setLayout(ProfitsPanelLayout);
        ProfitsPanelLayout.setHorizontalGroup(
            ProfitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProfitsPanel_ProfitsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ProfitsPanel_ManualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProfitsPanelLayout.setVerticalGroup(
            ProfitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfitsPanelLayout.createSequentialGroup()
                .addComponent(ProfitsPanel_ManualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProfitsPanel_ProfitsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Profits", ProfitsPanel);

        OperationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Operations and income", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        OperationsPanel_ManualPanel.setLayout(new java.awt.GridBagLayout());

        OperationsPanel_ManualPanel_DateOfOperations.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        OperationsPanel_ManualPanel_DateOfOperations.setText("Date of operations  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_DateOfOperations, gridBagConstraints);

        OperationsPanel_ManualPanel_MonthLabel.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_MonthLabel, gridBagConstraints);

        OperationsPanel_ManualPanel_YearLabel.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_YearLabel, gridBagConstraints);

        OperationsPanel_ManualPanel_DayLabel.setText("Day");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_DayLabel, gridBagConstraints);

        OperationsPanel_ManualPanel_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_MonthComboBox, gridBagConstraints);

        OperationsPanel_ManualPanel_DaySpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        OperationsPanel_ManualPanel_DaySpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 7);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_DaySpinner, gridBagConstraints);

        OperationsPanel_ManualPanel_YearSpinner.setModel(new javax.swing.SpinnerNumberModel(2015, 2015, 2150, 1));
        OperationsPanel_ManualPanel_YearSpinner.setEditor(new JSpinner.NumberEditor(OperationsPanel_ManualPanel_YearSpinner, "#"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_YearSpinner, gridBagConstraints);

        OperationsPanel_ManualPanel_ViewButton.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_ViewButton, gridBagConstraints);

        OperationsPanel_ManualPanel_CategoryLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        OperationsPanel_ManualPanel_CategoryLabel.setText("Category :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_CategoryLabel, gridBagConstraints);

        OperationsPanel_ManualPanel_CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"all", "electric", "computer", "mobile" , "maintenance" , "other" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_CategoryComboBox, gridBagConstraints);
        
        OperationsPanel_ManualPanel_SellersLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        OperationsPanel_ManualPanel_SellersLabel.setText("Sellers :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_SellersLabel, gridBagConstraints);
        
        OperationsPanel_ManualPanel_SellerComboBox.setPrototypeDisplayValue(new Worker(0, "00000000"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_SellerComboBox, gridBagConstraints);

        OperationsPanel_ManualPanel_IncomeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        OperationsPanel_ManualPanel_IncomeLabel.setText("Total income :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_IncomeLabel, gridBagConstraints);

        OperationsPanel_ManualPanel_IncomeTextField.setEditable(false);
        OperationsPanel_ManualPanel_IncomeTextField.setText(Double.toString(0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        OperationsPanel_ManualPanel.add(OperationsPanel_ManualPanel_IncomeTextField, gridBagConstraints);

        OperationsPanel_OperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date - Category", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        OperationsPanel_OperationsTable.setModel(operationsTableModel);
        ScrollPane3.setViewportView(OperationsPanel_OperationsTable);
        if (OperationsPanel_OperationsTable.getColumnModel().getColumnCount() > 0) {
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(0).setPreferredWidth(65);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(0).setMaxWidth(65);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(1).setPreferredWidth(270);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(1).setMaxWidth(290);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(2).setPreferredWidth(65);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(2).setMaxWidth(65);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(3).setMaxWidth(220);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(4).setMaxWidth(50);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(5).setMaxWidth(50);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(6).setPreferredWidth(220);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(6).setMaxWidth(220);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(7).setMaxWidth(100);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(8).setPreferredWidth(100);
        	OperationsPanel_OperationsTable.getColumnModel().getColumn(8).setMaxWidth(100);
        }
        
        
        
//////////////////////////////////////////////////////////////////////////////////////////
        OperationsPanel_ManualPanel_ViewButton.addActionListener(new OperationsPanel_ViewButtonListener(this, OperationsPanel_ManualPanel_MonthComboBox, OperationsPanel_ManualPanel_CategoryComboBox, operationsTableModel, OperationsPanel_ManualPanel_DaySpinner, OperationsPanel_ManualPanel_YearSpinner, OperationsPanel_OperationsTablePanel, OperationsPanel_ManualPanel_SellerComboBox));
        OperationsPanel_OperationsTable.getTableHeader().addMouseListener(rootOperationsSortByListener);
        OperationsPanel_OperationsTable.addMouseListener(new OperationsTableMouseListener(operationsTableModel, OperationsPanel_OperationsTable, this));
        OperationsPanel_OperationsTable.addKeyListener(new OperationsTableKeyListener(operationsTableModel, OperationsPanel_OperationsTable, this));
/////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        
        javax.swing.GroupLayout OperationsPanel_OperationsTablePanelLayout = new javax.swing.GroupLayout(OperationsPanel_OperationsTablePanel);
        OperationsPanel_OperationsTablePanel.setLayout(OperationsPanel_OperationsTablePanelLayout);
        OperationsPanel_OperationsTablePanelLayout.setHorizontalGroup(
            OperationsPanel_OperationsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OperationsPanel_OperationsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane3)
                .addContainerGap())
        );
        OperationsPanel_OperationsTablePanelLayout.setVerticalGroup(
            OperationsPanel_OperationsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperationsPanel_OperationsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout OperationsPanelLayout = new javax.swing.GroupLayout(OperationsPanel);
        OperationsPanel.setLayout(OperationsPanelLayout);
        OperationsPanelLayout.setHorizontalGroup(
            OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OperationsPanel_ManualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(OperationsPanel_OperationsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OperationsPanelLayout.setVerticalGroup(
            OperationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OperationsPanelLayout.createSequentialGroup()
                .addComponent(OperationsPanel_ManualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OperationsPanel_OperationsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Operations", OperationsPanel);

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
        
        
        
//////////////////////////////////////////////////////////////////////////////////        
        ToolBar_SearchButton.addActionListener(rootSearchButtonListener);
        ToolBar_ItemTextField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			rootSearchButtonListener.startSearching();
        		}
        	}
		});
        TextFieldHandeler.orientationHandeler(ToolBar_ItemTextField);
//////////////////////////////////////////////////////////////////////////////////  

        
        
        
        FileMenu.setText("File");

        FileMenu_ImportDatabaseToFile.setText("Save database to file");
        FileMenu.add(FileMenu_ImportDatabaseToFile);

        FileMenu_ExportDatabaseFromFile.setText("Load database from file");
        FileMenu.add(FileMenu_ExportDatabaseFromFile);
        FileMenu.add(MenuItemSeparator1);

//        FileMenu_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        FileMenu_Exit.setText("Exit");
        FileMenu.add(FileMenu_Exit);

        
        
/////////////////////////////////////////////////////////////////////////////////////////
        FileMenu_Exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(RootUserGUIFrame.this, "Do you want to save data to file before exit?", "Save to file and exit", JOptionPane.YES_NO_CANCEL_OPTION);
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
        		int option = JOptionPane.showConfirmDialog(RootUserGUIFrame.this, "Do you want to save data to file before exit?", "Save to file and exit", JOptionPane.YES_NO_CANCEL_OPTION);
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
        
        PreferencesMenu_Refresh.setText("Refresh");
        PreferencesMenu.add(PreferencesMenu_Refresh);
        PreferencesMenu_Refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        PreferencesMenu_ClearOperations.setText("Clear operations");
        PreferencesMenu.add(PreferencesMenu_ClearOperations);

        PreferencesMenu_SwitchToNormalUser.setText("Switch to normal user");
        PreferencesMenu.add(PreferencesMenu_SwitchToNormalUser);
        
        MenuBar.add(PreferencesMenu);

        setJMenuBar(MenuBar);
        
        
///////////////////////////////////////////////////////////////////////////////////
        PreferencesMenu_ClearOperations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClearOperationsDialog clearOperationsDialog = new ClearOperationsDialog(RootUserGUIFrame.this, database);
		        Dimension dimension = RootUserGUIFrame.this.getSize();
		        clearOperationsDialog.setLocation(dimension.width/2-clearOperationsDialog.getSize().width/2, dimension.height/2-clearOperationsDialog.getSize().height/2);
		        clearOperationsDialog.setVisible(true);
			}
		});
        
        PreferencesMenu_SwitchToNormalUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//////remove new dispatcher
				KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(RootUserGUIFrame.this.specialCharacterDispatcher);
				RootUserGUIFrame.this.dispose();
				new NormalUserGUIFrame().setVisible(true);
			}
		});
        
        PreferencesMenu_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
			
		});
///////////////////////////////////////////////////////////////////////////////////        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
            .addComponent(ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(TabbedPane))
        );

        pack();
        
        
///////////////////////////////////////////////////////////////////////////
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(specialCharacterDispatcher);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.gc();
///////////////////////////////////////////////////////////////////////////
        
        
    }

    private void addRootItem() {
		AddEditItemsDialog addEditItemsDialog = new AddEditItemsDialog(RootUserGUIFrame.this, "add", null, 0, rootItemPanelTableModel, ItemsPanel_ItemsTable);
        Dimension dimension = RootUserGUIFrame.this.getSize();
        addEditItemsDialog.setLocation(dimension.width/2-addEditItemsDialog.getSize().width/2, dimension.height/2-addEditItemsDialog.getSize().height/2);
        addEditItemsDialog.setVisible(true);
	}
    
    private void addAmountToRootItem() {
		AddToItemDialog addToItemDialog;
		try {
			addToItemDialog = new AddToItemDialog(RootUserGUIFrame.this, rootItemPanelTableModel, ItemsPanel_ItemsTable);
		} catch (ArrayIndexOutOfBoundsException e) {
			ErrorMessage.showErrorMessage(RootUserGUIFrame.this, e.getMessage());
			e.printStackTrace();
			return;
		}
        Dimension dimension = RootUserGUIFrame.this.getSize();
        addToItemDialog.setLocation(dimension.width/2-addToItemDialog.getSize().width/2, dimension.height/2-addToItemDialog.getSize().height/2);
        addToItemDialog.setVisible(true);
	}
    
    public void refresh() {
		database.connect();
		
		loadSellerComboBox();
		
		operationsTableModel.operations.clear();
		operationsTableModel.setLastSQL(null);
		OperationsPanel_ManualPanel_IncomeTextField.setText(Double.toString(0));
		operationsTableModel.fireTableDataChanged();
		OperationsPanel_OperationsTablePanel.setBorder(javax.swing.BorderFactory.
				createTitledBorder(null, "Date - category", javax.swing.border.TitledBorder.CENTER, 
						javax.swing.border.TitledBorder.ABOVE_TOP, 
						new java.awt.Font("Tahoma", 1, 12)));
		
		profitsTableModel.items.clear();
		profitsTableModel.setLastSQL(null);
		ProfitsPanel_ManualPanel_ProfitsTextFiled.setText(Double.toString(0));
		profitsTableModel.fireTableDataChanged();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.
				createTitledBorder(null, "Date - Category", javax.swing.border.TitledBorder.CENTER, 
						javax.swing.border.TitledBorder.ABOVE_TOP,
						new java.awt.Font("Tahoma", 1, 12)));
		
		rootItemPanelTableModel.items.clear();
		rootItemPanelTableModel.setLastSQL(null);
		rootItemPanelTableModel.setLastTotalCapitalSQL("SELECT Sum(available_capital) total_capital FROM skytech.items WHERE item_id = -1");
		ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(0));
		rootItemPanelTableModel.fireTableDataChanged();
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
			ErrorMessage.showErrorMessage(RootUserGUIFrame.this, e1.getMessage());
			e1.printStackTrace();
		} catch (InterruptedException e) {
			ErrorMessage.showErrorMessage(RootUserGUIFrame.this, e.getMessage());
			e.printStackTrace();
		}
	}
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSellerComboBox() {
		try {
			ProfitsPanel_ManualPanel_SellerComboBox.setModel(new javax.swing.DefaultComboBoxModel(getAllSellers()));
			OperationsPanel_ManualPanel_SellerComboBox.setModel(new javax.swing.DefaultComboBoxModel(getAllSellers()));
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
	
	public javax.swing.JTable getItemsPanel_ItemsTable() {
		return ItemsPanel_ItemsTable;
	}
	
	public RootItemPanelTableModel getRootItemPanelTableModel() {
		return rootItemPanelTableModel;
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
//                    defaults.put("Table:\"Table.cellRenderer\".background", new ColorUIResource(Color.lightGray));
//                    defaults.put("Table.alternateRowColor", new Color(214,217,223));
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RootUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RootUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RootUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RootUserGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RootUserGUIFrame().setVisible(true);
            }
        });
    }

    private BackUpAndRestore backUpAndRestore;
    private Database database;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem FileMenu_Exit;
    private javax.swing.JMenuItem FileMenu_ExportDatabaseFromFile;
    private javax.swing.JMenuItem FileMenu_ImportDatabaseToFile;
    private javax.swing.JPanel ItemsPanel;
    private javax.swing.JTable ItemsPanel_ItemsTable;
    private javax.swing.JPanel ItemsPanel_ItemsTablePanel;
    private javax.swing.JPanel ItemsPanel_ManualPanel;
    private javax.swing.JButton ItemsPanel_ManualPanel_AddItemButton;
    private javax.swing.JButton ItemsPanel_ManualPanel_AddToItemButton;
    private javax.swing.JLabel ItemsPanel_ManualPanel_CapitalLabel;
    private javax.swing.JTextField ItemsPanel_ManualPanel_CapitalTextField;
    private javax.swing.JComboBox<Category> ItemsPanel_ManualPanel_CategoryComboBox;
    private javax.swing.JLabel ItemsPanel_ManualPanel_CategoryLabel;
    private javax.swing.JButton ItemsPanel_ManualPanel_ViewButton;
    private RootItemPanelTableModel rootItemPanelTableModel;
    private RootItemsPanel_ManualPanel_ViewButtonListener rootItemsPanel_ManualPanel_ViewButtonListener;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JPopupMenu.Separator MenuItemSeparator1;
    private javax.swing.JPanel OperationsPanel;
    private javax.swing.JPanel OperationsPanel_ManualPanel;
    private javax.swing.JComboBox<Category> OperationsPanel_ManualPanel_CategoryComboBox;
    private javax.swing.JLabel OperationsPanel_ManualPanel_CategoryLabel;
    private javax.swing.JLabel OperationsPanel_ManualPanel_DateOfOperations;
    private javax.swing.JLabel OperationsPanel_ManualPanel_DayLabel;
    private javax.swing.JSpinner OperationsPanel_ManualPanel_DaySpinner;
    private javax.swing.JLabel OperationsPanel_ManualPanel_IncomeLabel;
    private javax.swing.JTextField OperationsPanel_ManualPanel_IncomeTextField;
    private javax.swing.JComboBox<Month> OperationsPanel_ManualPanel_MonthComboBox;
    private javax.swing.JLabel OperationsPanel_ManualPanel_MonthLabel;
    private javax.swing.JButton OperationsPanel_ManualPanel_ViewButton;
    private javax.swing.JLabel OperationsPanel_ManualPanel_YearLabel;
    private javax.swing.JSpinner OperationsPanel_ManualPanel_YearSpinner;
    private JLabel OperationsPanel_ManualPanel_SellersLabel;
    private JComboBox<Worker> OperationsPanel_ManualPanel_SellerComboBox;
    private javax.swing.JTable OperationsPanel_OperationsTable;
    private RootOperationsTableModel operationsTableModel;
    private javax.swing.JPanel OperationsPanel_OperationsTablePanel;
    private javax.swing.JMenu PreferencesMenu;
    private javax.swing.JMenuItem PreferencesMenu_SwitchToNormalUser;
    private javax.swing.JMenuItem PreferencesMenu_Refresh;
    private JMenuItem PreferencesMenu_ClearOperations;
    private javax.swing.JPanel ProfitsPanel;
    private javax.swing.JPanel ProfitsPanel_ManualPanel;
    private javax.swing.JComboBox<Category> ProfitsPanel_ManualPanel_CategoryComboBox;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_CategoryLabel;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_DateOfOperationsLabel;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_DayLabel;
    private javax.swing.JSpinner ProfitsPanel_ManualPanel_DaySpinner;
    private javax.swing.JComboBox<Month> ProfitsPanel_ManualPanel_MonthComboBox;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_MonthLabel;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_ProfitsLabel;
    private JLabel ProfitsPanel_ManualPanel_SellersLabel;
    private javax.swing.JTextField ProfitsPanel_ManualPanel_ProfitsTextFiled;
    private javax.swing.JButton ProfitsPanel_ManualPanel_ViewButton;
    private javax.swing.JLabel ProfitsPanel_ManualPanel_YearLabel;
    private javax.swing.JSpinner ProfitsPanel_ManualPanel_YearSpinner;
    private JComboBox<Worker> ProfitsPanel_ManualPanel_SellerComboBox;
    private javax.swing.JTable ProfitsPanel_ProfitsTable;
    private javax.swing.JPanel ProfitsPanel_ProfitsTablePanel;
    private ProfitsTableModel profitsTableModel;
    private javax.swing.JScrollPane ScrollPane1;
    private javax.swing.JScrollPane ScrollPane2;
    private javax.swing.JScrollPane ScrollPane3;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JLabel ToolBar_ItemLabel;
    private javax.swing.JTextField ToolBar_ItemTextField;
    private javax.swing.JButton ToolBar_SearchButton;
    private javax.swing.JSeparator jSeparator1;
    private RootSearchButtonListener rootSearchButtonListener;
}
