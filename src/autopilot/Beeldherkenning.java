package autopilot;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;

public class Beeldherkenning {

	private final int WIDTH = 200;
	private final int HEIGHT = 200;
	private float focalLength = 0.01f;
	private float objectSize = 1000f;
    private float[] radius = new float[1];
	private Point center = new Point();
	private Point screenCenter = new Point(100,100);
	
	private void imageRecognition(byte[] data){
	  //Laad de openCV library in
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        
      //Lees image in
        Mat image = new Mat(HEIGHT, WIDTH, CvType.CV_8SC3);
        image.put(0, 0, data);
        
      //Maak images aan
        Mat blurredImage = new Mat();
        Mat hsvImage = new Mat();
        Mat mask = new Mat();
        

        displayImage( Mat2BufferedImage(image));
        
      //convert the frame to HSV
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
        displayImage( Mat2BufferedImage(blurredImage));
        
      //Zoek kleur tussen deze ranges (rood)
        Scalar minValues = new Scalar(0,0,0);
        Scalar maxValues = new Scalar(180,255,250);
        Core.inRange(hsvImage, minValues, maxValues, mask);
        
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

      //Zoek de contouren van de mask
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
                
                for (int i = 0; i >= 0; i = (int) hierarchy.get(0, i)[0])
                {
                		//Teken de contouren in blauw
                        Imgproc.drawContours(blurredImage, contours, i, new Scalar(255, 0, 0));
                        //Bepaal minimal enclosing circle en teken hem op blurredImage
                        MatOfPoint2f contour2f = new MatOfPoint2f( contours.get(i).toArray() );                            
                        Imgproc.minEnclosingCircle(contour2f, center, radius);
                        int radiusInt = Math.round(radius[0]);
                        Imgproc.circle(blurredImage, center, radiusInt, new Scalar( 255,0 , 0 ));
                }
        }
        
        displayImage( Mat2BufferedImage(mask));
        displayImage( Mat2BufferedImage(blurredImage));
        System.out.println(distanceToObject(2*radius[0]));
        System.out.println(horizontalAngle(center));
    }

	public void step(){
		
		
		
	}
	
	
//	public static void main(String[] args){
//        
//        try {
//        	//Laad de openCV library in
//            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//            
//            //Maak images aan
//            Mat blurredImage = new Mat();
//            Mat hsvImage = new Mat();
//            Mat mask = new Mat();
//            
//            //Leest de image file van een folder
//            //Mat image = Imgcodecs.imread("C:\\Image\\cube2.jpg");
//            
//            //Zet data = byte[] om in Mat
//            byte[] data = Files.readAllBytes(new File("C:\\Image\\pixels.txt").toPath());
//            Mat image = new Mat(200, 200, CvType.CV_8UC3);
//            image.put(0, 0, data);
//            
//            //byte[] data = Files.readAllBytes(fileLocation);
//            //byte[] data = java.nio.file.Files.readAllBytes(fileLocation);
//            //image = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
//            
//            displayImage( Mat2BufferedImage(image));
//            // remove some noise
//            Imgproc.blur(image, blurredImage, new Size(7, 7));
//            
//            // convert the frame to HSV
//            Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
//            displayImage( Mat2BufferedImage(blurredImage));
//            
//            //Zoek kleur tussen deze ranges (rood)
//            Scalar minValues = new Scalar(0,0,0);
//            Scalar maxValues = new Scalar(180,255,250);
//            Core.inRange(hsvImage, minValues, maxValues, mask);
//            
//            List<MatOfPoint> contours = new ArrayList<>();
//            Mat hierarchy = new Mat();
//            //Schrijft de mask in een folder
//            //Imgcodecs.imwrite("mask.jpg", mask);
//            
//            //Radius en centrum van de minimaal insluitende cirkel
//            float[] radius = new float[1];
//			Point center = new Point();
//        
//            // find contours
//            Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
//            if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
//            {
//                    
//                    for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
//                    {
//                    		//Teken de contouren in blauw
//                            Imgproc.drawContours(blurredImage, contours, idx, new Scalar(255, 0, 0));
//                            //Bepaal minimal enclosing circle en teken hem op blurredImage
//                            MatOfPoint2f contour2f = new MatOfPoint2f( contours.get(idx).toArray() );                            
//                            Imgproc.minEnclosingCircle(contour2f, center, radius);
//                            int radiusInt = Math.round(radius[0]);
//                            Imgproc.circle(blurredImage, center, radiusInt, new Scalar( 255,0 , 0 ));
//                    }
//            }
//            
//            displayImage( Mat2BufferedImage(mask));
//            displayImage( Mat2BufferedImage(blurredImage));
//            System.out.println(distanceToObject(2*radius[0]));
//            System.out.println(horizontalAngle(center));
//        }
//            catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//         }
//       
//}
	

	//Bereken afstand van een object tot de camera
	public float distanceToObject(float objectHeightOnSensor){
		System.out.println(objectHeightOnSensor);
		return objectSize / objectHeightOnSensor * focalLength;
		
	}
	
	//Bereken de horizontale hoek tussen middelpunt van de afbeelding en het object
	public double horizontalAngle(Point center){
		double distancePointCenter = Math.abs(center.x - screenCenter.x);
		return Math.atan(distancePointCenter/focalLength);
	}
	
	//Bereken de verticale hoek tussen middelpunt van de afbeelding en het object
	public double verticalAngle(Point center){
		double distancePointCenter = Math.abs(center.y - screenCenter.y);
		return Math.atan(distancePointCenter/focalLength);
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
	//Display the window
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
