package pkj;

public class Pixel {

	public Pixel() {
		
	}
	
	
	
	
	
	public int getRed(int color) {
		int red = (color & 0x00ff0000) >> 16; //and then shift right by 16 bits
		return red;
	}
	
	public int getGreen(int color) {
		int green = (color & 0x0000ff00) >> 8;
		return green;
	}
	
	public int getBlue(int color) {
		int blue = color & 0x000000ff;
		return blue;
	}
	
	public int setRed(int Red,int color) {
		color = (color & 0xff00ffff); 
		color = color + (Red << 16);
		return color;
	}
	
	
	public int setGreen(int Green,int color) {
		color = (color & 0xffff00ff); 
		color = color + (Green << 8);
		return color;
	}
	
	public int setBlue(int Blue,int color) {
		color = (color & 0xffffff00); 
		color = color + Blue;
		return color;
	}
}
