/*
 * News.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package entity;

/**
 * News class.<br>
 * 
 * <pre>
 * Class thực hiện thao tác với News
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・get id
 * ・get title
 * ・get imgLink
 * ・get content
 * ・get fullContent
 * 
 * ・Set id
 * ・Set title
 * ・Set imgLink
 * ・Set content
 * ・Set fullContent
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class News {
    /** Store id */
    private int id;
    /** Store title */
    private String title;
    /** Store imgLink */
    private String imgLink;
    /** Store content */
    private String content;
    /** Store fullContent */
    private String fullContent;

    
    /**
     * Constructor.<br>
     * 
     * @param id
     * @param title
     * @param imgLink
     * @param content
     * @param fullContent
     */
    public News(int id, String title, String imgLink, String content, String fullContent) {
        this.id = id;
        this.title = title;
        this.imgLink = imgLink;
        this.content = content;
        this.fullContent = fullContent;
    }
    
    /**
     * Constructor.<br>
     */
    public News(){}

    /**
     * Get id.<br>
     * 
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.<br>
     * 
     * @param id the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get title.<br>
     * 
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.<br>
     * 
     * @param title the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get imgLink.<br>
     * 
     * @return the imgLink.
     */
    public String getImgLink() {
        return imgLink;
    }

    /**
     * Set imgLink.<br>
     * 
     * @param imgLink the imgLink.
     */
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    /**
     * Get content.<br>
     * 
     * @return the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Set content.<br>
     * 
     * @param content the content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get fullContent.<br>
     * 
     * @return the fullContent.
     */
    public String getFullContent() {
        return fullContent;
    }

    /**
     * Set fullContent.<br>
     * 
     * @param fullContent the fullContent.
     */
    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }
    
}
