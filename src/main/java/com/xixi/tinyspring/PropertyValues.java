package com.xixi.tinyspring;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class PropertyValues {

    List<PropertyValue> propertyValueList =new ArrayList<PropertyValue>();

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    public void  addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public PropertyValues() {
    }
}
