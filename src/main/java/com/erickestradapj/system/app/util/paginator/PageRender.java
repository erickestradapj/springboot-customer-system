package com.erickestradapj.system.app.util.paginator;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageRender<T> {

    private String url;
    private Page<T> page;

    private int totalPages;
    private int numberOfItemsPerPage;
    private int actualPage;

    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<>();

        numberOfItemsPerPage = page.getSize();
        totalPages = page.getTotalPages();
        actualPage = page.getNumber() + 1;

        int start, end;

        if (totalPages <= numberOfItemsPerPage) {
            start = 1;
            end = totalPages;
        } else {
            if (actualPage <= numberOfItemsPerPage / 2) {
                start = 1;
                end = numberOfItemsPerPage;
            } else if (actualPage >= totalPages - numberOfItemsPerPage / 2) {
                start = totalPages - numberOfItemsPerPage + 1;
                end = numberOfItemsPerPage;
            } else {
                start = actualPage - numberOfItemsPerPage / 2;
                end = numberOfItemsPerPage;
            }
        }

        for (int i = 0; i < end; i++) {
            pages.add(new PageItem(end + i, actualPage == start + i));
        }
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
