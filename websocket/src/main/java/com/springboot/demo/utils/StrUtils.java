package com.springboot.demo.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 字符串工具类
 */
public class StrUtils {

    public static Boolean isEmpty(Object type) {
        if (type != null && !"".equals(type)) {
            return false;
        }
        return true;
    }

    /**
     * 拿到后缀名
     *
     * @param fulleName
     * @return
     */
    public static String getSuffix(String fulleName) {
        if (isNullOrEmpty(fulleName)) return null;
        return fulleName.substring(fulleName.lastIndexOf("."), fulleName.length() - 1);
    }

    /**
     * 判断字符串为空字符串或者为null，如果满足其中一个条件，则返回true
     *
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        Object obj = (Object) str;
        return isNullOrEmpty(obj);
    }

    /**
     * 判断对象为空字符串或者为null，如果满足其中一个条件，则返回true
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object obj) {
        boolean isEmpty = false;
        if (obj == null) {
            isEmpty = true;
        } else if (obj instanceof String) {
            isEmpty = ((String) obj).trim().isEmpty();
        } else if (obj instanceof Collection) {
            isEmpty = (((Collection) obj).size() == 0);
        } else if (obj instanceof Map) {
            isEmpty = ((Map) obj).size() == 0;
        } else if (obj.getClass().isArray()) {
            isEmpty = Array.getLength(obj) == 0;
        } else if (obj instanceof Long) {
            isEmpty = Long.parseLong(obj + "") < 1;
        }
        return isEmpty;
    }


    /**
     * 检查 email输入是否正确 正确的书写格 式为 username@domain
     *
     * @param text
     * @return
     */
    public static boolean checkEmail(String text, int length) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && text.length() <= length;
    }

    /**
     * 检查电话输入 是否正确 正确格 式 012-87654321、0123-87654321、0123－7654321
     *
     * @param text
     * @return
     */
    public static boolean checkTelephone(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches(
                "(0\\d{2,3}-\\d{7,8})|" +
                        "(0\\d{9,11})|" +
                        "(\\d{7})|" +
                        "(\\d{8})|" +
                        "(4\\d{2}\\d{7})|" +
                        "(4\\d{2}-\\d{7})|" +
                        "(4\\d{2}-\\d{3}-\\d{4})|" +
                        "(4\\d{2}-\\d{4}-\\d{3})");
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param text
     * @return
     */
    public static boolean checkMobilephone(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("^1(3[0-9]|4[579]|5[0-35-9]|8[0-9]|7[015-8])\\d{8}$");
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param text
     * @return
     */
    public static boolean checkChineseName(String text, int length) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("^[\u4e00-\u9fa5]+$") && text.length() <= length;
    }

    /**
     * 检查字符串中是否有空格，包括中间空格或者首尾空格
     *
     * @param text
     * @return
     */
    public static boolean checkBlank(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("^\\s*|\\s*$");
    }

    /**
     * 检查字符串是 否含有HTML标签
     *
     * @param text
     * @return
     */

    public static boolean checkHtmlTag(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("<(\\S*?)[^>]*>.*?<!--\\1-->|<.*? />");
    }

    /**
     * 检查URL是 否合法
     *
     * @param text
     * @return
     */
    public static boolean checkURL(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     *
     * @param text
     * @return
     */
    public static boolean checkIP(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }


    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param text
     * @return
     */

    public static boolean checkQQ(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否 合法
     *
     * @param text
     * @return
     */
    public static boolean checkPostCode(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 检查身份证是 否合法,15位或18位(或者最后一位为X)
     *
     * @param text
     * @return
     */
    public static boolean checkIDCard(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("\\d{15}|\\d{18}|(\\d{17}[x|X])");
    }

    /**
     * 检查输入是否 超出规定长度
     *
     * @param length
     * @param text
     * @return
     */
    public static boolean checkLength(String text, int length) {
        return ((isNullOrEmpty(text)) ? 0 : text.length()) <= length;
    }

    /**
     * 判断是否为数字
     *
     * @param text
     * @return
     */
    public static boolean isNumber(String text) {
        if (isNullOrEmpty(text)) return false;
        return text.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 驼峰转下划线
     *
     * @param camelCaseName
     * @return
     */
    public static String camel2Underline(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().
                replaceAll("-", "").substring(0, 32);

        return uuid;
    }

    /**
     * 下划线转托驼峰
     *
     * @param underscoreName
     * @return
     */
    public static String underline2Camel(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     *   实体转map
     */
    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> entityToMap(Object entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (entity == null) {
            return map;
        }
        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if ("serialVersionUID".equalsIgnoreCase(field.getName())) {
                    continue;
                }
                map.put(field.getName(), field.get(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
