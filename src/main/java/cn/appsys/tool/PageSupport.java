package cn.appsys.tool;

/**
 * @program: APP
 * @description: 分页
 * @author: Xiaoxu
 * @create: 2020-11-29 22:51
 **/
public class PageSupport {
    private int currentPageNo = 1;
    private int totalCount = 0;
    private int pageSize = 0;
    private int totalPageCount = 1;

    public PageSupport() {
    }

    public int getCurrentPageNo() {
        return this.currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo > 0) {
            this.currentPageNo = currentPageNo;
        }

    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            this.setTotalPageCountByRs();
        }

    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }

    }

    public int getTotalPageCount() {
        return this.totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalPageCountByRs() {
        if (this.totalCount % this.pageSize == 0) {
            this.totalPageCount = this.totalCount / this.pageSize;
        } else if (this.totalCount % this.pageSize > 0) {
            this.totalPageCount = this.totalCount / this.pageSize + 1;
        } else {
            this.totalPageCount = 0;
        }

    }
}