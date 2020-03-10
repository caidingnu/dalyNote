package org.sidecar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * desc：Zuul的断路器
 *
 * @author CDN
 * date 2020/03/08 21:11
 */
@Component
public class MyFallBack implements FallbackProvider {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public String getRoute() {

//api服务id/名称，如果需要所有调用都支持回退，则return "*"或return null
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() {
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() {
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() {
                Map<String, Object> r = new HashMap<>();
                try {
                    r.put("state", "9999");
                    r.put("msg", "请求"+route+"错误，请求失败--Zuul返回");
                    r.put("errMsg",cause.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new ByteArrayInputStream(r.toString().getBytes(StandardCharsets.UTF_8));
//                return new  ByteArrayInputStream(("出现错误"+getRoute()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
