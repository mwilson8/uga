package edu.uga.cs1302.mp3files;

import java.util.ListIterator;
import org.junit.*;
import static org.junit.Assert.*;

public final class SimpleArrayListTester {

	public SimpleArrayList<String> s1;
	public ListIterator<String> iter;
	
		@Before
		public void setUp()
		{
			
			s1 = new SimpleArrayList<String>();
			iter = s1.listIterator(0);		
		}
		
		@After
		public void tearDown()
		{
			s1 = null;
			iter = null;
		}
		
	
		@Test
		public void testConstructors()
		{
			assertNotNull(s1);
			assertFalse(iter.hasNext());
			assertFalse(iter.hasPrevious());
			iter = s1.listIterator(0);
			s1.add("extra");
			assertFalse(iter.hasNext());
			s1.add("string");
			assertTrue(iter.hasNext());
			iter = s1.listIterator(1);
			assertTrue(iter.hasPrevious());
			s1 = null;
			assertNull(s1);
		}
		
		@Test
		public void testHasNextandNext()
		{
			s1.add("hello");
			s1.add("world");
			iter=s1.listIterator(0);
			assertEquals("Error: Iter successfully has next", iter.hasNext(), true);
			assertEquals(iter.nextIndex(),1);
			assertTrue(iter.hasNext());
			iter.next();
			assertFalse(iter.hasNext());
		}
		
		@Test
		public void testHasPreviousandPrevious()
		{
			s1.add("hello");
			s1.add("world");
			iter=s1.listIterator(0);
			assertFalse(iter.hasPrevious());
			iter=s1.listIterator(2);
			assertTrue(iter.hasPrevious());
			assertEquals(iter.previousIndex(), 1);
			iter.previous();
			assertTrue(iter.hasPrevious());
			assertEquals(iter.previousIndex(),0);
			iter.previous();
			assertFalse(iter.hasPrevious());
		}
		

		
		
	
}
