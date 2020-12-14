package com.cdn.one.customTransform;

import com.cdn.one.constant.Level;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc：
 *
 * @author CDN
 * date 2020/09/10 22:04
 */
@JsonComponent   //此注释会自动将 Serialize和Deserializer注册到jackson之中。
public class CustomeJackSon {
    static Log log = LogFactory.getLog(CustomeJackSon.class);
    /**
     * 日期序列化
     */
    public static class DateSerialize extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
            String _dateStr =  _format.format(date);
            jsonGenerator.writeString(_dateStr);
            log.info(">>>>>> >>>>>> >>>>>>  jackson序列化 <<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    /**
     * Level枚举序列化
     */
    public static class LevelSerialize extends JsonSerializer<Level> {
        @Override
        public void serialize(Level level, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            log.info(">>>>>> >>>>>> >>>>>>  jackson枚举序列化 <<<<<<<<<<<<<<<<<<<<<<<<<<<");
            jsonGenerator.writeString(level.getValue());
        }
    }
    /**
     * Level枚举反序列化
     */
//    public static class LevelDesSerialize extends JsonDeserializer<Level> {
//        @Override
//        public Level deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//             log.info(">>>>>>>>>>>>>>>>>>  反序列化 <<<<<<<<<<<<<<<<<<<<<<<<<<<");
//            return null;
//        }
//    }

}