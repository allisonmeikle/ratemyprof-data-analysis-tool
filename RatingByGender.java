package finalproject;

import java.util.ArrayList;
// import java.util.HashMap;

// import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RatingByGender extends DataAnalyzer{
	private ArrayList<String[]> info;
	private MyHashTable<String,Integer> result;

	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		result = new MyHashTable<String,Integer>();
		result.put("1",0);
		result.put("2",0);
		result.put("3",0);
		result.put("4",0);
		result.put("5",0);

		keyword = keyword.toLowerCase();
		String[] inputs = keyword.split(",");
		if (inputs.length != 2){
			return null;
		}

		for (int i=0; i < inputs.length; i++){
			inputs[i] = inputs[i].trim();
		}
		if (!inputs[0].equals("f") && !inputs[0].equals("m")){
			return null;
		}

		for (String[] line : info){
			if (line[0].equals(inputs[0])){
				int i;
				if (inputs[1].equals("difficulty")){
					i = 2;
				} else if (inputs[1].equals("quality")){
					i = 1;
				} else {
					return null;
				}
				result.put(line[i], result.get(line[i]) + 1);
			}
		}
		return result;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		info = new ArrayList<String[]>();
		for (String[] rating : this.parser.data){
			String[] temp = {rating[parser.fields.get("gender")].trim().toLowerCase(), rating[parser.fields.get("student_star")].substring(0,1), rating[parser.fields.get("student_difficult")].substring(0,1)};
			info.add(temp);
		}
		//ADD YOUR CODE ABOVE THIS
	}
}
