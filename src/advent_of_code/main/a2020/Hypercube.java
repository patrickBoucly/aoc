package advent_of_code.main.a2020;

public class Hypercube {
	private int x;
	private int y;
	private int z;
    private int w;

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		result = prime * result + w;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hypercube other = (Hypercube) obj;
		if (x != other.getX())
			return false;
		if (y != other.getY())
			return false;
		if (z != other.getZ())
			return false;
		if (w != other.getW())
			return false;
		
		return true;
	}

	

	public Hypercube(int x, int y, int z,int w) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	@Override
	public String toString() {
		
		return "("+x+","+y+","+z+","+w+")";
	}
}
