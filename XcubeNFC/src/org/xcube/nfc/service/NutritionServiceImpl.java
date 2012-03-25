package org.xcube.nfc.service;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.NutritionTotals;

public class NutritionServiceImpl implements NutritionService {

	private static NutritionTotals totals = new NutritionTotals();
	
	@Override
	public void addItem(Item item) {
		totals.addItem(item);
	}

	@Override
	public NutritionTotals getTotal() {
		return totals;
	}

}
