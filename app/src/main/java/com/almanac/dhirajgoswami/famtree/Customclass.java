package com.almanac.dhirajgoswami.famtree;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Customclass extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;
    private final String[] relations;
    private final Integer[] ImageIds;

    public Firebase myFirebaseRef;

    public Customclass(Activity context,String[] names,String[] relations,Integer[] imageIds ){
        super(context, R.layout.row,names);
        this.context=context;
        this.names=names;
        this.relations=relations;
        this.ImageIds=imageIds;
        Firebase.setAndroidContext(getContext());
        myFirebaseRef = new Firebase("https://dhirajrelation.firebaseio.com/");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View rowview;
        rowview = inflater.inflate(R.layout.row, null,true);


        final TextView nameoftheperson = (TextView) rowview.findViewById(R.id.textview2);
        final TextView relationships = (TextView) rowview.findViewById(R.id.textView3);
        final ImageView pics = (ImageView) rowview.findViewById(R.id.imageView);

        nameoftheperson.setText(names[position]);
        relationships.setText(relations[position]);
        pics.setImageResource(ImageIds[position]);


       myFirebaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {


               nameoftheperson.setText(String.valueOf(dataSnapshot.child("relation").child(String.valueOf(position)).child("Name").getValue()));
               relationships.setText(String.valueOf(dataSnapshot.child("relation").child(String.valueOf(position)).child("relation").getValue()));
               Picasso.with(getContext()).load(String.valueOf(dataSnapshot.child("relation").child(String.valueOf(position)).child("image").getValue())).into(pics);

           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {

           }
       });

        return rowview;
    }
}
