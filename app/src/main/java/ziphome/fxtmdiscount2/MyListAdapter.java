package ziphome.fxtmdiscount2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1 on 19.03.2017.
 */

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Categ>  categList;
    private ArrayList<Categ> categOriginal;

    public MyListAdapter(Context context, ArrayList<Categ> categList) {
        this.context = context;
        this.categList = new ArrayList<Categ>();
        this.categList.addAll(categList);
        this.categOriginal= new ArrayList<Categ>();
        this.categOriginal.addAll(categList);
    }


    @Override
    public int getGroupCount() {
        return this.categList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
       ArrayList<NamObj> arr = this.categList.get(groupPosition).getNameList();
        return arr.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.categList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
      ArrayList<NamObj> arr =  this.categList.get(groupPosition).getNameList();
        return arr.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Categ categ2 = (Categ) getGroup(groupPosition);
        if (convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.categ_item, null);
        }

        TextView categTv = (TextView) convertView.findViewById(R.id.categtv);
        categTv.setText(categ2.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        NamObj namObj = (NamObj) this.getChild(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.objects_items, null);
        }

        TextView objnametv = (TextView) convertView.findViewById(R.id.nametv);
        TextView objaddrtv = (TextView) convertView.findViewById(R.id.addresstv);
        objnametv.setText(namObj.getName());
        objaddrtv.setText(namObj.getAddress());


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }


    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(categList.size()));
        categList.clear();

        if(query.isEmpty()){
            categList.addAll(categOriginal);
        }
        else {

            for(Categ categ: categOriginal){

                ArrayList<NamObj> categList = categ.getNameList();
                ArrayList<NamObj> newList = new ArrayList<>();
                for(NamObj namObj: categList){
                    if(namObj.getName().toLowerCase().contains(query) ||
                            namObj.getAddress().toLowerCase().contains(query)){
                        newList.add(namObj);
                    }
                }
                if(newList.size() > 0){
                    Categ nCateg = new Categ(categ.getName(),newList);
                    this.categList.add(nCateg);


                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(categList.size()));
        notifyDataSetChanged();

    }






}
