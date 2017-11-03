/**
 * @file       	TEST.java
 * @brief      	Solution for TEST - Life, the Universe, and Everything
 * @author     	Ron
 * @created 	November 1, 2017
 * @modified   	November 1, 2017
 *      
 * @par [explanation]
 *		Rewrite small numbers (1-2 digits) from input to output.
 *		Stop processing input after reading in the number 42.
 *		Test values:
 *			Input:
 *			1
 *			2
 *			88
 *			42
 *			99
 *			
 *			Output:
 *			1
 *			2
 *			88
 */

package spoj;

import java.util.Scanner;

/**
 * Class for solving SPOJ TEST.
 */
public class TEST
{
    /**
     * Runs the solution for TEST.
     */
    public void run()
    {
        // Read system input
        Scanner in = new Scanner(System.in);
        // Continue reading input until a 42 or a non-integer is found
        while (in.hasNextInt())
        {
            int x = in.nextInt();
            if (x == 42)
            {
                break;
            }
			// Simply print the integer input (if 1-2 digits)
			if (x >= -99 && x <= 99)
			{
				System.out.println(x);
			}
        }
    }
}
