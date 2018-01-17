package com.pwc.newfind.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lhuang126 on 1/17/2018.
 */
@Entity
public class FavouriteIndustry {
    @Id(autoincrement = true)
    private Long id;
    private String industry;
    @Generated(hash = 777142378)
    public FavouriteIndustry(Long id, String industry) {
        this.id = id;
        this.industry = industry;
    }
    @Generated(hash = 742866524)
    public FavouriteIndustry() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIndustry() {
        return this.industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
