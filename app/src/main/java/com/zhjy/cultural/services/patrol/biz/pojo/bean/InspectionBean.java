package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import java.io.Serializable;

public class InspectionBean implements Serializable {

    private int recordId;

    private boolean removal;

    private boolean destroy;

    private boolean fireComplete;

    private String insideExplain;

    private boolean manage;

    private boolean construction;

    private String outsideExplain;

    private int wwId;

    private int status;

    private String statusDescription;

    private int flag;

    private String image_ids;

    private String point_ids;


    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public boolean isRemoval() {
        return removal;
    }

    public void setRemoval(boolean removal) {
        this.removal = removal;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public boolean isFireComplete() {
        return fireComplete;
    }

    public void setFireComplete(boolean fireComplete) {
        this.fireComplete = fireComplete;
    }

    public String getInsideExplain() {
        return insideExplain;
    }

    public void setInsideExplain(String insideExplain) {
        this.insideExplain = insideExplain;
    }

    public boolean isManage() {
        return manage;
    }

    public void setManage(boolean manage) {
        this.manage = manage;
    }

    public boolean isConstruction() {
        return construction;
    }

    public void setConstruction(boolean construction) {
        this.construction = construction;
    }

    public String getOutsideExplain() {
        return outsideExplain;
    }

    public void setOutsideExplain(String outsideExplain) {
        this.outsideExplain = outsideExplain;
    }

    public int getWwId() {
        return wwId;
    }

    public void setWwId(int wwId) {
        this.wwId = wwId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String image_ids) {
        this.image_ids = image_ids;
    }

    public String getPoint_ids() {
        return point_ids;
    }

    public void setPoint_ids(String point_ids) {
        this.point_ids = point_ids;
    }
}
