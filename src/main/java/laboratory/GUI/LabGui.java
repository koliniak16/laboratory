package laboratory.GUI;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import laboratory.domain.*;
import laboratory.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Component
public class LabGui extends JFrame implements ActionListener {

    private String messagesAppear;
    private String messagesPeselExists;
    private String messagesPeselCharacters;
    private String messagesPersonDoesntExists;
    private String messagesDate;
    private String messagesPLAF;


    private RestTemplate restTemplate;

    private DefaultTableModel model;
    private static final Logger LOG = LoggerFactory.getLogger(LabGui.class);
    private static final String PATH_EN = "src/main/resources/LanguageEN.xml";
    private static final String PATH_PL = "src/main/resources/LanguagePL.xml";
    private LanguagePack languageEN;
    private LanguagePack languagePL;
    private LanguagePack languagePack;
    private LanguagePack activeLanguagePack;
    private LanguageEnum currentLanguage = LanguageEnum.EN;

    private JPanel detailsPanel;
    private GridBagConstraints gbcMainPanel;
    private GridBagConstraints gbcDetailsPanel;
    private JButton clientsListButton;
    private JButton workersListButton;
    private JButton rentingsListButton;
    private JButton equipmentListButton;
    private JButton rentingDetailsButton;
    private JTextArea rentingDetailsTextArea;

    private JPanel clientDataPanel;
    private JLabel typeClientNameLabel;
    private JLabel typeClientSurnameLabel;
    private JLabel typeClientPeselLabel;
    private JTextArea typeClientNameTextArea;
    private JTextArea typeClientSurnameTextArea;
    private JTextArea typeClientPeselTextArea;
    private JLabel typeRentingNumberLabel;
    private JTextArea typeRentingNumberTextArea;

    private JPanel workerDataPanel;
    private JLabel typeWorkerNameLabel;
    private JLabel typeWorkerSurnameLabel;
    private JLabel typeWorkerPeselLabel;
    private JTextArea typeWorkerNameTextArea;
    private JTextArea typeWorkerSurnameTextArea;
    private JTextArea typeWorkerPeselTextArea;

    private JPanel equipmentDataPanel;
    private JLabel typeEquipmentNameLabel;
    private JLabel selectEquipmentTypeLabel;
    private JTextArea typeEquipmentNameTextArea;
    private String[] eqTypeData;
    private JComboBox<String> eqType;
    private JLabel filterLabel;
    private JTextArea filterTextArea;

    private JPanel rentingDataPanel;
    private JLabel typeRentingDateLabel;
    private JLabel typeReturnDateLabel;
    private JLabel typeRentingClientPeselLabel;
    private JLabel typeRentingWorkerIdLabel;
    private JLabel typeRentingPositionsLabel;
    private JTextArea typeRentingDateTextArea;
    private JTextArea typeReturnDateTextArea;
    private JTextArea typeRentingClientPeselTextArea;
    private JTextArea typeRentingWorkerIdTextArea;
    private JTextArea typeRentingPositionsTextArea;
    private JButton addRecordButton;

    private JLabel clientsNumber;
    private String clientsNumberBegin;
    private String info;

    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu languageSubMenu;
    private JMenu skinSubMenu;
    private JMenuItem langEN;
    private JMenuItem langPL;
    private JMenuItem skin1;
    private JMenuItem skin2;
    private JMenuItem skin3;

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTable dataTable;
    private JScrollPane dataTableScrollPane;
    private SelectedTableEnum selectedTableEnum;
    private CustomWebPanel customWebPanel;
    private JLabel watLogoLabel;
    private CustomLabel messages;


    @Autowired
    private ClientService clientService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private RentingService rentingService;
    @Autowired
    private WorkerService workerService;


    public LabGui() {
        this.setSize(new Dimension(1280, 640));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.initGUI();
        this.pack();
        this.setVisible(true);
    }




    @PostConstruct
    void setDataTable() {

        try {
            loadLanguagePack();
        } catch (IOException e) {
            LOG.error("error with loading language pack.");
        }


        Vector headers = new Vector();
        headers.addElement("Nr");
        headers.addElement("RentingDate");
        headers.addElement("ReturnDate");
        headers.addElement("ClientId");
        headers.addElement("WorkerId");
        Vector data = rentingService.createDataVector();
        model = new DefaultTableModel(data, headers);
        dataTable.setModel(model);
        selectedTableEnum=SelectedTableEnum.RENTING;

        messagesAppear = "Messages will appear here";
        messagesDate = "Return Date must be after Renting Date.";
        messagesPersonDoesntExists = "Worker, or Client doesn't exist.";
        messagesPeselCharacters = "Pesel Number should contains 11 characters";
        messagesPeselExists = "Person with that Pesel Number already exists.";
        messagesPLAF = "Error during loading PLAF.";
    }




    private JPanel createDetailsPanel() {


        customWebPanel = new CustomWebPanel("data/WAT.jpg", "http://www.wat.edu.pl");
        watLogoLabel = new JLabel();
        watLogoLabel = customWebPanel.makeLabel();
        clientsListButton = new CustomButton("Show Clients List");

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        typeRentingNumberLabel = new JLabel("Search By Renting Number");
        typeRentingNumberTextArea = new JTextArea();
        typeRentingNumberTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        clientsListButton.addActionListener(this);
        workersListButton = new JButton("Show workers list");
        workersListButton.addActionListener(this);
        rentingsListButton = new JButton("Show rentings list");
        rentingsListButton.addActionListener(this);
        equipmentListButton = new JButton("Show equipments list");
        equipmentListButton.addActionListener(this);
        rentingDetailsButton = new JButton("Show renting details");
        rentingDetailsButton.addActionListener(this);
        rentingDetailsTextArea = new JTextArea();
        rentingDetailsTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addRecordButton = new JButton("Add Record");
        addRecordButton.addActionListener(this);

        clientsNumber = new JLabel("");
        clientsNumberBegin = "Actual amount of clients in database: ";

        clientDataPanel = new JPanel();
        typeClientNameLabel = new JLabel("Type Client Name");
        typeClientSurnameLabel = new JLabel("Type Client Surname");
        typeClientPeselLabel = new JLabel("Type Client Pesel");
        typeClientNameTextArea = new JTextArea();
        typeClientNameTextArea.setLineWrap(true);
        typeClientNameTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeClientSurnameTextArea = new JTextArea();
        typeClientSurnameTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeClientPeselTextArea = new JTextArea();
        typeClientPeselTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        workerDataPanel = new JPanel();
        typeWorkerNameLabel = new JLabel("Type Worker Name");
        typeWorkerSurnameLabel = new JLabel("Type Worker Surname");
        typeWorkerPeselLabel = new JLabel("Type Worker Pesel");
        typeWorkerNameTextArea = new JTextArea();
        typeWorkerNameTextArea.setLineWrap(true);
        typeWorkerNameTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeWorkerSurnameTextArea = new JTextArea();
        typeWorkerSurnameTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeWorkerPeselTextArea = new JTextArea();
        typeWorkerPeselTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        equipmentDataPanel = new JPanel();
        typeEquipmentNameLabel = new JLabel("Type Equipment Name");
        selectEquipmentTypeLabel = new JLabel("Select Equipment Type");
        typeEquipmentNameTextArea = new JTextArea();
        typeEquipmentNameTextArea.setLineWrap(true);
        typeEquipmentNameTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        eqTypeData = new String[3];
        eqTypeData[0] = "Chemical";
        eqTypeData[1] = "Biological";
        eqTypeData[2] = "Physical";
        eqType = new JComboBox<>(eqTypeData);

        rentingDataPanel = new JPanel();
        typeRentingDateLabel = new JLabel("Type Renting Date Label yyyy-mm-dd");
        typeReturnDateLabel = new JLabel("Type Return Date Label yyyy-mm-dd");
        typeRentingClientPeselLabel = new JLabel("Type Client Pesel");
        typeRentingWorkerIdLabel = new JLabel("Type Worker Id");
        typeRentingPositionsLabel = new JLabel("Type Equipments Ids");
        typeRentingDateTextArea= new JTextArea();
        typeRentingDateTextArea.setLineWrap(true);
        typeRentingDateTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeReturnDateTextArea= new JTextArea();
        typeReturnDateTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeRentingClientPeselTextArea= new JTextArea();
        typeRentingClientPeselTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeRentingWorkerIdTextArea= new JTextArea();
        typeRentingWorkerIdTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typeRentingPositionsTextArea = new JTextArea();
        typeRentingPositionsTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));




        clientDataPanel.setLayout(new BoxLayout(clientDataPanel, BoxLayout.PAGE_AXIS));
        clientDataPanel.add(typeClientNameLabel);
        clientDataPanel.add(typeClientNameTextArea);
        clientDataPanel.add(typeClientSurnameLabel);
        clientDataPanel.add(typeClientSurnameTextArea);
        clientDataPanel.add(typeClientPeselLabel);
        clientDataPanel.add(typeClientPeselTextArea);

        workerDataPanel.setLayout(new BoxLayout(workerDataPanel, BoxLayout.PAGE_AXIS));
        workerDataPanel.add(typeWorkerNameLabel);
        workerDataPanel.add(typeWorkerNameTextArea);
        workerDataPanel.add(typeWorkerSurnameLabel);
        workerDataPanel.add(typeWorkerSurnameTextArea);
        workerDataPanel.add(typeWorkerPeselLabel);
        workerDataPanel.add(typeWorkerPeselTextArea);

        filterLabel = new JLabel("Filtring");
        filterTextArea = new JTextArea();
        filterTextArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {


                if(filterTextArea.getText().equals("")){
                    updateTableModel(equipmentListButton);

                }
                else{
                    Vector headers = new Vector();
                    headers.addElement("Id");
                    headers.addElement("Name");
                    headers.addElement("Type");
                    Vector data = equipmentService.getEquipmentByName(filterTextArea.getText());
                    model = new DefaultTableModel(data,headers);
                    dataTable.setModel(model);
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {


            if(filterTextArea.getText().equals("")){
                updateTableModel(equipmentListButton);



            }
            else{
                Vector headers = new Vector();
                headers.addElement("Id");
                headers.addElement("Name");
                headers.addElement("Type");
                Vector data = equipmentService.getEquipmentByName(filterTextArea.getText());
                model = new DefaultTableModel(data,headers);
                dataTable.setModel(model);
                selectedTableEnum=SelectedTableEnum.EQUIPMENT;
                workerDataPanel.setVisible(false);
                rentingDataPanel.setVisible(false);
                clientDataPanel.setVisible(false);
                equipmentDataPanel.setVisible(true);
                titleLabel.setText("Equipments List");
            }

            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {

            }
        });

        equipmentDataPanel.setLayout(new BoxLayout(equipmentDataPanel, BoxLayout.PAGE_AXIS));
        equipmentDataPanel.add(typeEquipmentNameLabel);
        equipmentDataPanel.add(typeEquipmentNameTextArea);
        equipmentDataPanel.add(selectEquipmentTypeLabel);
        equipmentDataPanel.add(eqType);
        equipmentDataPanel.add(filterLabel);
        equipmentDataPanel.add(filterTextArea);

        rentingDataPanel.setLayout(new BoxLayout(rentingDataPanel, BoxLayout.PAGE_AXIS));
        rentingDataPanel.add(typeRentingDateLabel);
        rentingDataPanel.add(typeRentingDateTextArea);
        rentingDataPanel.add(typeReturnDateLabel);
        rentingDataPanel.add(typeReturnDateTextArea);
        rentingDataPanel.add(typeRentingClientPeselLabel);
        rentingDataPanel.add(typeRentingClientPeselTextArea);
        rentingDataPanel.add(typeRentingWorkerIdLabel);
        rentingDataPanel.add(typeRentingWorkerIdTextArea);
        rentingDataPanel.add(typeRentingPositionsLabel);
        rentingDataPanel.add(typeRentingPositionsTextArea);







        gbcDetailsPanel = new GridBagConstraints();
        gbcDetailsPanel.insets = new Insets(3, 3, 3, 3);
        gbcDetailsPanel.anchor = GridBagConstraints.NORTHEAST;
        int i = 0;

        gbcDetailsPanel.insets = new Insets(15, 15, 15, 15);
        gbcDetailsPanel.gridx = 1;
        gbcDetailsPanel.gridy = i;
        gbcDetailsPanel.gridwidth = 2;
        gbcDetailsPanel.weighty = 2.0;
        gbcDetailsPanel.fill = GridBagConstraints.HORIZONTAL;
        detailsPanel.add(clientsListButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(workersListButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(equipmentListButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(rentingsListButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.insets = new Insets(3, 3, 3, 3);
        gbcDetailsPanel.gridy = i;
        gbcDetailsPanel.fill = GridBagConstraints.EAST;
        gbcDetailsPanel.anchor = GridBagConstraints.EAST;
        detailsPanel.add(typeRentingNumberLabel, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        gbcDetailsPanel.fill = GridBagConstraints.HORIZONTAL;
        detailsPanel.add(typeRentingNumberTextArea, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.insets = new Insets(15, 15, 15, 15);
        gbcDetailsPanel.gridy = i;
        detailsPanel.add(rentingDetailsButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.insets = new Insets(3, 3, 3, 3);
        gbcDetailsPanel.gridy = i;
        gbcDetailsPanel.fill = GridBagConstraints.WEST;
        detailsPanel.add(clientDataPanel, gbcDetailsPanel);
        clientDataPanel.setVisible(false);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(workerDataPanel, gbcDetailsPanel);
        workerDataPanel.setVisible(false);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(equipmentDataPanel, gbcDetailsPanel);
        equipmentDataPanel.setVisible(false);

        i++;

        gbcDetailsPanel.gridy = i;
        gbcDetailsPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcDetailsPanel.anchor = GridBagConstraints.EAST;
        detailsPanel.add(rentingDataPanel, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(addRecordButton, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(watLogoLabel, gbcDetailsPanel);

        i++;

        gbcDetailsPanel.gridy = i;
        detailsPanel.add(clientsNumber, gbcDetailsPanel);

        return detailsPanel;
    }




    public void initGUI() {

        setTitle("Laboratory");
        menuBar = new JMenuBar();
        mainMenu = new JMenu("Options");
        languageSubMenu = new JMenu("Language");
        skinSubMenu = new JMenu("PLAF");
        langEN = new JMenuItem("English");
        langEN.addActionListener(this);
        langPL = new JMenuItem("Polish");
        langPL.addActionListener(this);
        skin1 = new JMenuItem("Skin 1");
        skin1.addActionListener(this);
        skin2 = new JMenuItem("Skin 2");
        skin2.addActionListener(this);
        skin3 = new JMenuItem("Skin 3");
        skin3.addActionListener(this);
        skinSubMenu.add(skin1);
        skinSubMenu.add(skin2);
        skinSubMenu.add(skin3);
        languageSubMenu.add(langEN);
        languageSubMenu.add(langPL);
        mainMenu.add(languageSubMenu);
        mainMenu.add(skinSubMenu);
        menuBar.add(mainMenu);
        setJMenuBar(menuBar);



        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(1280, 860));
        this.getContentPane().add(mainPanel);

        titleLabel = new JLabel("Rentings List");
        messages = new CustomLabel("Messages will appear here");
        messages.setColor(Color.RED);
        messages.setFontSize(18);
        messages.setPreferredSize(new Dimension(1000, 100));

        DefaultTableModel emptyModel = new DefaultTableModel();
        dataTable = new JTable(emptyModel);
        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(600, 350));
        detailsPanel = createDetailsPanel();




        gbcMainPanel = new GridBagConstraints();

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        mainPanel.add(titleLabel, gbcMainPanel);

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 3;
        gbcMainPanel.gridwidth = 1;
        mainPanel.add(messages, gbcMainPanel);

        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 1;
        gbcMainPanel.gridwidth = 1;
        gbcMainPanel.gridheight = 1;
        gbcMainPanel.anchor = GridBagConstraints.NORTH;
        mainPanel.add(detailsPanel, gbcMainPanel);

        gbcMainPanel.anchor = GridBagConstraints.CENTER;
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1.0;
        gbcMainPanel.weighty = 1.0;
        mainPanel.add(dataTableScrollPane, gbcMainPanel);

    }




    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();

       if(source==skin1){
           try {
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
               SwingUtilities.updateComponentTreeUI(this);
               this.pack();
           } catch (Exception ex) {
               LOG.error(messagesPLAF);
           }
       }

       else if(source==skin2){
           try {
               UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
               SwingUtilities.updateComponentTreeUI(this);
           } catch (Exception ex) {
               LOG.error(messagesPLAF);
           }
       }

       else if(source==skin3){
           try {
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
               SwingUtilities.updateComponentTreeUI(this);
           } catch (Exception ex) {
               LOG.error(messagesPLAF);
           }
        }

        else if(source==langPL){
            if(currentLanguage != LanguageEnum.PL){
                currentLanguage = LanguageEnum.PL;
                activeLanguagePack = languagePL;
                changeLanguage(languagePL);
            }
        }

        else if(source==langEN){
            if(currentLanguage != LanguageEnum.EN){
                currentLanguage = LanguageEnum.EN;
                activeLanguagePack = languagePL;
                changeLanguage(languageEN);
            }
       }


       else if(source==clientsListButton){
           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {
                   updateTableModel(clientsListButton);
                   return null;
               }
           };
           worker.execute();
       }

       else if(source==workersListButton){
           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {
                   updateTableModel(workersListButton);
                   return null;
               }
           };
           worker.execute();
       }

       else if(source==equipmentListButton){
           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {
                   updateTableModel(equipmentListButton);
                   return null;
               }
           };
           worker.execute();
       }

       else if(source==rentingsListButton){
           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {
                   updateTableModel(rentingsListButton);
                   return null;
               }
           };
           worker.execute();
       }

       else if(source==rentingDetailsButton){
           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {
                   updateTableModel(rentingDetailsButton);
                   return null;
               }
           };
           worker.execute();
       }


        else if(source==addRecordButton){

           SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
               protected Void doInBackground() throws Exception {

                   restTemplate = new RestTemplate();
                   info = restTemplate.getForObject("http://localhost:8080/api/dataInfo", String.class);
                   clientsNumber.setText(clientsNumberBegin + info);

                        if(selectedTableEnum == SelectedTableEnum.CLIENT){
                            try {
                                validatePeselLenght(typeClientPeselTextArea.getText());
                                Client client = new Client();
                                client.setName(typeClientNameTextArea.getText());
                                client.setSurname(typeClientSurnameTextArea.getText());
                                client.setPesel(typeClientPeselTextArea.getText());
                                try {
                                    clientService.addClient(client);
                                } catch (Exception ConstraintViolationException) {
                                    LOG.error(messagesPeselExists);
                                    messages.setText(messagesPeselExists);
                                }
                                updateTableModel(clientsListButton);
                            } catch (WrongPeselException e1) {
                                LOG.error(messagesPeselCharacters);
                                messages.setText(messagesPeselCharacters);
                            }

                        }

                        else if(selectedTableEnum == SelectedTableEnum.WORKER){

                                try {
                                    validatePeselLenght(typeWorkerPeselTextArea.getText());
                                    Worker worker = new Worker();
                                    worker.setName(typeWorkerNameTextArea.getText());
                                    worker.setSurname(typeWorkerSurnameTextArea.getText());
                                    worker.setPesel(typeWorkerPeselTextArea.getText());
                                    try {
                                        workerService.addWorker(worker);
                                    } catch (Exception ConstraintViolationException) {
                                        LOG.error(messagesPeselExists);
                                        messages.setText(messagesPeselExists);
                                    }
                                    updateTableModel(workersListButton);
                                } catch (WrongPeselException e1) {
                                    LOG.info(messagesPeselCharacters);
                                    messages.setText(messagesPeselCharacters);
                                }
                            }



                        else if(selectedTableEnum == SelectedTableEnum.EQUIPMENT){
                            Equipment equipment = new Equipment();
                            equipment.setEqName(typeEquipmentNameTextArea.getText());
                            equipment.setType(eqType.getSelectedItem().toString());
                            equipmentService.addEquipment(equipment);
                            updateTableModel(equipmentListButton);
                        }

                        else if(selectedTableEnum == SelectedTableEnum.RENTING) {
                                try {
                                    validateDates(Date.valueOf(typeRentingDateTextArea.getText()), Date.valueOf(typeReturnDateTextArea.getText()));
                                    if (workerService.getWorker(Long.parseLong(typeRentingWorkerIdTextArea.getText())) != null && clientService.getClientPesel(typeRentingClientPeselTextArea.getText()) != null) {
                                        Renting renting = new Renting();
                                        renting.setRentingDate(Date.valueOf(typeRentingDateTextArea.getText()));
                                        renting.setReturnDate(Date.valueOf(typeReturnDateTextArea.getText()));
                                        renting.setWorker(workerService.getWorker(Long.parseLong(typeRentingWorkerIdTextArea.getText())));
                                        renting.setClient(clientService.getClientPesel(typeRentingClientPeselTextArea.getText()));
                                        renting.setRentingPositions(equipmentService.getEquipmentByIds(typeRentingPositionsTextArea.getText()));
                                        rentingService.addRenting(renting);
                                        updateTableModel(rentingsListButton);
                                    }
                                } catch (WrongDateException e1) {
                                    LOG.info(messagesDate);
                                    messages.setText(messagesDate);
                                }
                            }


                   return null;
               }
           };
           worker.execute();
        }
    }


    public void validateDates(Date firstDate, Date secondDate) throws WrongDateException{
        if (firstDate.after(secondDate)){
            throw new WrongDateException(messagesDate);
        }
    }

    public void validatePeselLenght(String pesel) throws WrongPeselException {
        if(pesel.length() != 11){
            throw new WrongPeselException(messagesPeselCharacters);
        }
    }



    public void updateTableModel(Object source){

        if(source==clientsListButton){
            Vector headers = new Vector();
            headers.addElement("Id");
            headers.addElement("Name");
            headers.addElement("Surname");
            headers.addElement("Pesel");
            Vector data = clientService.createDataVector();
            model = new DefaultTableModel(data,headers);
            dataTable.setModel(model);
            selectedTableEnum=SelectedTableEnum.CLIENT;
            workerDataPanel.setVisible(false);
            equipmentDataPanel.setVisible(false);
            rentingDataPanel.setVisible(false);
            clientDataPanel.setVisible(true);
            titleLabel.setText("Clients List");
        }

        if(source==workersListButton){
            Vector headers = new Vector();
            headers.addElement("Id");
            headers.addElement("Name");
            headers.addElement("Surname");
            headers.addElement("Pesel");
            Vector data = workerService.createDataVector();
            model = new DefaultTableModel(data,headers);
            dataTable.setModel(model);
            selectedTableEnum=SelectedTableEnum.WORKER;
            equipmentDataPanel.setVisible(false);
            rentingDataPanel.setVisible(false);
            clientDataPanel.setVisible(false);
            workerDataPanel.setVisible(true);
            titleLabel.setText("Workers List");
        }

        if(source==equipmentListButton){
            Vector headers = new Vector();
            headers.addElement("Id");
            headers.addElement("Name");
            headers.addElement("Type");
            Vector data = equipmentService.createDataVector();
            model = new DefaultTableModel(data,headers);
            dataTable.setModel(model);
            selectedTableEnum=SelectedTableEnum.EQUIPMENT;
            workerDataPanel.setVisible(false);
            rentingDataPanel.setVisible(false);
            clientDataPanel.setVisible(false);
            equipmentDataPanel.setVisible(true);
            titleLabel.setText("Equipments List");
        }

        if(source==rentingsListButton){
            Vector headers = new Vector();
            headers.addElement("Nr");
            headers.addElement("RentingDate");
            headers.addElement("ReturnDate");
            headers.addElement("ClientId");
            headers.addElement("WorkerId");
            Vector data = rentingService.createDataVector();
            model = new DefaultTableModel(data, headers);
            dataTable.setModel(model);
            selectedTableEnum=SelectedTableEnum.RENTING;
            workerDataPanel.setVisible(false);
            equipmentDataPanel.setVisible(false);
            clientDataPanel.setVisible(false);
            rentingDataPanel.setVisible(true);
            titleLabel.setText("Rentings List");
        }

        if(source==rentingDetailsButton){
            Vector headers = new Vector();
            headers.addElement("No.");
            headers.addElement("EquipmentName");
            int i=0;
            Vector vectorRows = new Vector();
            List<Equipment> equipments = rentingService.getRenting(Long.parseLong(typeRentingNumberTextArea.getText())).getRentingPositions();
            for(Equipment equipment : equipments){
                Vector<Object> row = new Vector<>();
                i++;
                row.addElement(i);
                row.addElement(equipment.getEqName());
                vectorRows.addElement(row);
            }

            model = new DefaultTableModel(vectorRows, headers);
            dataTable.setModel(model);
            selectedTableEnum=SelectedTableEnum.RENTINGDETAILS;
            workerDataPanel.setVisible(false);
            equipmentDataPanel.setVisible(false);
            clientDataPanel.setVisible(false);
            rentingDataPanel.setVisible(false);
            titleLabel.setText("Renting Details List");
        }



    }




    public void loadLanguagePack() throws IOException {
        File file = new File(PATH_EN);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = languagePack.inputStreamToString(new FileInputStream(file));
        languageEN = xmlMapper.readValue(xml, LanguagePack.class);
        File file2 = new File(PATH_PL);
        xml = languagePack.inputStreamToString(new FileInputStream(file2));
        languagePL = xmlMapper.readValue(xml, LanguagePack.class);
    }




    public void changeLanguage(LanguagePack languagePack){

         clientsListButton.setText(languagePack.getClientsListButton());
         typeRentingNumberLabel.setText(languagePack.getTypeRentingNumberLabel());
         typeClientNameLabel.setText(languagePack.getTypeClientNameLabel());
         typeClientSurnameLabel.setText(languagePack.getTypeClientSurnameLabel());
         typeClientPeselLabel.setText(languagePack.getTypeClientPeselLabel());
         typeWorkerNameLabel.setText(languagePack.getTypeWorkerNameLabel());
         typeWorkerSurnameLabel.setText(languagePack.getTypeWorkerSurnameLabel());
         typeWorkerPeselLabel.setText(languagePack.getTypeWorkerPeselLabel());
         typeEquipmentNameLabel.setText(languagePack.getTypeEquipmentNameLabel());
         selectEquipmentTypeLabel.setText(languagePack.getSelectEquipmentTypeLabel());
         eqTypeData[0] = languagePack.getEqTypeData1();
         eqTypeData[1] = languagePack.getEqTypeData2();
         eqTypeData[2] = languagePack.getEqTypeData3();
         eqType.removeAll();
         eqType.addItem(eqTypeData[0]);
         eqType.addItem(eqTypeData[1]);
         eqType.addItem(eqTypeData[2]);
         typeRentingDateLabel.setText(languagePack.getTypeRentingDateLabel());
         typeReturnDateLabel.setText(languagePack.getTypeReturnDateLabel());
         typeRentingClientPeselLabel.setText(languagePack.getTypeRentingClientPeselLabel());
         typeRentingWorkerIdLabel.setText(languagePack.getTypeRentingWorkerIdLabel());
         typeRentingPositionsLabel.setText(languagePack.getTypeRentingPositionsLabel());
         addRecordButton.setText(languagePack.getAddRecordButton());
         workersListButton.setText(languagePack.getWorkersListButton());
         rentingsListButton.setText(languagePack.getRentingsListButton());
         equipmentListButton.setText(languagePack.getEquipmentListButton());
         rentingDetailsButton.setText(languagePack.getRentingDetailsButton());
         mainMenu.setText(languagePack.getMainMenu());
         languageSubMenu.setText(languagePack.getLanguageSubMenu());
         skinSubMenu.setText(languagePack.getSkinSubMenu());
         langEN.setText(languagePack.getLangEN());
         langPL.setText(languagePack.getLangPL());
         skin1.setText(languagePack.getSkin1());
         skin2.setText(languagePack.getSkin2());
         skin3.setText(languagePack.getSkin3());
         messages.setText(languagePack.getMessagesAppear());
         messagesAppear = languagePack.getMessagesAppear();
         messagesDate = languagePack.getMessagesDate();
         messagesPersonDoesntExists = languagePack.getMessagesPersonDoesntExists();
         messagesPeselCharacters = languagePack.getMessagesPeselCharacters();
         messagesPeselExists = languagePack.getMessagesPeselExists();
         messagesPLAF = languagePack.getMessagesPLAF();
         if(selectedTableEnum == SelectedTableEnum.RENTING){
             titleLabel.setText(languagePack.getTitleLabelRentings());
             dataTable.getColumnModel().getColumn(0).setHeaderValue(languagePack.getHeaderRentingNr());
             dataTable.getColumnModel().getColumn(1).setHeaderValue(languagePack.getHeaderRentingDate());
             dataTable.getColumnModel().getColumn(2).setHeaderValue(languagePack.getHeaderRentingReturnDate());
             dataTable.getColumnModel().getColumn(3).setHeaderValue(languagePack.getHeaderRentingClientId());
             dataTable.getColumnModel().getColumn(4).setHeaderValue(languagePack.getHeaderRentingWorkerId());
         }
         else if(selectedTableEnum == SelectedTableEnum.CLIENT){
             titleLabel.setText(languagePack.getTitleLabelClients());
             dataTable.getColumnModel().getColumn(0).setHeaderValue(languagePack.getHeaderPersonId());
             dataTable.getColumnModel().getColumn(1).setHeaderValue(languagePack.getHeaderPersonName());
             dataTable.getColumnModel().getColumn(2).setHeaderValue(languagePack.getHeaderPersonSurname());
             dataTable.getColumnModel().getColumn(3).setHeaderValue(languagePack.getHeaderPersonPesel());
         }
         else if(selectedTableEnum == SelectedTableEnum.WORKER){
             titleLabel.setText(languagePack.getTitleLabelWorkers());
             dataTable.getColumnModel().getColumn(0).setHeaderValue(languagePack.getHeaderPersonId());
             dataTable.getColumnModel().getColumn(1).setHeaderValue(languagePack.getHeaderPersonName());
             dataTable.getColumnModel().getColumn(2).setHeaderValue(languagePack.getHeaderPersonSurname());
             dataTable.getColumnModel().getColumn(3).setHeaderValue(languagePack.getHeaderPersonPesel());
         }
         else if(selectedTableEnum == SelectedTableEnum.EQUIPMENT){
             titleLabel.setText(languagePack.getTitleLabelEquipments());
             dataTable.getColumnModel().getColumn(0).setHeaderValue(languagePack.getHeaderEquipmentId());
             dataTable.getColumnModel().getColumn(1).setHeaderValue(languagePack.getHeaderEquipmentName());
             dataTable.getColumnModel().getColumn(2).setHeaderValue(languagePack.getHeaderEquipmentType());
         }
         else if(selectedTableEnum == SelectedTableEnum.RENTINGDETAILS){
             titleLabel.setText(languagePack.getTitleLabelRentingDetails());
             dataTable.getColumnModel().getColumn(0).setHeaderValue(languagePack.getHeaderWorkerDetailsNumber());
             dataTable.getColumnModel().getColumn(1).setHeaderValue(languagePack.getHeaderWorkerDetailsEquipmentName());
         }
         clientsNumberBegin = languagePack.getClientsNumberBegin();
         clientsNumber.setText(clientsNumberBegin + info);

    }


}
