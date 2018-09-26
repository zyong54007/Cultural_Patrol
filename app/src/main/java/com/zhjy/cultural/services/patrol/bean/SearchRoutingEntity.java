package com.zhjy.cultural.services.patrol.bean;

import java.util.List;

public class SearchRoutingEntity {
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
        private boolean removal;
        private boolean destroy;
        private boolean fireComplete;
        private String insideExplain;
        private boolean manage;
        private boolean construction;
        private String outsideExplain;
        private Ww ww;
        private User user;
        private int status;
        private String statusDescription;
        private int flag;
        private int flow;
        private String flowPath;
        private String writeTime;
        private String createTime;
        private String updateTime;
        private int pointNum;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setRemoval(boolean removal) {
            this.removal = removal;
        }

        public boolean getRemoval() {
            return removal;
        }

        public void setDestroy(boolean destroy) {
            this.destroy = destroy;
        }

        public boolean getDestroy() {
            return destroy;
        }

        public void setFireComplete(boolean fireComplete) {
            this.fireComplete = fireComplete;
        }

        public boolean getFireComplete() {
            return fireComplete;
        }

        public void setInsideExplain(String insideExplain) {
            this.insideExplain = insideExplain;
        }

        public String getInsideExplain() {
            return insideExplain;
        }

        public void setManage(boolean manage) {
            this.manage = manage;
        }

        public boolean getManage() {
            return manage;
        }

        public void setConstruction(boolean construction) {
            this.construction = construction;
        }

        public boolean getConstruction() {
            return construction;
        }

        public void setOutsideExplain(String outsideExplain) {
            this.outsideExplain = outsideExplain;
        }

        public String getOutsideExplain() {
            return outsideExplain;
        }

        public void setWw(Ww ww) {
            this.ww = ww;
        }

        public Ww getWw() {
            return ww;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlow(int flow) {
            this.flow = flow;
        }

        public int getFlow() {
            return flow;
        }

        public void setFlowPath(String flowPath) {
            this.flowPath = flowPath;
        }

        public String getFlowPath() {
            return flowPath;
        }

        public void setWriteTime(String writeTime) {
            this.writeTime = writeTime;
        }

        public String getWriteTime() {
            return writeTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setPointNum(int pointNum) {
            this.pointNum = pointNum;
        }

        public int getPointNum() {
            return pointNum;
        }

    }

    public class Ww {

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
        private String createTime;
        private String updateTime;

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

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

    }

    public class User {

        private int id;
        private String username;
        private String truename;
        private String mobile;
        private int status;
        private Organization organization;
        private int type;
        private boolean deleted;
        private String createTime;
        private String updateTime;
        private int totalWw;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getTruename() {
            return truename;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return mobile;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setOrganization(Organization organization) {
            this.organization = organization;
        }

        public Organization getOrganization() {
            return organization;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public boolean getDeleted() {
            return deleted;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setTotalWw(int totalWw) {
            this.totalWw = totalWw;
        }

        public int getTotalWw() {
            return totalWw;
        }

    }

    public class Organization {

        private int id;
        private int pId;
        private String name;
        private String path;
        private String iconOpen;
        private String iconClose;
        private boolean isParent;
        private int userCount;
        private int wwCount;
        private int recordCount;
        private int finishRecordCount;
        private String percent;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public int getPId() {
            return pId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public void setIconOpen(String iconOpen) {
            this.iconOpen = iconOpen;
        }

        public String getIconOpen() {
            return iconOpen;
        }

        public void setIconClose(String iconClose) {
            this.iconClose = iconClose;
        }

        public String getIconClose() {
            return iconClose;
        }

        public void setIsParent(boolean isParent) {
            this.isParent = isParent;
        }

        public boolean getIsParent() {
            return isParent;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }

        public int getUserCount() {
            return userCount;
        }

        public void setWwCount(int wwCount) {
            this.wwCount = wwCount;
        }

        public int getWwCount() {
            return wwCount;
        }

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public int getRecordCount() {
            return recordCount;
        }

        public void setFinishRecordCount(int finishRecordCount) {
            this.finishRecordCount = finishRecordCount;
        }

        public int getFinishRecordCount() {
            return finishRecordCount;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getPercent() {
            return percent;
        }

    }
}
