package laboratory.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LanguagePack {


    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }


    String clientsListButton;
    String typeRentingNumberLabel;
    String typeClientNameLabel;
    String typeClientSurnameLabel;
    String typeClientPeselLabel;
    String typeWorkerNameLabel;
    String typeWorkerSurnameLabel;
    String typeWorkerPeselLabel;
    String typeEquipmentNameLabel;
    String selectEquipmentTypeLabel;
    String eqTypeData1;
    String eqTypeData2;
    String eqTypeData3;
    String typeRentingDateLabel;
    String typeReturnDateLabel;
    String typeRentingClientPeselLabel;
    String typeRentingWorkerIdLabel;
    String typeRentingPositionsLabel;
    String addRecordButton;
    String workersListButton;
    String rentingsListButton;
    String equipmentListButton;
    String rentingDetailsButton;
    String mainMenu;
    String languageSubMenu;
    String skinSubMenu;
    String langEN;
    String langPL;
    String skin1;
    String skin2;
    String skin3;
    String titleLabelRentings;
    String titleLabelClients;
    String titleLabelWorkers;
    String titleLabelEquipments;
    String titleLabelRentingDetails;
    String messagesAppear;
    String messagesPeselExists;
    String messagesPeselCharacters;
    String messagesPersonDoesntExists;
    String messagesDate;
    String messagesPLAF;
    String headerPersonId;
    String headerPersonName;
    String headerPersonSurname;
    String headerPersonPesel;
    String headerEquipmentId;
    String headerEquipmentName;
    String headerEquipmentType;
    String headerRentingNr;
    String headerRentingDate;
    String headerRentingReturnDate;
    String headerRentingClientId;
    String headerRentingWorkerId;
    String headerWorkerDetailsNumber;
    String headerWorkerDetailsEquipmentName;
    String clientsNumberBegin;


    public String getClientsNumberBegin() {
        return clientsNumberBegin;
    }

    public void setClientsNumberBegin(String clientsNumberBegin) {
        this.clientsNumberBegin = clientsNumberBegin;
    }

    public String getClientsListButton() {
        return clientsListButton;
    }

    public void setClientsListButton(String clientsListButton) {
        this.clientsListButton = clientsListButton;
    }

    public String getTypeRentingNumberLabel() {
        return typeRentingNumberLabel;
    }

    public void setTypeRentingNumberLabel(String typeRentingNumberLabel) {
        this.typeRentingNumberLabel = typeRentingNumberLabel;
    }

    public String getTypeClientNameLabel() {
        return typeClientNameLabel;
    }

    public void setTypeClientNameLabel(String typeClientNameLabel) {
        this.typeClientNameLabel = typeClientNameLabel;
    }

    public String getTypeClientSurnameLabel() {
        return typeClientSurnameLabel;
    }

    public void setTypeClientSurnameLabel(String typeClientSurnameLabel) {
        this.typeClientSurnameLabel = typeClientSurnameLabel;
    }

    public String getMessagesPLAF() {
        return messagesPLAF;
    }

    public void setMessagesPLAF(String messagesPLAF) {
        this.messagesPLAF = messagesPLAF;
    }

    public String getTypeClientPeselLabel() {
        return typeClientPeselLabel;
    }

    public void setTypeClientPeselLabel(String typeClientPeselLabel) {
        this.typeClientPeselLabel = typeClientPeselLabel;
    }

    public String getTypeWorkerNameLabel() {
        return typeWorkerNameLabel;
    }

    public void setTypeWorkerNameLabel(String typeWorkerNameLabel) {
        this.typeWorkerNameLabel = typeWorkerNameLabel;
    }

    public String getTypeWorkerSurnameLabel() {
        return typeWorkerSurnameLabel;
    }

    public void setTypeWorkerSurnameLabel(String typeWorkerSurnameLabel) {
        this.typeWorkerSurnameLabel = typeWorkerSurnameLabel;
    }

    public String getTypeWorkerPeselLabel() {
        return typeWorkerPeselLabel;
    }

    public void setTypeWorkerPeselLabel(String typeWorkerPeselLabel) {
        this.typeWorkerPeselLabel = typeWorkerPeselLabel;
    }

    public String getTypeEquipmentNameLabel() {
        return typeEquipmentNameLabel;
    }

    public void setTypeEquipmentNameLabel(String typeEquipmentNameLabel) {
        this.typeEquipmentNameLabel = typeEquipmentNameLabel;
    }

    public String getSelectEquipmentTypeLabel() {
        return selectEquipmentTypeLabel;
    }

    public void setSelectEquipmentTypeLabel(String selectEquipmentTypeLabel) {
        this.selectEquipmentTypeLabel = selectEquipmentTypeLabel;
    }

    public String getEqTypeData1() {
        return eqTypeData1;
    }

    public void setEqTypeData1(String eqTypeData1) {
        this.eqTypeData1 = eqTypeData1;
    }

    public String getEqTypeData2() {
        return eqTypeData2;
    }

    public void setEqTypeData2(String eqTypeData2) {
        this.eqTypeData2 = eqTypeData2;
    }

    public String getEqTypeData3() {
        return eqTypeData3;
    }

    public void setEqTypeData3(String eqTypeData3) {
        this.eqTypeData3 = eqTypeData3;
    }

    public String getTypeRentingDateLabel() {
        return typeRentingDateLabel;
    }

    public void setTypeRentingDateLabel(String typeRentingDateLabel) {
        this.typeRentingDateLabel = typeRentingDateLabel;
    }

    public String getTypeReturnDateLabel() {
        return typeReturnDateLabel;
    }

    public void setTypeReturnDateLabel(String typeReturnDateLabel) {
        this.typeReturnDateLabel = typeReturnDateLabel;
    }

    public String getTypeRentingClientPeselLabel() {
        return typeRentingClientPeselLabel;
    }

    public void setTypeRentingClientPeselLabel(String typeRentingClientPeselLabel) {
        this.typeRentingClientPeselLabel = typeRentingClientPeselLabel;
    }

    public String getTypeRentingWorkerIdLabel() {
        return typeRentingWorkerIdLabel;
    }

    public void setTypeRentingWorkerIdLabel(String typeRentingWorkerIdLabel) {
        this.typeRentingWorkerIdLabel = typeRentingWorkerIdLabel;
    }

    public String getTypeRentingPositionsLabel() {
        return typeRentingPositionsLabel;
    }

    public void setTypeRentingPositionsLabel(String typeRentingPositionsLabel) {
        this.typeRentingPositionsLabel = typeRentingPositionsLabel;
    }

    public String getAddRecordButton() {
        return addRecordButton;
    }

    public void setAddRecordButton(String addRecordButton) {
        this.addRecordButton = addRecordButton;
    }

    public String getWorkersListButton() {
        return workersListButton;
    }

    public void setWorkersListButton(String workersListButton) {
        this.workersListButton = workersListButton;
    }

    public String getRentingsListButton() {
        return rentingsListButton;
    }

    public void setRentingsListButton(String rentingsListButton) {
        this.rentingsListButton = rentingsListButton;
    }

    public String getEquipmentListButton() {
        return equipmentListButton;
    }

    public void setEquipmentListButton(String equipmentListButton) {
        this.equipmentListButton = equipmentListButton;
    }

    public String getRentingDetailsButton() {
        return rentingDetailsButton;
    }

    public void setRentingDetailsButton(String rentingDetailsButton) {
        this.rentingDetailsButton = rentingDetailsButton;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public String getLanguageSubMenu() {
        return languageSubMenu;
    }

    public void setLanguageSubMenu(String languageSubMenu) {
        this.languageSubMenu = languageSubMenu;
    }

    public String getSkinSubMenu() {
        return skinSubMenu;
    }

    public void setSkinSubMenu(String skinSubMenu) {
        this.skinSubMenu = skinSubMenu;
    }

    public String getLangEN() {
        return langEN;
    }

    public void setLangEN(String langEN) {
        this.langEN = langEN;
    }

    public String getLangPL() {
        return langPL;
    }

    public void setLangPL(String langPL) {
        this.langPL = langPL;
    }

    public String getSkin1() {
        return skin1;
    }

    public void setSkin1(String skin1) {
        this.skin1 = skin1;
    }

    public String getSkin2() {
        return skin2;
    }

    public void setSkin2(String skin2) {
        this.skin2 = skin2;
    }

    public String getSkin3() {
        return skin3;
    }

    public void setSkin3(String skin3) {
        this.skin3 = skin3;
    }

    public String getTitleLabelRentings() {
        return titleLabelRentings;
    }

    public void setTitleLabelRentings(String titleLabelRenings) {
        this.titleLabelRentings = titleLabelRenings;
    }

    public String getTitleLabelClients() {
        return titleLabelClients;
    }

    public void setTitleLabelClients(String titleLabelClients) {
        this.titleLabelClients = titleLabelClients;
    }

    public String getTitleLabelWorkers() {
        return titleLabelWorkers;
    }

    public void setTitleLabelWorkers(String titleLabelWorkers) {
        this.titleLabelWorkers = titleLabelWorkers;
    }

    public String getTitleLabelEquipments() {
        return titleLabelEquipments;
    }

    public void setTitleLabelEquipments(String titleLabelEquipments) {
        this.titleLabelEquipments = titleLabelEquipments;
    }

    public String getTitleLabelRentingDetails() {
        return titleLabelRentingDetails;
    }

    public void setTitleLabelRentingDetails(String titleLabelRentingDetails) {
        this.titleLabelRentingDetails = titleLabelRentingDetails;
    }

    public String getMessagesAppear() {
        return messagesAppear;
    }

    public void setMessagesAppear(String messagesAppear) {
        this.messagesAppear = messagesAppear;
    }

    public String getMessagesPeselExists() {
        return messagesPeselExists;
    }

    public void setMessagesPeselExists(String messagesPeselExists) {
        this.messagesPeselExists = messagesPeselExists;
    }

    public String getMessagesPeselCharacters() {
        return messagesPeselCharacters;
    }

    public void setMessagesPeselCharacters(String messagesPeselCharacters) {
        this.messagesPeselCharacters = messagesPeselCharacters;
    }

    public String getMessagesPersonDoesntExists() {
        return messagesPersonDoesntExists;
    }

    public void setMessagesPersonDoesntExists(String messagesPersonDoesntExists) {
        this.messagesPersonDoesntExists = messagesPersonDoesntExists;
    }

    public String getMessagesDate() {
        return messagesDate;
    }

    public void setMessagesDate(String messagesDate) {
        this.messagesDate = messagesDate;
    }

    public String getHeaderPersonId() {
        return headerPersonId;
    }

    public void setHeaderPersonId(String headerPersonId) {
        this.headerPersonId = headerPersonId;
    }

    public String getHeaderPersonName() {
        return headerPersonName;
    }

    public void setHeaderPersonName(String headerPersonName) {
        this.headerPersonName = headerPersonName;
    }

    public String getHeaderPersonSurname() {
        return headerPersonSurname;
    }

    public void setHeaderPersonSurname(String headerPersonSurname) {
        this.headerPersonSurname = headerPersonSurname;
    }

    public String getHeaderPersonPesel() {
        return headerPersonPesel;
    }

    public void setHeaderPersonPesel(String headerPersonPesel) {
        this.headerPersonPesel = headerPersonPesel;
    }

    public String getHeaderEquipmentId() {
        return headerEquipmentId;
    }

    public void setHeaderEquipmentId(String headerEquipmentId) {
        this.headerEquipmentId = headerEquipmentId;
    }

    public String getHeaderEquipmentName() {
        return headerEquipmentName;
    }

    public void setHeaderEquipmentName(String headerEquipmentName) {
        this.headerEquipmentName = headerEquipmentName;
    }

    public String getHeaderEquipmentType() {
        return headerEquipmentType;
    }

    public void setHeaderEquipmentType(String headerEquipmentType) {
        this.headerEquipmentType = headerEquipmentType;
    }

    public String getHeaderRentingNr() {
        return headerRentingNr;
    }

    public void setHeaderRentingNr(String headerRentingNr) {
        this.headerRentingNr = headerRentingNr;
    }

    public String getHeaderRentingDate() {
        return headerRentingDate;
    }

    public void setHeaderRentingDate(String headerRentingDate) {
        this.headerRentingDate = headerRentingDate;
    }

    public String getHeaderRentingReturnDate() {
        return headerRentingReturnDate;
    }

    public void setHeaderRentingReturnDate(String headerRentingReturnDate) {
        this.headerRentingReturnDate = headerRentingReturnDate;
    }

    public String getHeaderRentingClientId() {
        return headerRentingClientId;
    }

    public void setHeaderRentingClientId(String headerRentingClientId) {
        this.headerRentingClientId = headerRentingClientId;
    }

    public String getHeaderRentingWorkerId() {
        return headerRentingWorkerId;
    }

    public void setHeaderRentingWorkerId(String headerRentingWorkerId) {
        this.headerRentingWorkerId = headerRentingWorkerId;
    }

    public String getHeaderWorkerDetailsNumber() {
        return headerWorkerDetailsNumber;
    }

    public void setHeaderWorkerDetailsNumber(String headerWorkerDetailsNumber) {
        this.headerWorkerDetailsNumber = headerWorkerDetailsNumber;
    }

    public String getHeaderWorkerDetailsEquipmentName() {
        return headerWorkerDetailsEquipmentName;
    }

    public void setHeaderWorkerDetailsEquipmentName(String headerWorkerDetailsEquipmentName) {
        this.headerWorkerDetailsEquipmentName = headerWorkerDetailsEquipmentName;
    }
}
