package com.xf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu  extends BaseDomain {

    private String name;

    private String url;

    private String icon;

    /**
     * 通过多方(子菜单)找一方(父菜单)
     * JsonIgnore:生成json到前台的时间，子菜单不需要再去找父菜单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;

    /**
     * 通过一方(父菜单)找多方(子菜单)
     *     1.父菜单一定要拿到对应的子菜单 -> 必需写children
     *     2.用户是拥有这个子菜单权限的
     *          -> 不能配置OneToMany,否则会导致权限失效
     *          -> 这个值应该是由我们自己控制
     *  Transient：临时属性(JPA不管这个属性，和数据库没有关系)
     */
    @Transient
    private List<Menu> children = new ArrayList<>();

    public String getName() {
        return name;
    }
    public String getText() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
