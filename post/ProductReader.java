package post;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
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

/**
 *
 * @author shalaka
 *Gets filename as an input and parses the file into productMap
 */
public class ProductReader {

    public static HashMap<String, Product> parseProducts(String productFile) {

        HashMap<String, Product> productMap = new HashMap<String, Product>();

        try {
            File prodFile = new File(productFile);

            @SuppressWarnings("resource")
            Scanner productScanner = new Scanner(prodFile);
            productScanner.useDelimiter("\\s{2,}\\s*");
            while(productScanner.hasNextLine()) {
                Product product = new  Product();
                product.setUPC(productScanner.next());
                product.setProductDescription(productScanner.next());
                product.setPrice(Double.parseDouble(productScanner.nextLine()));
                productMap.put(product.getUpc(), product);
            }
        }
        catch(FileNotFoundException ex){
            System.out.print("Products file is not found" + ex);
        }
        return productMap;
    }

    public static HashMap<String, Product> parseDBProducts()
            throws SAXException, ParserConfigurationException {

        HashMap<String, Product> productMap = new HashMap<String, Product>();

        try {
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
            while ((output = br.readLine()) != null) {
                msg += output;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(msg.getBytes()));
            NodeList productCatalogNodes = doc.getElementsByTagName("productCatalog");

            for (int i = 0; i < productCatalogNodes.getLength(); i++) {
                Node productCatalogTree = productCatalogNodes.item(i);
                Element productCatalogTreeElement = (Element) productCatalogTree;
                Product product = new Product();

                product.setUPC(
                        productCatalogTreeElement.getElementsByTagName("upc").item(0).getTextContent());
                product.setProductDescription(
                        productCatalogTreeElement.getElementsByTagName("givenName").item(0).getTextContent());
                product.setPrice(
                        Double.parseDouble(productCatalogTreeElement.getElementsByTagName("price").item(0).getTextContent()));
                productMap.put(product.getUpc(), product);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productMap;
    }
}