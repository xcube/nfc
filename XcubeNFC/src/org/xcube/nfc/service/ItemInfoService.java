package org.xcube.nfc.service;

import org.xcube.nfc.domain.ItemInfo;

/**
 * Service for retrieving more info about item(s)
 * 
 * @author pete
 */
public interface ItemInfoService {

	/**
	 * @param upc unique product code
	 * @return item domain object
	 */
	ItemInfo getItemInfo(String upc);
}
