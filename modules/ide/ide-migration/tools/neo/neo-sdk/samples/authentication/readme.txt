############################################################################
# Welcome to the SAP BTP SDK for the Neo environment Authentication Sample #
############################################################################

1. Prerequisites for execution on your local server from within Eclipse

In order to run the sample from within your Eclipse installation you will have to create users for the local server.
You can do that by double-clicking on your local server in the Servers view and then switching to the Users tab.
There please add a new user with the user id john and the password doe.

This is done automatically by the Maven build. It uses the json files located here and copies them (check out pom.xml)
to the local server that it creates on-the-fly during the build. By this the integration tests find the sample user.
For the local server that you create yourself in Eclipse, you have to do it separately/conveniently in the server view.
