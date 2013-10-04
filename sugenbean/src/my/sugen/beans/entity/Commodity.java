/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.sugen.beans.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import my.sugen.beans.SugenHelper;

/**
 *
 * @author cafestop
 */
@Entity
public class Commodity implements Serializable {

    //宝贝唯一编号
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //宝贝简单描述
    private String description;
    //搭配建议
    private String suggestion;
    private char suggestionType; //1. 文字；2. 图片
    //经销商
    private String dealer;
    //经销商产品编号
    private String dealerId;
    //宝贝标题
    private String title;
    private float price;
    //宝贝原料
    private String fabrics;
    //模特图地址
    private String modelImgsStr;

    //实物图地址
    private String commodityImgsStr;

    private String colorStr;

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getModelImgsStr() {
        return modelImgsStr;
    }

    public void setModelImgsStr(String modelImgsStr) {
        this.modelImgsStr = modelImgsStr;
    }

    public String getCommodityImgsStr() {
        return commodityImgsStr;
    }

    public void setCommodityImgsStr(String commodityImgsStr) {
        this.commodityImgsStr = commodityImgsStr;
    }

    public String getColorStr() {
        return colorStr;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    //指数
    private char resiliencyIdx; //弹性指数
    private char thicknessIdx; //厚薄指数
    private char styleIdx; //版型指数
    private char lengthIdx; //长度指数

    //关联销售
    //搭配购买
    @OneToMany
    private Collection<Commodity> related;
    //推荐宝贝
    @OneToMany
    private Collection<Commodity> recommanded;

    //尺寸信息
    @OneToMany(cascade = ALL)
    private Collection<Size> sizes;

    public Commodity() {
    }

    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public char getSuggestionType() {
        return suggestionType;
    }

    public void setSuggestionType(char suggestionType) {
        this.suggestionType = suggestionType;
    }

    public String getDeaer() {
        return dealer;
    }

    public void setDeaer(String deaer) {
        this.dealer = deaer;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFabrics() {
        return fabrics;
    }

    public void setFabrics(String fabrics) {
        this.fabrics = fabrics;
    }

    public ArrayList<String> getModelImgs() {
        return SugenHelper.string2Array(modelImgsStr);
    }

    public void setModelImgs(ArrayList<String> modelImgs) {
        this.modelImgsStr = SugenHelper.array2String(modelImgs);
    }

    public ArrayList<String> getCommodityImgs() {
        return SugenHelper.string2Array(commodityImgsStr);
    }

    public void setCommodityImgs(ArrayList<String> commodityImgs) {
        this.commodityImgsStr = SugenHelper.array2String(commodityImgs);;
    }

    public ArrayList<String> getColors() {
        return SugenHelper.string2Array(colorStr);
    }

    public void setColors(ArrayList<String> colors) {
        this.colorStr = SugenHelper.array2String(colors);
    }

    public char getResiliencyIdx() {
        return resiliencyIdx;
    }

    public void setResiliencyIdx(char resiliencyIdx) {
        this.resiliencyIdx = resiliencyIdx;
    }

    public char getThicknessIdx() {
        return thicknessIdx;
    }

    public void setThicknessIdx(char thicknessIdx) {
        this.thicknessIdx = thicknessIdx;
    }

    public char getStyleIdx() {
        return styleIdx;
    }

    public void setStyleIdx(char styleIdx) {
        this.styleIdx = styleIdx;
    }

    public char getLengthIdx() {
        return lengthIdx;
    }

    public void setLengthIdx(char lengthIdx) {
        this.lengthIdx = lengthIdx;
    }

    public Collection<Commodity> getRelated() {
        return related;
    }

    public void setRelated(Collection<Commodity> related) {
        this.related = related;
    }

    public Collection<Commodity> getRecommanded() {
        return recommanded;
    }

    public void setRecommanded(Collection<Commodity> recommanded) {
        this.recommanded = recommanded;
    }

    public Collection<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Collection<Size> sizes) {
        this.sizes = sizes;
    }

}
