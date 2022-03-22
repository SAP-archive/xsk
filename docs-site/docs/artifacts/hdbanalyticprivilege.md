---
title: HDBAnalyticPrivilege
---

HDBAnalyticPrivilege
===

## Overview
---

The information on this page will help you learn how to continue using analytic privileges in your XS Classic applications running on XSK.

## Transition path 
---

1. All classical XML-based analytic privileges need to be SQL analytic privileges.
2. The `applyPrivilegeType` attribute in the calculation views must be set to `SQL_ANALYTIC_PRIVILEGE`
3. References to `SESSION_USER` are changed to `SESSION_CONTEXT('APPLICATIONUSER')`. This is automatically done during migration.
4. Assignment of analytic privileges happens only through hdbroles. During migration, a default hdbrole is created which references the analytic privileges used in the project and is assigned to the technical XSK user.

## Reference
---

!!! note "SAP Help Portal"

    For more information, see [Analytic Privileges](.hdbanalyticprivilege)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/2d3056363053436898fcf5a3141b482f.html).