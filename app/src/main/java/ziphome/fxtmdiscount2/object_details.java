package ziphome.fxtmdiscount2;

import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class object_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_details);



        String valueName = getIntent().getStringExtra("valueName");
        String valueOffer = getIntent().getStringExtra("valueOffer");
        final String valueAddress = getIntent().getStringExtra("valueAddress");
        String valueWHours = getIntent().getStringExtra("valueWHours");
        final String valueGps = getIntent().getStringExtra("valueGps");

        TextView tvName   = (TextView) findViewById(R.id.tvNname);
        TextView tvOffer  = (TextView) findViewById(R.id.offerDetails);
        TextView tvAddess = (TextView) findViewById(R.id.addressDetails);
        TextView tvWHours = (TextView) findViewById(R.id.whDetails);

        Button showMap = (Button) findViewById(R.id.showMap);

        tvName.setText(valueName);
        tvOffer.setText(valueOffer);
        tvAddess.setText(valueAddress);
        tvWHours.setText(valueWHours);
        Linkify.addLinks(tvWHours, Linkify.WEB_URLS| Linkify.EMAIL_ADDRESSES| Linkify.PHONE_NUMBERS);

        /*    WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://google.com");
        */

        //  String htmlText = "<html><body>Percent test: 100% <br>";
      //  htmlText=htmlText+valueName+ "</body></html>";
       // webView.loadDataWithBaseURL(null, htmlText, "text/html", "en_US", null);

        showMap.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           // Zamenyem Null
                String valGps = valueGps;


                if ((valGps==null)||(valGps=="")) {
                    valGps=valueAddress;
                }
                else {
                    valGps = valueGps;
                }


               // String geoURI = "geo:0,0?q=москва+2-Я Пугачевская 3 к.1&z=8";
                String geoURI = "geo:0,0?q="+valGps+"&z=14";
               // String geoURI = "geo:34.671996,33.041673?z=21";
              //  Toast.makeText(getApplicationContext(), geoURI, Toast.LENGTH_SHORT).show();
                String title = getResources().getString(R.string.dialog_title);
                Uri geo = Uri.parse(geoURI);
                Intent geoIntent = new Intent(Intent.ACTION_VIEW, geo);
                startActivity(geoIntent);
        /*        Intent chooser = Intent.createChooser(geoIntent, title);


                PackageManager packageManager = getPackageManager();

                if (geoIntent.resolveActivity(getPackageManager()) !=null )

                {
                    startActivity(chooser);
                }

          */
            }
        });
    }


    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }





}
