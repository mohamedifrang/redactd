package com.redactd.contentms.response;

import com.redactd.contentms.bean.PlatformSummary;
import com.redactd.contentms.bean.Content;

public class ContentResponse {
    private Content content;
    private PlatformSummary platform;
    public ContentResponse() {
    }
    public ContentResponse(Content content, PlatformSummary platform) {
        this.content = content;
        this.platform = platform;
    }
    public Content getContent() {
        return content;
    }
    public void setContent(Content content) {
        this.content = content;
    }
    public PlatformSummary getPlatformSummary() {
        return platform;
    }
    public void setPlatformSummary(PlatformSummary platform) {
        this.platform = platform;
    }
}






