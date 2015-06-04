package com.cntysoft.micromart.model;

/**
 * Created by Administrator on 2015/5/26.
 */
public class VersionUpdate {

    private String version;
    private String updateUrl;

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
