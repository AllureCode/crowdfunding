package com.crowdfunding.util;

import com.crowdfunding.bean.Page;

/**
 *封装返回数据
 */
public class AjaxResult {
    private boolean success;
    private String message;
    private Page page;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
