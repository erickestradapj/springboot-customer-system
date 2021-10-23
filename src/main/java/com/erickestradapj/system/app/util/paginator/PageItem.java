package com.erickestradapj.system.app.util.paginator;

import lombok.Data;

@Data
public class PageItem {

    private int number;
    private boolean actual;

    public PageItem(int number, boolean actual) {
        this.number = number;
        this.actual = actual;
    }
}
