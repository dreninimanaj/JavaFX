package com.example.knk_gr23.Models.Filter;

abstract class Filter {
    public abstract String buildQuery();
}

public class PaymentFilter extends Filter {
    private Integer month;
    private Double minRemainingBalance;
    private Double maxRemainingBalance;
    private int page;
    private int size;

    public PaymentFilter(Integer month, Double minRemainingBalance, Double maxRemainingBalance, int page, int size) {
        this.month = month;
        this.minRemainingBalance = minRemainingBalance;
        this.maxRemainingBalance = maxRemainingBalance;
        this.page = page;
        this.size = size;
    }

    @Override
    public String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM payments WHERE 1=1");

        if (this.month != null) {
            query.append(" AND month = ").append(this.month);
        }

        if (this.minRemainingBalance != null) {
            query.append(" AND balance >= ").append(this.minRemainingBalance);
        }

        if (this.page >= 0 && this.size > 0) {
            int offset = this.page * this.size;
            int limit = this.size;
            query.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        }

        return query.toString();
    }

    // Getters and setters
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getMinRemainingBalance() {
        return minRemainingBalance;
    }

    public void setMinRemainingBalance(Double minRemainingBalance) {
        this.minRemainingBalance = minRemainingBalance;
    }

    public Double getMaxRemainingBalance() {
        return maxRemainingBalance;
    }

    public void setMaxRemainingBalance(Double maxRemainingBalance) {
        this.maxRemainingBalance = maxRemainingBalance;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
