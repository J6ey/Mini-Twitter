import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class uses composite design. Notifies new posts using observers .
 *
 */
public class Users implements UserInterface{
	
	private String userId;
	private List<Users> userList;
	private List<Users> followingList;
	private List<PostObs> followerList;
	private List<Post> messageList;
	private UserFeed feed;
	
	
	public Users(String id){
		this.userId = id;
		userList = new ArrayList<>();
		followingList = new ArrayList<>();
		followerList = new ArrayList<>();
		messageList = new ArrayList<>();
		followerList.add( new PostObs(messageList, feed));
		
	}
	
	public List<Post> getPosts(){
		return messageList;
	}
	
	public void post( String msg ){
		Post p = new Post(this, msg );
		messageList.add( p );
		notifyObservers();
	}
	
	public void addUser(Users user){
		userList.add(user);
	}
	
	public List<Users> getUserList(){
		return userList;
	}
	
	private void notifyObservers(){
		for ( PostObs ob : followerList ){
			ob.update();
		}
	}
	
	public void addFollowing(Users following){
		following.followerList.add(new PostObs( following.messageList, feed ) );
		followingList.add(following);
	}
	
	public List<Users> getFollower(){
		return followingList;
	}
	
	public String toString(){
		return userId;
	}
	
	public void allowVisitor(VisitorsInterface visitor) {
		visitor.visit(this);
	}
	
	public UserFeed getNewsFeed() {
		return feed;
	}
}
