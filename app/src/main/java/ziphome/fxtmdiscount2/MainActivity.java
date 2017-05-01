package ziphome.fxtmdiscount2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.app.SearchManager;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;


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

        explistView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                  NamObj namObj = (NamObj) listAdapter.getChild(groupPosition,childPosition);

                 Intent intent = new Intent (explistView.getContext() , object_details.class);
                intent.putExtra("valueName",namObj.getName());
                intent.putExtra("valueOffer",namObj.getoffer());
                intent.putExtra("valueAddress",namObj.getAddress());
                intent.putExtra("valueWHours",namObj.getwHours());
                intent.putExtra("valueGps",namObj.getGps());
                startActivity(intent);

               // System.err.println(namObj.getAddress());

             //   Toast.makeText(getApplicationContext(), namObj.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

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
     Cell offer = row.getCell(2);
     Cell address = row.getCell(3);
     Cell wHours = row.getCell(4);
     Cell gps =  row.getCell(5);

     categTmp=categ.getStringCellValue();

     nameList = new ArrayList<NamObj>();
     objname = new NamObj(name.getStringCellValue(), offer.getStringCellValue(),address.getStringCellValue(),wHours.getStringCellValue(),gps.getStringCellValue());
     nameList.add(objname);

     // Loop is starting from 2nd Row of xls list till the end of data
     for (int i=1; i< sheet.getPhysicalNumberOfRows(); i++ ){
          row = sheet.getRow(i);
          categ = row.getCell(0);
          name = row.getCell(1);
          offer = row.getCell(2);
          address= row.getCell(3);
          wHours = row.getCell(4);
          gps =  row.getCell(5);
         if (categTmp==categ.getStringCellValue())  {
             objname = new NamObj(name.getStringCellValue(), offer.getStringCellValue(),address.getStringCellValue(),wHours.getStringCellValue(),gps.getStringCellValue());;
             nameList.add(objname);
         }
         else {
             categitem = new Categ(categTmp,nameList);
             categlist.add(categitem);
             categTmp = categ.getStringCellValue();

             nameList = new ArrayList<NamObj>();
             objname = new NamObj(name.getStringCellValue(), offer.getStringCellValue(),address.getStringCellValue(),wHours.getStringCellValue(),gps.getStringCellValue());
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
