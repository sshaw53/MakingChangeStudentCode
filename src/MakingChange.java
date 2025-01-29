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
    public static long countWays(int target, int[] coins) {
        Arrays.sort(coins);
        long[] currentCounts = new long[target + 1];
        int coinLen = coins.length;
        int countsLen = currentCounts.length;

        // How can we do this within our recursive function s.t. it saves current counts as we're finding the values
        for (int i = 1; i < countsLen; i++){
            currentCounts[i] = findCount(coins, currentCounts, target, 0, 0, coinLen);
        }

        return currentCounts[target];
    }

    public static long findCount(int[] coins, long[] currentCounts, int target, int currentIdx, int total, int coinLen) {
        if (total > target) {
            return 0;
        }
        else if (total == target) {
            return 1;
        }

        long count = 0;

        for (int i = currentIdx; i < coinLen; i++){
            // find some way to take advantage of the dynamic programming****

            // If we've already seen a potential combination that we've found, skip recursing
            // and set count to that value
            count += findCount(coins, currentCounts, target, i, (total + coins[i]), coinLen);
        }

        return count;
    }
}
