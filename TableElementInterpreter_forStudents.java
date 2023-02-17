/*-------------------------------------------------------------------------*
 *---									---*
 *---		TableElementInterpreter.java				---*
 *---									---*
 *---	    This file defines a class that parses scientific values	---*
 *---	from sites like "https://en.wikipedia.org/wiki/Mercury_(planet)"---*
 *---	into their values, units and error bars, and prints them with	---*
 *---	their associated attributes.					---*
 *---									---*
 *---	----	----	----	----	----	----	----	----	---*
 *---									---*
 *---	Version 1a		2022 February 6		Joseph Phillips	---*
 *---									---*
 *-------------------------------------------------------------------------*/


import java.io.*;	// PrintStream, IOException, etc.

import java.util.*;
import java.util.regex.*;  

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


class	TableElementInterpreter
{
  //  I.  Constants:
  //  PURPOSE:  To tell the URL to analyze.
  final static
  String	DEFAULT_URL_STR	= "https://en.wikipedia.org/wiki/"	+
				  "Mercury_(planet)";

  //  PURPOSE:  To
  final public static
  String	DEFAULT_CONTENT_ID_STR		= "content";

  //  PURPOSE:  To represent the +/- sign.
  final public static
  char		PLUS_MINUS_CHAR			= '\u00B1';

  //  PURPOSE:  To represent the approximately equals sign.
  final public static
  char		APPROXIMATELY_EQUALS_CHAR	= '\u2248';
  
  //  PURPOSE:  To represent the degree symbol.
  final public static
  char		DEGREES_CHAR			= '\u00B0';
  
  //  PURPOSE:  To represent the minutes symbol.
  final public static
  char		MINUTES_CHAR			= '′';
  
  //  PURPOSE:  To represent the begin bracket
  final public static
  char		BEGIN_BRACKET_CHAR		= '\u005B';


  //  II.  Variables:
  //  PURPOSE:  To hold the parsing of the URL to parse.
  private
  Document	document_	= null;

  //  PURPOSE:  To 
  private
  String	contentIdStr_	= DEFAULT_CONTENT_ID_STR;

  //  PURPOSE:  To hold the default subject.
  private
  String	defaultSubject_	= "";

  //  III.  Protected methods:
  //  PURPOSE:  To return an AnnotatedNumber instance corresponding to the
  //	value, units and error bars of 'tableElement'.
  protected
  AnnotatedNumber cleanAndParseTableElement (Element	tableElement)
  {
      String text = tableElement.text().trim();
      String[] words = text.split("\\s+");
      String value = words[0];
      String unit = "AU";
      //String[] words = text.split("\\s+");

      return(new AnnotatedNumber(value, unit)) ;	// CHANGE THAT NULL
  }


  //  PURPOSE:  To handle Html table 'table' with attributes (mostly) in the
  //		leftmost column.  No return value.
  protected void findDataInTableWithLeftmostColumnHeadings (Element	table)
  {
    //  YOUR CODE HERE
      Elements	rowsTr	= table.getElementsByTag("tr");
      for  (Element row : rowsTr)
      {
          Elements	columns	= row.getElementsByTag("th");
          for  (Element tableElement : columns)
          {
              System.out.print(tableElement.text() + " is ");
              cleanAndParseTableElement(tableElement);
          }
          columns	= row.getElementsByTag("td");
          for  (Element tableElement2 : columns)
          {
              System.out.println(tableElement2.text());
              cleanAndParseTableElement(tableElement2);
          }
      }
  }


  //  PURPOSE:  To find and parse the table whose class name is "infobox".
  //	No parameters.  No return value.
  protected void findAndParseTable()
  {
    //  YOUR CODE HERE
      Elements tags = getDocument().getElementsByTag("table");
      for  (Element tables : tags){
          if( tables.hasClass("infobox")){
              findDataInTableWithLeftmostColumnHeadings(tables);
          }
      }
  }

  //  IV.  Constructor(s), factory(s), etc.
  //  PURPOSE:  To initialize 'this'.  No parameters.  No return value.
  public
  TableElementInterpreter	(String		url,
  				 String		contentIdStr
				)
  {
    //  I.  Application validity check:

    //  II.  Initialize member vars:
    //  II.A.  Initialize 'document_':
    try
    {
      defaultSubject_	= url.substring(url.lastIndexOf('/')+1);
      document_		= Jsoup.connect(url).get();
    }
    catch  (Exception error)
    {
      System.err.println(error.toString());
      System.exit(1);
    }

    //  II.B.  Initialize 'contentIdStr':
    contentIdStr_	= contentIdStr;

  }

  //  V.  Accessor(s):
  //  PURPOSE:  To return the parsing of the URL to parse.  No parameters.
  protected
  Document	getDocument	()
  				{ return(document_); }


  //  PURPOSE:  To 
  protected
  String	getContentIdStr	()
  				{ return(contentIdStr_); }

  //  PURPOSE:  To return the default subject.
  protected
  String	getDefaultSubject
				()
				{ return(defaultSubject_); }

  //  VI.  Mutator(s):

  //  VII.  Method(s) that do main and misc. work of class:
  //  PURPOSE:
  public static
  void		main		(String	args[]
  				)
  {
    String	urlStr		= (args.length == 0)
    				  ? DEFAULT_URL_STR
				  : args[0];
    String	contentStr	= DEFAULT_CONTENT_ID_STR;
    TableElementInterpreter
		toAnalyze	= new TableElementInterpreter
						(urlStr,
						 contentStr
						);

    toAnalyze.findAndParseTable();
  }
}
