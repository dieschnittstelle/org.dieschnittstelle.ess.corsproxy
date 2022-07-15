package org.dieschnittstelle.ess.corsproxy;

public class CORSRequest {

    public static enum CORSHttpMethod {GET, POST, PUT, DELETE, PATCH, HEAD}

    private CORSHttpMethod method;
    private String url;
    private String encoding;
    private boolean useRedirectingClient;

    public CORSRequest() {

    }

    public CORSHttpMethod getMethod() {
        return method;
    }

    public void setMethod(CORSHttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return "CORSRequest{" +
                "method=" + method +
                ", url='" + url + '\'' +
                ", encoding='" + encoding + '\'' +
                '}';
    }

    public boolean isUseRedirectingClient() {
        return useRedirectingClient;
    }

    public void setUseRedirectingClient(boolean useRedirectingClient) {
        this.useRedirectingClient = useRedirectingClient;
    }
}
