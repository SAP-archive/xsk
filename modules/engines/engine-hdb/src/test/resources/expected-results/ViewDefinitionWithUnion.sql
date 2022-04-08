VIEW "DBADMIN"."gstr2::ViewDefinitionWithUnion.employees_view_with_union" AS SELECT "gstr2::ViewDefinitionWithUnion.employees"."ID" as "EmployeeId",
          "gstr2::ViewDefinitionWithUnion.employees"."NAME" as "EmployeeName",
          "ER"."TYPE" as "EmployeeRoleType",
          "ES"."AMOUNT" as "EmployeeSalary" FROM "gstr2::ViewDefinitionWithUnion.employees" join "gstr2::ViewDefinitionWithUnion.employee_roles" AS "ER" on "ER"."ID" = "gstr2::ViewDefinitionWithUnion.employees"."ID" join "gstr2::ViewDefinitionWithUnion.employee_salaries" AS "ES" on "ES"."ID" = "gstr2::ViewDefinitionWithUnion.employees"."ID"  WHERE "gstr2::ViewDefinitionWithUnion.employees"."NAME" = 'John' UNION SELECT 0 as "EmployeeId",
          'Ben' as "EmployeeName",
          'Developer' as "EmployeeRoleType",
          '2200' as "EmployeeSalary" FROM "DUMMY"