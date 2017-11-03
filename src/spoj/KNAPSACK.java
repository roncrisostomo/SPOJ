/**
 * @file       	KNAPSACK.java
 * @brief      	Solution for KNAPSACK - The Knapsack Problem
 * @author     	Ron
 * @created 	November 1, 2017
 * @modified   	November 3, 2017
 *      
 * @par [explanation]
 *		You are packing for a vacation, but have limited space in your knapsack.
 *		Maximize the total value of items you can pack in your knapsack.
 *		Input format:
 *			Line 1 - [Knapsack capacity] [Number of potential items to bring]
 *			Line 2+ - [Item size] [Item value] (one item per line)
 *		Test values:
 *			Input:
 *			4 5
 *			1 8
 *			2 4
 *			3 0
 *			2 5
 *			2 3
 * 
 *			Output:
 *			13
 */

package spoj;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for solving SPOJ KNAPSACK.
 */
public class KNAPSACK
{
    /**
     * Runs the solution for KNAPSACK.
     */
    public void run()
    {
		// Read system input
        Scanner in = new Scanner(System.in);
		
		// Read S and N from input
        int S = in.nextInt();
		int N = in.nextInt();
		in.nextLine();
		// Create list of N Items
		List<Item> items = new ArrayList<>(N);
		// Read all N items from input
		for (int itemNo = 0; itemNo < N; ++itemNo)
		{
			Item newItem = new Item();
			newItem.size = in.nextInt();
			newItem.value = in.nextInt();
			items.add(newItem);
			in.nextLine();
		}
		// Solve
		int maxV = solveKnapsack(items, S, N - 1);
//		int maxV = solveKnapsack_dp(items, S, N);
		System.out.println(maxV);
    }
	
	/**
	 * Solves the Knapsack problem with the given list of items.
	 * @param items List of items that can be put in the knapsack
	 * @param remS Remaining space in the knapsack
	 * @param i Current item index
	 * @return The maximum total value of items that can be put in the knapsack
	 */
	private int solveKnapsack(List<Item> items, int remS, int i)
	{
		// Stop when we reach the last item
		if (i < 0)
		{
			return 0;
		}
		// If there's not enough space for item i, skip it and check the next item
		if (items.get(i).size > remS)
		{
			return solveKnapsack(items, remS, i - 1);
		}
		// If item i can fit...
		else
		{
			// Select which would get a better result between leaving it out or putting it in the knapsack
			return Math.max(solveKnapsack(items, remS, i - 1),
							solveKnapsack(items, remS - items.get(i).size, i - 1) + items.get(i).value);
		}
	}
	
	/**
	 * Solves the Knapsack problem with the given list of items using dynamic programming.
	 * @param items List of items that can be put in the knapsack
	 * @param S Knapsack capacity
	 * @param N Number of items to choose from
	 * @return The maximum total value of items that can be put in the knapsack
	 */
	private int solveKnapsack_dp(List<Item> items, int S, int N)
	{
		Integer[][] m = new Integer[N+1][S+1];
		for (int j = 0; j <= S; ++j)
		{
			m[0][j] = 0;
		}
		for (int i = 1; i <= N; ++i)
		{
			for (int j = 0; j <= S; ++j)
			{
				int wi = items.get(i-1).size;
				int vi = items.get(i-1).value;
				if (wi > j)
				{
					m[i][j] = m[i-1][j];
				}
				else
				{
					m[i][j] = Math.max(m[i-1][j], m[i-1][j-wi] + vi);
				}
			}
		}
		return m[N][S];
	}
	
	/**
	 * Data struct for an item in the Knapsack problem.
	 */
	private final class Item
	{
		public Integer size;
		public Integer value;
	}
}
