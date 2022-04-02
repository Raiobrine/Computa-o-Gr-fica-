package model;

public class Ponto {
	private int x;
	private int y;
	private int z;
	private int w;
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ponto(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.setZ(z);
	}
	
	public Ponto(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.setZ(z);
		this.setW(w);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
}
