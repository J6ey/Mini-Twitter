import java.util.List;
/**
 * 
 * This class uses observers pattern on the user's class.
 *
 */

public class PostObs implements ObserverInterface{
	
		private List<Post> posts;
		private int current;
		private UserFeed feed;
		
		public PostObs(List<Post> posts, UserFeed feed){
			this.posts = posts;
			current = posts.size();
			this.feed = feed;
		}
		
		@Override
		public void update() {
			for ( ; current < posts.size(); current++){
				feed.addPost(posts.get(current));
			}
		}
}
