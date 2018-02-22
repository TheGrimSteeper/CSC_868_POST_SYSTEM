import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NetClientGet {

    public void getProductCatalog() throws ParserConfigurationException, SAXException {
        try {

            /*
              GET
             */
            URL urlProductCatalog = new URL("http://localhost:8080/StoreServer1/webresources/com.storeserver1entity.productcatalog");
            HttpURLConnection getConnProductCatalog = (HttpURLConnection) urlProductCatalog.openConnection();
            getConnProductCatalog.setRequestMethod("GET");
            getConnProductCatalog.setRequestProperty("Accept", "application/xml");

            if (getConnProductCatalog.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + getConnProductCatalog.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (getConnProductCatalog.getInputStream())));

            String output, msg = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                msg += output;
                System.out.println(output);
            }
            System.out.println("--------------------------\n\n");

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(msg.getBytes()));
            NodeList productCatalogNodes = doc.getElementsByTagName("productCatalog");

            String upc, description;
            double price;

            for (int i = 0; i < productCatalogNodes.getLength(); i++) {
                Node productCatalogTree = productCatalogNodes.item(i);
                Element productCatalogTreeElement = (Element) productCatalogTree;
                upc = productCatalogTreeElement.getElementsByTagName("upc").item(0).getTextContent();
                description = productCatalogTreeElement.getElementsByTagName("givenName").item(0).getTextContent();
                price = Double.parseDouble(productCatalogTreeElement.getElementsByTagName("price").item(0).getTextContent());
                System.out.println(upc + " " + description + " " + price);
            }

            System.out.println("--------------------------\n\n");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postTransaction() {
        try {

            /*
                POST
             */

            String output;
            URL urlTransactions = new URL("http://localhost:8080/StoreServer1/webresources/com.storeserver1entity.transactions");
            HttpURLConnection postConnTransactions = (HttpURLConnection) urlTransactions.openConnection();
            postConnTransactions.setDoOutput(true);
            postConnTransactions.setRequestMethod("POST");
            postConnTransactions.setRequestProperty("Content-Type", "application/xml");

            String newTransactionsString =
                    "<transactions> \n"
                            + "           <customerName>Billy</customerName> \n"
                            + "           <givenDate>2008-11-12T00:00:00-08:00</givenDate> \n"
                            + "           <paymentType>CREDIT</paymentType> \n"
                            + "           <total>10.20</total> \n"
                            + "           <transactionId>6</transactionId> \n"
                            + "     </transactions> ";

            OutputStream postOutputStream = postConnTransactions.getOutputStream();
            postOutputStream.write(newTransactionsString.getBytes());
            postOutputStream.flush();

            if (postConnTransactions.getResponseCode() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + postConnTransactions.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (postConnTransactions.getInputStream())));
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            postConnTransactions.disconnect();

            URL urlTransactionLines = new URL("http://localhost:8080/StoreServer1/webresources/com.storeserver1entity.transactionlines");
            HttpURLConnection postConnTransactionLines = (HttpURLConnection) urlTransactionLines.openConnection();
            postConnTransactionLines.setDoOutput(true);
            postConnTransactionLines.setRequestMethod("POST");
            postConnTransactionLines.setRequestProperty("Content-Type", "application/xml");

            String newTransactionLinesString =
                    "<transactionLines>  \n"
                            + "              <quantity>10</quantity>  \n"
                            + "              <subtotal>25.00</subtotal>  \n"
                            + "              <transactionId>  \n"
                            + "                     <customerName>Billy</customerName> \n"
                            + "                     <givenDate>2008-11-12T00:00:00-08:00</givenDate> \n"
                            + "                     <paymentType>CREDIT</paymentType> \n"
                            + "                     <total>10.20</total> \n"
                            + "                     <transactionId>6</transactionId> \n"
                            + "              </transactionId>  \n"
                            + "              <transactionLineId>6_2</transactionLineId>  \n"
                            + "              <upc>  \n"
                            + "                     <givenName>CARROTS</givenName>  \n"
                            + "                     <price>1.48</price>  \n"
                            + "                     <upc>0003</upc>  \n"
                            + "              </upc>  \n"
                            + "      </transactionLines>";

            postOutputStream = postConnTransactionLines.getOutputStream();
            postOutputStream.write(newTransactionLinesString.getBytes());
            postOutputStream.flush();

            if (postConnTransactionLines.getResponseCode() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + postConnTransactionLines.getResponseCode());
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(
                    (postConnTransactionLines.getInputStream())));
            System.out.println("Output from Server .... \n");
            while ((output = br1.readLine()) != null) {
                System.out.println(output);
            }

            postConnTransactionLines.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        NetClientGet testClient = new NetClientGet();
        testClient.getProductCatalog();
        testClient.postTransaction();


    }

}
