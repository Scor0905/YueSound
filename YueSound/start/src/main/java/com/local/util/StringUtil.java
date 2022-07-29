package com.local.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author hhs
 * @create 2022-07-01 11:22
 */
public class StringUtil implements Serializable {
    private static final long serialVersionUID = 2143367839995921470L;
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static final String EMPTY = "";
    public StringUtil() {
    }

    public static String nullToEmpty(Object value) {
        if (null == value) {
            return "";
        } else {
            String tempString = String.valueOf(value);
            return !"null".equalsIgnoreCase(tempString) && !"".equals(tempString) ? tempString : "";
        }
    }

    public static String nvl(Object value, String def) {
        if (null == value) {
            return def;
        } else {
            String tempString = String.valueOf(value);
            return !"null".equalsIgnoreCase(tempString) && !"".equals(tempString) ? tempString : def;
        }
    }

    public static <T> T getGenericsValue(Object value, T clazz) {
        if (isEmpty(value)) {
            return null;
        } else {
            Object newInstance = null;

            try {
                Constructor<? extends Object> constructor = clazz.getClass().getConstructor(String.class);
                if (constructor != null) {
                    newInstance = constructor.newInstance(value.toString());
                }
            } catch (NoSuchMethodException var5) {
            } catch (InstantiationException var6) {
            } catch (IllegalAccessException var7) {
            } catch (IllegalArgumentException var8) {
            } catch (InvocationTargetException var9) {
            }

            return (T) newInstance;
        }
    }

    public static boolean isTrue(String val) {
        if (isEmpty(val)) {
            return false;
        } else {
            return "y".equalsIgnoreCase(val) || "true".equalsIgnoreCase(val) || "1".equalsIgnoreCase(val);
        }
    }

    public static long toLong(Object value) {
        return toLong(value, 0L);
    }

    public static long toLong(Object value, long defaultVal) {
        try {
            return Long.parseLong(nullToEmpty(value));
        } catch (Exception var4) {
            return defaultVal;
        }
    }

    public static boolean isEmpty(Object value) {
        String valueString = nullToEmpty(value);
        if (null == valueString) {
            return true;
        } else {
            return "null".equalsIgnoreCase(valueString) || "".equals(valueString);
        }
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isNotBlank(String value) {
        return StringUtils.isNotBlank(value);
    }

    public static boolean isNotEmpty(String... values) {
        boolean res = true;
        if (ArrayUtils.isNotEmpty(values)) {
            String[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                if (null == value || "".equals(value)) {
                    res = false;
                    break;
                }
            }
        } else {
            res = false;
        }

        return res;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    public static String formateNum(String valueString) {
        NumberFormat format = new DecimalFormat("#,##0.00");
        BigDecimal bigDecimal = toBigDecimal(valueString);
        return format.format(bigDecimal.doubleValue());
    }

    public static BigDecimal formateNumToDecimal(String valueString) {
        BigDecimal bigDecimal = toBigDecimal(valueString);
        return bigDecimal;
    }

    public static BigDecimal toBigDecimal(String value) {
        BigDecimal decimal = new BigDecimal(Double.valueOf("0"));
        decimal = decimal.setScale(2, 5);
        if (null == value) {
            return decimal;
        } else if (!"null".equalsIgnoreCase(value) && !"".equals(value.trim())) {
            NumberFormat format = new DecimalFormat("0.00");
            BigDecimal bigDecimal = new BigDecimal(Double.valueOf(value));
            BigDecimal res = new BigDecimal(format.format(bigDecimal));
            return res;
        } else {
            return decimal;
        }
    }

    public static String dateToString(Timestamp timestamp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(timestamp);
    }

    public static String[] stringToArray(String string) {
        if (isEmpty(string)) {
            return new String[0];
        } else {
            String[] result = new String[string.length()];

            for(int i = 0; i < string.length(); ++i) {
                result[i] = string.substring(i, i + 1);
            }

            return result;
        }
    }

    public static String getUpperHeadStrNo_(String originStr) {
        String[] chars = stringToArray(originStr);
        StringBuffer target = new StringBuffer();

        for(int j = 0; j < chars.length; ++j) {
            if (j == 0) {
                target.append(chars[j].toUpperCase());
            } else if (chars[j - 1].equals("_")) {
                target.append(chars[j].toUpperCase());
            } else if (!chars[j].equals("_")) {
                target.append(chars[j].toLowerCase());
            }
        }

        return target.toString();
    }

    public static String getLowerHeadStrNo_(String originStr) {
        String[] chars = stringToArray(originStr);
        StringBuffer target = new StringBuffer();

        for(int j = 0; j < chars.length; ++j) {
            if (j == 0) {
                target.append(chars[j].toLowerCase());
            } else if (chars[j - 1].equals("_")) {
                target.append(chars[j].toUpperCase());
            } else if (!chars[j].equals("_")) {
                target.append(chars[j].toLowerCase());
            }
        }

        return target.toString();
    }

    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String dateToStrLong(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String celanIllegalChar(String str) {
        if (str != null) {
            Pattern pattern = Pattern.compile("\\t|\r|\n");
            Matcher m = pattern.matcher(str);
            return m.replaceAll("");
        } else {
            return str;
        }
    }

    public static String strTransHump(String str) {
        String[] split = str.split("_");
        String resStr = split[0].toLowerCase();

        for(int i = 1; i < split.length; ++i) {
            resStr = resStr + split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length()).toLowerCase();
        }

        return resStr;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        while(matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        while(matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String trim(String s) {
        return s == null ? null : s.trim();
    }

    public static String nvl(String s, String d) {
        return s == null ? d : s.trim();
    }

    public static String nvl(String s) {
        return s == null ? "" : s.trim();
    }

    public static String toChinese(Integer number) {
        String str = Integer.toString(number);
        String[] s1 = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = new String[]{"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = str.length();

        for(int i = 0; i < n; ++i) {
            int num = str.charAt(i) - 48;
            if (i != n - 1 && num != 0) {
                result = result + s1[num] + s2[n - 2 - i];
            } else {
                result = result + s1[num];
            }
        }

        return result;
    }

    public static String buildCodeName(String code, String name) {
        return buildCodeName(code, name, " ");
    }

    public static String buildCodeName(String code, String name, String split) {
        StringBuffer content = new StringBuffer();
        if (isNotEmpty(code)) {
            if (isEmpty(split)) {
                split = " ";
            }

            content.append(code).append(split);
        }

        if (isNotEmpty(name)) {
            content.append(name);
        }

        return content.toString();
    }

    public static String urlEncode(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
        }

        return str;
    }

    public static String getStringValue(Object value) {
        return getStringValue(value, "");
    }

    public static String getStringValue(Object value, String defalutValue) {
        String finalValue = defalutValue;

        try {
            if (null != value) {
                if (value instanceof BigDecimal) {
                    BigDecimal temp = (BigDecimal)value;
                    finalValue = temp.toString();
                } else if (value instanceof Integer) {
                    Integer temp = (Integer)value;
                    finalValue = temp.toString();
                } else if (value instanceof String) {
                    finalValue = value.toString();
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return finalValue;
    }

    public static String checkName(String strName, String nameType) throws PatternSyntaxException {
        String result = "";
        Pattern pattern = Pattern.compile("[0-9a-zA-Z一-龥]+$");
        boolean isPat = pattern.matcher(strName).matches();
        if (!isPat && (strName.indexOf("'") > -1 || strName.indexOf("\"") > -1 || strName.indexOf("/") > -1 || strName.indexOf("\\") > -1 || strName.indexOf("<") > -1 || strName.indexOf(">") > -1 || strName.indexOf("?") > -1)) {
            result = nameType + "名称不能有特殊字符 ' \" / \\ < > ?";
        }

        return result;
    }

    public static int getWordCountRegex(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    public static boolean isInteger(Object str) {
        boolean res = false;

        try {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            if (null != str) {
                res = pattern.matcher(str.toString()).matches();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return res;
    }

    public static String transStringWord(String keyword) {
        if (isNotEmpty(keyword)) {
            keyword = keyword.replace("%", "/%");
            keyword = keyword.replace("_", "/_");
            keyword = keyword.replace("\\", "/\\");
            return keyword;
        } else {
            return "";
        }
    }

    public static String getGeneralField(CharSequence getOrSetMethodName) {
        String getOrSetMethodNameStr = getOrSetMethodName.toString();
        if (!getOrSetMethodNameStr.startsWith("get") && !getOrSetMethodNameStr.startsWith("set")) {
            return getOrSetMethodNameStr.startsWith("is") ? removePreAndLowerFirst(getOrSetMethodName, 2) : null;
        } else {
            return removePreAndLowerFirst(getOrSetMethodName, 3);
        }
    }

    public static String removePreAndLowerFirst(CharSequence str, int preLength) {
        if (str == null) {
            return null;
        } else if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            return str.length() > preLength + 1 ? first + str.toString().substring(preLength + 1) : String.valueOf(first);
        } else {
            return str.toString();
        }
    }
}