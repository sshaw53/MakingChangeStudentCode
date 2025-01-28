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
        long count = 0;
        int coinLen = coins.length;

        for (int i = 0; i < coinLen; i++){
            count += findCount(coins, currentCounts, target, i, coins[i], coinLen);
        }

        return count;
    }

    public static long findCount(int[] coins, long[] currentCounts, int target, int currentIdx, int total, int coinLen) {
        if (total > target) {
            return 0;
        }
        else if (total == target) {
            return currentCounts[target];
        }

        long count = 0;

        for (int i = currentIdx; i < coinLen; i++){
            // find some way to take advantage of the dynamic programming
            count += findCount(coins, currentCounts, target, i, (total + coins[i]), coinLen);
            currentCounts[total + coins[i]] += count;
        }
    }
}
