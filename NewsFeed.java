import java.util.List;

public class NewsFeed implements ObserverInterface{
	
	private UPanel up;
	private List<Post> posts; 
	private int current;
	
	public NewsFeed(UserFeed feed, UPanel panel){
		this.up = panel;
		this.posts = feed.getPosts();
		current = posts.size();
	}
	
	public void update(){
		for(current = 0; current< posts.size(); current++){
			up.addNewsPost(posts.get(current));
		}
	}
	
}
