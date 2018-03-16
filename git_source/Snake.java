package git_source;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Snake extends JFrame implements GameSnake  {
	File file;
	String directory = file.getParent();
	String file_name = file.getName();
	Image image;
	Graphics g;
	public Snake(File file) {
		this.directory = directory;
		this.file_name =  file_name;
		
		setSize(1000 , 1000);
		
		setTitle(file_name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(image);
		
	}
	
	@Override
	public void initSnake() {
		System.out.println("===========================================" + "\n" + "Initiallizing Snake..." + "\n" + "===========================================");
		loadChanges(file);
		loadImages(g);
	}

	private void loadImages(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();
        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.DARK_GRAY);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
        
        
	}

	@Override
	public void saveChanges(File file) {
		System.out.println("===========================================" + "\n" + "Inserting some information" + "\n" + "===========================================");
		Scanner scanner = new Scanner(System.in);
		String changes = scanner.nextLine();
		file.canWrite();
		byte [] buffer = changes.getBytes();
		
		try (FileOutputStream stream = new FileOutputStream(file)) {
			for (byte each : buffer) {
				stream.write(each);
			}
			
		}
		catch (IOException exception) {
			System.out.println("Output / Input error");
		} 
	}

	@Override
	public void loadChanges(File file) {
		file.canRead();
		try (FileInputStream stream = new FileInputStream(file)) {
			int symbol;
			
			while ((symbol = stream.read()) != -1) {
				System.out.print((char) symbol); 
			}
			
		}
		catch (IOException exception) {
			System.out.println("Input / Output error");
		}
	}

	@Override
	public  void closeSnake() {
		System.out.println("===========================================" + "\n" + "Closing Snake..." + "\n" + "===========================================");
	}
	
	
}
