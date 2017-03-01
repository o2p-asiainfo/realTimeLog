package com.asiainfo.integration.o2p.log.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

public final class TimeZoneUtil {
	public static Timestamp timeZoneConver(Date converTime, String timeZone) {
		TimeZone fromTimeZone = Calendar.getInstance().getTimeZone();
		TimeZone toTimeZone = fromTimeZone;
		if (StringUtils.hasText(timeZone)) {
			toTimeZone = TimeZone.getTimeZone(timeZone);
		}
		Date result = changeTimeZone(converTime, fromTimeZone, toTimeZone);
		if (result == null) {
			return null;
		} else {
			return new Timestamp(result.getTime());
		}
	}
	
	public static Timestamp timeZoneConver(String timeZone) {
		return timeZoneConver(new Date(), timeZone);
	}

	private static Date changeTimeZone(Date date, TimeZone oldZone,
			TimeZone newZone) {
		Date dateTmp = null;
		if (date != null) {
			Calendar newZoneCal = Calendar.getInstance(newZone);
			newZoneCal.setTime(date);

			Calendar oldZoneCal = Calendar.getInstance(oldZone);
			oldZoneCal.setTime(date);
			
			int timeOffset = (oldZoneCal.get(Calendar.ZONE_OFFSET) - newZoneCal.get(Calendar.ZONE_OFFSET))
					+ (oldZoneCal.get(Calendar.DST_OFFSET) - newZoneCal
							.get(Calendar.DST_OFFSET));
			dateTmp = new Date(date.getTime() - timeOffset);
		}
		return dateTmp;
	}
	private TimeZoneUtil(){
	}
}
