package pkj;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MethodHelper {
	
	public MethodHelper() {
		
	}
	
	public Image makeGray(Image img) {
		  int red = 0;
		  int green = 0;
		  int blue = 0;
		  int clr = 0;
          WritableImage img2 = new WritableImage((int)img.getWidth(), (int)img.getHeight());
          
		 for (int x = 0; x < img.getWidth(); x++) {
	            for (int y = 0; y < img.getHeight(); y++) {
	            	PixelReader pr = img.getPixelReader();
	            	   clr = pr.getArgb(x, y);
	            	   red = getRed(clr); 
	                   green = getGreen(clr);
	                   blue = getBlue(clr);
	                   
	                 
	                   
	                   int avg = (red+green+blue)/3;
	                   clr = setRed(avg, clr);
	                   clr = setGreen(avg, clr);
	                   clr = setBlue(avg, clr);
	                   
	                  PixelWriter pw = img2.getPixelWriter();
	                  pw.setArgb(x, y, clr);
	                  
	            }
	     }
         
		 return img2;
	}
	
	public void savegrayImage(Image img,String path){

		int reg = path.lastIndexOf("\\");
		int regdot = path.lastIndexOf(".");

		String name = path.substring(reg+1,regdot);
		name = name + "-gray.png";
		
		String path2 = path.substring(0,reg+1);
		String total = path2 + name;
	
		File output = new File(total);
		 RenderedImage renderedImage  = SwingFXUtils.fromFXImage(img, null);
        
        try {
        	ImageIO.write(renderedImage, "png", output);
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}
	
	public void saveinvImage(Image img,String path){

		int reg = path.lastIndexOf("\\");
		int regdot = path.lastIndexOf(".");

		String name = path.substring(reg+1,regdot);
		name = name + "-inv.png";
		
		String path2 = path.substring(0,reg+1);
		String total = path2 + name;
	
		File output = new File(total);
		 RenderedImage renderedImage  = SwingFXUtils.fromFXImage(img, null);
        
        try {
        	ImageIO.write(renderedImage, "png", output);
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}
	
	
	public ArrayList<Image> selectgrayImages() throws FileNotFoundException {
		ArrayList<Image> al = new ArrayList<Image>();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			FileInputStream fr = new FileInputStream(f);
			Image img = new Image(fr);
			Image img2 = makeGray(img);
			savegrayImage(img2,f.getPath());
			al.add(img);
			al.add(img2);
		}
		return al;
	}
	
	public HBox printImages(ArrayList<Image> al) {
		HBox hbox = new HBox();
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		int i = 1;
		for(Image img: al) {
			if(i%2 == 0)
			{
				ImageView imgv = new ImageView(img);
				imgv.setFitWidth(250);
				imgv.setFitHeight(250);
				vbox1.getChildren().add(imgv);
			}
			else {
				ImageView imgv = new ImageView(img);
				imgv.setFitWidth(250);
				imgv.setFitHeight(250);
				vbox2.getChildren().add(imgv);
			}
			i++;
		}
		hbox.getChildren().addAll(vbox2,vbox1);
		return hbox;
	}
	
	public Image makeInversion(Image img) {
		 int red = 0;
		  int green = 0;
		  int blue = 0;
		  int clr = 0;
         WritableImage img2 = new WritableImage((int)img.getWidth(), (int)img.getHeight());
         
		 for (int x = 0; x < img.getWidth(); x++) {
	            for (int y = 0; y < img.getHeight(); y++) {
	            	PixelReader pr = img.getPixelReader();
	            	   clr = pr.getArgb(x, y);
	            	   red = getRed(clr); 
	                   green = getGreen(clr);
	                   blue = getBlue(clr);
	                   
	                   clr = setRed(255-red, clr);
	                   clr = setGreen(255-green, clr);
	                   clr = setBlue(255-blue, clr);
	                   
	                  PixelWriter pw = img2.getPixelWriter();
	                  pw.setArgb(x, y, clr);
	                  
	            }
	     }
        
		 return img2;
	}
	
	public ArrayList<Image> selectinvImages() throws FileNotFoundException {
		ArrayList<Image> al = new ArrayList<Image>();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			FileInputStream fr = new FileInputStream(f);
			Image img = new Image(fr);
			Image img2 = makeInversion(img);
			saveinvImage(img2,f.getPath());
			al.add(img);
			al.add(img2);
		}
		return al;
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
