package com.framework.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;


import com.google.common.primitives.Ints;

/**
 * @ClassName: NumberUtils
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Apr 9, 2014 5:56:41 PM
 * 
 */

public class NumberUtils {

	/**
	 * @Title: getRandomNumber
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param min
	 * @param @param max
	 * @param @return
	 * @return int return type
	 * @throws
	 */

	public static int getRandomNumber(int min, int max) {
		int returnvalue = min + (int) (Math.random() * max);
		return returnvalue;
	}

	/**
	 * @Title: getMaxNumber
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param array
	 * @param @return
	 * @return int return type
	 * @throws
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int getMaxNumber(int[] array) {
		List asList = Arrays.asList(ArrayUtils.toObject(array));

		Integer maxnumber = (Integer)Collections.max(asList);
		int intValue = maxnumber.intValue();
		return intValue;
	}

	/**
	 * @Title: getMinNumber
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param array
	 * @param @return
	 * @return int return type
	 * @throws
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int getMinNumber(int[] array) {
		List asList = Arrays.asList(ArrayUtils.toObject(array));

		Integer maxnumber = (Integer)Collections.min(asList);
		return maxnumber.intValue();
	}

	/**
	 * @Title: getMaxNumber2
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param array
	 * @param @return
	 * @return int return type
	 * @throws
	 */

	public static int getMaxNumber2(int[] array) {

		int maxnumber = Ints.max(array);
		return maxnumber;
	}

	/** 
	* @Title: getMaxNumberfromList 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param numberlist
	* @param @return    
	* @return int    return type
	* @throws 
	*/ 
	
	public static int getMaxNumberfromList(List<Integer> numberlist){
		 int[] intArray = new int[numberlist.size()];
		    for (int i = 0; i < numberlist.size(); i++) {
		        intArray[i] = numberlist.get(i);
		    }
		
		int maxnumber=Ints.max(intArray);
		return maxnumber;
	}
	
	/** 
	* @Title: getMinNumberfromList 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param numberlist
	* @param @return    
	* @return int    return type
	* @throws 
	*/ 
	
	public static int getMinNumberfromList(List<Integer> numberlist){
		 int[] intArray = new int[numberlist.size()];
		    for (int i = 0; i < numberlist.size(); i++) {
		        intArray[i] = numberlist.get(i);
		    }
		
		int maxnumber=Ints.min(intArray);
		return maxnumber;
	}
}
