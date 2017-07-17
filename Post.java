
public class Post {
	private String message;
	private Users user;
	
	public Post( Users user, String msg ){
		this.message = msg;
		this.user = user;
	}
	
	public String getMessage(){
		return message;
	}
	
	public Users getUser(){
		return user;
	}
	
	public String toString(){
		return user + ": " + message;
	}
}
