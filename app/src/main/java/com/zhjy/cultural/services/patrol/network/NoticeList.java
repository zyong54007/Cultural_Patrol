package com.zhjy.cultural.services.patrol.network;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NoticeList implements Serializable {
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

        private Notice notice;
        private int userId;
        private boolean read;
        private String user;

        public void setNotice(Notice notice) {
            this.notice = notice;
        }

        public Notice getNotice() {
            return notice;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public boolean getRead() {
            return read;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }

    }

    public class Notice {

        private int id;
        private String theme;
        private String content;
        private String createTime;
        private int totalUserCount;
        private int readUserCount;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getTheme() {
            return theme;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setTotalUserCount(int totalUserCount) {
            this.totalUserCount = totalUserCount;
        }

        public int getTotalUserCount() {
            return totalUserCount;
        }

        public void setReadUserCount(int readUserCount) {
            this.readUserCount = readUserCount;
        }

        public int getReadUserCount() {
            return readUserCount;
        }

    }

}