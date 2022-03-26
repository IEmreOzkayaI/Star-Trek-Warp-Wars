package tools;

import entities.Computer;

public class ComputerList {

		
		private Computer list[];

		public ComputerList() {
			list = new Computer[0];
		}

		public void Add(Computer item) {// when come new data it creates itself copy.And "new itself = get's new instance" 
			  							  //after that gets their initial data back.
			Computer tempArray[] = list;
			list = new Computer[list.length + 1];

			for (int i = 0; i < tempArray.length; i++) {
				list[i] = tempArray[i];
			}
			list[list.length - 1] = item;

		}

		public Computer[] getList() { // return array
			return list;
		}
		
		public int length() { // return length of array
			return list.length;
		}


}
