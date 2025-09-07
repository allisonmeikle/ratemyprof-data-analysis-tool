package finalproject;

import java.util.ArrayList;
// import java.util.Collections;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RatingByKeyword extends DataAnalyzer {
	private MyHashTable<ArrayList<String>, String> commentAndStar;
	private MyHashTable<String,Integer> result;
    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		result = new MyHashTable<String,Integer>();
		result.put("1",0);
		result.put("2",0);
		result.put("3",0);
		result.put("4",0);
		result.put("5",0);
		for (MyPair entry : commentAndStar.getEntries()){
			if (((ArrayList<String>)entry.getKey()).contains(keyword.trim().toLowerCase())){
				result.put(((String)entry.getValue()).substring(0,1), result.get(((MyPair<ArrayList<String>,String>)entry).getValue()) + 1);
			}
		}
		return result;
	}
	private ArrayList<String> processComment(String comment){
		comment = comment.toLowerCase();
		comment = comment.replaceAll("[^abcdefghijklmnopqrstuvwxyz']", " ");
		comment = comment.trim();

		ArrayList<String> words = new ArrayList<String>();
		String[] word = comment.split(" ");
		for (String str : word){
			if (!str.equals(" ") && !str.equals("")){
				words.add(str);
			}
		}
		return words;
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		commentAndStar = new MyHashTable<ArrayList<String>,String>();
		for (String[] rating : this.parser.data){
			ArrayList<String> words = this.processComment(rating[parser.fields.get("comments")]);
			commentAndStar.put(words, rating[parser.fields.get("student_star")].substring(0,1));
		}
		//ADD YOUR CODE ABOVE THIS
	}
}
