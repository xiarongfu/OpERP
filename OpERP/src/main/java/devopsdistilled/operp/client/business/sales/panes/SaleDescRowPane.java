package devopsdistilled.operp.client.business.sales.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import devopsdistilled.operp.client.abstracts.EntityOperation;
import devopsdistilled.operp.client.abstracts.EntityPane;
import devopsdistilled.operp.client.business.sales.controllers.SaleDescRowController;
import devopsdistilled.operp.client.business.sales.panes.controllers.SaleDescRowPaneController;
import devopsdistilled.operp.client.business.sales.panes.models.observers.SaleDescRowPaneModelObserver;
import devopsdistilled.operp.client.exceptions.EntityValidationException;
import devopsdistilled.operp.client.items.models.observers.ItemModelObserver;
import devopsdistilled.operp.server.data.entity.business.SaleDescRow;
import devopsdistilled.operp.server.data.entity.items.Item;

public class SaleDescRowPane
		extends
		EntityPane<SaleDescRow, SaleDescRowController, SaleDescRowPaneController>
		implements SaleDescRowPaneModelObserver, ItemModelObserver {

	private final JPanel pane;
	private final JTextField priceField;
	private final JTextField quantityField;
	private final JTextField amountField;
	private final JComboBox<Item> itemCombo;

	public SaleDescRowPane() {
		pane = new JPanel();
		pane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));

		JLabel lblItem = new JLabel("Item");
		pane.add(lblItem, "cell 0 0,alignx trailing");

		itemCombo = new JComboBox<>();
		itemCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					getController().getModel().getEntity()
							.setItem((Item) e.getItem());
				}
			}
		});
		pane.add(itemCombo, "cell 1 0,growx");

		JLabel lblPrice = new JLabel("Price");
		pane.add(lblPrice, "cell 0 1,alignx trailing");

		priceField = new JTextField();
		priceField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					Double price = Double.parseDouble(priceField.getText()
							.trim());
					getController().getModel().getEntity().setRate(price);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getPane(),
							"Price Field must be numeric value");
					priceField.setText("0");
					getController().getModel().getEntity().setRate(0.0);
				}
			}
		});
		pane.add(priceField, "cell 1 1,growx");
		priceField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		pane.add(lblQuantity, "cell 0 2,alignx trailing");

		quantityField = new JTextField();
		quantityField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					Long quantity = Long.parseLong(quantityField.getText()
							.trim());
					getController().getModel().getEntity()
							.setQuantity(quantity);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getPane(),
							"Quantity Field must be numeric value");

					quantityField.setText("0");
					getController().getModel().getEntity().setQuantity(0L);
				}
			}
		});
		pane.add(quantityField, "cell 1 2,growx");
		quantityField.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		pane.add(lblAmount, "cell 0 3,alignx trailing");

		amountField = new JTextField();
		amountField.setEditable(false);
		pane.add(amountField, "cell 1 3,growx");
		amountField.setColumns(10);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		pane.add(btnReset, "flowx,cell 1 4");

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getController().validate();

					
					
				} catch (EntityValidationException e1) {
					JOptionPane.showMessageDialog(getPane(), e1.getMessage());
				}
			}
		});
		pane.add(btnOk, "cell 1 4");
	}

	@Override
	public void resetComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	public SaleDescRowController getEntityController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JComponent getPane() {
		return pane;
	}

	@Override
	public void updateEntity(SaleDescRow saleDescRow,
			EntityOperation entityOperation) {

		if (EntityOperation.Create == entityOperation) {

		} else if (EntityOperation.Edit == entityOperation) {

		}
	}

	@Override
	public void updateItems(List<Item> items) {
		Item prevSelected = (Item) itemCombo.getSelectedItem();
		itemCombo.removeAllItems();

		for (Item item : items) {
			itemCombo.addItem(item);
			if (prevSelected != null)
				if (prevSelected.compareTo(item) == 0)
					itemCombo.setSelectedItem(item);
		}
	}

}
