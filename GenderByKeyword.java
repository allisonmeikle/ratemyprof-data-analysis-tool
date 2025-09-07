package finalproject;

import java.util.ArrayList;
// import java.util.Collections;
import java.util.LinkedList;

// import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenderByKeyword extends DataAnalyzer {
	private MyHashTable<String,ArrayList<String>> wordsByGender;
	private MyHashTable<String,Integer> result;
	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		result = new MyHashTable<String, Integer>();
		for (MyPair entry : wordsByGender.getEntries()) {
			result.put(((MyPair<String,ArrayList<String>>)entry).getKey(), 0);
			int count = 0;
			for (String s : ((MyPair<String,ArrayList<String>>)entry).getValue()) {
				if (s.equals(keyword.trim().toLowerCase())) {
					count++;
				}
			}
			result.put(((MyPair<String,ArrayList<String>>)entry).getKey(), count);
		}
		return result;
		//ADD YOUR CODE ABOVE THIS
	}

	private ArrayList<String> processComment(String comment){
		comment = comment.trim().toLowerCase().replaceAll("[^abcdefghijklmnopqrstuvwxyz']", " ");
		ArrayList<String> words = new ArrayList<String>();
		String[] word = comment.split(" ");
		for (String str : word){
			if (!str.equals(" ") && !str.equals("") && !words.contains(str)){
				words.add(str);
			}
		}
		return words;
	}
	@Override
	public void extractInformation() {
		wordsByGender = new MyHashTable<>();

		for (String[] rating : parser.data) {
			String gender = rating[parser.fields.get("gender")];
			ArrayList<String> comment = processComment(rating[parser.fields.get("comments")]);

			ArrayList<String> words = wordsByGender.get(gender);
			if (words == null) {
				words = new ArrayList<>();
				wordsByGender.put(gender, words);
			}
			words.addAll(comment);
		}
	}
}
