package com.randomnoun.maven.doxia.module.html;

/* Licensed to the Apache Software Foundation (ASF) under one */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import org.apache.maven.doxia.module.xhtml.XhtmlParser;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.sink.Sink;

import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.XMLWriter;
import org.codehaus.plexus.component.annotations.Component;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
* Implementation of {@link org.apache.maven.doxia.parser.Parser} for HTML documents.
* <p/>
* Defers parsing to the tagsoup library.
*
* @author knoxg
*/
@Component( role = Parser.class, hint = "html" )
public class HtmlParser
   extends XhtmlParser
{

   /**
    * The role hint for the {@link HtmlParser} Plexus component.
    */
   public static final String ROLE_HINT = "html";

   /**
    * {@inheritDoc}
    */
   @Override
   public void parse( Reader source, Sink sink )
       throws ParseException
   {
       try
       {
    	   InputStream is = new ReaderInputStream(source);

    	   /* false parameter below = parse as XML */
           String cleanHtml = getCleanXml(is, false); // let's just go straight to XML then 
           super.parse( new StringReader( cleanHtml ), sink );
       }
       catch ( SAXException e )
       {
           throw new ParseException( "Failed reading HTML source document", e );
       }
   }
   
	/** Clean a HTML inputStream through the tagsoup filter. The returned string is guaranteed to be 
	 * well-formed XML (and can therefore be used by other tools that expect valid XML). 
	 * 
	 * @param is input XML stream
	 * @param isHtml if true, uses the HTML schema, omits the XML declaration, and uses the html method
	 * 
	 * @throws SAXException if the tagsoup library could not parse the input string
	 * @throws IllegalStateException if an error occurred reading from a string (should never occur)
	 */ 
	public static String getCleanXml(InputStream inputStream, boolean isHtml) throws SAXException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputSource is = new InputSource();
			is.setByteStream(inputStream); // could use raw inputstream here later

			XMLReader xmlReader = new org.ccil.cowan.tagsoup.Parser();
			Writer w = new OutputStreamWriter(baos);
			XMLWriter tagsoupXMLWriter = new XMLWriter(w);
			tagsoupXMLWriter.setOutputProperty(XMLWriter.OMIT_XML_DECLARATION, "yes");
			if (isHtml) {
				HTMLSchema theSchema = new HTMLSchema();
				xmlReader.setProperty(org.ccil.cowan.tagsoup.Parser.schemaProperty, theSchema);
	
				tagsoupXMLWriter.setOutputProperty(XMLWriter.METHOD, "html");
				tagsoupXMLWriter.setPrefix(theSchema.getURI(), "");
			}
			
			xmlReader.setContentHandler(tagsoupXMLWriter);
			xmlReader.parse(is);
			return baos.toString();
		} catch (IOException ioe) {
			throw (IllegalStateException) new IllegalStateException("IO Exception reading from string").initCause(ioe);		
		}
	}

}

