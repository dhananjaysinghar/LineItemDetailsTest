package com;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        
        // object-1
        ListItemDetails lid = new ListItemDetails();
        ItemDetails id = new ItemDetails();
        id.setName("item-1");
        id.setNo(1);
        ItemDetails id2 = new ItemDetails();
        id2.setName("item-2");
        id2.setNo(1000);
        lid.setItemDetails(Arrays.asList(id, id2));
        lid.setType("type1");

        // object-2
        ListItemDetails lid2 = new ListItemDetails();
        ItemDetails id3 = new ItemDetails();
        id3.setName("item-2");
        id3.setNo(2000);
        ItemDetails id4 = new ItemDetails();
        id4.setName("item-4");
        id4.setNo(4);
        lid2.setItemDetails(Arrays.asList(id3, id4));
        lid2.setType("type 2");

        
        // add object-1 and object-2
        List<ListItemDetails> list = Arrays.asList(lid, lid2);
        String value = "item-2";

        
        // Print the Old List
        System.out.println("OLD: " + list);

        //use copyOnWriteArrayList for multiple thread operation
        CopyOnWriteArrayList<ListItemDetails> newList = new CopyOnWriteArrayList<>(list);

        for (ListItemDetails e : newList) {
            e.setItemDetails(getFilteredItemDetails(e.getItemDetails(), value));
        }

        //Print the new List
        System.out.println("NEW:" + newList);

    }

    private static List<ItemDetails> getFilteredItemDetails(List<ItemDetails> itemDetails, String value) {
        return itemDetails.stream().filter(e -> e.getName().equalsIgnoreCase(value)).collect(Collectors.toList());
    }

}