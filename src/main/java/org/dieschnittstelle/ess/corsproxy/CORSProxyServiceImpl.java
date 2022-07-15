package org.dieschnittstelle.ess.corsproxy;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.io.*;
import java.util.concurrent.Future;

public class CORSProxyServiceImpl implements CORSProxyService {

    @Override
    public CORSResponse runCORSRequest(CORSRequest corsRequest) {
        // create an http client
        HttpClient client = createClient(corsRequest.isUseRedirectingClient());
        // create an http request from the CORSRequest
        String requestUrl = corsRequest.getUrl();
        HttpUriRequest httpRequest = null;
        switch (corsRequest.getMethod()) {
            case POST: httpRequest = new HttpPost(requestUrl);break;
            case GET: httpRequest = new HttpGet(requestUrl);break;
            case PUT: httpRequest = new HttpPut(requestUrl);break;
            case PATCH: httpRequest = new HttpPatch(requestUrl);break;
            case DELETE: httpRequest = new HttpDelete(requestUrl);break;
            case HEAD: httpRequest = new HttpHead(requestUrl);break;
            default: {
                throw new BadRequestException("Cannot handle CORS request: " + corsRequest + ". Specified request method is not supported.");
            }
        }

        CORSResponse corsResponse = new CORSResponse();
        try {
            System.out.println("running request: " + corsRequest);
            HttpResponse response = client.execute(httpRequest);
            System.out.println("received response: " + response);

            corsResponse.setStatus(response.getStatusLine().getStatusCode());
            // we read the result
            String responseBody = readBodyAsString(response.getEntity().getContent(), corsRequest.getEncoding());
            corsResponse.setResponseText(responseBody);
        }
        catch (Exception e) {
            e.printStackTrace();
            corsResponse.setStatus(500);
            corsResponse.setResponseText("Got exception running proxy on CORS request " + corsRequest + ": " + e);
        }

        return corsResponse;
    }

    public static String readBodyAsString(InputStream is, String encoding) throws IOException {
        StringBuffer buf = new StringBuffer();

        BufferedReader br = new BufferedReader(encoding == null || encoding.trim().length() == 0
                ? new InputStreamReader(is)
                : new InputStreamReader(is,encoding));

        try {
            String line = br.readLine();
            if (line != null) {
                buf.append(line);
            }
            for(int linecount = 0; line != null; ++linecount) {
                line = br.readLine();
                if (line != null) {
                    buf.append(line);
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }

        return buf.toString();
    }

    public HttpClient createClient(boolean redirecting) {
        // we create a default timeout setting
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).build();

        try {
            HttpClient client = null;
            if (!redirecting) {
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .disableRedirectHandling()
                        .build();
            } else {
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .build();
            }

            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
