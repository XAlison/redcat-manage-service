package com.ywsoftware.oa.modules.sys.domain.entity;

public class SysUploadFile {
    private String id;
    private String path;
    private String name;
    private java.util.Date createdDate;
    /**
     * 文件类型：批文件/单文件
     */
    private String type;
    /**
     * 文件组ID
     */
    private String fileGroupId;

    public String getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }
}
