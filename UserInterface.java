
/**
 * 
 * This interface uses the composite pattern to implement 
 * Users and UserGroup
 *
 */
public interface UserInterface {
	public String toString();
	public void allowVisitor(VisitorsInterface visitor);
}
