package com.digitalnatura.gdocs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import android.os.Environment;

import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Key;


public class AuxSpreadsheet {

	public String xestorFollas(AuxSpreadsheet xestorFollas, String title, final String token11, Boolean exact)  
throws IOException {
	// TODO Auto-generated method stub
	
	HttpRequestFactory wiseRequestFactory;
	HttpTransport transport = AndroidHttp.newCompatibleTransport();
	wiseRequestFactory = transport.createRequestFactory(new HttpRequestInitializer() {
        public void initialize(HttpRequest req) throws IOException {
        
            GoogleHeaders headers = new GoogleHeaders();
            headers.gdataVersion = "3";
            headers.setApplicationName("dosgoogledocs");            
            headers.setGoogleLogin(token11);
            
            
            req.setEnableGZipContent(true);
            req.setHeaders(headers);
 
        }
    });
	
	
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full?alt=json");
//	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
	 WiseUrl url = new WiseUrl("https://spreadsheets.google.com/feeds/list/tZxfWbnb54F3gYX2F8epY_A/od6/private/full?alt=json");

	 
	 url.title = title;
     url.title_exact = exact;
     
     HttpResponse response = wiseRequestFactory.buildGetRequest(url).execute();
     String hols = response.parseAsString();
	
     File newTextFile = new File(Environment.getExternalStorageDirectory()+"/"+"json.json");
     FileWriter fw = new FileWriter(newTextFile);
     fw.write(hols);
     fw.close();
     
     
	return hols;
	
}
static class WiseUrl extends GenericUrl {
    @Key String title;
    @Key("title-exact") Boolean title_exact;
    @Key String fields;
    @Key String sq;
    @Key String orderby;
    @Key Boolean reverse;

    WiseUrl(String url) {
        super(url);
    }

}

}