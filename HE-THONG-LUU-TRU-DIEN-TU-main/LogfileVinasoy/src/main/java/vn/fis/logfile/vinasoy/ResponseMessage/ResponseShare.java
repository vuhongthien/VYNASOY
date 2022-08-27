package vn.fis.logfile.vinasoy.ResponseMessage;

public class ResponseShare {
    private String iduser;
    private String idattachment;
    private String url;

    public ResponseShare(String iduser, String idattachment, String url) {
        this.iduser = iduser;
        this.idattachment = idattachment;
        this.url = url;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdattachment() {
        return idattachment;
    }

    public void setIdattachment(String idattachment) {
        this.idattachment = idattachment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
