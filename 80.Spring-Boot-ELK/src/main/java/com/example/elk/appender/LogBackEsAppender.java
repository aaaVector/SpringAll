package com.example.elk.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @Author: vector
 * @Date: 2020/8/16 17:44
 */
public class LogBackEsAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void append(ILoggingEvent iLoggingEvent) {
        JSONObject jsonObject = new JSONObject();
        IThrowableProxy thrownProxy = iLoggingEvent.getThrowableProxy();
        jsonObject.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        jsonObject.put("className", iLoggingEvent.getLoggerName());
        jsonObject.put("methodName", iLoggingEvent.getMarker().getName());
        jsonObject.put("logMessage", iLoggingEvent.getMessage());
        jsonObject.put("logLevel", iLoggingEvent.getLevel().toString());
        jsonObject.put("logThread", iLoggingEvent.getThreadName());
        jsonObject.put("errorMsg", thrownProxy == null ? "" : thrownProxy.getMessage());
        jsonObject.put("exception", thrownProxy == null ? "" : thrownProxy.getClassName());
        jsonObject.put("stackTrace", thrownProxy == null ? "" : parseException(thrownProxy.getStackTraceElementProxyArray()));
        HttpURLConnection urlConnection = null;
        String body = jsonObject.toJSONString();
        try {
            urlConnection = (HttpURLConnection)new URL("http://192.168.56.101:9200").openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            Writer writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            writer.write(body);
            writer.flush();
            writer.close();
            int rc = urlConnection.getResponseCode();
            if (rc != 200) {
                throw new IOException("Got response code [" + rc + "] from server with data " + body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert urlConnection != null;
            urlConnection.disconnect();
        }
    }

    public String parseException(StackTraceElementProxy[] stackTrace) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        Arrays.stream(stackTrace).map(StackTraceElementProxy::getStackTraceElement).forEach((e) -> sb.append(e.getClassName()).append(".").append(e.getMethodName()).append("(").append(e.getFileName()).append(":").append(e.getLineNumber()).append(")").append("\n"));
        return sb.toString();
    }


}
