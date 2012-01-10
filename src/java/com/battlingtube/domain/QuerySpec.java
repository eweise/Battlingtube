package com.battlingtube.domain;

public class QuerySpec {
    private int page;
    private int pageSize;
    private String sortField;
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    boolean sortAscending = true;

    public QuerySpec(int page, int pageSize, String sortField, boolean asc) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortAscending = asc;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public int getfirstResult() {
        return (page - 1) * pageSize;
    }

    public boolean getSortAscending() {
        return sortAscending;
    }
}
