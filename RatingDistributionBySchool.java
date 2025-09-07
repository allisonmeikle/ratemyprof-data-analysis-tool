package finalproject;

// import javafx.util.Pair;

import java.util.ArrayList;

public class RatingDistributionBySchool extends DataAnalyzer {
	private MyHashTable<String,MyHashTable<String,ArrayList<Double>>> allNamesAndRatings;
	private MyHashTable<String,Integer> result;

	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		result = new MyHashTable<String,Integer>();

		if (allNamesAndRatings.get(keyword.trim().toLowerCase()) == null){
			return null;
		}

		for (MyPair entry : allNamesAndRatings.getEntries()){
			if (entry.getKey().equals(keyword.trim().toLowerCase())){
				for (MyPair<String,ArrayList<Double>> prof : (ArrayList<MyPair>)((MyHashTable)entry.getValue()).getEntries()){
					double sum = 0.0;
					for (Double rating : prof.getValue()){
						sum += rating;
					}
					double average = Math.round(sum/prof.getValue().size() * 100) / 100.0;
					result.put(prof.getKey().concat("\n")+average, prof.getValue().size());
				}
			}
		}
		return result;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		allNamesAndRatings = new MyHashTable<String,MyHashTable<String,ArrayList<Double>>>();

		for (String[] rating : this.parser.data){
			String school = rating[parser.fields.get("school_name")].trim().toLowerCase();
			String prof = rating[parser.fields.get("professor_name")].trim().toLowerCase();

			if (allNamesAndRatings.get(school) == null){
				MyHashTable<String,ArrayList<Double>> temp = new MyHashTable();
				ArrayList<Double> ratings = new ArrayList<Double>();
				ratings.add(Double.parseDouble(rating[parser.fields.get("student_star")]));
				temp.put(prof, ratings);
				allNamesAndRatings.put(school, temp);
			}

			else if (allNamesAndRatings.get(school) != null && allNamesAndRatings.get(school).get(prof) == null){
				ArrayList<Double> ratings = new ArrayList<Double>();
				ratings.add(Double.parseDouble(rating[parser.fields.get("student_star")]));
				allNamesAndRatings.get(school).put(prof,ratings);
			}

			else if (allNamesAndRatings.get(school) != null && allNamesAndRatings.get(school).get(prof) != null) {
				ArrayList<Double> ratings = allNamesAndRatings.get(school).get(prof);
				ratings.add(Double.parseDouble(rating[parser.fields.get("student_star")]));
			}
		}
		//ADD YOUR CODE ABOVE THIS
	}
}
