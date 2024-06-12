package icecream.model;

import java.util.ArrayList;

public class IcecreamCup {
	
	private String taste;
    private boolean corn = false;
	
	public IcecreamCup(String newTaste) {
		this.taste = newTaste;
	}
	
	public String getTaste() {
		
		if (this.corn) {
			return taste += " 믹스 (콘 추가)";
		}
		return taste += " 믹스";
	}
	
	public void setCorn(boolean corn) {
		this.corn = corn;
	}
	public boolean getCorn() {
		return this.corn;
	}

	public Icecream getIcecreamFromCup() {
		
		// 아이스크림에 taste와 이름 부여
		Icecream mixedIce = new Icecream(1800, this.taste, "<커스텀 믹스>");
		return mixedIce;
		
	}

	

}
