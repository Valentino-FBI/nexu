/**
 * © Nowina Solutions, 2015-2015
 *
 * Concédée sous licence EUPL, version 1.1 ou – dès leur approbation par la Commission européenne - versions ultérieures de l’EUPL (la «Licence»).
 * Vous ne pouvez utiliser la présente œuvre que conformément à la Licence.
 * Vous pouvez obtenir une copie de la Licence à l’adresse suivante:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Sauf obligation légale ou contractuelle écrite, le logiciel distribué sous la Licence est distribué «en l’état»,
 * SANS GARANTIES OU CONDITIONS QUELLES QU’ELLES SOIENT, expresses ou implicites.
 * Consultez la Licence pour les autorisations et les restrictions linguistiques spécifiques relevant de la Licence.
 */
package lu.nowina.nexu.flow.operation;

import lu.nowina.nexu.api.CardAdapter;
import lu.nowina.nexu.api.DetectedCard;
import lu.nowina.nexu.api.ScAPI;
import lu.nowina.nexu.api.TokenId;

/**
 * Possible keys in the map returned by {@link CreateTokenOperation} and {@link GetTokenOperation}.
 *
 * @author Jean Lepropre (jean.lepropre@nowina.lu)
 */
public enum TokenOperationResultKey {
	/**
	 * {@link TokenId}
	 */
	TOKEN_ID,
	
	/**
	 * {@link Boolean}
	 */
	ADVANCED_CREATION,
	
	/**
	 * {@link DetectedCard}
	 */
	SELECTED_CARD,
	
	/**
	 * {@link CardAdapter}
	 */
	SELECTED_CARD_ADAPTER,
	
	/**
	 * {@link ScAPI}
	 */
	SELECTED_API,
	
	/**
	 * {@link String}
	 */
	SELECTED_API_PARAMS;
}
