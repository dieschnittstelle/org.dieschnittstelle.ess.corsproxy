package org.dieschnittstelle.ess.corsproxy;

public class CORSResponse {

    private int status;
    private String responseText;

    public CORSResponse() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public String toString() {
        return "CORSResponse{" +
                "status=" + status +
                ", responseText='" + responseText + '\'' +
                '}';
    }
}
