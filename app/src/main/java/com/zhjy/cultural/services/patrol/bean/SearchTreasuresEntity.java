package com.zhjy.cultural.services.patrol.bean;

import java.util.List;

public class SearchTreasuresEntity {
    private int total;
    private List<Datas> datas;
    private int pageSize;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }

    public List<Datas> getDatas() {
        return datas;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public class Datas {

        private int id;
        private String title;
        private int wwType;
        private String wwTypeStr;
        private String address;
        private int fxType;
        private String fxTypeStr;
        private String opentime;
        private String content;
        private int wwStatus;
        private String wwStatusStr;
        private String point;
        private String picturePath;
        private String managementUnit;
        private boolean deleted;
        private int totalRecord;
        private int totalStatus0;
        private int totalStatus1;
        private int totalStatus2;
        private int totalStatus3;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setWwType(int wwType) {
            this.wwType = wwType;
        }

        public int getWwType() {
            return wwType;
        }

        public void setWwTypeStr(String wwTypeStr) {
            this.wwTypeStr = wwTypeStr;
        }

        public String getWwTypeStr() {
            return wwTypeStr;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setFxType(int fxType) {
            this.fxType = fxType;
        }

        public int getFxType() {
            return fxType;
        }

        public void setFxTypeStr(String fxTypeStr) {
            this.fxTypeStr = fxTypeStr;
        }

        public String getFxTypeStr() {
            return fxTypeStr;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setWwStatus(int wwStatus) {
            this.wwStatus = wwStatus;
        }

        public int getWwStatus() {
            return wwStatus;
        }

        public void setWwStatusStr(String wwStatusStr) {
            this.wwStatusStr = wwStatusStr;
        }

        public String getWwStatusStr() {
            return wwStatusStr;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPoint() {
            return point;
        }

        public void setPicturePath(String picturePath) {
            this.picturePath = picturePath;
        }

        public String getPicturePath() {
            return picturePath;
        }

        public void setManagementUnit(String managementUnit) {
            this.managementUnit = managementUnit;
        }

        public String getManagementUnit() {
            return managementUnit;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public boolean getDeleted() {
            return deleted;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalStatus0(int totalStatus0) {
            this.totalStatus0 = totalStatus0;
        }

        public int getTotalStatus0() {
            return totalStatus0;
        }

        public void setTotalStatus1(int totalStatus1) {
            this.totalStatus1 = totalStatus1;
        }

        public int getTotalStatus1() {
            return totalStatus1;
        }

        public void setTotalStatus2(int totalStatus2) {
            this.totalStatus2 = totalStatus2;
        }

        public int getTotalStatus2() {
            return totalStatus2;
        }

        public void setTotalStatus3(int totalStatus3) {
            this.totalStatus3 = totalStatus3;
        }

        public int getTotalStatus3() {
            return totalStatus3;
        }

    }

}
