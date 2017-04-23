package ziphome.fxtmdiscount2;

import java.util.ArrayList;

/**
 * Created by 1 on 19.03.2017.
 */

public class Categ {
    String catname;
    ArrayList<NamObj> nameList = new ArrayList<NamObj>();

    public Categ(String catname, ArrayList<NamObj> namobj) {
        this.catname = catname;
        this.nameList = namobj;
    }


    public String getName(){
        return catname;
    }

    public ArrayList<NamObj> getNameList(){
        return this.nameList;
    }

    public void setNameList(ArrayList<NamObj> arr){
        this.nameList=arr;
    }

    public void setCatName(String catName){
        this.catname=catName;
    }

}
