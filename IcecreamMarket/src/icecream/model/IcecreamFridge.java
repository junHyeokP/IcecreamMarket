package icecream.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IcecreamFridge {

	ArrayList<Icecream> icecreamList = new ArrayList<>();
	final int MAX_QUANTITY = 10;
	private String icecreamFilename = "icecreamlist.txt";
	private boolean isSaved;
	
	public IcecreamFridge() throws IOException {
		loadIcecreamListFromFile();
		isSaved = true;
	}
	

	private void loadIcecreamListFromFile()throws IOException {
		BufferedReader fr;
		try {
			fr = new BufferedReader(new FileReader(icecreamFilename)); // 로컬 파일 내 icecreamlist.txt라는 파일을 찾아 읽어주는 명령어
			BufferedReader bf = new BufferedReader(fr);
			String name;
			while ((name = bf.readLine()) != null && !name.equals("")) {
				String taste = bf.readLine();
				int price = Integer.parseInt(bf.readLine());
				icecreamList.add(new Icecream(name, taste, price));
			}
		
		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage() + "파일을 불러오는중 오류가 발생했습니다.");
		}
		
	}
	
	public boolean isValidIcecream(String name) {
		for (Icecream icecream : icecreamList) {
			if (icecream.getName().equals(name)) return true;
		}
		return false;
	}

	public int getNumIcecreams() {
		return icecreamList.size();
	}
	
	public String getIcecreamInfo(int i) {
		return icecreamList.get(i).toString();
	}

	public boolean isValidBook(String name) {
		for (Icecream icecream : icecreamList) {
			if (icecream.getName() == name) {
				return true;
			}
		}
		return false;
	}

	public Icecream getIcecreamByName(String name) {
		for (Icecream icecream : icecreamList) {
			if (icecream.getName().equals(name)) {
				return icecream;
			}
		}
		return null;
	}
	

	public int getMaxQuantity() {
		return this.MAX_QUANTITY;
	}
	
	public boolean isEmpty() {
		return icecreamList.size() == 0;
	}


	public void deleteItem(String name) {
		icecreamList.remove(getIcecreamByName(name));
		isSaved = false;
		
	}

	public void addIcecream(String name, String taste, int price) {
		
		Icecream icecream = new Icecream(name, taste, price);
		icecreamList.add(icecream);
		isSaved = false;
	}
	public boolean isSaved() {
		return isSaved;
	}

	public void writeIcecreamList2File() {
		
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(icecreamFilename)); // 변수가 참조하고 있는 텍스트 파일에 Icecream의 정보들을 줄 바꿈하며 적음 
			for (Icecream icecream : icecreamList) {  
				fw.write(icecream.getName() + "\n");
				fw.write(icecream.getTaste() + "\n");
				fw.write(icecream.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public Icecream getIcecreamCupByName(String name, IcecreamCup icup) {
		return icup.getIcecreamFromCup(); // 아이스크림 컵 자체를 담으려 했는데 방법이 안떠올라서 그냥 아이스크림을 담음
	}
}

