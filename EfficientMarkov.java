import java.util.*;

public class EfficientMarkov extends BaseMarkov{
	private Map <String, ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		super();
		myMap = new HashMap <String, ArrayList<String>>();
	}
	public EfficientMarkov(int obj) {
		super(obj);
		myMap = new HashMap <String, ArrayList<String>>();
	}
	@Override
	public void setTraining(String text) {
		myText = text; //gets text value from original 
		for (int i = 0; i<=text.length()-myOrder;i++) { //loops through all except the last #(order) indexes
			String help = myText.substring(i, i+myOrder); //initializes the key
			if(i== text.length()-myOrder) { //checks if this will be the last key
				if (!myMap.containsKey(help)){
					myMap.put(help, new ArrayList<String>()); //initializes if key not in map
				}
				List <String> current = myMap.get(help);
				current.add(PSEUDO_EOS); //adds last value indicator
				myMap.put(help, (ArrayList<String>) current);
			} else {
				String nexthelp = myText.substring(i+ myOrder, i+ myOrder +1); //everything except the last value
				if (!myMap.containsKey(help)){
					myMap.put(help, new ArrayList<String>()); //intiialzies key
				}
				List <String> current = myMap.get(help);
				current.add(nexthelp);
				myMap.put(help, (ArrayList<String>) current); //adds the string
			}
		}

	}
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.containsKey(key)){
			return myMap.get(key); //return key if key is in map
		}
		else {
			throw new NoSuchElementException(key+" not in map"); //tells user key not in map
		}
	}
}
