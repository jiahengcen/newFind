package com.pwc.newfind.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lhuang126 on 1/30/2018.
 */
@Entity
public class FindingTitle {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    @Generated(hash = 1956245870)
    public FindingTitle(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    @Generated(hash = 758350674)
    public FindingTitle() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
