package pos;

public class KeyValuePairs {
	public final String key;
	public final String value1;
	public final String value2;
	
	KeyValuePairs(String key, String value1){
		this.key = key;
		this.value1 = value1;
		this.value2 = null;
	}
	
	KeyValuePairs(String key, String value1, String value2){
		this.key = key;
		this.value1 = value1;
		this.value2 = value2;
	}

}
