package com.kononowicz24.letterssnake.helpers;

/**
 * Created by k24 on 19.01.19.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by k24 on 27.12.17.
 */

public class RandomAssetGenerator {
    /**
     * Generates list of string based on base and max count (used for assets)
     * @param base beggining of the name
     * @param count how many assets are to be chosen from
     * @return list of all possible assets names
     */
    public static List<String> generate(String base, int count) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i=0; i<count; i++) {
            arrayList.add(base+(i+1)+".png");
        }
        return arrayList;
    }

    /**
     * Selects one random Sring from the list
     * @param arrayList list to be chosen from
     * @return chosen String
     */
    public static String choose(List<String> arrayList) {
        Random random = new Random();
        int index = random.nextInt(arrayList.size());
        return arrayList.get(index);
    }
}

