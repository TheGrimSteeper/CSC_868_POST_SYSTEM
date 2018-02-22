package post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class POSTModel implements POSTDataSource {
    enum PaymentType {
        Cash, Credit, Debit
    }
   static ArrayList<String> UPCList = new ArrayList<String>();


    public ArrayList<String> getUPCList() {
		return UPCList;
	}

	POSTModel() {
    }

    public ArrayList<String> getItemsForUPCMenu(HashMap<String, Product> productList) {
    	for(Entry<String, Product> upc: productList.entrySet()) {
    		UPCList.add(upc.getKey());
    	}
        return UPCList;
    }

    public String[] getPaymentTypes() {
        List<String> paymentTypesList = new ArrayList<String>();
        for (PaymentType type : PaymentType.values()) {
            paymentTypesList.add(type.toString());
        }
        return paymentTypesList.toArray(new String[0]);
    }

}


