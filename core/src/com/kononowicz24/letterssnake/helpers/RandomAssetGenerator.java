package com.kononowicz24.retrosnake2.helpers;

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
    public static List<String> generate(String base, int count) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i=0; i<count; i++) {
            arrayList.add(base+(i+1)+".png");
        }
        return arrayList;
    }
    public static String choose(List<String> arrayList) {
        Random random = new Random();
        int index = random.nextInt(arrayList.size());
        return arrayList.get(index);
    }
}

