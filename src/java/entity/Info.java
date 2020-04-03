/*
 * Info.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package entity;

/**
 * Info class.<br>
 * 
 * <pre>
 * Class thực hiện thao tác với Info
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・get id
 * ・get name
 * ・get content
 * 
 * ・Set id
 * ・Set name
 * ・Set content
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class Info {
    /** Store id */
    private int id;
    /** Store name */
    private String name;
    /** Store content */
    private String content;
   
    /**
     * Constructor.<br>
     * 
     * @param id
     * @param name
     * @param content
     * 
     */
    public Info(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

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
     * Get name.<br>
     * 
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.<br>
     * 
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
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
    
}
