package com.zhjy.cultural.services.patrol.network;

import java.io.Serializable;
import java.util.List;

/**
 * 通知详情
 */
public class NoticeDetails implements Serializable {
    private List<Documents> documents;
    private Notice notice;

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public class Documents {

        private int id;
        private String imgPath;
        private String fileName;
        private int relationId;
        private int type;

        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }

        public void setRelationId(int relationId) {
            this.relationId = relationId;
        }

        public int getRelationId() {
            return relationId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
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
