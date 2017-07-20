
/**
 * 
 * This is the visitor's interface that implements
 * VisitorCount, Positive, and ValidateUser.
 *
 */

public interface VisitorsInterface {
	
	public void visit(Users users);
	public void visit(UserGroup group);
	public void resetCount();
}
