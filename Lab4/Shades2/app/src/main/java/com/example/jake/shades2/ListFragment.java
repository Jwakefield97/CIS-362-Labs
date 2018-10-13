package com.example.jake.shades2;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListFragment extends Fragment {
    private OnItemSelectedListener listener;
    List<String> shadeListing; //master list
    List<String> shadeNameDetail; //detail list

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        shadeListing = new ArrayList<>(Arrays.asList(DummyData.shade_name));
        shadeNameDetail = new ArrayList<>(Arrays.asList(DummyData.shade_detail));
        final ArrayAdapter<String> shadeAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_shade,R.id.list_item_shade_textview, shadeListing);

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = rootView.findViewById(R.id.listview_shades);
        listView.setAdapter(shadeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String shadeIndexString = shadeAdapter.getItem(i);
                String information = shadeIndexString + "\n\n\n" + shadeNameDetail.get(i);
                updateDetail(information);
            }
        });
        return rootView;
    }

    public interface OnItemSelectedListener {
        void onShadeItemSelected(String link);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof OnItemSelectedListener){
            listener = (OnItemSelectedListener) activity;
        }else{
            throw new ClassCastException(activity.toString()+" must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public void updateDetail(String information){
        listener.onShadeItemSelected(information);
    }
}
