package service;

import javax.xml.ws.WebFault;

@WebFault(name="dataException")
public class dataException extends Exception {

    private faultinfo f;

    public dataException(String s, faultinfo f) {
        super(s);
        this.f = f;
    }

    public faultinfo getF() {
        return f;
    }

    public void setF(faultinfo f) {
        this.f = f;
    }
}
