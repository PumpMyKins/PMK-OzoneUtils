package fr.pmk_ozone.island.data;

public class IslandFileData {

	private int x;
	private int z;
	private String pathName;

	public IslandFileData(int x , int z , String p) {
		
		this.x = x;
		this.z = z;
		
		this.pathName = p;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
}
