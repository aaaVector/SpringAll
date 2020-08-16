//package com.example.elk.appender;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.logging.log4j.core.Filter;
//import org.apache.logging.log4j.core.LogEvent;
//import org.apache.logging.log4j.core.appender.AbstractAppender;
//import org.apache.logging.log4j.core.config.plugins.Plugin;
//import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
//import org.apache.logging.log4j.core.config.plugins.PluginElement;
//import org.apache.logging.log4j.core.config.plugins.PluginFactory;
//import org.apache.logging.log4j.core.impl.ThrowableProxy;
//import org.apache.logging.log4j.core.layout.PatternLayout;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.ProtocolException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//
///**
// * 需要导入spring-boot-starter-log4j2
// * slf4j2 自定义appender输出日志到指定地方
// * @Author: vector
// * @Date: 2020/8/16 11:27
// */
//@Plugin(name = "Log4jEsAppender", category = "Core", elementType = "appender", printObject = true)
//public class Log4jEsAppender extends AbstractAppender {
//
//    private static String host;
//    private static Integer port;
//    private static String index;
//    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    protected Log4jEsAppender(String name, Filter filter, org.apache.logging.log4j.core.Layout<? extends Serializable> layout, boolean ignoreExceptions) {
//        super(name, filter, layout, ignoreExceptions);
//    }
//
//    /**
//     * 自定义log操作，存储到es
//     * 还可以考虑异步收集，批量收集、失败重试等功能完善。
//     */
//    @Override
//    public void append(LogEvent event) {
//        JSONObject jsonObject = new JSONObject();
//        ThrowableProxy thrownProxy = event.getThrownProxy();
//        jsonObject.put("time", dateTimeFormatter.format(LocalDateTime.now()));
//        jsonObject.put("className", event.getLoggerName());
//        jsonObject.put("methodName", event.getSource().getMethodName());
//        jsonObject.put("logMessage", event.getMessage().getFormattedMessage());
//        jsonObject.put("logLevel", event.getLevel().name());
//        jsonObject.put("logThread", event.getThreadName());
//        jsonObject.put("errorMsg", thrownProxy == null ? "" : thrownProxy.getMessage());
//        jsonObject.put("exception", thrownProxy == null ? "" : thrownProxy.getName());
//        jsonObject.put("stackTrace", thrownProxy == null ? "" : parseException(thrownProxy.getStackTrace()));
//
//        HttpURLConnection urlConnection = (HttpURLConnection)((HttpURLConnection)this.settings.getUrl().openConnection());
//        try {
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(true);
//            urlConnection.setReadTimeout(10000);
//            urlConnection.setConnectTimeout(10000);
//            urlConnection.setRequestMethod("PUT");
//            String body = jsonObject.toJSONString();
//            urlConnection.setRequestProperty("Content-Type", "application/json");
//
//            Writer writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
//            writer.write(body);
//            writer.flush();
//            writer.close();
//            int rc = urlConnection.getResponseCode();
//            if (rc != 200) {
//                throw new IOException("Got response code [" + rc + "] from server with data " + data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            urlConnection.disconnect();
//        }
//    }
//
//    public String parseException(StackTraceElement[] stackTrace) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("\n");
//        Arrays.stream(stackTrace).forEach((e) -> sb.append(e.getClassName()).append(".").append(e.getMethodName()).append("(").append(e.getFileName()).append(":").append(e.getLineNumber()).append(")").append("\n")
//        );
//        return sb.toString();
//    }
//
//    /**
//     * 接收配置文件中的参数
//     *
//     * @param name
//     * @param filter
//     * @param layout
//     * @return
//     */
//    @PluginFactory
//    public static Log4jEsAppender createAppender(@PluginAttribute("name") String name,
//                                                 @PluginElement("Filter") final Filter filter,
//                                                 @PluginElement("Layout") PatternLayout layout,
//                                                 @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
//                                                 @PluginAttribute("host") String host,
//                                                 @PluginAttribute("port") Integer port,
//                                                 @PluginAttribute("index") String index
//    ) {
//        if (name == null) {
//            LOGGER.error("No name provided for ESAppender");
//            return null;
//        }
//        if (layout == null) {
//            layout = PatternLayout.createDefaultLayout();
//        }
//        Log4jEsAppender.host = host;
//        Log4jEsAppender.port = port;
//        Log4jEsAppender.index = index;
//        return new Log4jEsAppender(name, filter, layout, ignoreExceptions);
//    }
//
//
//}
