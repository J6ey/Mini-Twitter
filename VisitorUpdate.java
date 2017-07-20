
public class VisitorUpdate implements VisitorsInterface{
	Users updated;
	
	public void visit(Users user){
		if(updated == null){
			updated = user;
		} else {
			long prevTime = updated.getNewsFeed().getPrevTime();
			long nextTime = user.getNewsFeed().getPrevTime();
			if(prevTime < nextTime){
				updated = user;
			}
		}
	}
	
	public Users getNewTime(){
		return updated;
	}
	

	@Override
	public void visit(UserGroup group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetCount() {
		updated = null;
		
	}

}
