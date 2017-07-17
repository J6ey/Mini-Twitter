

public class VisitorCount implements VisitorsInterface{
	
	private int usersNum;
	private int groupsNum;
	private int messageNum;
	
	public VisitorCount(){
		usersNum = 0;
		groupsNum = 0;
		messageNum = 0;
	}
	
	public void visit(Users users){
		usersNum++;
		messageNum = messageNum + users.getPosts().size();
	}
	
	public void visit(UserGroup group){
		groupsNum++;
	}
	
	public int getUsersNum(){
		return usersNum;
	}
	
	public int getGroupsNum(){
		return groupsNum;
	}
	
	public int getMessageNum(){
		return messageNum;
	}
	
	public void resetNum(){
		usersNum = 0;
		groupsNum = 0;
		messageNum = 0;
	}

	public void resetCount() {
		usersNum = 0;
		groupsNum = 0;
		messageNum = 0;
		
	}
	
}
