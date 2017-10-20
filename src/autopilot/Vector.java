package autopilot;

public class Vector {

	
	private double xcoordinaat;
	private double ycoordinaat;
	private double zcoordinaat;
	

	public Vector(double x, double y ,double z) {
		this.xcoordinaat=x;
		this.ycoordinaat=y;
		this.zcoordinaat=z;
	}
	
	public double getXcoordinaat() {
		return this.xcoordinaat;
	}
	
	public double getYcoordinaat() {
		return this.ycoordinaat;
	}
	

	public double getZcoordinaat() {
		return this.zcoordinaat;
	}
	
	
	@SuppressWarnings("null")
	public Vector crossproduct(Vector vector1, Vector vector2 ){
		
		Vector vector_3 = null;
		vector_3.xcoordinaat = (vector1.ycoordinaat*vector2.zcoordinaat-vector1.zcoordinaat*vector2.ycoordinaat);
		vector_3.ycoordinaat = (vector1.zcoordinaat*vector2.xcoordinaat-vector1.xcoordinaat*vector2.zcoordinaat);
		vector_3.zcoordinaat = (vector1.xcoordinaat*vector2.ycoordinaat-vector1.ycoordinaat*vector2.xcoordinaat);
		
		return vector_3;
	}
	
	public double dotproduct( Vector vector1, Vector vector2 ){
		
		return vector1.xcoordinaat*vector2.xcoordinaat+vector1.ycoordinaat*vector2.ycoordinaat+vector1.zcoordinaat*vector2.zcoordinaat;	
	}
	
	@SuppressWarnings("null")
	public Vector add( Vector vector1, Vector vector2 ){
		
		Vector vector_3 = null;
		vector_3.xcoordinaat = vector1.xcoordinaat+vector2.xcoordinaat;
		vector_3.ycoordinaat = vector1.ycoordinaat+vector2.ycoordinaat;
		vector_3.zcoordinaat = vector1.zcoordinaat+vector2.zcoordinaat;
		
		return vector_3;
		
	}
	
	public Vector scale( Vector vector1, double a ){
		vector1.xcoordinaat=vector1.getXcoordinaat()*a;
		vector1.ycoordinaat=vector1.getYcoordinaat()*a;
		vector1.zcoordinaat=vector1.getZcoordinaat()*a;
		
		return vector1;
		
	}

	public double Norm( Vector vector1){
		return Math.sqrt(Math.pow(vector1.xcoordinaat,2)+Math.pow(vector1.ycoordinaat,2)+Math.pow(vector1.zcoordinaat, 2));
	}

	
	
	
	
	
	
	
	
	
	
}
