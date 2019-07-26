import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Document {

    private static final String URL_TEMPLATE = "https://www.treasury.gov.ua/ua/requisites?page=";

    public static org.jsoup.nodes.Document getPage(String url) throws IOException {


        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("x-october-request-handler", "onFilter");
        httpPost.addHeader("x-october-request-partials", "requisitesCatalog::items");
        httpPost.addHeader("x-requested-with", "XMLHttpRequest");
        ResponseHandler<String> handler = new BasicResponseHandler();
        System.out.println("fetching: " + url);
        String response = client.execute(httpPost, handler);
        response = StringEscapeUtils.unescapeJava(response);
        response = StringUtils.substringAfter(response, "\"render\":\"");

        return Jsoup.parse(response);


    }

    public Tax writeInTax(org.jsoup.nodes.Document document) {


        Tax tax = new Tax();

        Elements settlement = document.select(".inner-item:first-of-type");
        tax.setSettlement(settlement.text());
        settlement.remove();

        Elements recipient = document.select(".inner-item:first-of-type");
        tax.setRecipient(recipient.text());
        recipient.remove();

        Elements recipientCodeEDRPOU = document.select(".inner-item:first-of-type");
        tax.setRecipientCodeEDRPOU(recipientCodeEDRPOU.text());
        recipientCodeEDRPOU.remove();

        Elements recipientBank = document.select(".inner-item:first-of-type");
        tax.setRecipientBank(recipientBank.text());
        recipientBank.remove();

        Elements bankCodeMFO = document.select(".inner-item:first-of-type");
        tax.setBankCodeMFO(bankCodeMFO.text());
        bankCodeMFO.remove();

        Elements accountNumber = document.select(".inner-item:first-of-type");
        tax.setAccountNumber(accountNumber.text());
        accountNumber.remove();

        Elements codeForClassificationOfBudgetRevenues = document.select(".inner-item:first-of-type");
        tax.setCodeForClassificationOfBudgetRevenues(codeForClassificationOfBudgetRevenues.text());
        codeForClassificationOfBudgetRevenues.remove();

        Elements nameOfTaxCollectionPayment = document.select(".inner-item:first-of-type");
        tax.setNameOfTaxCollectionPayment(nameOfTaxCollectionPayment.text());
        nameOfTaxCollectionPayment.remove();

        Elements thePresenceOfADepartmentalFeature = document.select(".inner-item:first-of-type");
        tax.setThePresenceOfADepartmentalFeature(thePresenceOfADepartmentalFeature.text());
        thePresenceOfADepartmentalFeature.remove();


        return tax;

    }

    public ArrayList<Tax> getAllTaxes() {
        ArrayList<Tax> taxes = new ArrayList<>();
        Tax taxColumnName = new Tax();
        taxColumnName.setSettlement("Населений пункт");
        taxColumnName.setRecipient("Отримувач");
        taxColumnName.setRecipientCodeEDRPOU("Код отримувача (ЄДРПОУ)");
        taxColumnName.setRecipientBank("Банк отримувача");
        taxColumnName.setBankCodeMFO("Код банку (МФО)");
        taxColumnName.setAccountNumber("Номер рахунку");
        taxColumnName.setCodeForClassificationOfBudgetRevenues("Код класифікації доходів бюджету");
        taxColumnName.setNameOfTaxCollectionPayment("Найменування податку збору платежу");
        taxColumnName.setThePresenceOfADepartmentalFeature("Наявність відомчої ознаки");
        taxes.add(taxColumnName);
        for (int i = 1;  ; i++) {
            try {
                org.jsoup.nodes.Document page = getPage(URL_TEMPLATE + i);
                if (page.select(".inner-item").isEmpty())
                    break;
                ArrayList<Tax> taxesFromPage = getTaxesFromPage(page);
                taxes.addAll(taxesFromPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return taxes;
    }


    public ArrayList<Tax> getTaxesFromPage(org.jsoup.nodes.Document document) {
        ArrayList<Tax> taxes = new ArrayList<>();


        while (!document.select(".inner-item").isEmpty()) {

            Tax tax = writeInTax(document);
            taxes.add(tax);


        }
        return taxes;
    }
}
