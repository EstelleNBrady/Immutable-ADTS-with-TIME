package edu.uwm.cs351;
import java.util.Objects;

//Estelle Brady
//CS - 351 section 401
//collaborated with some members in the tutoring room - EMS E280
/**
 * An amount of time, always positive.
 * To create a duration, scale an existing duration.
 */
public class Duration implements Comparable<Duration> {
	private final long extent; // this must remain private.  Do NOT add a getter!
	
	
	// this constructor must remain private:
	private Duration(long milliseconds) {
		extent = milliseconds;
	}
	
	//created final constants that can't be manipulated 
	public static final Duration INSTANTANEOUS = new Duration(0);
	public static final Duration MILLISECOND = new Duration(1);
	public static final Duration SECOND = new Duration(1000);
	public static final Duration MINUTE = new Duration(60000);
	public static final Duration HOUR = new Duration(3600000);
	public static final Duration DAY = new Duration(86400000);
	public static final Duration YEAR = new Duration(31557600000L);



	

	
	// If you are overriding a method from a super class, always
	// annotate it "@Override" as here, overriding Object#equals(Object)
	
	/**
	 * will check validity of x and if equal to an object
	 * @param x added cannot be null
	 *@return true for false
	 *
	 */
	@Override // implementation
	public boolean equals(Object x) {
		if(x == null)
			return false;
		if (!(x instanceof Duration))
			return false;
		Duration dx = (Duration)x;
		if (dx.extent == this.extent)
			return true;
		else 
			return false;
		
	}
	
	@Override // implementation
	public int hashCode() {
		return Objects.hash(this.extent);
	}
	
	// If you are overriding a method from an interface, then Java 5
	// says you CANNOT use Override, but anything later says you MAY.
	// Please always do unless you write a javadoc comment. 
	@Override // required
	public int compareTo(Duration other) {
		if (this.extent < other.extent)
			return -1; //-1 if this comes before (less than value since duration)
		if (this.extent == other.extent)
			return 0;
		else 
			return 1;
	}
	

		
	
	
	@Override // implementation
	public String toString() {
		
		if(this.extent < 1000)
			return (double)extent + " ms.";
		if(this.extent >= 1000 && this.extent < 60000)
			return divide(SECOND) + " s.";
		if(this.extent >= 60000 && this.extent < 3600000)
			return divide(MINUTE) + " min.";
		if(this.extent >= 3600000 && this.extent < 86400000)
			return divide(HOUR) + " hr.";
		if(this.extent >= 86400000 && this.extent < 31557600000L)
			return divide(DAY) + " days";
		if(this.extent >= 31557600000L)
			return divide(YEAR) + " years";
		return "0";
		
		
	}
	
	// Methods that are not standard or private must have a documentation comment:
	
	/**
	 * Add two durations together to get a larger duration.
	 * @param d duration being added to this, must not be null
	 * @return new duration equal to the combined duration of this and the argument.
	 * @throws NullPointerException if d is null
	 */
	
	//If the duration is null, instead of the program crashing it will throw the exception message.
	//the argument duration and the this duration are added together.
	public Duration add(Duration d) {
		if (d == null) 
			throw new NullPointerException("argument is null");
		Duration sum = new Duration(d.extent + this.extent);
		return sum;
	}
	
		
	
	
	/**
	 * Subtracts two durations to get a new duration
	 * @param dur is checked to be less that than this.extent
	 * @throws ArithmeticException if it is null
	 * @return a new duration that is subtracting the param and the current duration.
	 */
	public Duration subtract(Duration dur) {
		if (dur.extent > this.extent)
			throw new ArithmeticException("arithmetic exception");
		Duration sub = new Duration(this.extent - dur.extent);
		return sub;
	}
	
	/**
	 * this rounds and multiplies the param and the current object to get double
	 * @param d1 is checked to be less than zero
	 * @throws IllegalArgumentException if d1 is less than 0
	 * @return a double that is multiplies this.extent and the param and is rounded.
	 */
	public Duration scale(double d1) {
		if (d1 < 0)
			throw new IllegalArgumentException("cannot be a negative number");
	 Duration scaled = new Duration (Math.round(d1 * this.extent));
	return scaled;
	}
	
	/**
	 * divides two durations and returns it as a double.
	 * @param d2 is checked to equal zero
	 * @throws a new arithmeticException error if d2 is equal to 0
	 * @return a double of the param divided by the current duration
	 */
	public double divide(Duration d2) {
		if(d2.extent == 0)
			throw new ArithmeticException("Can not be divided by zero - arithmetic exception");
		double divideExtent = this.extent;
		double argumentExtent = d2.extent;
		double div = (divideExtent / argumentExtent );
		return div;
}
}
