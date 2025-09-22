public class SelectMetrics {
    private long comparisons;
    private long swaps;
    private long maxDepth;
    private long currentDepth;

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getMaxDepth() { return maxDepth; }

    public void reset() {
        comparisons = swaps = maxDepth = currentDepth = 0;
    }
}
