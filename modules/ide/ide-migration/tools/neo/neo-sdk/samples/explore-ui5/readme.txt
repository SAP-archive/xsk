#########################################################################
# Welcome to the SAP BTP SDK for the Neo environment Explore UI5 Sample #
#########################################################################

1. Prerequisites for execution on your local server from within Eclipse

1.1 (Optional) Configure an HTTP proxy for the UI resource download from the cloud

The sample will load its UI resources from the cloud at "https://hana.ondemand.com". In case you run the sample
from behind an outgoing HTTP proxy, make sure you have configured the proxy as part of the SAP BTP local runtime
in Eclipse. To do this, double-click the local server in the Servers view, then click the "Open launch
configuration" link on the Overview tab, this opens the "Edit launch configuration properties" view. Navigate there to
the "Arguments" tab and add your proxy definition as "VM arguments" in following format:
-Dhttps.proxyHost=<your_proxy_host> -Dhttps.proxyPort=<your_proxy_port>

2. Prerequisites for execution of the Selenium web driver integration tests from Maven and within Eclipse

2.1 Install Firefox browser

The sample integration tests show you how to use Selenium web driver ("http://seleniumhq.org") to automatically test
an UI5-based application. They specifically use the Selenium Firefox web driver which is easiest to set up. As a
prerequisite you will need to install the browser beforehand from "http://www.mozilla.org".

Note: The required Selenium web driver may not work with the latest Firefox browser. If you notice issues,
you may try to update the Selenium web driver to the latest version in the parent pom.xml property selenium.test.framework.version.
