import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 
 * counts and returns number of positive messages.
 *
 */


public class Positive implements VisitorsInterface {
	
	private int positiveNum;
	private int totalNum;
	private Set<String> goodWords;
	
	public Positive(){
		String[] words = {"good", "great", "excellent", "awesome", "superb", "cute", "astonishing"};
		goodWords = new HashSet<String>();
		for (String word : words){
			goodWords.add(word);
		}
	}
	
	public void visit(Users user) {
		List<Post> posts = user.getPosts();
		for( Post p : posts ){
			totalNum++;
			String contents = p.getMessage();
			for ( String word : contents.split(" ") ){
				word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				if ( goodWords.contains(word) ){
					positiveNum++;
					break;
				}
			}
		}
	}
	
	public double getPositivePercentage(){
		if (totalNum == 0){
			return 0;
		}
		return  (double)positiveNum / totalNum;
	}
	

	public void visit(UserGroup group){
	}
	
	public void resetCount(){
		positiveNum = 0;
		totalNum = 0;
	}
}
