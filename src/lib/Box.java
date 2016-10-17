package lib;

public class Box implements Comparable<Box>{

	private int height;
	private int length;
	private int width;

	public Box(int height, int length, int width){

		this.height = height;
		this.length = length;
		this.width = width;
	}

	public Box(){}

	public static Box createBox(int height, int side1, int side2){

		Box b = new Box();
		b.height = height;

		if(side1 >= side2) {

			b.length = side1;
			b.width = side2;
		} else {
			b.length = side2;
			b.width = side1;
		}

		return b;	
	}

	public int getHeight() {
		return height;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public int compareTo(Box b){
		if(this.length * this.width >= b.length * b.width)
			return -1;
		else {
			return 1;
		}
	}

}