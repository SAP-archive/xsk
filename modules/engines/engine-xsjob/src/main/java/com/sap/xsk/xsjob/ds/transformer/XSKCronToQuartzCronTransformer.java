/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.transformer;

import com.sap.xsk.xsjob.ds.api.XSKCronExpressionException;
import java.text.ParseException;
import javax.inject.Singleton;

@Singleton
public class XSKCronToQuartzCronTransformer {

  private static final int XSK_CRON_YEAR = 0;
  private static final int XSK_CRON_MONTH = 1;
  private static final int XSK_CRON_DAY = 2;
  private static final int XSK_CRON_DAY_OF_WEEK = 3;
  private static final int XSK_CRON_HOUR = 4;
  private static final int XSK_CRON_MINUTE = 5;
  private static final int XSK_CRON_SECOND = 6;

  private String[] xskCronExpressionArr;

  public XSKCronToQuartzCronTransformer() {
  }

  public String transform(String xskCronExpression) throws ParseException {
    xskCronExpressionArr = xskCronExpression.split(" ");

    QuartzCronExpression quartzCronExpression = new QuartzCronExpression();
    quartzCronExpression.setYear(parseRange(xskCronExpressionArr[XSK_CRON_YEAR]));
    quartzCronExpression.setMonth(parseRange(xskCronExpressionArr[XSK_CRON_MONTH]));
    quartzCronExpression.setDayOfMonth(parseRange(xskCronExpressionArr[XSK_CRON_DAY]));

    String quartzDayOfWeek = parseRange(xskCronExpressionArr[XSK_CRON_DAY_OF_WEEK]);
    quartzDayOfWeek = parseDayOfWeekElement(quartzDayOfWeek);
    quartzCronExpression.setDayOfWeek(quartzDayOfWeek);

    quartzCronExpression.setHours(parseRange(xskCronExpressionArr[XSK_CRON_HOUR]));
    quartzCronExpression.setMinutes(parseRange(xskCronExpressionArr[XSK_CRON_MINUTE]));
    quartzCronExpression.setSeconds(parseRange(xskCronExpressionArr[XSK_CRON_SECOND]));

    return quartzCronExpression.toString();
  }

  private String parseRange(String xskCronElement) {
    if (!xskCronElement.contains(":")) {
      return xskCronElement;
    }

    String[] splitXscCronElement = xskCronElement.split(":");

    return String.join("-", splitXscCronElement);
  }

  private String parseDayOfWeekElement(String dayOfWeekElement) throws ParseException {
    if (dayOfWeekElement.equals("*") &&
        xskCronExpressionArr[XSK_CRON_DAY].equals("*")) {
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

    throw new XSKCronExpressionException(String.join(" ", xskCronExpressionArr), XSK_CRON_DAY_OF_WEEK);
  }
}
