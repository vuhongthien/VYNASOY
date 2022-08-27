package vn.fis.logfile.vinasoy.config.exception;

public class ResponseMessage {
    private String mess;

    public ResponseMessage(String mess) {
        this.mess = mess;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
