/**
 * @file       	SPOJ.java
 * @brief      	Entry point for solutions to SPOJ problems
 * @author     	Ron
 * @created 	November 1, 2017
 * @modified   	November 4, 2017
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
		Solver solver = new KNAPSACK();
//		Solver solver = new ONP();
//		Solver solver = new TEST();
		solver.readInput();
		
		long startTime = System.nanoTime();
		
		solver.solve();
//        new ONP().run();
//        new TEST().run();

		long stopTime = System.nanoTime();
		float runTimeMs = (stopTime - startTime) * 0.000001f;
		System.out.println("Run time: " + runTimeMs + " ms");
    }
}