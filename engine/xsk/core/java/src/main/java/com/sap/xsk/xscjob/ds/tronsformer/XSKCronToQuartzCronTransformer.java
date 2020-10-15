package com.sap.xsk.xscjob.ds.tronsformer;

import java.text.ParseException;

import javax.inject.Singleton;

import com.sap.xsk.xscjob.ds.api.XSKCronExpressionException;

@Singleton
public class XSKCronToQuartzCronTransformer {

    private static final int XSK_CRON_YEAR = 0;
    private static final int XSK_CRON_MONTH = 1;
    private static final int XSK_CRON_DAY = 2;
    private static final int XSK_CRON_DAY_OF_WEEK = 3;
    private static final int XSK_CRON_HOUR = 4;
    private static final int XSK_CRON_MINUTE = 5;
    private static final int XSK_CRON_SECOND = 6;

    private String[] xscCronExpressionArr;

    public XSKCronToQuartzCronTransformer() {
    }

    public String transform(String xscCronExpression) throws ParseException {
        xscCronExpressionArr = xscCronExpression.split(" ");

        QuartzCronExpression quartzCronExpression = new QuartzCronExpression();
        quartzCronExpression.setYear(parseRange(xscCronExpressionArr[XSK_CRON_YEAR]));
        quartzCronExpression.setMonth(parseRange(xscCronExpressionArr[XSK_CRON_MONTH]));
        quartzCronExpression.setDayOfMonth(parseRange(xscCronExpressionArr[XSK_CRON_DAY]));

        String quartzDayOfWeek = parseRange(xscCronExpressionArr[XSK_CRON_DAY_OF_WEEK]);
        quartzDayOfWeek = parseDayOfWeekElement(quartzDayOfWeek);
        quartzCronExpression.setDayOfWeek(quartzDayOfWeek);

        quartzCronExpression.setHours(parseRange(xscCronExpressionArr[XSK_CRON_HOUR]));
        quartzCronExpression.setMinutes(parseRange(xscCronExpressionArr[XSK_CRON_MINUTE]));
        quartzCronExpression.setSeconds(parseRange(xscCronExpressionArr[XSK_CRON_SECOND]));

        return quartzCronExpression.toString();
    }

    private String parseRange(String xscCronElement) {
        if (!xscCronElement.contains(":")) {
            return xscCronElement;
        }

        String[] splitXscCronElement = xscCronElement.split(":");

        return String.join("-", splitXscCronElement);
    }

    private String parseDayOfWeekElement(String dayOfWeekElement) throws ParseException {
        if (dayOfWeekElement.equals("*") &&
                xscCronExpressionArr[XSK_CRON_DAY].equals("*")) {
            return "?";
        } else if (!dayOfWeekElement.contains(".")) {
            return dayOfWeekElement.toUpperCase();
        }

        String[] splitDayOfWeekElement = dayOfWeekElement.split(".");
        String dayOfWeek = splitDayOfWeekElement[1].toUpperCase();

        int occurrence = Integer.parseInt(splitDayOfWeekElement[0]);

        if (occurrence >= 0) {
            return dayOfWeek + "#" + occurrence;
        } else if (occurrence == -1) {
            return dayOfWeek + "L";
        } else if (occurrence < -1 && occurrence >= -5) {
            return dayOfWeek + "L" + "-" + occurrence;
        }

        throw new XSKCronExpressionException(String.join(" ", xscCronExpressionArr), XSK_CRON_DAY_OF_WEEK);
    }
}
