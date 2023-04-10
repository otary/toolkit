package cn.chenzw.toolkit.core.convert.impl;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.exception.ConvertException;
import cn.chenzw.toolkit.core.lang.DateKit;

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
            mills = DateKit.toInstant((TemporalAccessor) value).toEpochMilli();
        } else {
            String sValue = convertToStr(value);
            try {
                mills = DateKit.parseDate(sValue).getTime();
            } catch (ParseException e) {
                throw new ConvertException(e);
            }
        }

        if (mills == null) {
            return null;
        }

        return new Date(mills);
    }
}
