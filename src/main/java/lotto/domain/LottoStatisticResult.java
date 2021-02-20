package lotto.domain;

import java.util.Map;

public class LottoStatisticResult {

    private final Map<Rank, Long> rankCount;

    private static final int LOTTO_PAY = 1000;
    private static final long NO_COUNT = 0L;

    public LottoStatisticResult(Map<Rank, Long> rankCount) {
        this.rankCount = rankCount;
    }

    public Long getOrNoCount(Rank rank) {
        return rankCount.getOrDefault(rank, NO_COUNT);
    }

    public double calculateIncomeRate() {
        long rewardSum = rankCount.keySet()
                                  .stream()
                                  .mapToLong(rank -> rankCount.get(rank) * rank.getReward())
                                  .sum();

        long payCount = rankCount.values()
                                 .stream()
                                 .mapToLong(t -> t)
                                 .sum();

        return (double) rewardSum / (payCount * LOTTO_PAY);
    }
}
