package com.digitalnatura.gdocs;


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GdocsActivity extends Activity {
	
    private Button button1;
    private Account account;
    
    private static final String tag = "dosgoogledocsactivity";
    String token11;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

AccountManager.get(GdocsActivity.this).getAuthTokenByFeatures("com.google","wise", null, 
GdocsActivity.this, null, null, doneCallback, null);			                
            }		
        });

    }

    private AccountManagerCallback<Bundle> doneCallback = new AccountManagerCallback<Bundle>() {
        public void run(AccountManagerFuture<Bundle> arg0) {

            Bundle b;
            try {
                b = arg0.getResult();

                String name = b.getString(AccountManager.KEY_ACCOUNT_NAME);
                String type = b.getString(AccountManager.KEY_ACCOUNT_TYPE);
                 token11 = b.getString(AccountManager.KEY_AUTHTOKEN);

                account = new Account(name, type);

// este é o método que chamamos:
                colleSpreadSheets();
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    };
	private String title;

	protected void colleSpreadSheets() throws JSONException {

		   String retornojedi = null;
//creamos obxecto AuxSpreadsheet que 
//leva implementado o método xestorFollas e os seus correspondentes parámetros
		     AuxSpreadsheet xestorFollas = new AuxSpreadsheet();

     	    try {
//"dsdf" é o nome da folla que busco, se non poño nada apareceran todas
     	    	retornojedi= xestorFollas.xestorFollas(xestorFollas, "", token11, null);
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			JSONObject base = new JSONObject(retornojedi);
////			JSONArray data = base.getJSONArray("category");
			JSONObject cona = base.getJSONObject("feed");
//			JSONObject category = cona.getJSONObject("title");
////			int resultados = Integer.parseInt(category.getString("$t"));
//			
			JSONArray menuitemArray = cona.getJSONArray("entry");
//			
////			int resultados = menuitemArray.length();
			String titulosdefollas[] = new String[menuitemArray.length()];
//			 
//			
//			
			for (int i = 0; i < menuitemArray.length(); i++) {
//				
				JSONObject entrada =	menuitemArray.getJSONObject(i);
				JSONObject titulo = entrada.getJSONObject("gsx$personajes");
				title = titulo.getString("$t");
				titulosdefollas[i] = title;
//				
//				
////				System.out.println(menuitemArray.getJSONObject(i)
////						.getString("value").toString());
////				System.out.println(menuitemArray.getJSONObject(i).getString(
////						"onclick").toString());
			}
			
			
			


			
			
//imprimimos o resultado na pantalla:
			
			
     	  TextView mResultado = (TextView)findViewById(R.id.texto);   
     	  mResultado.setText("Hello "+ titulosdefollas[1]);	
	}
}