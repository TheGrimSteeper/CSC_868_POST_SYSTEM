# Cashier.java:
* public Customer processCustomer(String relativePath, String name) throws ParseException 

------------------------------------------------------------
# Customer.java:
> Public methods are called by **PostSystem**

* public String getFirstName()  
* public String getLastName() 
* public String getCashOrCredit() 
* public double getCashAmount() 
* public String getCreditCardNumber() 


* private HashMap<String, Integer> getShoppingCart() 
* private void setShoppingCart(HashMap<String, Integer> shoppingCart) 
* private void setFirstName(String firstName) 
* private void setCashAmount(double cashAmount) 
* private void setCashOrCredit(String cashOrCredit) 
* private void setLastName(String lastName) 
* private void setCreditCardNumber(String creditCardNumber) 

------------------------------------------------------------
# Item.java:
> Public methods are called by **Product**

* public String getUPC() 
* public void setUPC(String uPC) 
* public String getProductDescription() 
* public void setProductDescription(String productDescription) 


* private int getQuantity() 
* private void setQuantity(int quantity) 
* private double getUnitPrice() 
* private void setUnitPrice(double unitPrice) 
* private double getSubTotal() 
* private void setSubTotal(double subTotal) 
* static ArrayList<Item> populateItemDetails(ArrayList<Item> itemList, HashMap<String, Product> productMap)

------------------------------------------------------------
# Main.java:
* public static void main(String[] args) 

------------------------------------------------------------
# Manager.java:
> Public methods are called by **PostSystem**

* public static PostSystem startUpStore() 

------------------------------------------------------------
# ParserClass.java:

* public static Product[] productParser(String relativeFilePath) 
> Called by **Manager**

* public static boolean customerFound(String relativeFilePath, String fullName) 
> Called by **PostSystem**

* public static HashMap<String, Integer> returnShoppingCart(String relativeFilePath, String fullName) 
> Called by **Cashier**

* public static String cashOrCredit(String relativeFilePath, String fullName) 
> Called by **Customer** and **Cashier**

* public static double returnAmountPaid(String relativeFilePath, String fullName) throws ParseException 
> Called by **Cashier**

* public static String returnCreditCardNumber(String relativeFilePath, String fullName) 
> Called by **Cashier**


* private static BufferedReader startBufferedReader(String relativeFilePath) 
* private static int lineCounter(String relativeFilePath) 
* private static int nonEmptyLineCounter(String relativeFilePath) 
* private static boolean isNotEmptyLine(String currentLine) 
* private static boolean hasMoreLines(int currentLine, String relativeFilePath) 
* private static Product createProductObject(String currentLine) 
* private static boolean nameMatches(String currentLine, String fullName) 

------------------------------------------------------------
# Payment.java:
* private double getAmount() 
* private void setAmount(double amount) 
* private String getPaymentMode() 
* private void setPaymentMode(String paymentMode) 

------------------------------------------------------------
# PostSystem.java:
> Public methods are called by **Manager**

* public static String getProductspath() 
* public static void main(String[] arg) throws ParseException 


* private Store getStore() 
* private void setStore(Store store) 
* private Cashier getCashier() 
* private void setCashier(Cashier cashier) 
* private Customer getCurrentCustomer() 
* private void setCurrentCustomer(Customer currentCustomer) 
* private static String getTransactionspath() 
* private void readyForCustomer() throws ParseException 
* private String createRecipt(Product[] catalog, Customer currentCustomer) 

------------------------------------------------------------
# Product.java:
> Public methods are called by **Item**

* public String getUPC() 
* public void setUPC(String uPC) 
* public String getProductDescription() 
* public void setProductDescription(String productDescription) 


* private double getPrice() 
* private void setPrice(double price) 

------------------------------------------------------------
# ProductCatalog.java:
> Public methods are called by **PostSystem**

* public String toString() 
* public Product[] getProduct() 


* private void setProduct(Product[] product) 

------------------------------------------------------------
# Regex.java:
> Public methods are called by **ParserClass**

* public static boolean isItUPC(String string) 
* public static boolean isItProduct(String string) 
* public static boolean isItPrice(String string) 
* public static boolean isItName(String string) 
* public static boolean isItCreditCard(String string) 

------------------------------------------------------------
# Store.java:
> Public methods are called by **PostSystem**

* public ProductCatalog getCatalog() 
* public static String getStoreName() 


* private void setCatalog(ProductCatalog catalog) 

------------------------------------------------------------
# Transaction.java:
* private String getCustomerName() 
* private void setCustomerName(String customerName) 
* private ArrayList<Item> getItem() 
* private void setItem(ArrayList<Item> item) 
* private Payment getPayment() 
* private void setPayment(Payment payment) 
