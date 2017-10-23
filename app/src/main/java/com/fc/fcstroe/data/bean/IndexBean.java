package com.fc.fcstroe.data.bean;

import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class IndexBean {
    private List<Banner> banners;// TODO: 2017-08-22  
    private List<AppInfo> recommendApps;
    private List<AppInfo> recommendGames;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        banners = banners;
    }

    public List<AppInfo> getRecommendApps() {
        return recommendApps;
    }

    public void setRecommendApps(List<AppInfo> recommendApps) {
        this.recommendApps = recommendApps;
    }

    public List<AppInfo> getRecommendGames() {
        return recommendGames;
    }

    public void setRecommendGames(List<AppInfo> recommendGames) {
        this.recommendGames = recommendGames;
    }
}
