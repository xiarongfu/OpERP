package devopsdistilled.operp.client.business.sales.panes.controllers;

import devopsdistilled.operp.client.abstracts.EntityPaneController;
import devopsdistilled.operp.client.business.sales.panes.SalePane;
import devopsdistilled.operp.client.business.sales.panes.models.SalePaneModel;
import devopsdistilled.operp.server.data.entity.business.Sale;

public interface SalePaneController extends
		EntityPaneController<Sale, SalePaneModel, SalePane> {

}
