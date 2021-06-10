package com.xixi.tinyspring.bean.xml;

import cn.hutool.core.util.StrUtil;
import com.xixi.tinyspring.bean.AbstractBeanDefinitionReader;
import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.BeanReference;
import com.xixi.tinyspring.bean.PropertyValue;
import com.xixi.tinyspring.bean.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {



    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 加载资源
     *
     * @param resource
     * @throws Exception
     */
    @Override
    public void loadBeanDefinition(String resource) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(resource).getInputStream();
        loadBeanDefinitions(inputStream);
    }

    protected void loadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);
        registerBeanDefinitions(doc);
        inputStream.close();
    }


    protected void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if(item instanceof Element){
                Element ele = (Element)item;
                processBeanDefinition(ele);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        String initMethod = ele.getAttribute("init-method");
        String destroyMethod = ele.getAttribute("destroy-method");
        String scope = ele.getAttribute("scope");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClass(className);
        beanDefinition.setScope(StrUtil.isNotBlank(scope)? scope: BeanDefinition.SINGLETON);
        beanDefinition.setInitMethodName(initMethod);
        beanDefinition.setDestroyMethod(destroyMethod);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList property = ele.getElementsByTagName("property");
        for (int i = 0;i<property.getLength();i++){
            Node node = property.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if(value!=null && value.length()>0){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }
        }
    }

}
