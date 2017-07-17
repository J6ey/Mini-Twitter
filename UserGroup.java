import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class uses composite design.
 *
 */
public class UserGroup implements UserInterface{

	private String groupId;
	private List<UserInterface> groupList;
	
	public UserGroup(){
		
	}

	public UserGroup(String groupId){
		this.groupId = groupId;
		groupList = new ArrayList<UserInterface>();
	}
	
	public void addGroup(UserInterface group){
		groupList.add(group);
	}
	
	public String toString(){
		return groupId;
	}
	
	public void allowVisitor(VisitorsInterface visitor){
		visitor.visit(this);
		for(UserInterface user : groupList){
			user.allowVisitor(visitor);
		}
		
	}
	

	
	
}
