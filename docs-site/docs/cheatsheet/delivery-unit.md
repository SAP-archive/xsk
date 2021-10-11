# Create Delivery Units

A delivery unit (DU) is a group of transportable packages that contain objects used for content delivery. You can use the SAP HANA Application Lifecycle Management to create a DU for your application content or your software component.


!!! note "SAP Help Portal"

    For more information, see [Create a Delivery Unit](https://help.sap.com/viewer/a4d43a319ecf464e9d838454a6bdb9ad/2.0.03/en-US/d5ca92aba6b4445aba17ca3f8d671217.html).

## Prerequisites

!!! info "Prerequisites"
    To create a delivery unit with the SAP HANA Application Lifecycle Management, you must ensure the following prerequisites are met:
    
    - You have access to an SAP HANA system.
    - You have the privileges granted by a role based on the SAP HANA `sap.hana.xs.lm.roles::Administrator` user role template.
    - The `vendor` ID is defined for the DU; the vendor ID defines the repository namespace in which the new DU resides.

## Context

You use a DU to transport the design-time objects that are stored in the SAP HANA repository between two systems, for example, from a development system to a consolidation system. To create a new delivery unit using the SAP HANA application lifecycle management, perform the following steps.

## Steps

- Open SAP HANA Application Lifecycle Management.

    - SAP HANA Application Lifecycle Management is available on the SAP HANA XS Web server at the following URL: **`http://<WebServerHost>:80<SAPHANAinstance>/sap/hana/XS/lm`**
    
    ![](https://user-images.githubusercontent.com/12174161/132681639-6b26dd88-f2ca-43c2-858e-ce3bef2155d3.png)

- Choose the `PRODUCTS` tab.

   1. Select the `Delivery Units` tab.
   1. Click the `Create` button.
   1. The `New Delivery Unit` dialog box appears.

       ![](https://user-images.githubusercontent.com/12174161/132681635-408a0e35-7dac-4945-8fe3-9ee23c6552ec.png)

   1. Enter details for the new DU.

       ![](https://user-images.githubusercontent.com/12174161/132681631-e5f91d98-f587-46a3-a32e-ded916e97ae3.png)

   1. When entering details, note the following points:

       - `Name` - the field is mandatory and you must follow strict naming conventions, for example, use capital letters.
       - `Vendor` - this field is mandatory. However, you cannot enter a vendor here, the box is populated by the value you enter when defining the vendor in the `SETTINGS` tab.

           ![](https://user-images.githubusercontent.com/12174161/132681626-68eef9d7-1c16-47da-b821-9a67de6010d6.png)

       - `Version` - version numbers must take the form `#.#.#`, for example, `1.0.5`, where:
   
           - `1` - the DU version number.
           - `0` - the support package version (if required).
           - `5` - the patch version (if required).

           !!! note
    
               The numbers you enter here refer to the application component that you are developing. The numbers do not refer to the patch or service-pack level deployed on the SAP HANA server.

   1. Choose `Create` - the new delivery unit is added to the SAP HANA repository in the namespace specified by the vendor ID and the application path.
   1. Check the status bar at the bottom of the browser window for error messages.
   1. Choose the message link to display the message text.
