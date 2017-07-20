import java.util.HashSet;

public class ValidateUser implements VisitorsInterface{
	
	private boolean isValid;
	private HashSet<String> id;
	
	public ValidateUser(){
		id = new HashSet<String>();
		
	}

	public void visit(Users users) {
		if(id.contains(users.toString()) || id.contains(" ")){
			isValid = false;
		} else {
			id.add(users.toString());
		}
		
	}

	public void visit(UserGroup group) {
		if(id.contains(group.toString()) || id.contains(" ")){
			isValid = false;
		} else {
			id.add(group.toString());
		}
		
	}

	public void resetCount() {
		id = new HashSet<>();
		isValid = true;
		
	}
	
	public boolean valid(){
		return isValid;
	}
}
