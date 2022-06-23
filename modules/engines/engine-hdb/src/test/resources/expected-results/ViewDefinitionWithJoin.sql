VIEW "DBADMIN"."gstr2::ViewDefinitionWithJoin.employees_view_with_join" AS SELECT "gstr2::ViewDefinitionWithJoin.employees"."ID" as "EmployeeId",
          "gstr2::ViewDefinitionWithJoin.employees"."NAME" as "EmployeeName",
          "ER"."TYPE" as "EmployeeRoleName",
          "ES"."AMOUNT" as "EmployeeSalary" FROM "gstr2::ViewDefinitionWithJoin.employees" join "gstr2::ViewDefinitionWithJoin.employee_roles" AS "ER" on "ER"."ID" = "gstr2::ViewDefinitionWithJoin.employees"."ID" join "gstr2::ViewDefinitionWithJoin.employee_salaries" AS "ES" on "ES"."ID" = "gstr2::ViewDefinitionWithJoin.employees"."ID"