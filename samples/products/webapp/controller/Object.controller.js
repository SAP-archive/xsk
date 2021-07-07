sap.ui.define([
	"./BaseController",
	"sap/ui/model/json/JSONModel",
	"sap/ui/core/routing/History",
	"../model/formatter",
    "sap/m/MessageBox",
    "sap/m/MessageToast"
], function(BaseController, JSONModel, History, formatter, MessageBox, MessageToast) {
	"use strict";

	return BaseController.extend("products.demo.app.controller.Object", {

		formatter: formatter,

		/* =========================================================== */
		/* lifecycle methods                                           */
		/* =========================================================== */

		/**
		 * Called when the worklist controller is instantiated.
		 * @public
		 */
		onInit: function() {
			// Model used to manipulate control states. The chosen values make sure,
			// detail page is busy indication immediately so there is no break in
			// between the busy indication for loading the view's meta data
			var iOriginalBusyDelay,
				oViewModel = new JSONModel({
					busy: true,
					delay: 0,
					customerEditMode: false
				});

			this.getRouter().getRoute("object").attachPatternMatched(this._onObjectMatched, this);

			// Store original busy indicator delay, so it can be restored later on
			iOriginalBusyDelay = this.getView().getBusyIndicatorDelay();
			this.setModel(oViewModel, "objectView");
			this.getOwnerComponent().getModel().metadataLoaded().then(function() {
				// Restore original busy indicator delay for the object view
				oViewModel.setProperty("/delay", iOriginalBusyDelay);
			});
		},

		/* =========================================================== */
		/* event handlers                                              */
		/* =========================================================== */

		/**
		 * Event handler  for navigating back.
		 * It there is a history entry we go one step back in the browser history
		 * If not, it will replace the current entry of the browser history with the worklist route.
		 * @public
		 */
		onNavBack: function() {
			var sPreviousHash = History.getInstance().getPreviousHash();

			if (sPreviousHash !== undefined) {
				history.go(-1);
			} else {
				this.getRouter().navTo("worklist", {}, true);
			}
		},

		/**
		 * Event handler when pressed button for open the product dialog
		 *
		 * @public
		 */
		onOpenProductDialogPress: function(oEvent) {

			var oModel = this.getModel();
			var sOrderId = oEvent.getSource().getBindingContext().getProperty("Id");

			var oView = this.getView();
			if (!this._ProductDialog) {
				this._ProductDialog = sap.ui.xmlfragment(oView.getId(),
					"products.demo.app.view.fragments.ProductDialog", this);
				oView.addDependent(this._ProductDialog);
			}

			var oEntryCtx = oModel.createEntry("/Items", {
				properties: {
					ItemId: "0",
					OrderId: sOrderId
				}
			});

			this._ProductDialog.setBindingContext(oEntryCtx);
			this._ProductDialog.setModel(oModel);
			this._ProductDialog.open();
		},

		/**
		 * Event handler when pressed button for close product dialog
		 *
		 * @public
		 */
		onCloseProductPress: function() {

			var oModel = this.getModel();
			var oCtx = this._ProductDialog.getBindingContext();
			oModel.deleteCreatedEntry(oCtx);

			oModel.resetChanges();
			this._ProductDialog.close();
		},

		/**
		 * Event handler when pressed button on create product
		 *
		 * @public
		 */
		onCreateProductPress: function() {

			var oModel = this.getModel();
			oModel.submitChanges();
			this._ProductDialog.close();
		},

		/**
		 * Event handler when pressed delete button into products table
		 *
		 * @param {sap.ui.base.Event} oEvent the table selectionChange event
		 *
		 * @public
		 */
		onDeleteProduct: function(oEvent) {

			var sPath = oEvent.getParameter('listItem').getBindingContextPath();
			this.deleteObject(sPath);

		},

		/**
		 * Edit customer info button press event handler.
		 *
		 * @public
		 */
		onEditCustomerPress: function() {

			this.getModel("objectView").setProperty("/customerEditMode", true);
		},

		/**
		 * Save customer info button press event handler.
		 *
		 * @public
		 */
		onUpdateCustomerInfoPress: function() {

			this.getModel("objectView").setProperty("/customerEditMode", false);
			var oModel = this.getModel();

			var oCtx = this.getView().byId("simpleFormId").getBindingContext();
			var sPath = oCtx.getPath();
			var oData = this._formatOrderObject(oCtx.getObject());

			oModel.update(sPath, oData, {
				success: function() {
					MessageToast.show("Object has been updated!");
				},
				error: function() {
					MessageBox.error("Error!");
				}
			});
		},

		/**
		 * Cancel customer info button press event handler.
		 *
		 * @public
		 */
		onCloseUpdateCustomerInfoPress: function() {

			this.getModel("objectView").setProperty("/customerEditMode", false);
			var oModel = this.getModel();
			oModel.resetChanges();
		},

		/* =========================================================== */
		/* internal methods                                            */
		/* =========================================================== */

		/**
		 * Binds the view to the object path.
		 * @function
		 * @param {sap.ui.base.Event} oEvent pattern match event in route 'object'
		 * @private
		 */
		_onObjectMatched: function(oEvent) {
			var sObjectId = oEvent.getParameter("arguments").objectId;
			this.getModel().metadataLoaded().then(function() {
				var sObjectPath = this.getModel().createKey("Orders", {
					Id: sObjectId
				});
				this._bindView("/" + sObjectPath);
			}.bind(this));
		},

		_formatOrderObject: function(oData) {
			return {
				Id: oData.Id,
				CreatedAt: oData.CreatedAt,
				CreatedBy: oData.CreatedBy,
				CustomerName: oData.CustomerName,
				CustomerSurname: oData.CustomerSurname,
				Description: oData.Description,
				Address: oData.Address,
				Email: oData.Email,
				Phone: oData.Phone,
				Status: oData.Status
			};
		},

		/**
		 * Binds the view to the object path.
		 * @function
		 * @param {string} sObjectPath path to the object to be bound
		 * @private
		 */
		_bindView: function(sObjectPath) {
			var oViewModel = this.getModel("objectView"),
				oDataModel = this.getModel();

			this.getView().bindElement({
				path: sObjectPath,
				events: {
					dataRequested: function() {
						oDataModel.metadataLoaded().then(function() {
							// Busy indicator on view should only be set if metadata is loaded,
							// otherwise there may be two busy indications next to each other on the
							// screen. This happens because route matched handler already calls '_bindView'
							// while metadata is loaded.
							oViewModel.setProperty("/busy", true);
						});
					},
					dataReceived: function() {
						oViewModel.setProperty("/busy", false);
					}
				}
			});
		}

	});

});