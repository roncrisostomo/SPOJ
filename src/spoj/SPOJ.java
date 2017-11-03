/**
 * @file       	SPOJ.java
 * @brief      	Entry point for solutions to SPOJ problems
 * @author     	Ron
 * @created 	November 1, 2017
 * @modified   	November 3, 2017
 */

package spoj;

import java.io.IOException;

/**
 * Entry point for solutions to SPOJ problems.
 */
public class SPOJ
{
    /**
     * Program entry point.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
//		long startTime = System.currentTimeMillis();
		
		new KNAPSACK().run();
//        new ONP().run();
//        new TEST().run();

//		long stopTime = System.currentTimeMillis();
//		long runTime = stopTime - startTime;
//		System.out.println("Run time: " + runTime);
    }
}