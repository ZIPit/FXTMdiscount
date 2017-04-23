package ziphome.fxtmdiscount2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.app.SearchManager;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.SearchView;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import static android.graphics.Color.BLACK;
//import android.widget.SearchView.OnQueryTextListener;


//public class MainActivity extends Activity implements
//        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

public class MainActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

//public class MainActivity extends AppCompatActivity {
    MyListAdapter listAdapter;
    ExpandableListView explistView;
    ArrayList<Categ>  categlist = new ArrayList<Categ>();
    ArrayList<NamObj> nameList = new ArrayList<NamObj>();
    NamObj objname;
    Categ categitem;

    SearchView search;

    int grpcountint;

    String categTmp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explistView = (ExpandableListView) findViewById(R.id.explv);

        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        iv.setImageResource(R.drawable.logowhite);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);


        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        search.setOnCloseListener((SearchView.OnCloseListener) this);
        displaylist();


    }

    public void displaylist(){

        initData();
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(MainActivity.this, categlist);
        //attach the adapter to the list
        grpcountint = listAdapter.getGroupCount();
        explistView.setAdapter(listAdapter);

    }


    public void initData(){

        categTmp ="";
try {
    Workbook wb = WorkbookFactory.create(getAssets().open("listdata.xls"));
    // We take first sheet page
     Sheet sheet = wb.getSheetAt(0);
    // Take first row
     Row row = sheet.getRow(0);
     Cell categ = row.getCell(0);
     Cell name = row.getCell(1);

     categTmp=categ.getStringCellValue();

     nameList = new ArrayList<NamObj>();
     objname = new NamObj(name.getStringCellValue());
     nameList.add(objname);

     // Loop is starting from 2nd Row of xls list till the end of data
     for (int i=1; i< sheet.getPhysicalNumberOfRows(); i++ ){
          row = sheet.getRow(i);
          categ = row.getCell(0);
          name = row.getCell(1);
         if (categTmp==categ.getStringCellValue())  {
             objname = new NamObj(name.getStringCellValue());
             nameList.add(objname);
         }
         else {
             categitem = new Categ(categTmp,nameList);
             categlist.add(categitem);
             categTmp = categ.getStringCellValue();

             nameList = new ArrayList<NamObj>();
             objname = new NamObj(name.getStringCellValue());
             nameList.add(objname);

         }
    }
     // Will Add the last category with items
    categitem = new Categ(categTmp,nameList);
    categlist.add(categitem);

}
catch (Exception ex) {
    return;
}

/*
        objname = new NamObj("Suenso Spa");
        nameList.add(objname);
        objname = new NamObj("Quick Spa Limassol");
        nameList.add(objname);
        objname = new NamObj("Limassol Sporting Center");
        nameList.add(objname);
        objname = new NamObj("Sanctrum Fitness and spa");
        nameList.add(objname);
        objname = new NamObj("Dekathlon Gym");
        nameList.add(objname);
        categitem = new Categ("SPA&GYM",nameList);
        categlist.add(categitem);



        nameList = new ArrayList<NamObj>();
        objname = new NamObj("Carob Mill Restaurant");
        nameList.add(objname);
        objname = new NamObj("Pier One");
        nameList.add(objname);
        objname = new NamObj("BEER & BEER – Hofbrau Munchen");
        nameList.add(objname);
        objname = new NamObj("Wagamama  ");
        nameList.add(objname);
        objname = new NamObj("Hobo Mediterraneo  ");
        nameList.add(objname);
        objname = new NamObj("Taras Bulba  ");
        nameList.add(objname);
        objname = new NamObj("Burger Lab  ");
        nameList.add(objname);
        objname = new NamObj("Columbia Steak House  ");
        nameList.add(objname);
        objname = new NamObj("Dionysus Mansion  ");
        nameList.add(objname);
        objname = new NamObj("The Noodle house  ");
        nameList.add(objname);
        objname = new NamObj("La Boca  ");
        nameList.add(objname);
        objname = new NamObj("Gin Fish  ");
        nameList.add(objname);
        objname = new NamObj("Do Wine Dine and Up Town Square");
        nameList.add(objname);
        objname = new NamObj("Benzai Sushi bar  ");
        nameList.add(objname);
        objname = new NamObj("The Fish Market  ");
        nameList.add(objname);
        objname = new NamObj("The Garden  ");
        nameList.add(objname);
        objname = new NamObj("TGI Fridays  ");
        nameList.add(objname);
        objname = new NamObj("Cristal Marina  ");
        nameList.add(objname);
        objname = new NamObj("Nippon  ");
        nameList.add(objname);
        objname = new NamObj("La Isla  ");
        nameList.add(objname);
        objname = new NamObj("Simply Fresh  ");
        nameList.add(objname);
        objname = new NamObj("Puesta Oyster bar and Grill");
        nameList.add(objname);
        categitem = new Categ("Restaurants / cafe / bars",nameList);
        categlist.add(categitem);

        nameList = new ArrayList<NamObj>();
        objname = new NamObj("La maison Du Vin");
        nameList.add(objname);
        objname = new NamObj("French Depot");
        nameList.add(objname);
        objname = new NamObj("Hadjiantonas Winery");
        nameList.add(objname);
        objname = new NamObj("Dafermou Winery");
        categitem = new Categ("Wine Shops / Winery",nameList);
        categlist.add(categitem);


        nameList = new ArrayList<NamObj>();
        objname = new NamObj("Trussardi  ");
        nameList.add(objname);
        objname = new NamObj("No name  ");
        nameList.add(objname);
        objname = new NamObj("Timinis  ");
        nameList.add(objname);
        objname = new NamObj("Wanted Boutique  ");
        nameList.add(objname);
        objname = new NamObj("First boutique  ");
        nameList.add(objname);
        objname = new NamObj("Elegance  ");
        nameList.add(objname);
        objname = new NamObj("Nespresso  ");
        nameList.add(objname);
        objname = new NamObj("New York Sweets  ");
        nameList.add(objname);
        objname = new NamObj("Pralina  ");
        nameList.add(objname);
        objname = new NamObj("La Gallerie");
        nameList.add(objname);
        categitem = new Categ("Shops",nameList);
        categlist.add(categitem);

        nameList = new ArrayList<NamObj>();
        objname = new NamObj("CXC Toys");
        nameList.add(objname);
        objname = new NamObj("ELC Toys");
        nameList.add(objname);
        categitem = new Categ("Kids Stores",nameList);
        categlist.add(categitem);


        nameList = new ArrayList<NamObj>();
        objname = new NamObj("Vite Flower Shop");
        nameList.add(objname);
        objname = new NamObj("Floralink");
        nameList.add(objname);
        objname = new NamObj("Flowers & Designs (Rois)");
        nameList.add(objname);
        categitem = new Categ("Flower Shops",nameList);
        categlist.add(categitem);


        nameList = new ArrayList<NamObj>();
        objname = new NamObj("Atteshlis Travel Agency  ");
        nameList.add(objname);
        objname = new NamObj("Аscot travel  ");
        nameList.add(objname);
        objname = new NamObj("Taxi Service");
        nameList.add(objname);
        objname = new NamObj("Escape Quest Room");
        nameList.add(objname);
        objname = new NamObj("Sayious Adventure Park");
        nameList.add(objname);
        objname = new NamObj("Fasouri Waterpark");
        nameList.add(objname);
        categitem = new Categ("Entertainment/ Service",nameList);
        categlist.add(categitem);


        nameList = new ArrayList<NamObj>();
        objname = new NamObj("Narex Car Rent  ");
        nameList.add(objname);
        objname = new NamObj("Aida Car Rent  ");
        nameList.add(objname);
        objname = new NamObj("Sixt Rent a car  ");
        nameList.add(objname);
        objname = new NamObj("Leos Cars  ");
        nameList.add(objname);
        objname = new NamObj("Privilage rent a car  ");
        nameList.add(objname);
        categitem = new Categ("Car Insurance",nameList);
        categlist.add(categitem);

*/

    }


    @Override
    public boolean onClose() {
        listAdapter.filterData("");

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        if (grpcountint!=listAdapter.getGroupCount()){

           if  (listAdapter.getGroupCount()!=0){

                 for (int i=0 ; i<listAdapter.getGroupCount(); i++){

                     explistView.expandGroup(i) ;

                 }


                   }
        }
        else {
            for (int i = 0; i < listAdapter.getGroupCount(); i++) {

                explistView.collapseGroup(i);

            }

        }
        return false;
    }
}
