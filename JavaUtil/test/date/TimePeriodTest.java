package date;

import static org.junit.Assert.*;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import ch.boxi.javaUtil.date.TimePeriod;

public class TimePeriodTest {
	//timetabel: before, fromDate, in1, in2, toDate, after
	private TimePeriod period;
	private Date fromDate;
	private Date toDate;
	
	private Date after;
	private Date before;
	private Date in1;
	private Date in2;
	
	@Before
	public void setUp() throws Exception {
		fromDate = new Date(1000);
		toDate = new Date(2000);
		period = new TimePeriod(fromDate, toDate);
		
		after = new Date(toDate.getTime()+500);
		before = new Date(fromDate.getTime()-500);
		in1 = new Date(fromDate.getTime()+200);
		in2 = new Date(fromDate.getTime()+800);
	}
	
	@Test
	public void nullTest(){
		try{
			period.setFromDate(null);
		}catch(NullPointerException e){
			assertTrue(true);
		}
		try{
			period.setToDate(null);
		}catch(NullPointerException e){
			assertTrue(true);
		}
		try{
			period = new TimePeriod(null, toDate);
		}catch(NullPointerException e){
			assertTrue(true);
		}
		try{
			period = new TimePeriod(fromDate, null);
		}catch(NullPointerException e){
			assertTrue(true);
		}
		try{
			period = new TimePeriod(null, null);
		}catch(NullPointerException e){
			assertTrue(true);
		}
	}

	@Test
	public void testDateCompare(){
		assertEquals(TimePeriod.AFTER, period.compareTo(after));
		assertEquals(TimePeriod.BEFOR, period.compareTo(before));
		assertEquals(TimePeriod.EQUALS, period.compareTo(in1));
		
		assertEquals(TimePeriod.EQUALS, period.compareTo(fromDate));
		assertEquals(TimePeriod.EQUALS, period.compareTo(toDate));
	}
	
	@Test
	public void testOverlapping(){
		TimePeriod beforeOverlapping = new TimePeriod(before, in1);
		TimePeriod afterOverlapping = new TimePeriod(in2, after);
		
		TimePeriod beforeOverlappingFirstDate = new TimePeriod(before, fromDate);
		TimePeriod afterOverlappinglastDate = new TimePeriod(toDate, after);
		
		assertEquals(TimePeriod.BEFOROVERLAPPING, period.compareTo(beforeOverlapping));
		assertEquals(TimePeriod.AFTEROVERLAPPING, period.compareTo(afterOverlapping));
		
		assertEquals(TimePeriod.BEFOROVERLAPPING, period.compareTo(beforeOverlappingFirstDate));
		assertEquals(TimePeriod.AFTEROVERLAPPING, period.compareTo(afterOverlappinglastDate));
	}
	
	@Test
	public void testBiggerSmaller(){
		TimePeriod bigger = new TimePeriod(before, after);
		TimePeriod smaller = new TimePeriod(in1, in2);
		TimePeriod equals = new TimePeriod(fromDate, toDate);
		
		assertEquals(TimePeriod.BIGGER, period.compareTo(bigger));
		assertEquals(TimePeriod.SMALLER, bigger.compareTo(period));
		
		assertEquals(TimePeriod.SMALLER, period.compareTo(smaller));
		assertEquals(TimePeriod.BIGGER, smaller.compareTo(period));
		
		assertEquals(TimePeriod.EQUALS, equals.compareTo(period));
	}
}
