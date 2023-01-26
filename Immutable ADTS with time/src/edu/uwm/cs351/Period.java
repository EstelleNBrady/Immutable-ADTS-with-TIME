package edu.uwm.cs351;
import java.util.Objects;
//Estelle Brady
//CS - 351 section 401
//collaborated with some members in the tutoring room - EMS E280

/**
 * A time period within historical time.
 */

//created new objects that represent a period.
public class Period {
	private final Time startTime;
	private final Time endTime;
	private final Duration length;
	
	
	/**
	 * Construct endTime given the start time and length by adding start and length.
	 * @param s start time, must not be null
	 * @param l length, must not be null
	 * @throws NullPointerException if null.
	 * @returns s added to length to get endTime
	 */
	
	public Period(Time s, Duration l) {
		// TODO
		this.startTime=s;
		this.length=l;
		if (s == null || l == null)
			throw new NullPointerException("cannot be null");
		this.endTime = s.add(l);
	}
	
	
	/**
	 * Construct the length given the start and end time by adding them.
	 * @param from start time, must not be null
	 * @param to end time, must not be null or before the start time
	 * @throws NullPointerException
	 * @returns the difference of from to to in this.length.
	 */

	public Period(Time from, Time to) {
		this.startTime = from;
		this.endTime = to;
		if (from == null || to == null)
			throw new NullPointerException("cannot be null");
		this.length = from.difference(to);
	}
	
	/**
	 * Construct the starttime given the length and end time by adding them.
	 * @param len length of the period, must not be null
	 * @param end end time of the period.
	 * @throws NullPointerException if it is null
	 * @returns end and len subtracted to get this.StartTime
	 */
	public Period(Duration len, Time end) {
		// TODO
		this.length = len;
		this.endTime = end;
		if (len == null || end == null)
			throw new NullPointerException("cannot be null");
		this.startTime = end.subtract(len);
	}
	
	/**
	 * Return the start time of the period.
	 * @return beginning time
	 */
	public Time getStart() {
		return this.startTime;
	}
	
	/**
	 * Return the stop time of the period.
	 * @return end time
	 */
	public Time getStop() {
		return this.endTime;
	}
	
	/**
	 * Return the length of the period.
	 * @return the amount of time in this period
	 */
	public Duration getLength() {
		return this.length;
	}
	
	@Override // implementation
	public boolean equals(Object x) {
		if(x ==null)
			return false;
		if (!(x instanceof Period))
			return false;
		Period dx = (Period)x;
		if (startTime.equals(dx.startTime) && endTime.equals(dx.endTime) && length.equals(dx.length))
			return true;
		else 
			return false;
	}
	
	@Override // implementation
	public int hashCode() {
		return Objects.hash(this.getStart(), this.getStop(), this.getLength());
	}
	
	@Override // implementation
	public String toString() {
		return "["+  this.startTime+ "; " + this.length + "]";
		}
	
	/**
	 * Return whether this period overlaps with the parameter by using compare to and comparing to zero
	 * If one appointment starts where the other ends, they do not overlap.
	 * @param p period to compare to, must not be null
	 * @throws NullPointerException if it is null
	 * @return whether this period overlaps the parameter with true or false.
	 */
	public boolean overlap(Period p) {
		if (p == null)
			throw new NullPointerException("cannot be null");
		if ( this.getStart().compareTo(p.getStop()) == 0 || this.getStop().compareTo(p.getStart()) == 0) 
			return false;
		if( this.getStop().compareTo(p.getStart()) < 0 || this.getStart().compareTo(p.getStop()) >0)
			return false;
		return true;	
			}
}
