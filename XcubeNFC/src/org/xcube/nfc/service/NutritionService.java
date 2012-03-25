package org.xcube.nfc.service;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.NutritionTotals;

public interface NutritionService {
	void addItem(Item item);
	
	NutritionTotals getTotal();
}
