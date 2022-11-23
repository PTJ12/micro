package xpit.top.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

/**
 * 时间戳转化为简单时间格式
 * @author Pu Tongjiao
 * @date 2022/9/27 15:03
 */
public class DateUtils {
    public static Date timestampToDate(BigInteger date) {
        return new Date(Long.parseLong(String.valueOf(date) + "000"));
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateFormat =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(date) + "000")));
//        return dateFormat;
    }

    public static BigInteger dateToTimestamp(Date date) throws ParseException {
        long time = date.getTime() / 1000;
        return BigInteger.valueOf(time);
    }
}
