package eduardo.test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Generates a random array of arrays of integers or integers. Flattens an array of integers or integers
 * @author eduardo
 *
 */
public final class FlattenArray {
	// Default configuration values for generating random arrays
	public static int MAX_LENGH_ARRAY = 5;
	public static int MAX_INT_VALUE = 100;
	public static int MAX_DEEP_LEVEL = 5;
	
	private static int maxLenghArray;
	private static int maxIntValue;
	private static int maxDeepLevel;
	
	public static void setMaxLenghArray(int maxLenghArray) {
		FlattenArray.maxLenghArray = maxLenghArray;
	}

	public static int getMaxLenghArray() {
		return maxLenghArray;
	}

	public static int getMaxIntValue() {
		return maxIntValue;
	}

	public static int getMaxDeepLevel() {
		return maxDeepLevel;
	}

	public static void setMaxIntValue(int maxIntValue) {
		FlattenArray.maxIntValue = maxIntValue;
	}

	public static void setMaxDeepLevel(int maxDeepLevel) {
		FlattenArray.maxDeepLevel = maxDeepLevel;
	}
	
	static {
		maxLenghArray = maxLenghArray != 0? maxLenghArray: MAX_LENGH_ARRAY;
		maxIntValue = maxIntValue != 0? maxIntValue: MAX_INT_VALUE;
		maxDeepLevel = maxDeepLevel != 0? maxDeepLevel: maxDeepLevel;
	}
	
	/**
	 * Private constructor to avoid class instantiation
	 */
	private FlattenArray() {};
	
	/**
	 * Helper class for generating random complex integer arrays
	 * @return A random complex integer array object
	 */
	public static Object[] populateObject() {
		// Integer random generator
		Random rdm = new Random((new Date()).getTime());
		// Master array
		Object[] objectArray = new Object[rdm.nextInt(maxLenghArray)+1];
		populateObject(objectArray, rdm, 0);
		
		return objectArray;
		
	}

	/**
	 * Generates a random content for the object with other objects or integers
	 * @param o Object to populate
	 * @param rdm Random object to generate random integers
	 * @param level Max deep level of new arrays
	 */
	private static void populateObject(final Object[] o, final Random rdm, int level) {
		
		if(o == null) {
			return;
		}
		
		level++;
		// Populate array
		for(int i = 0; i < o.length; i++) {
			if(level <= maxDeepLevel) {
				o[i] = rdm.nextInt(2) == 0? rdm.nextInt(maxIntValue) : new Object[rdm.nextInt(maxLenghArray)+1];
				if(o[i] instanceof Object[]) {
					populateObject((Object[]) o[i], rdm, level);
				} 
			} else {
				o[i] = rdm.nextInt(maxIntValue);
			}
		}
	}
	
	public static int[] flatArray(final Object[] o) {
		
		return flatArray(o, null);
	}
	
	/**
	 * Flattens an array of arrays of integers or integers to a flat array of integers
	 * @param o Object that is an array of arrays of integers or integers
	 * @param flatArray Result temporal recursive array
	 * @return
	 */
	private static int[] flatArray(final Object[] o, int[] flatArray) {
		if(o == null) {
			return flatArray;
		}
	
		for(int i = 0; i < o.length; i++) {
			if(o[i] instanceof Object[]) {
				flatArray = flatArray((Object[]) o[i], flatArray);
			} else {
				if(flatArray == null) {
					flatArray = new int[1];
					flatArray[flatArray.length-1] = (int) o[i];
				} else {
					flatArray = reArrangeArray(flatArray);
					flatArray[flatArray.length-1] = (int) o[i];
				}
			}
		}
		return flatArray;
	}
	
	private static int[] reArrangeArray(final int[] intArray) {
		return Arrays.copyOf(intArray, intArray.length + 1);
	}
	
	public static String printFlatArray(final int[] flatArray) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int integer: flatArray) {
			sb.append(integer + ",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		
		return sb.toString();
	}
	

	public static void main(String[] args) {
		// Integer random generator
		Random rdm = new Random((new Date()).getTime());
		
		// Initiate with custom parameters
		setMaxLenghArray(6);
		setMaxIntValue(500);
		setMaxDeepLevel(4);
	
		// Master array
		Object[] objectArray = new Object[rdm.nextInt(maxLenghArray)+1];
		
		populateObject(objectArray, rdm, 0);
		int[] flatArray = flatArray(objectArray);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int integer: flatArray) {
			sb.append(integer + ",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		System.out.println(sb.toString());
		
		objectArray = populateObject();
		System.out.println(printFlatArray(flatArray(objectArray)));
	}
}
