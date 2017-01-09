/**
 * 
 */
package ru.danbug.seoparser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ru.danbug.seoparser.hosturl.HostURL;

/**
 * @author Admin
 *
 */
public class SEOParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Document doc = null;
		try {
			doc = Jsoup.parse((new HostURL("http://docs.dron2004.ru/")).makeURL(), 5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc);
	}

}
