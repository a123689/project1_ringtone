package com.example.dat.project1_ringtone.View.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dat.project1_ringtone.View.Adapter.AdapterCategory;
import com.example.dat.project1_ringtone.View.Event.CallbackClick;
import com.example.dat.project1_ringtone.View.Event.CallbackClickMain;
import com.example.dat.project1_ringtone.Model.Category;
import com.example.dat.project1_ringtone.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    CallbackClickMain callbackClickMain;
    public CategoryFragment(CallbackClickMain callbackClickMain) {
        // Required empty public constructor
        this.callbackClickMain = callbackClickMain;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycleviewCategory);
        ArrayList arraylist = new ArrayList<>();
        arraylist.add(new Category(1,"a", R.drawable.rectangle));
        arraylist.add(new Category(2,"b", R.drawable.rectangle1));
        arraylist.add(new Category(3,"c", R.drawable.rectangle2));
        arraylist.add(new Category(4,"d", R.drawable.rectangle3));
        arraylist.add(new Category(5,"e", R.drawable.rectangle4));
        arraylist.add(new Category(6,"f", R.drawable.rectangle5));
        arraylist.add(new Category(1,"a", R.drawable.rectangle));
        arraylist.add(new Category(2,"b", R.drawable.rectangle1));
        arraylist.add(new Category(3,"c", R.drawable.rectangle2));
        arraylist.add(new Category(4,"d", R.drawable.rectangle3));
        arraylist.add(new Category(5,"e", R.drawable.rectangle4));
        arraylist.add(new Category(6,"f", R.drawable.rectangle5));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        AdapterCategory adapterCategory = new AdapterCategory(arraylist, getActivity(), new CallbackClick() {
            @Override
            public void click(View view) {
                callbackClickMain.onclickmain();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapterCategory);
    }
}
