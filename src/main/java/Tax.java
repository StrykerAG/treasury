public class Tax {
    private String settlement;
    private String recipient;
    private String recipientCodeEDRPOU;
    private String recipientBank;
    private String bankCodeMFO;
    private String accountNumber;
    private String codeForClassificationOfBudgetRevenues;
    private String nameOfTaxCollectionPayment;
    private String thePresenceOfADepartmentalFeature;

    @Override
    public String toString() {
        return
                        settlement + ';' +
                        recipient + ';' +
                        recipientCodeEDRPOU + ';' +
                        recipientBank + ';' +
                        bankCodeMFO + ';' +
                        accountNumber + ';' +
                        codeForClassificationOfBudgetRevenues + ';' +
                        nameOfTaxCollectionPayment + ';' +
                        thePresenceOfADepartmentalFeature + ';';
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientCodeEDRPOU() {
        return recipientCodeEDRPOU;
    }

    public void setRecipientCodeEDRPOU(String recipientCodeEDRPOU) {
        this.recipientCodeEDRPOU = recipientCodeEDRPOU;
    }

    public String getRecipientBank() {
        return recipientBank;
    }

    public void setRecipientBank(String recipientBank) {
        this.recipientBank = recipientBank;
    }

    public String getBankCodeMFO() {
        return bankCodeMFO;
    }

    public void setBankCodeMFO(String bankCodeMFO) {
        this.bankCodeMFO = bankCodeMFO;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCodeForClassificationOfBudgetRevenues() {
        return codeForClassificationOfBudgetRevenues;
    }

    public void setCodeForClassificationOfBudgetRevenues(String codeForClassificationOfBudgetRevenues) {
        this.codeForClassificationOfBudgetRevenues = codeForClassificationOfBudgetRevenues;
    }

    public String getNameOfTaxCollectionPayment() {
        return nameOfTaxCollectionPayment;
    }

    public void setNameOfTaxCollectionPayment(String nameOfTaxCollectionPayment) {
        this.nameOfTaxCollectionPayment = nameOfTaxCollectionPayment;
    }

    public String getThePresenceOfADepartmentalFeature() {
        return thePresenceOfADepartmentalFeature;
    }

    public void setThePresenceOfADepartmentalFeature(String thePresenceOfADepartmentalFeature) {
        this.thePresenceOfADepartmentalFeature = thePresenceOfADepartmentalFeature;
    }
}

