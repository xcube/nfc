package org.xcube.nfc.service;

import org.xcube.nfc.domain.Item;

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
    Item getItem(String upc);
}
