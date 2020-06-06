package service;

public class faultinfo {
    private String errMsg;

    public faultinfo(String s){
        this.errMsg=s;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
