package src;

public class Border {
	public int index1, index2;
	
	public Border(int index1, int index2) {
		this.index1 = index1;
		this.index2 = index2;
	}

	public boolean equals(Border other){
		return (this.index1 == other.index1 && this.index2 == other.index2) || ( (this.index1 == other.index2 && this.index2 == other.index1));
	}
}

