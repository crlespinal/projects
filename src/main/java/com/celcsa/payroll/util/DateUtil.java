package com.celcsa.payroll.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.celcsa.payroll.items.DateRange;


/**
 * DateUtil
 */
public class DateUtil {

    public static final int DAY_BEGIN_HOUR = 0;
    public static final int DAY_BEGIN_MINUTE = 0;
    public static final int DAY_END_HOUR = 23;
    public static final int DAY_END_MINUTE = 59;

    public static Date toBeginningOfDate(Date from){
        return DateUtil.toDate(DateUtil.convertToLocalDateViaInstant(from).withHour(DAY_BEGIN_HOUR).withMinute(DAY_BEGIN_MINUTE));    
    }

    public static Date toEndOfDate(Date from){
        return DateUtil.toDate(DateUtil.convertToLocalDateViaInstant(from).withHour(DAY_END_HOUR).withMinute(DAY_END_MINUTE));    
    }

    public static Date toDate(LocalDateTime localDateTime){
        return Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime();
    }

    public static boolean isTargetInSource(DateRange source, DateRange target, boolean checkReverse){

        final LocalDateTime sFrom = DateUtil.convertToLocalDateViaInstant(source.getFrom());
        final LocalDateTime sTo = DateUtil.convertToLocalDateViaInstant(source.getTo());

        final LocalDateTime tFrom = DateUtil.convertToLocalDateViaInstant(target.getFrom());
        final LocalDateTime tTo = DateUtil.convertToLocalDateViaInstant(target.getTo());

        if(!checkReverse){
            final boolean targetFromHourInSource = tFrom.isAfter(sFrom) && tFrom.isBefore(sTo);
            final boolean targetToHourInSource = tTo.isBefore(sTo) && sTo.isAfter(sFrom);
            if(targetFromHourInSource || targetToHourInSource)
                return true;
        } else {
            final boolean isSourceFromHourInTarget = sFrom.isAfter(tFrom) && sFrom.isBefore(tTo);
            final boolean isSourceToHourInTarget = sTo.isBefore(tTo) && sTo.isAfter(tFrom);
            if(isSourceFromHourInTarget || isSourceToHourInTarget)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Date from = Date.from(LocalDateTime.of(2019, 9, 29, 8, 00).atZone(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDateTime.of(2019, 9, 29, 12, 00).atZone(ZoneId.systemDefault()).toInstant());

        Date tFrom = Date.from(LocalDateTime.of(2019, 9, 29, 7, 00).atZone(ZoneId.systemDefault()).toInstant());
        Date tTo = Date.from(LocalDateTime.of(2019, 9, 29, 17, 00).atZone(ZoneId.systemDefault()).toInstant());


        DateRange s = new DateRange(from, to);
        DateRange t = new DateRange(tFrom, tTo);

        System.out.println(DateUtil.isTargetInSource(s, t, true));
    }
}