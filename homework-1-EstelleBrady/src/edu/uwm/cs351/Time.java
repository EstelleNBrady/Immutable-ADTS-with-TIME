package edu.uwm.cs351;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
//Estelle Brady
//CS - 351 section 401
//collaborated with some members in the tutoring room - EMS E280
/**
 * A point in time.
 */

public class Time implements Comparable<Time> {
	
	//we are creating a new object in Time.
	private final long point;
	
	/**
	 * constructor - Makes this.point to equal a time
	 * @param time
	 */
	//setting point to be time.
	private Time(long time) {

		this.point = time;	
	}
	
	@Override // implementation
	public int hashCode() {
		return Objects.hash(this.point);
	}
	

	public boolean equals(Object x) {
		if(x == null)
			return false;
		if (!(x instanceof Time))
			return false;
		Time dx = (Time)x;
		if (dx.point == this.point)
			return true;
		else 
			return false;
	}

	/**
	 * sets point to be in current time in milliseconds
	 */
	public Time() {
		point = System.currentTimeMillis();
	}
	
	/**
	 * this method will set a pattern that is wanted to for string, creates new simpledateformat object
	 * sets this to the pattern. It then sets the time zone and we plug in the point into our new object and into the patter
	 * @return String of the date that was created.
	 */
	public String toString() {
		String format = "z G yyyy/MM/dd HH:mm:ss";
		SimpleDateFormat simpDF = new SimpleDateFormat(format);
		simpDF.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = simpDF.format(new Date(point));
		return date;
	}
	
	
	/**
	 * Create a time according to the time parameter.
	 * @param c calendar object representing a time, must not be null
	 * @throws NullPointerException if it is null
	 */
	public Time(Calendar c) {
		// TODO
		if (c == null)
			throw new NullPointerException();
		point = c.getTimeInMillis();
		
	}
	

	
	/**
	 * Return the difference between two time points.
	 * The order doesn't matter --- the difference is always a
	 * (positive) Duration. We must do a check to make sure it won't be less than 0.
	 * Conveted the time into a duration.
	 * @param t time point to compute difference with
	 * @return duration between two time points. - finalDiff
	 */
	public Duration difference(Time t) {
		Duration t8 = Duration.MILLISECOND.scale(this.point);
		Duration t9 = Duration.MILLISECOND.scale(t.point);
		if(this.point < t.point) {
			Duration variable1 = t9.subtract(t8);
			return variable1;
		}
		else if(this.point > t.point) {
			Duration variable2 = t8.subtract(t9);
			return variable2;
		}
		Duration finalDif = t8.subtract(t9);
		return finalDif;
		}
		
				
		
	

	/**
	 * Return the time point after a particular duration.
	 * If the point advances too far into the future,
	 * more than a hundred million years from now, this
	 * method may malfunction.
	 * Converts a time into a duration, call add method, convert to a double, a long and back into time.
	 * @param d duration to advance, must not be null
	 * @throws NullPointerException if it is null.
	 * @return new time after given duration - finalAdd
	 */
	public Time add(Duration d) {
		if (d == null) 
			throw new NullPointerException("argument is null");
		Duration scaleT = Duration.MILLISECOND.scale(this.point);
		Duration addDur = scaleT.add(d);
		double divv = addDur.divide(d.MILLISECOND);
		Time finalAdd = new Time(Math.round(divv));
		return finalAdd;
	}

	
	/**
	 * Return the time point before a particular duration.
	 * If a point regresses too far into the past,
	 * more than a hundred million years from now,
	 * this method may malfunction.
	 * we convert to a duration, call subtract method, convert into a double, a long and then a time
	 * @param d duration to regress, must not be null
	 * @throws NullPointerException if null
	 * @return new time before this one by the given duration. - finalSub
	 */
	public Time subtract(Duration d) {
		if (d == null) 
			throw new NullPointerException("argument is null");
		Duration scaleTimeS = Duration.MILLISECOND.scale(this.point);
		Duration subDur = scaleTimeS.subtract(d);
		double divS = subDur.divide(d.MILLISECOND);
		Time finalSub = new Time(Math.round(divS));
		return finalSub;
	}
	
	/**
	 * Return the time as a (mutable) Calendar object.
	 * @return new Calendar object for time.
	 */
	public Calendar asCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(point);
		return cal;
	}

	@Override // implementation
	public int compareTo(Time o) {
		if (this.point < o.point)
			return -1; //-1 if this comes before (less than value since duration)
		if (this.point == o.point)
			return 0;
		else 
			return 1;
	}
}
