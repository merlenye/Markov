import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map <WordGram, ArrayList<String>> myMap; //makes a new map with Wordgram key and ArrayList values

	public EfficientWordMarkov(){
		super();
		myMap = new HashMap <WordGram, ArrayList<String>>();
	}

	public EfficientWordMarkov(int obj) {
		super(obj);
		myMap = new HashMap <WordGram, ArrayList<String>>();
	}

	public void setTraining(String text) {
		super.setTraining(text); //gets text parameter from original class
		for (int i = 0; i<=myWords.length-myOrder;i++) { //loops through the length of mywords - order 
			WordGram help = new WordGram(myWords, i, myOrder); //initializes new wordgram
			if(i == myWords.length-myOrder) { //this will be the last key made
				if (!myMap.containsKey(help)){ //if the map doesnt contain the key, it initializes that
					myMap.put(help, new ArrayList<String>());
				}
				List <String> current = myMap.get(help); 
				current.add(PSEUDO_EOS); //idnciates this is the last value
				myMap.put(help, (ArrayList<String>) current);
			} else { //this will run if its not the last key made
				String nexthelp = myWords[i+myOrder];
				if (!myMap.containsKey(help)){ //if key not in map it initializes it
					myMap.put(help, new ArrayList<String>());
				}
				List <String> current = myMap.get(help);
				current.add(nexthelp); //if key is in map this updates it
				myMap.put(help, (ArrayList<String>) current);
			}
		}

	}
	@Override
	public ArrayList<String> getFollows(WordGram key){ //makes new getFollows meathod with a WordGram parameter
		if (myMap.containsKey(key)){ //looks through map keys for given wordgram
			return myMap.get(key); // returns wordgram if its in the map
		}
		else
			throw new NoSuchElementException(key+" not in map"); //lets user know the given WordGram is not 
	}

}
