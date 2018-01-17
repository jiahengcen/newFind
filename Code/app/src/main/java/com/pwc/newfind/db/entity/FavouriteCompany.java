package com.pwc.newfind.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lhuang126 on 1/17/2018.
 */
@Entity
public class FavouriteCompany {
    @Id(autoincrement = true)
    private Long id;
    private String companyCode;
    private String companyName;
    @Generated(hash = 1794349782)
    public FavouriteCompany(Long id, String companyCode, String companyName) {
        this.id = id;
        this.companyCode = companyCode;
        this.companyName = companyName;
    }
    @Generated(hash = 788396067)
    public FavouriteCompany() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
