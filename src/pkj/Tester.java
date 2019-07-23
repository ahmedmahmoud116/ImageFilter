package pkj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Tester extends Application{
	
	public Tester() {
		
	}

	@Override
	public void start(Stage arg0) throws Exception {	
		Pane gp = new Pane();
		
		MethodHelper mh = new MethodHelper();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("if you want gray images select 1 and if you want invert images select 2: ");
		int chos = sc.nextInt();
		while(chos != 1 && chos != 2) {
			System.out.println("Please choose 1 or 2:");
			chos = sc.nextInt();
		}
		switch(chos) {
		case(1):{
			ArrayList<Image> al = mh.selectgrayImages();
			HBox hbox = mh.printImages(al);
			gp = new Pane(hbox);
			break;
		}
		case(2):{

			ArrayList<Image> al2 = mh.selectinvImages();
			HBox hbox2 = mh.printImages(al2);
			gp = new Pane(hbox2);
			break;
		}
		}

		

		sc.close();
		Stage primarystage = new Stage();
		Scene scene = new Scene(gp);
		primarystage.setTitle("Your Images");
		primarystage.setScene(scene);
		primarystage.show();
	}
	
	
	public static void main(String[] args) throws IOException {
		Application.launch();
	}
}
