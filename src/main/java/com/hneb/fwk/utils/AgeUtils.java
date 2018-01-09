package com.hneb.fwk.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AgeUtils {
    public AgeUtils() {
    }

    public static Map<String, Integer> calcAge(Date date, Date referDate) {
        boolean bigger = true;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        if(referDate.getTime() > date.getTime()) {
            c1.setTime(referDate);
            c2.setTime(date);
            bigger = true;
        } else {
            c1.setTime(date);
            c2.setTime(referDate);
            bigger = false;
        }

        int year = c1.get(1) - c2.get(1);
        int month = c1.get(2) + 1 - (c2.get(2) + 1);
        int day = c1.get(5) - c2.get(5);
        if(day < 0) {
            --month;
            if(month < 0) {
                month += 12;
                --year;
            }

            int preMonth = c1.get(2) + 1 - 1;
            if(preMonth <= 0) {
                preMonth = 12;
            }

            if(preMonth == 1 || preMonth == 3 || preMonth == 5 || preMonth == 7 || preMonth == 8 || preMonth == 10 || preMonth == 12) {
                day = 31 - c2.get(5) + c1.get(5);
            }

            if(preMonth == 4 || preMonth == 6 || preMonth == 9 || preMonth == 11) {
                day = 30 - c2.get(5) + c1.get(5);
            }

            if(preMonth == 2) {
                day = 28 - c2.get(5) + c1.get(5);
                int c1Year = c1.get(1);
                if(c1Year % 100 == 0 && c1Year % 400 == 0 || c1Year % 100 != 0 && c1Year % 4 == 0) {
                    ++day;
                }
            }
        }

        if(month < 0) {
            --year;
            month += 12;
        }

        Map<String, Integer> map = new HashMap();
        map.put("YEAR", Integer.valueOf(year > 0?year:0));
        map.put("MONTH", Integer.valueOf(month > 0?month:0));
        map.put("DAY", Integer.valueOf(day));
        if(!bigger) {
            map.put("YEAR", Integer.valueOf(-((Integer)map.get("YEAR")).intValue()));
            map.put("MONTH", Integer.valueOf(-((Integer)map.get("MONTH")).intValue()));
            map.put("DAY", Integer.valueOf(-((Integer)map.get("DAY")).intValue()));
        }

        return map;
    }

    public static String calcAgeAsString(Date date, Date referDate) {
        Map<String, Integer> map = calcAge(date, referDate);
        int year = ((Integer)map.get("YEAR")).intValue();
        int month = ((Integer)map.get("MONTH")).intValue();
        int day = ((Integer)map.get("DAY")).intValue();
        StringBuffer result = new StringBuffer();
        result.append(year + "岁");
        result.append(month + "个月");
        result.append(day + "天");
        return result.toString();
    }
}
