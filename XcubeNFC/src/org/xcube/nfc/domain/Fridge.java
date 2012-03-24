package org.xcube.nfc.domain;

/**
 * A collection of items with quantities that represents all the food currently uneaten
 * @author dns
 *
 */
public class Fridge extends ItemsWithQuantities {

	/**
	 * Add all the items in a basket to the fridge
	 * @param basket
	 */
	public void addBasket(Basket basket) {
		for (ItemWithQuantity i : basket.getItems()) {
			addItem(i);
		}
	}
}
