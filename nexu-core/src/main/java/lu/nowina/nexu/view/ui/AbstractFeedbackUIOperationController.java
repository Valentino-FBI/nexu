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
package lu.nowina.nexu.view.ui;

import java.util.Arrays;

import lu.nowina.nexu.NexuLauncher;
import lu.nowina.nexu.api.EnvironmentInfo;
import lu.nowina.nexu.api.Feedback;
import lu.nowina.nexu.generic.FeedbackSender;
import lu.nowina.nexu.generic.HttpDataSender;
import lu.nowina.nexu.view.core.AbstractUIOperationController;
import lu.nowina.nexu.view.core.UIOperationController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Convenient base class for {@link UIOperationController} whose result is a feedback that can be provided to {@link FeedbackClient}. 
 *
 * @author Jean Lepropre (jean.lepropre@nowina.lu)
 */
public abstract class AbstractFeedbackUIOperationController extends AbstractUIOperationController<Feedback> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFeedbackUIOperationController.class);
	
	private Feedback feedback;
	private String serverUrl;
	private String applicationVersion;
	
	@Override
	public final void init(Object... params) {
		try {
			feedback = (Feedback) params[0];
			serverUrl = (String) params[1];
			applicationVersion = (String) params[2];
		} catch(final ClassCastException | ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Expected parameters: Feedback, serverUrl (String) and application version (String)");
		}
		
		if((feedback == null) || (serverUrl == null) || (applicationVersion == null)) {
			throw new IllegalArgumentException("Expected parameters: Feedback, serverUrl (String) and application version (String)");
		}

		if(params.length > 3) {
			doInit(Arrays.copyOfRange(params, 3, params.length));
		} else {
			doInit((Object) null); 
		}
	}

	/**
	 * Allows subclasses to use additional parameters or perform some specific initialization.
	 * 
	 * <p>This implementation does nothing.
	 * 
	 * @param params Additional parameters of the concrete controller.
	 */
	protected void doInit(Object... params) {
		// Do nothing by contract
	}
	
	/**
	 * Sends the given feedback to the given server URL and calls {@link #signalEnd(Feedback)}.
	 */
	protected final void sendFeedback() {
		try {
			feedback.setNexuVersion(applicationVersion);
			feedback.setInfo(EnvironmentInfo.buildFromSystemProperties(System.getProperties()));
			
			FeedbackSender sender = new FeedbackSender(NexuLauncher.getConfig(), new HttpDataSender(NexuLauncher.getProxyConfigurer()));
			sender.sendFeedback(feedback);
			signalEnd(feedback);
		} catch (Exception ex) {
			LOGGER.error("Cannot send feedback", ex);
			signalEnd(feedback);
		}
	}
	
	protected Feedback getFeedback() {
		return feedback;
	}
}
