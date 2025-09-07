package finalproject;

// import javafx.util.Pair;

// import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RatingDistributionByProf extends DataAnalyzer {
	private MyHashTable<String,String[]> allNamesAndRatings;
	private MyHashTable<String,Integer> ratingsAndCounts;

    public RatingDistributionByProf(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		ratingsAndCounts = new MyHashTable<String,Integer>(5);
		ratingsAndCounts.put("1",0);
		ratingsAndCounts.put("2",0);
		ratingsAndCounts.put("3",0);
		ratingsAndCounts.put("4",0);
		ratingsAndCounts.put("5",0);

		if (allNamesAndRatings.get(keyword.trim().toLowerCase()) == null){
			return null;
		}

		for (MyPair entry : allNamesAndRatings.getEntries()){
			if (entry.getKey().equals(keyword.trim().toLowerCase())){
				for (String rating : (String[])entry.getValue()){
					String value = rating.substring(0,1);
					ratingsAndCounts.put(value, ratingsAndCounts.get(value)+1);
				}
			}
		}
		return ratingsAndCounts;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		allNamesAndRatings = new MyHashTable<String, String[]>();

		for (String[] rating : this.parser.data) {
			String prof = rating[parser.fields.get("professor_name")].trim().toLowerCase();
			if (allNamesAndRatings.get(prof) == null) {
				String[] temp = { rating[parser.fields.get("student_star")] };
				allNamesAndRatings.put(prof, temp);
			} else {
				String[] currentRatings = allNamesAndRatings.get(prof);
				String[] newRatings = new String[currentRatings.length + 1];
				System.arraycopy(currentRatings, 0, newRatings, 0, currentRatings.length);
				newRatings[newRatings.length - 1] = rating[parser.fields.get("student_star")].substring(0, 1);
				allNamesAndRatings.put(prof, newRatings);
			}
		}
	}
}
