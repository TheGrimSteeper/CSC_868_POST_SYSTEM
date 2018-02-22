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

    public static void main(String[] args) throws ParserConfigurationException, SAXException {

        try {

            /*
              GET
             */
            URL urlProductCatalog = new URL("http://localhost:8080/StoreServer/webresources/com.storeentity.productcatalog");
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

            /*
            Following are 2 alternatives for accessing values in XML tree nodes
            */
            
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

            /*
                POST
             */

            URL urlLineItem = new URL("http://localhost:8080/StoreServer/webresources/com.personentity.transactionlines");
            HttpURLConnection postConnLineItem = (HttpURLConnection) urlLineItem.openConnection();
            postConnLineItem.setDoOutput(true);
            postConnLineItem.setRequestMethod("POST");
            postConnLineItem.setRequestProperty("Content-Type", "application/xml");

            String newLineItemString =
                           "<person> \n"
                    + "           <gender>F</gender> \n"
                    + "           <personId>50</personId> \n"
                    + "     </person> ";

            OutputStream postOutputStream = postConnLineItem.getOutputStream();
            postOutputStream.write(newLineItemString.getBytes());
            postOutputStream.flush();

            if (postConnLineItem.getResponseCode() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + postConnLineItem.getResponseCode());
            }

            br = new BufferedReader(new InputStreamReader(
                    (postConnLineItem.getInputStream())));
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            postConnLineItem.disconnect();

            HttpURLConnection postConnPersonName = (HttpURLConnection) urlPersonName.openConnection();
            postConnPersonName.setDoOutput(true);
            postConnPersonName.setRequestMethod("POST");
            postConnPersonName.setRequestProperty("Content-Type", "application/xml");

            String newPersonNameString = 
                            "<transactionLines>  \n"
                    + "              <familyName>Sally</familyName>  \n"
                    + "              <givenName>Smith</givenName>  \n"
                    + "              <personId>  \n"
                    + "                     <gender>F</gender>  \n"
                    + "                     <personId>50</personId>  \n"
                    + "              </personId>  \n"
                    + "              <personNameId>50</personNameId>  \n"
                    + "      </transactionLines>";

            postOutputStream = postConnPersonName.getOutputStream();
            postOutputStream.write(newPersonNameString.getBytes());
            postOutputStream.flush();

            if (postConnPersonName.getResponseCode() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + postConnPersonName.getResponseCode());
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(
                    (postConnPersonName.getInputStream())));
            System.out.println("Output from Server .... \n");
            while ((output = br1.readLine()) != null) {
                System.out.println(output);
            }

            postConnPersonName.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
