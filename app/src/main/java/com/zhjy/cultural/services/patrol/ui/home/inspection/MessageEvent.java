package com.zhjy.cultural.services.patrol.ui.home.inspection;

public class MessageEvent {
    private int num;
    private int handlenum;
    private int completennum;
    private int type;

    //巡检记录
    public MessageEvent(int num, int type) {
        this.num = num;
        this.type = type;
    }

    //异常巡检记录
    public MessageEvent(int num, int handlenum, int completennum, int type) {
        this.num = num;
        this.handlenum = handlenum;
        this.completennum = completennum;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHandlenum() {
        return handlenum;
    }

    public void setHandlenum(int handlenum) {
        this.handlenum = handlenum;
    }

    public int getCompletennum() {
        return completennum;
    }

    public void setCompletennum(int completennum) {
        this.completennum = completennum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
