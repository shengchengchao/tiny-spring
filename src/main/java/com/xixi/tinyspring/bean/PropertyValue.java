package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class PropertyValue {

    private String name;

    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public PropertyValue() {
    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
