/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcc.wikimapia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Magdiel Ildefonso
 */
public class ReadFileXML {
//    private String link;

    private final File filepath;
    private URL url;

    public ReadFileXML() {
       try {
            this.url = new URL("http://api.wikimapia.org/?key=example&"
                    + "function=place.search&q=IFPB&lat=-6.887479&lon=-38.542893&"
                    + "format=&pack=&language=en&page=1&count=50&category=&"
                    + "categories_or=&categories_and=&distance=");
            System.out.println(this.url);
       } catch (MalformedURLException ex) {
            Logger.getLogger(ReadFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.filepath = new File(url.toString());
    }

    public ObjectSearchXML readFileReturnXMLObject() {
        ObjectSearchXML result = null;
        try {
            URLConnection urlConnection = url.openConnection();
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(url.openStream());
            doc.normalize();
            
            String title = doc.getElementsByTagName("title").item(0).getTextContent();
            String description = doc.getElementsByTagName("description").item(0).getTextContent();

            NodeList nodeListDatas = doc.getElementsByTagName("polygon");
            ArrayList<String> polygons = new ArrayList();
            for (int i = 0; i < nodeListDatas.getLength(); i++) {
                polygons.add(nodeListDatas.item(i).getTextContent());
            }
            
            result = new ObjectSearchXML();
            result.setDescription(description);
            result.setTitle(title);
            result.setPoints(polygons);
        
        } catch (IOException ex) {
            Logger.getLogger(ReadFileXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReadFileXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ReadFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

}
