package autopilot;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;

public class Beeldherkenning {

	public Beeldherkenning() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
        
        try {
        	//Laad de openCV library in
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            
            Mat blurredImage = new Mat();
            Mat hsvImage = new Mat();
            Mat mask = new Mat();
            Mat morphOutput = new Mat();
            //Leest de image file van een folder
            Mat image = Imgcodecs.imread("Red_CUBE_2.jpg");
            
            
            // remove some noise
            Imgproc.blur(image, blurredImage, new Size(7, 7));
            
            // convert the frame to HSV
            Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
            displayImage( Mat2BufferedImage(blurredImage));
            
            //Zoek kleur tussen deze ranges (rood)
            Scalar minValues = new Scalar(0,0,0);
            Scalar maxValues = new Scalar(180,255,250);
            Core.inRange(hsvImage, minValues, maxValues, mask);
            
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            //Schrijft de mask in een folder
            //Imgcodecs.imwrite("mask.jpg", mask);
        
            // find contours
            Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
            System.out.println(contours);
            if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
            {
                    // for each contour, display it in blue
                    for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
                    {
                            Imgproc.drawContours(blurredImage, contours, idx, new Scalar(250, 0, 0));
                    }
            }
            
            //Imgproc.minEnclosingCircle(points, center, radius);
            displayImage( Mat2BufferedImage(mask));
            displayImage( Mat2BufferedImage(blurredImage));
        }
            catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
         }
       
}
	
	public static BufferedImage Mat2BufferedImage(Mat m){
		// source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
		// Fastest code
		// The output can be assigned either to a BufferedImage or to an Image

		    int type = BufferedImage.TYPE_BYTE_GRAY;
		    if ( m.channels() > 1 ) {
		        type = BufferedImage.TYPE_3BYTE_BGR;
		    }
		    int bufferSize = m.channels()*m.cols()*m.rows();
		    byte [] b = new byte[bufferSize];
		    m.get(0,0,b); // get all the pixels
		    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
		    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		    System.arraycopy(b, 0, targetPixels, 0, b.length);  
		    return image;
		}
	
	public static void displayImage(Image img2){   
	    //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
	    ImageIcon icon=new ImageIcon(img2);
	    JFrame frame=new JFrame();
	    frame.setLayout(new FlowLayout());        
	    frame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);     
	    JLabel lbl=new JLabel();
	    lbl.setIcon(icon);
	    frame.add(lbl);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}	


