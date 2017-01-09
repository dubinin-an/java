package ru.danbug.seoparser.hosturl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import lombok.Getter;
import lombok.Setter;

public class HostURL {

	/**
	 * Протокол http/https
	 */
	@Getter @Setter private String proto="";
	/**
	 * Общий текст для сеттера и геттера 
	 * 
	 * -- SETTER --
	 * Текст для СЕТТЕРА
	 * @param Строка
	 * 
	 * -- GETTER --
	 * Текст для ГЕТТЕРА
	 * @return Строку домен/хост
	 */
	@Getter @Setter private String host="";
	@Getter @Setter private String path="/";
	@Getter @Setter private String orig="";
	@Getter @Setter private String param="";
	@Getter @Setter private String urlHash="";
	
	/**
	 * Test text
	 * Return  String URL 
	 */
	@Getter @Setter private String url="";
	
	/**
	 * Создает HostURL Из строки 
	 */
	public HostURL(String link){
		regexpURL(link);
		setUrl(getProto()+"://"+getHost()+ getPath()+getParam()+getUrlHash());		
	}
	
	/**
	 * Создает HostURL Из:
	 * String URL, String PROTO, String HOST(DOMAIN) 
	 */
	public HostURL(String link, String proto, String host){
		setProto(proto);
		setHost(host);
		regexpURL(link);
		setUrl(getProto()+"://"+getHost()+ getPath()+getParam()+getUrlHash());
		
	}
	/**
	 * Возращает оригинальный JAVA URL из нашего маленького HostURL
	 * @return Объект URL - для организации загрузок по HTTPS
	 */
	public URL makeURL(){
		URL u = null;
		try {
			u = new URL(getUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return u;
	}

	private boolean regexpURL(String link){
		setOrig(link);
		if (link.startsWith("//")) {
			link = "http:".concat(link);
		}
		if (link.startsWith("#")) {
			setUrlHash(link);
		}
		
		Pattern p;
		Matcher m = null;
				
		boolean foundMatch = false;
		try {
		 p = Pattern.compile(
		  "^\n" +
		  "(# Scheme\n" +
		  " ([a-z][a-z0-9+\\-.]*):\n" +
		  " (# Authority & path\n" +
		  "  //\n" +
		  "  ([a-z0-9\\-._~%!$&'()*+,;=]+@)?              # User\n" +
		  "  ([a-z0-9\\-._~%]+                            # Named host\n" +
		  "  |       \\[[a-f0-9:.]+\\]                            # IPv6 host\n" +
		  "  |       \\[v[a-f0-9][a-z0-9\\-._~%!$&'()*+,;=:]+\\])  # IPvFuture host\n" +
		  "  (:[0-9]+)?                                  # Port\n" +
		  "  ((/[a-z0-9\\-._~%!$&'()*+,;=:@]+)*/?)        # Path\n" +
		  " |# Path without authority\n" +
		  "  (/?[a-z0-9\\-._~%!$&'()*+,;=:@]+(/[a-z0-9\\-._~%!$&'()*+,;=:@]+)*/?)?\n" +
		  " )\n" +
		  "|# Relative URL (no scheme or authority)\n" +
		  " ([a-z0-9\\-._~%!$&'()*+,;=@]+(/[a-z0-9\\-._~%!$&'()*+,;=:@]+)*/?  # Relative path\n" +
		  " |(/[a-z0-9\\-._~%!$&'()*+,;=:@]+)+/?)                                   # Absolute path\n" +
		  ")\n" +
		  "# Query\n" +
		  "(\\?[a-z0-9\\-._~%!$&'()*+,;=:@/?]*)?\n" +
		  "# Fragment\n" +
		  "(\\#[a-z0-9\\-._~%!$&'()*+,;=:@/?]*)?\n" +
		  "$", 
		  Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.MULTILINE | Pattern.COMMENTS);
		 m = p.matcher(link);
		 foundMatch = m.find();
		} catch (PatternSyntaxException ex) {
		 // Syntax error in the regular expression
			ex.printStackTrace();
		}

		if (foundMatch) {
			for(int i=1;i<=m.groupCount();i++){
//					System.out.println(i+". "+ m.group(i));
				if (m.group(i) != null){
					switch (i) {
					case 2:
						setProto(m.group(i).toLowerCase());
						break;
					case 5:
						setHost(m.group(i).toLowerCase());
						break;
					case 7:
						if(m.group(i).equals("")){
							setPath("/");
						}else{
							setPath(m.group(i));
						}
						break;
					case 11:
						setPath(m.group(i));
						break;
					case 14:
						setParam(m.group(i));
						break;
					case 15:
						setUrlHash(m.group(i));
						break;
					}
				}
			}
		} 
		return foundMatch;
	}

	
	@Override
	public String toString() {
		return "URL [orig=" + orig +" proto=" + proto + ", host=" + host + ", path=" + path + ", param=" + param
				+ ", hash=" + urlHash + "]";
	}
		


}

