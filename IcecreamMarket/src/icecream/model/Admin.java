package icecream.model;

public class Admin extends User {
	
	private final String AdminID = "junhyeok";
	private final String AdminPWD = "1237";
	
	
	public boolean confirmAdmin(String[] idpwdArr) {
		if (idpwdArr[0].equals(AdminID) && idpwdArr[1].equals(AdminPWD)) {
			return true;
		}
		return false;
	}
	
}
