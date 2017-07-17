import java.util.ArrayList;
import java.util.List;


public class UserFeed{
	private List<Post> feeds;
	private List<NewsFeed> observers;
	
	public UserFeed(){
		feeds= new ArrayList<Post>();
		observers = new ArrayList<NewsFeed>();
	}

	public void addPost(Post post) {
		feeds.add(post);
		notifyObservers();
	}
	
	private void notifyObservers() {
		for (ObserverInterface ob : observers){
			ob.update();
		}
	}

	public void attachNewsFeedObserver( NewsFeed obs ){
		observers.add(obs);
	}
	
	public void detachNewsFeedObserver( NewsFeed obs ){
		observers.remove(obs);
	}
	
	public List<Post> getPosts(){
		return feeds;
	}

}
