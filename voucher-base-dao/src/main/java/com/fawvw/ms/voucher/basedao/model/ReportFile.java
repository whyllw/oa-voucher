package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class ReportFile {
    private Integer id;

    private String reportName;

    private String extention;

    private String fkOperationUserRefId;

    private Integer fileRecordNum;

    private String fileSize;

    private String url;

    private String queryParam;

    private String exportProgress;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;

    private Byte active;

    public ReportFile(Integer id, String reportName, String extention, String fkOperationUserRefId, Integer fileRecordNum, String fileSize, String url, String queryParam, String exportProgress, Date createTime, Date updateTime, Byte deleted, Byte active) {
        this.id = id;
        this.reportName = reportName;
        this.extention = extention;
        this.fkOperationUserRefId = fkOperationUserRefId;
        this.fileRecordNum = fileRecordNum;
        this.fileSize = fileSize;
        this.url = url;
        this.queryParam = queryParam;
        this.exportProgress = exportProgress;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.active = active;
    }

    public ReportFile() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention == null ? null : extention.trim();
    }

    public String getFkOperationUserRefId() {
        return fkOperationUserRefId;
    }

    public void setFkOperationUserRefId(String fkOperationUserRefId) {
        this.fkOperationUserRefId = fkOperationUserRefId == null ? null : fkOperationUserRefId.trim();
    }

    public Integer getFileRecordNum() {
        return fileRecordNum;
    }

    public void setFileRecordNum(Integer fileRecordNum) {
        this.fileRecordNum = fileRecordNum;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam == null ? null : queryParam.trim();
    }

    public String getExportProgress() {
        return exportProgress;
    }

    public void setExportProgress(String exportProgress) {
        this.exportProgress = exportProgress == null ? null : exportProgress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
}