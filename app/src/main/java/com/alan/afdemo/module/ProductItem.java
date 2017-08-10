package com.alan.afdemo.module;

/**
 * Created by yinqingjiang on 8/10/17.
 */

public class ProductItem {
    private String backgroundImage;
    private String topDescription;
    private String title;
    private String promoMessage;
    private String bottomDescription;
    private String selectButton_one;
    private String getSelectButton_two;
    private String button_one_link;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getTopDescription() {
        return topDescription;
    }

    public void setTopDescription(String topDescription) {
        this.topDescription = topDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        this.bottomDescription = bottomDescription;
    }

    public String getSelectButton_one() {
        return selectButton_one;
    }

    public void setSelectButton_one(String selectButton_one) {
        this.selectButton_one = selectButton_one;
    }

    public String getGetSelectButton_two() {
        return getSelectButton_two;
    }

    public void setGetSelectButton_two(String getSelectButton_two) {
        this.getSelectButton_two = getSelectButton_two;
    }

    public String getButton_one_link() {
        return button_one_link;
    }

    public void setButton_one_link(String button_one_link) {
        this.button_one_link = button_one_link;
    }

    public String getButton_two_link() {
        return button_two_link;
    }

    public void setButton_two_link(String button_two_link) {
        this.button_two_link = button_two_link;
    }

    private String button_two_link;
}
