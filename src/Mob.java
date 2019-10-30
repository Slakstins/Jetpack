import java.awt.Image;
import java.awt.Rectangle;

public abstract class Mob {
	private int xPos;
	private int yPos;
	private double xVel;
	private double yVel;
	private double xAcceleration;
	private double yAcceleration;
	private Image image;
	public final double gravity = 0.05;
	private int width;
	private int height;
	public final int defaultMobHeight = 100;
	public final int defaultMobWidth = 100;
	private double maxXVel = 0.5;
	private double maxYVel = 0.5;
	
	public Mob(int xPos, int yPos) {
		this.height = defaultMobHeight;
		this.width = defaultMobWidth;
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = 0;
		this.yVel = 0;
		this.xAcceleration = 0;
		this.yAcceleration = gravity;
		this.image = null;
		System.out.println("Position updated!");
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.defaultMobWidth, this.defaultMobHeight);
	}
	
	

	public abstract void fly();
	public abstract void kill();
	public abstract void spawn();
	public abstract void shoot();
	
	public void updateMovement() {
		this.velUpdate();
		this.posUpdate();
		
	}
	
	public void setXAccel(double xAccel) {
		this.xAcceleration = xAccel;
	}
	
	public void setYAccel(double yAccel) {
		this.yAcceleration = yAccel;
	}
	
	
	
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void setYVel(double d) {
		if (this.yVel > -1.5) {
		this.yVel = d;
		}
	}
	public double getYVel() {
		
		return this.yVel;
	}
	public double getXVel() {
		return this.xVel;
	}
	
	public void setXVel(double vel) {
//		if (vel > -this.maxXVel && vel < this.maxXVel) {
//			
//		}
		this.xVel = vel;
	}
	

	
	/**
	 * Call this often to make smooth!
	 * values will have to be reduced if calling more often for smoothness
	 */
	public void posUpdate() {
		this.xPos += this.xVel;
		this.yPos += this.yVel;
	}
	/**
	 * call often to make smooth
	 */
	public void velUpdate() {
		if (this.xVel > 0) {
			this.xVel -= 0.01;
		}
		if (this.xVel < 0) {
			this.xVel += 0.01;
		}
		if (this.xVel + this.xAcceleration < this.maxXVel || this.maxXVel + this.xAcceleration > -this.maxXVel) {
		this.xVel += this.xAcceleration;
		}
		if (this.yVel < this.maxYVel || this.yVel > -this.maxYVel) {
		this.yVel += this.yAcceleration;
		}
	}
	public double getXAcceleration() {
		return this.xAcceleration;
	}
	public double getYAcceleration() {
		return this.yAcceleration;
	}
	
	public void setXAcceleration(double accelerationX) {
		this.xAcceleration = accelerationX;
	}
	
	public void setYAcceleration(double accelerationY) {
		this.yAcceleration = accelerationY;
	}
	
	public int getX() {
		return this.xPos;
	}
	public int getY() {
		return this.yPos;
	}
	
	
	
	public void setImage(Image newImage) {
		this.image = newImage;
	}
	
	public Image getImage() {
		return this.image;
	}
	

	
	
	
	

}
