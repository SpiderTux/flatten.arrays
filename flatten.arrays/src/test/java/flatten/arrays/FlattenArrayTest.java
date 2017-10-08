package flatten.arrays;

import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import eduardo.test.FlattenArray;
import junit.framework.TestCase;

public class FlattenArrayTest extends TestCase {
	
	@Before
	public void init() {
		FlattenArray.setMaxDeepLevel(2);
		FlattenArray.setMaxIntValue(100);
		FlattenArray.setMaxLenghArray(2);
	}
	
	@Test
	public void populateObjectTest() {
		// Integer random generator
		Random rdm = new Random((new Date()).getTime());
		// Create an example array with integers and arrays
		Object[] o = new Object[FlattenArray.getMaxLenghArray()];
		FlattenArray.populateObject(o, rdm, 0);
		
		if( !(o instanceof Object[])) {
			fail();
		}
		
		for(Object object: o) {
			if( !(object instanceof Object[]) && (int) object > FlattenArray.getMaxIntValue()) {
				fail();
			}
		}
	}
	
	@Test
	public void flatArrayTest() {

		// Create an example array with integers and arrays
		Object[] o = new Object[FlattenArray.getMaxLenghArray()];
		o[0] = 1;
		Object[] arrayTemp = new Object[2];
		arrayTemp[0] = 2;
		arrayTemp[1] = 3;
		o[1] = arrayTemp;
		
		assertEquals("[1,2,3]", FlattenArray.printFlatArray(FlattenArray.flatArray(o)) );
	}
}
