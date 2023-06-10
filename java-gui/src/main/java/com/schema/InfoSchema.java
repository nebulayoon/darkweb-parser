package com.schema;

import org.bson.types.ObjectId;

public class InfoSchema {
    private String category = null;
    private String siteName = null;
    private String darkWebUrl = null;
    private String lang = null;
    private int state = 999;
    private String engine = null;
    private String searchAt = null;

    public InfoSchema() {};

    public InfoSchema(String category, String siteName, String darkWebUrl, String lang, int state, String engine){
        this.category = category;
        this.siteName = siteName;
        this.darkWebUrl = darkWebUrl;
        this.lang = lang;
        this.state = state;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "InfoSchema [category=" + category + ", url=" + siteName + ", lang=" + lang + ", state=" + state + ", engine=" + engine + "searchAt=" + searchAt + "]";
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSiteName() { return this.siteName; }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDarkWebUrl() { return this.darkWebUrl; }

    public void setDarkWebUrl(String darkWebUrl) { this.darkWebUrl = darkWebUrl; }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEngine() {
        return this.engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getSearchAt() {
        return this.searchAt;
    }

    public void setSearchAt(String searchAt) {
        this.searchAt = searchAt;
    }
}
