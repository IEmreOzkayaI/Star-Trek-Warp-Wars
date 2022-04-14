package tools;


public class RandomMovingList {
	private char list[];

	public RandomMovingList() {
		list = new char[0];
	}

	public void Add(char item) {// when come new data it creates itself copy.And "new itself = get's new instance" 
		  							  //after that gets their initial data back.
		char tempArray[] = list;
		list = new char[list.length + 1];

		for (int i = 0; i < tempArray.length; i++) {
			list[i] = tempArray[i];
		}
		list[list.length - 1] = item;

	}

	public char[] getList() { // return array
		return list;
	}
	
	public int length() { // return length of array
		return list.length;
	}
}
