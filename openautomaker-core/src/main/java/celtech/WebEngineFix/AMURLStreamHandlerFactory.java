package celtech.WebEngineFix;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.openautomaker.environment.preference.application.NamePreference;

import jakarta.inject.Inject;

/**
 *
 * @author Ian
 */
//TODO: Refactor this whole thing to use the new HttpRequest anc client stuff
public class AMURLStreamHandlerFactory implements URLStreamHandlerFactory {

	private final NamePreference namePreference;

	@Inject
	protected AMURLStreamHandlerFactory(
			NamePreference namePreference) {

		super();

		this.namePreference = namePreference;
	}

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if (protocol.equals("http")) {
			return new AMURLHandler(namePreference);
		}
		return null;
	}

}