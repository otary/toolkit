package cn.chenzw.toolkit.commons.support.convert.impl;

import cn.chenzw.toolkit.commons.DateExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;

import java.text.ParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class DateTypeConverter extends AbstractTypeConverter<Date> {

    @Override
    protected Date convertInternal(Object value) {
        Long mills = null;
        if (value instanceof Calendar) {
            mills = ((Calendar) value).getTimeInMillis();
        } else if (value instanceof Long) {
            mills = (Long) value;
        } else if (value instanceof TemporalAccessor) {
            mills = DateExtUtils.toInstant((TemporalAccessor) value).toEpochMilli();
        } else {
            String sValue = convertToStr(value);
            try {
                mills = DateExtUtils.parseDate(sValue).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (mills == null) {
            return null;
        }

        return new java.util.Date(mills);
    }
}
