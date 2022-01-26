package com.xf.domain;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission  extends BaseDomain {


    private String name;

    private String url;

    private String descs;

    private String sn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

//    public Long getMenuId() {
//        return menuId;
//    }
//    public void setMenuId(Long menuId) {
//        this.menuId = menuId;
//    }

}
