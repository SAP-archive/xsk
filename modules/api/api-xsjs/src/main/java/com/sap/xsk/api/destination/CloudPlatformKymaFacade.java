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
package com.sap.xsk.api.destination;

import com.sap.cloud.sdk.cloudplatform.CloudPlatform;
import com.sap.cloud.sdk.cloudplatform.CloudPlatformFacade;
import io.vavr.control.Try;
import javax.annotation.Nonnull;

public class CloudPlatformKymaFacade implements CloudPlatformFacade {

  private Try<CloudPlatform> cloudPlatform;

  @Nonnull
  public Try<CloudPlatform> tryGetCloudPlatform() {
    if (this.cloudPlatform == null) {
      this.cloudPlatform = Try.of(CloudPlatformKyma::new);
    }

    return this.cloudPlatform;
  }
}
