package aubo;

public class PositionValue {
	public double joint1, joint2, joint3, joint4, joint5, joint6;
	public double x,y,z;
	
	public PositionValue() {
		joint1 = 0;
		joint2 = 0;
		joint3 = 0;
		joint4 = 0;
		joint5 = 0;
		joint6 = 0;
		x = 0;
		y = 0;
		z = 0;
	}
	
	public void setJointValue (int j1, int j2, int j3, int j4, int j5, int j6) {
		this.joint1 = j1;
		this.joint2 = j2;
		this.joint3 = j3;
		this.joint4 = j4;
		this.joint5 = j5;
		this.joint6 = j6;
	}
}
