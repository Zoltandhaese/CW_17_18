package testbed;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import autopilot.Drone;
import autopilot.Vector;

public class Fysica_Testbed {
	
	private static final double XNow = 0;
	private static final double ZNow = 0;
	private static final double YNow = 0;


	public Drone drone;

	private double verStabilization;
	private double rightwingi;
	private double horStabilization;
	private double leftwingi;
	private double thrust;

	public Fysica_Testbed( double thrust, double leftwingi, double rightwingi, double horStabilization, double verStabilization  ) {
		setthrust(thrust);
		setleftwing(leftwingi );
		setrightwing(rightwingi);
		setHor(horStabilization);
		setVer(verStabilization);
	}

	private void setVer(double verStabilization) {
		// TODO Auto-generated method stub
		this.verStabilization=verStabilization;
	}

	private void setrightwing(double rightwingi) {
		this.rightwingi=rightwingi;		
	}

	private void setHor(double horStabilization) {
		this.horStabilization=horStabilization;
	}

	private void setleftwing(double leftwingi) {
		this.leftwingi=leftwingi;
		
	}

	private void setthrust(double thrust) {
		this.thrust=thrust;
	}
	
	public Vector AttackvectorLeft = new Vector (Math.sin(0),Math.sin(this.leftwingi), -Math.cos(this.leftwingi));
	public Vector AttackvectorRight = new  Vector (Math.sin(0),Math.sin(this.rightwingi), -Math.cos(this.rightwingi));
	public Vector AttackvectorVertical = new Vector (Math.sin(this.verStabilization),Math.sin(0), -Math.cos(this.verStabilization));
	public Vector AttackvectorHorizontal = new Vector (Math.sin(0),Math.sin(this.horStabilization), -Math.cos(this.horStabilization));
	
	public double XvelocityStart = 0;
	public double YvelocityStart = 0;
	public double ZvelocityStart = -100;


	private double XNextposition;
	private double YNextposition;
	private double ZNextposition;
	
	//met krachten werken hoe?
	
	public float[] Berekening_lift(){
		
	}
	public double getAccelerationX(){
		double Xacceleration = 0;
		return Xacceleration ;
	}
	
	public double getAccelerationY(){
		double AccelerationY = 0;
		return AccelerationY;
	}
	
	public double getAccelerationZ(){
		double AccelerationZ = 0;
		return AccelerationZ;
	}
		
	
	
	public Vector Nextposition (double time){
		//voor elke as de bewegings vergelijking uitschijven met de a van de krachten en de vorige v (als die er is (0))
		 
		 
		
		 double x  =this.XNow + XvelocityStart*time+this.getAccelerationX()/2*Math.pow(time, 2);
		 double y = this.YNow + YvelocityStart*time+this.getAccelerationY()/2*Math.pow(time, 2);
		 double z = this.ZNow + ZvelocityStart*time+this.getAccelerationZ()/2*Math.pow(time, 2);
		
		 Vector Nextposition = new Vector(x, y, z);
		 
		return Nextposition;
		
		
		
	}     
}
	
		

