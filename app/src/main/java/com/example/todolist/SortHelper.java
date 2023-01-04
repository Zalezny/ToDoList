package com.example.todolist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortHelper {

//    ArrayList<ItemDataModel> itemList;
//
//    public SortHelper(ArrayList<ItemDataModel> itemList) {
//        this.itemList = itemList;
//    }

    public static void sortAmount(ArrayList<ItemDataModel> itemList) {
        Collections.sort(itemList, new Comparator<ItemDataModel>() {

            @Override
            public int compare(ItemDataModel o1, ItemDataModel o2) {
                return o1.text.compareTo(o2.text);
            }
        });



        /*ItemDataModel[] tempArray = itemList.toArray(new ItemDataModel[itemList.size()]);*/

        /*for (int i = 0; i < itemList.size(); i++) {
            for(int j=i+1;j<itemList.size();j++) {
                String tempi = itemList.get(i).text;
                String tempj = itemList.get(j).text;

                if(tempj.length()<tempi.length()) {
                    ItemDataModel tmpItem = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j]= tmpItem;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(tempArray));*/



    }
}
