package com.almanac.dhirajgoswami.famtree;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    String[] names = {"CARING MOTHER","INSPIRATIONAL FATHER","STYLISH SISTER","ENERGETIC BROTHER"};
    String[] relations = {"MOTHER","FATHER","SISTER","BROTHER"};
    Integer[] ImageIds = {R.drawable.mother,R.drawable.father,R.drawable.sister,R.drawable.brother};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Customclass adptr = new Customclass(this, names, relations, ImageIds);
        setListAdapter(adptr);

    }
    }