package com.battlingtube.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class QueryResult<T> {

    private List<T> results;
    private int totalCount;
    private int resultsPerPage;
    private int currentPage;

    public QueryResult(List<T> results, int totalCount, int resultsPerPage, int currentPage) {
        this.results = results;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.resultsPerPage = resultsPerPage;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumberOfPages() {
        if (totalCount == 0 || resultsPerPage == 0) {
            return 0;
        } else {
            float nonRound = (totalCount / (resultsPerPage * 1F));
            BigDecimal big = new BigDecimal(nonRound).setScale(0, RoundingMode.UP);
            return big.intValue();
        }
    }

    public int getStartPosition() {
        return lowerbound() + 1;
    }

    public int getEndPosition() {
        return lowerbound() + results.size();
    }

    public boolean isLastPage() {
        return getCurrentPage() == getNumberOfPages();   
    }

    private int lowerbound() {
        return (currentPage == 1 ? 0 : (currentPage - 1) * resultsPerPage);
    }


}
