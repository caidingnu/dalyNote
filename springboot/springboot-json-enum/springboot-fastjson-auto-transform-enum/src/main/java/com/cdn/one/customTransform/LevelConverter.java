package com.cdn.one.customTransform;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.cdn.one.constant.Level;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastjson  系列化和反序列化
 */
public class LevelConverter implements ObjectSerializer, ObjectDeserializer {

    static Log log = LogFactory.getLog(LevelConverter.class);
    /**
     * 反序列化(前端-->>>后端)
     *
     * 通长情况下，前端传的也是code，数据库存的也是code，
     * 一般不需要反序列化操作，根据实际情况而定
     * @param parser
     * @param type
     * @param fieldName
     * @param <T>
     * @return
     */
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Level level = parser.parseObject(Level.class);
//        return mineralCode != null ? (T)mineralCode.getDisplayName() : null;
        log.info(">>>>>>>>>>>>>>>>>>  fastjson反序列化 <<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return (T)level;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    /**
     * 序列化  （后端--->>前端）
     * @param serializer
     * @param object
     * @param fieldName
     * @param fieldType
     * @param features
     * @throws IOException
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
        Level level = (Level)object;
        serializer.write(level != null ? level.getValue() :"未知");
        log.info(">>>>>> >>>>>> >>>>>>  fastjson序列化 <<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
