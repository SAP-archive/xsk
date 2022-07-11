# XSK - Integration Tests UI
*Short guide on how to Run & Debug the Selenium tests in XSK* \

**Notice:**
If you make changes to **XSK** that, for example change the content in migrated files or their names/extensions, \
you might have to change the test's expected content. \
The expected project content after the test executes a migration is located [here](https://github.com/SAP/xsk/tree/main/integration-tests/ui/src/test/resources/migration/MIGR_TOOLS). \
You can modify/add/delete files here and it would change what the test is validating as content after a successful migration.

## 1. Run & Debug Locally
- **Prerequisites:** \
  Running XSK with working connection to a Hana2 instance. \
  Firefox installed. \
  Chrome installed.

- **Open the module** \
  In **Intelij** click on **File > Open** and navigate to your local **XSK** project directory, \
  Select the **xsk-integration-tests-parent** module and. \
  Open it in a New Window (*as if it were a seperate project*).

- **Create a Debug Configuration** \
  Click on **Run > Edit Configurations** \
  Add a new **JUnit** configuration called **'Selenium'**:
    - In the module dropdown select the **integration-tests-ui** module.
    - For test class select the **MigrationITest** class
    - In the environment variables field set the following env vars:
      ```
      ITESTS_SELENIUM_MODE=headed;
      ITESTS_SELENIUM_NEO_REGION=<*your credentials here*/>;  
      ITESTS_SELENIUM_NEO_SUBACCOUNT=<*your credentials here*/>;  
      ITESTS_SELENIUM_NEO_USERNAME=<*your credentials here*/>;  
      ITESTS_SELENIUM_NEO_PASSWORD=<*your credentials here*/>;  
      ITESTS_SELENIUM_HANA_DB_SCHEMA=<*your credentials here*/>;  
      ITESTS_SELENIUM_HANA_DB_USERNAME=<*your credentials here*/>;  
      ITESTS_SELENIUM_HANA_DB_PASSWORD=<*your credentials here*/>;  
      
      // Replace '<your credentials here>' with the ones you input during the migration steps.
      ```
- **Run the tests**
    - :warning: Before running the tests you must have a running XSK build on your machine, which should visible on localhost.\
      (*This is what the tests will execute against*)
    - Press **Debug** (ctrl + D) with the **'Selenium' Configuration** selected and wait for a Web Browser to launch and start automatically navigating through the migration steps.

## 2. Run & Debug in GitAction
To run the tests in a **GitHub Action** and verify your changes are working correctly,
you can do it by temporarily modifying the **PR** or **Nightly GitHub Workflow** in your current branch.

By replacing [this](https://github.com/SAP/xsk/blob/631d49023ad0c2705452307484b8ba117270e77c/.github/workflows/nightly.yml#L3)
```
on:
  schedule:
    - cron: "0 22 * * *"
```
with
```
on:
  push:
    branches: [ <your current branch name here> ]
```
then **commiting & pushing** to the **XSK** GitHub repo, \
you can initiate a run of the nightly for your current branch. \
This will run the Selenium and other hdb integration tests.
You can also view logs and failures from **GitHub** in **XSK** Project's [Actions Tab](https://github.com/SAP/xsk/actions).
> :warning: Make sure to revert this change before merging a pull request.

## 3. Commandline
As with any JUnit tests you can also run these with `mvn clean test` from inside the **integration-tests-parent** module, but you must also specify the `environment variables from Step 1` and add the `-Pitests` profile