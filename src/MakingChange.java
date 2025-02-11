import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author SIERRA SHAW
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */

    // Tabulation Approach!
    public static long countWays(int target, int[] coins) {
        Arrays.sort(coins);
        int coinLen = coins.length;
        long[][] combinations = new long[coinLen][target + 1];

        // Fill in the one's because we know if the target is zero, to return 1
        for (int i = 0; i < coinLen; i++) {
            combinations[i][0] = 1;
        }

        // Loop through every value in the table and fill it in as we go
        for (int i = 0; i < coinLen; i++) {
            for (int j = 1; j <= target; j++) {
                long sum = 0;

                // As long as the value we're looking for is in bounds of the array, look for it

                // Include case
                if (j - coins[i] >= 0) {
                    sum += combinations[i][j - coins[i]];
                }

                // Exclude case
                if (i - 1 >= 0) {
                    sum += combinations[i - 1][j];
                }

                // Save the value in the array so we can reuse
                combinations[i][j] = sum;
            }
        }

        // Return the bottom rightmost index
        return combinations[coinLen - 1][target];
    }
}

 /*
    // Memoization Approach!!
    public static long countWays(int target, int[] coins) {
        Arrays.sort(coins);
        int coinLen = coins.length;
        long[][] combinations = new long[coinLen][target + 1];

        // Fill in the ones because we know if the target is zero, to return 1
        for (int i = 0; i < coinLen; i++) {
            combinations[i][0] = 1;
        }

        // Call the recursive function on the bottom rightmost index of the array
        long currentCounts = findCount(target, coinLen - 1, coins, combinations);

        return currentCounts;
    }

    public static long findCount(int target, int currentIdx, int[] coins, long[][] currentCounts) {
        // Base cases
        if (target < 0) {
            return 0;
        }
        else if (target == 0) {
            return 1;
        }
        else if (currentIdx >= coins.length) {
            return 0;
        }

        long count = 0;

        // Include the current coin, as long as it's in bounds of the array
        if (target - coins[currentIdx] >= 0 && currentCounts[currentIdx][target - coins[currentIdx]] != 0) {
            count += currentCounts[currentIdx][target - coins[currentIdx]];
        }
        // If it's not in the array already, add it to the recursive call stack and save that value in the array
        // once we find it
        else if (target - coins[currentIdx] >= 0){
            long toAdd = findCount(target - coins[currentIdx], currentIdx, coins, currentCounts);
            currentCounts[currentIdx][target - coins[currentIdx]] = toAdd;
            count += toAdd;
        }

        // Exclude the current coin
        if (currentIdx - 1 >= 0 && currentCounts[currentIdx - 1][target] != 0) {
            count += currentCounts[currentIdx - 1][target];
        }
        // Add to call stack if it's not in the array & save the value once we find it
        else if (currentIdx - 1 >= 0){
            long toAdd = findCount(target, currentIdx - 1, coins, currentCounts);
            currentCounts[currentIdx - 1][target] = toAdd;
            count += toAdd;
        }

        return count;
    }
}
*/