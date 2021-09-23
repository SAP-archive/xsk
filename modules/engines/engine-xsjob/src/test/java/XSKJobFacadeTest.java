/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import com.sap.xsk.xsjob.ds.facade.XSKJobFacade;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.manager.SchedulerInitializer;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;

public class XSKJobFacadeTest extends AbstractDirigibleTest {
  @Test
  public void createJob() throws IOException, SchedulerException, ParseException, SQLException {
    SchedulerInitializer initializer = new SchedulerInitializer();
    initializer.initialize();
    String xsjob = org.apache.commons.io.IOUtils.
        toString(XSKJobFacadeTest.class.getResourceAsStream("/TestXSKJob.xsjob"), StandardCharsets.UTF_8);
    XSKJobFacade.newJob(xsjob, "/TestXSKJob.xsjob");
  }
}
