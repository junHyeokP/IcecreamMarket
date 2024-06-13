package icecream.model;

import java.util.ArrayList;

public class IcecreamCup {
	
	private String taste;
    private boolean corn = false;
	
	public IcecreamCup(String newTaste) {
		this.taste = newTaste;
	}
	
	
	public String getTasteMix() {
		
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
		
		// 아이스크림에 이름, taste와 가격 부여, 이름인 <커스텀 믹스>와 가격 1800원은 고정
		Icecream mixedIce = new Icecream("<커스텀 믹스>", this.taste, 1800);
		return mixedIce;
		
	}

	

	

}
