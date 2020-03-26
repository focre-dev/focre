package com.focre.base.jackson;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.commons.lang3.StringUtils;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormat extends StdDateFormat {

	private static final long serialVersionUID = -1584835462167266964L;

	@Override
	public Date parse(String dateStr, ParsePosition pos) {
		SimpleDateFormat sdf = null;
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (dateStr.length() == 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 16) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 19) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 23) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse(dateStr, pos);
		}
		return super.parse(dateStr, pos);
	}

	@Override
	public Date parse(String dateStr) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = null;
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (dateStr.length() == 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 16) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 19) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 23) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse(dateStr, pos);
		}
		return super.parse(dateStr, pos);
	}

	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date, toAppendTo, fieldPosition);
	}

	public CustomDateFormat clone() {
		return new CustomDateFormat();
	}
}
