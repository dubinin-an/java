/**
 * 
 */
package ru.danbug.seoparser.link;

public class extLink extends Link {
	
	/**
	 * @param l - строка полный URL c прото и хостом(доменом)
	 */
	public extLink(String l) {
		super(l);
		setLocal(false);
	}
	
	/**
	 * @param link - строка URL 
	 * @param proto - протокол
	 * @param host - хост(домен)
	 */
	public extLink(String link, String proto, String host) {
		super(link, proto, host);
		setLocal(false);
	}
}