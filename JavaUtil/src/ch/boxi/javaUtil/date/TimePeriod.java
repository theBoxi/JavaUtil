package ch.boxi.javaUtil.date;

import java.sql.Date;

public class TimePeriod implements Comparable<TimePeriod>{
	public static final int BEFOR = -3;
	public static final int BEFOROVERLAPPING = -2;
	public static final int BIGGER = -1;
	public static final int EQUALS = 0;
	public static final int SMALLER = 1;
	public static final int AFTEROVERLAPPING = 2;
	public static final int AFTER = 3;
	
	private Date fromDate;
	private Date toDate;
	
	public TimePeriod(Date fromDate, Date toDate){
		setFromDate(fromDate);
		setToDate(toDate);
	}

	public Date getFromDate() {
		return fromDate;
	}public void setFromDate(Date fromDate) {
		if(fromDate == null){
			throw new NullPointerException("Null is not allawed!");
		}
		this.fromDate = fromDate;
	}public Date getToDate() {
		return toDate;
	}public void setToDate(Date toDate) {
		if(toDate == null){
			throw new NullPointerException("Null is not allawed!");
		}
		this.toDate = toDate;
	}
	
	@Override
	public String toString(){
		return "from: " + fromDate + " to: " + toDate;
	}

	@Override
	public int compareTo(TimePeriod o) {
		if(toDate.before(o.getFromDate()))
			return AFTER;
		if(fromDate.after(o.getFromDate()) && toDate.before(o.getToDate()))
			return BIGGER;
		if(toDate.before(o.getToDate()) && (toDate.after(o.getFromDate()) || toDate.compareTo(o.getFromDate()) == 0))
			return AFTEROVERLAPPING;
		if(toDate.compareTo(o.getToDate()) == 0 && fromDate.compareTo(o.getFromDate()) == 0)
			return EQUALS;
		if(fromDate.before(o.getFromDate()) && toDate.after(o.getToDate()))
			return SMALLER;
		if(fromDate.after(o.getFromDate()) && (fromDate.before(o.getToDate()) || fromDate.compareTo(o.getToDate()) == 0))
			return BEFOROVERLAPPING;
		else
			return BEFOR;
	}
	
	public int compareTo(Date d) {
		if(fromDate.after(d)){
			return BEFOR;
		} else if(
				(fromDate.before(d) || fromDate.compareTo(d) == 0)
				&& (toDate.after(d) || toDate.compareTo(d) == 0)){
			return EQUALS;
		} else{
			return AFTER;
		}	
	}

}
