##################################################################
# Welcome to the SAP BTP SDK for the Neo environment Mail Sample #
##################################################################

The mail sample shows how to use the SAP BTP connectivity service APIs to send e-mail.
The sample provides a simple web UI to compose an e-mail message and send it.

1. Execution on Your Local Server

You can run the sample on a local server without any additional configuration.
The sample UI can be reached under 'http://localhost:8080/mail/'. The local mail 
sample stores e-mails as '*.msg' files on the file system under '<local-server>/work/mailservice'.

2. Prerequisites for Execution on the Cloud

2.1 Deploy the mail sample to a subaccount on SAP BTP and start it. The Eclipse internal web browser will show the web UI when the startup
has been completed.

2.2 In order to run the sample on the cloud you will have to configure a mail destination.
Technically, mail destinations are a type of connectivity destination. As with connectivity destinations that are specified
in a properties file and uploaded to the cloud, you also need to upload the mail destination configuration file for the
configuration to be effective.

2.2.1 Create a text file named 'Session' and open it in a text editor. Add the listed properties below and enter values for
the parameters 'mail.user', 'mail.password' and 'mail.smtp.host'. Save your changes.

Name=Session
Type=MAIL
mail.transport.protocol=smtp
mail.smtp.host=<enter SMTP host name>
mail.smtp.auth=true
mail.smtp.starttls.enable=true
mail.smtp.port=587
mail.user=<enter account user>
mail.password=<enter account password

2.2.2 To upload the file, use the Neo console client and enter a command in the following form:

neo put-destination --account <account_name> --application <application_name> --user <user_SDN_ID> --localpath Session

2.2.3 You need to restart the application in order for the configured mail destination to become visible

Restart the application e.g. in Eclipse by opening the "Servers" view (Window > Show View > Others > Servers).
Right-click your application and select "Restart".

3. Prerequisites for Maven Build with Cloud Integration Tests

In order for the cloud integration tests to pass, you need to provide Maven properties for the mail server/account:
mvn clean verify -P cloud-integration-tests -Dmail.host=... -Dmail.user=... -Dmail.password=... -Dmail.from.address=... -Dmail.to.address=...

Hint: To avoid retyping the above whenever you call Maven, you can of course add the properties directly to the pom.xml.
      You may like to use environment variables and set the properties in the pom.xml based on their values,
      especially when it comes to sensitive information like passwords which you should avoid to store in clear text.

The mail destination is configured automatically by the Maven build. It uses the mail destination located in 'Session'.
Required values for 'mail.user', 'mail.password' and 'mail.smtp.host' are injected from the properties above. After that
the mail destination is uploaded to the cloud using the Neo console client (check out the pom.xml).
