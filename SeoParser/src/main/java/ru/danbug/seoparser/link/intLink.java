/**
 * 
 */
package ru.danbug.seoparser.link;

import lombok.Getter;
import lombok.Setter;

public class intLink extends Link {
	@Getter @Setter private boolean visited;

	/**
	 * @param l - строка полный URL c прото и хостом(доменом)
	 */
	public intLink(String l) {
		super(l);
		setLocal(true);
		setVisited(false);
	}
	/**
	 * @param link - строка URL 
	 * @param proto - протокол
	 * @param host - хост(домен)
	 */
	public intLink(String link, String proto, String host) {
		super(link, proto, host);
		setLocal(true);
		setVisited(false);
	}


}