package main.java.model.utility;

/**
 * The <code>Dimension</code> class encapsulates the width and
 * height of a component in a single object.
 */
public class Dimension {

	/**
	 * The width dimension;  negative values can be used.
	 */
	public int width;

	/**
	 * The height dimension;  negative values can be used.
	 */
	public int height;

	public Dimension(){
		this(0,0);
	}
	public Dimension(int i){
		this(i,i);
	}
	
	public Dimension(int width, int height){
		this.height = height;
		this.width = width;
	}
	public Dimension(Dimension d){
		this(d.width,d.height);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
    public String toString() {
        return getClass().getName() + "[width=" + width + ",height=" + height + "]";
    }
	
	
}
