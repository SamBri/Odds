package com.nothing.odds;

import java.time.Duration;

import com.nothing.OptType;
import com.nothing.exception.HowLongException;
import com.nothing.responses.HowLongExceptionResponse;


public class MyDurationUtils {

	public static HowLongResponse getTheSubmissionDuration(Duration howLongD) {
		//howLongD = howLongD.abs();
		
		System.out.println("@@ :: "+howLongD);
		
		char sign = (!howLongD.isNegative()) ? '+' : '-';


		howLongD = howLongD.abs();
		Integer hours = howLongD.toHoursPart();
		Long days = howLongD.toDaysPart();
		Integer seconds = howLongD.toSecondsPart();
		Integer minutes = howLongD.toMinutesPart();
		
		
		
		
		
		

		System.out.println("before (hours) : " + hours);
		System.out.println("before (minutes) : " + minutes);
		System.out.println("before (seconds) : " + seconds);
		System.out.println("before (days) : " + days);

		Integer weeks = days.intValue() / 7;
		Integer month = weeks / 4;
		Integer year = month / 13;

		System.out.println("after (year) : " + year);
		System.out.println("after (month) : " + month);
		System.out.println("after (weeks) : " + weeks);
		System.out.println("after (hours) : " + hours);
		System.out.println("after (minutes) : " + minutes);
		System.out.println("after (seconds) : " + seconds);
		System.out.println("after (days) : " + days);

		// weeks

		MyDuration duration = MyDuration.NOT_FOUND;
		// PT-3H-30M-41.6589405S


		String message = null;
		
		String optType=null;
		
		HowLongResponse howLongResponse= new HowLongResponse();
		if(sign =='-' ) {
			
			optType = OptType.SINCE_CREATED.toString();
			howLongResponse.setOptType(optType);
			// since-created
			System.out.println("@@@ /since-created");
			duration = getInterval('-',hours, days, seconds, minutes, weeks, month, year);

		} else if(sign =='+' ) {
			optType=OptType.ESTIMATED_AHEAD.toString();
			howLongResponse.setOptType(optType);

			// estimated ahead
			System.out.println("@@@ /estimated-ahead");
		  duration = getInterval('+',hours, days, seconds, minutes, weeks, month, year);
	
		} else {
		
			System.out.println("@@@ invalid arguments sent");
		}
		
		



		switch (duration) {

		case HOURS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);
			break;

		case MINUTES:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);

			break;

		case SECONDS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);

			break;

		case DAYS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);
			break;

		case WEEKS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);
			break;

		case MONTHS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);

			break;

		case YEARS:
			// System.out.println(duration.getDurationMessage());
			message = duration.getDurationMessage();
			howLongResponse.setHowLong(message);

			break;

		default:
		
			throw new HowLongException("invalid argument sent for the route chosen");

		}

		return howLongResponse;
	}

	private static MyDuration getInterval(char sign, Integer hours, Long days, Integer seconds, Integer minutes,
			Integer weeks, Integer month, Integer year) {
		MyDuration duration;
		String message = null;

		if (year.intValue() != 0) {

			System.out.println("@@ years");

			duration = MyDuration.YEARS;

			if (sign == '-') {

				if (year < 1) {

					message = year + " year " + " ago ";

				} else {

					message = year + " years " + " ago ";

				}

			}

			if (sign == '+') {

				if (year <= 1) {

					message = year + " year " + " ahead ";

				} else {

					message = year + " years " + " ahead ";

				}

			}

			duration.setDurationMessage(message);
			return duration;

		}

		if (month.intValue() != 0) {

			duration = MyDuration.MONTHS;

			System.out.println("@@ months");

			if (sign == '-') {

				if (month <= 1) {

					message = month + " month " + " ago ";

				} else {

					message = month + " months " + " ago ";

				}

			}

			if (sign == '+') {

				if (month <= 1) {

					message = month + " month " + " ahead ";

				} else {

					message = month + " months " + " ahead ";

				}

			}

//			
//				if (month <= 1) {
//
//					message = month.toString().replace("-", "") + " month " + " ago ";
//
//				} else {
//
//					message = month.toString().replace("", "") + " months " + " ago ";
//
//				}

			duration.setDurationMessage(message);

			return duration;
		}

		if (weeks.intValue() != 0) {

			System.out.println("@@ weeks");

			duration = MyDuration.WEEKS;

			if (sign == '+') {

				if (weeks <= 1) {
					message = weeks.toString() + " week " + " ahead ";

				}

				else {

					message = weeks.toString() + " weeks " + " ahead ";

				}

			}

			if (sign == '-') {

				if (weeks <= 1) {
					message = weeks.toString() + " week " + " ago ";

				}

				else {

					message = weeks.toString() + " weeks " + " ago ";

				}

			}

			duration.setDurationMessage(message);

			return duration;

		}

		if (days.intValue() != 0) {

			System.out.println("@@ days");

			duration = MyDuration.DAYS;

			if (sign == '+') {

				if (days <= 1) {
					message = days.toString() + " day " + " ahead ";

				}

				else {

					message = days.toString() + " days " + " ahead ";

				}

			}

			if (sign == '-') {

				if (days <= 1) {
					message = days.toString() + " day " + " ago ";

				}

				else {

					message = days.toString() + " days " + " ago ";

				}

			}

			duration.setDurationMessage(message);

			return duration;
		}

		// hours
		if (hours.intValue() != 0) {

			System.out.println("@@ hours");

			duration = MyDuration.HOURS;

			if (sign == '+') {

				if (hours <= 1) {
					message = hours.toString() + " hour " + " ahead ";

				}

				else {

					message = hours.toString() + " hours " + " ahead ";
				}

			}

			if (sign == '-') {

				if (hours <= 1) {
					message = hours.toString() + " hour " + " ago ";

				}

				else {

					message = hours.toString() + " hours " + " ago ";
				}

			}

			duration.setDurationMessage(message);
			return duration;
		}

		if (minutes.intValue() != 0) {

			System.out.println("@@ minutes");
			duration = MyDuration.MINUTES;

			if (sign == '-') {

				if (minutes <= 1) {

					message = minutes.toString() + " minute " + " ago ";
				}

				else {

					message = minutes.toString() + " minutes " + " ago ";

				}
			}

			if (sign == '+') {

				if (minutes <= 1) {

					message = minutes.toString() + " minute " + " ahead ";
				}

				else {

					message = minutes.toString() + " minutes " + " ahead ";

				}
			}

			duration.setDurationMessage(message);
			return duration;

		}

		if (seconds.intValue() < 60) {

			System.out.println("@@ seconds");

			duration = MyDuration.SECONDS;

			message = "just now";

			duration.setDurationMessage(message);

			return duration;

		}

		return null;
	}

	public MyDurationUtils() {
		super();
	}

}
