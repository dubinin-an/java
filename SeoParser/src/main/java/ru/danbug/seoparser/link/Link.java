/**
 * 
 */
package ru.danbug.seoparser.link;

import lombok.Getter;
import lombok.Setter;
import ru.danbug.seoparser.hosturl.HostURL;

/**
 * @author dan
 * Базовый класс для хранения ссылок с сайта
 */
public abstract class Link {
	@Getter @Setter private HostURL url;
	@Getter @Setter private int returnCode;
	@Getter @Setter private String mimeType;
	@Getter @Setter private boolean local;
	
	public Link(String l){
		setUrl(new HostURL(l));
	}
	
	public Link(String link, String proto, String host) {
		setUrl(new HostURL(link, proto, host));
	}
	
}
