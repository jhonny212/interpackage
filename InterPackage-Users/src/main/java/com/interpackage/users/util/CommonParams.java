package com.interpackage.users.util;


import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestParam;

public class CommonParams {

    private int page=0;
    private int max=10;
    private boolean pagination=true;



    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }
}
