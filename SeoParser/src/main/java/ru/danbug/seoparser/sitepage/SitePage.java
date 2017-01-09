/**
 * 
 */
package ru.danbug.seoparser.sitepage;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Admin
 * 
 *
 */

import lombok.Getter;
import lombok.Setter;
import ru.danbug.seoparser.hosturl.HostURL;
import ru.danbug.seoparser.link.extLink;
import ru.danbug.seoparser.link.intLink;

public class SitePage {
	@Getter @Setter private HostURL pageUrl;
	@Getter @Setter private String htmlText;
	@Getter @Setter private Document jsoupDoc;
	@Getter @Setter private LinkedList<intLink> intLinks;
	@Getter @Setter private LinkedList<extLink> extLinks;

	/**
	 * Создание объекта Страница. 
	 */
	public SitePage(HostURL url) {
		setPageUrl(url);
		getSitePage();
		fillLinksLists();
	}
	
	/**
	 * Скачиваем страницу.
	 * Зaполняем textHTML и jsoupDoc.
	 */
	private void getSitePage(){
		getPageUrl().ge
		Document doc = null;
		try {
			doc = Jsoup.parse(getPageUrl().makeURL(), 5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setJsoupDoc(doc);
		setHtmlText(doc.html());
	}

	private void fillLinksLists(){
		Elements links = jsoupDoc.select("a[href!=''][href!='#']");	
		
		for (Element element : links) {
			String attrHref = element.attr("href");
//			if (LinkFunc.isPageURL(attrHref)){
//				Link link = new Link(attrHref);
//				if(!linkMap.containsKey(attrHref))
//					linkMap.put(attrHref, link);
//			}
		}
	}
		
	
}
