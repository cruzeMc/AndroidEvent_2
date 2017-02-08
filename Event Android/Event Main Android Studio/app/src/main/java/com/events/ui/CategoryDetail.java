package com.events.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.events.R;
import com.events.custom.CustomFragment;
import com.events.model.Category;

import java.util.ArrayList;

/**
 * Created by Cruze on 12/10/2016.
 */

public class CategoryDetail extends CustomFragment {
    private ArrayList<Category> categoryArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_item, container, false);
        setHasOptionsMenu(true);

        //setCategoryList(v);

        return v;
    }

    private void setCategoryList(View v) {
        getCategoryData();
        loadCategoryView(v);
    }

    private void getCategoryData() {
//        APIService service = ServerCommunicator.getServerCommunicator().create(APIService.class);
//        Call<List<Category>> call = service.getCategories();
//
//        call.enqueue(new Callback<List<Category>>() {
//            @Override
//            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                List<Category> categories = response.body();
//                categoryArrayList = new ArrayList<>();
//
//                for(int i = 0; i< categories.size(); i++) {
//                    int id = categories.get(i).getId();
//                    String categoryName = categories.get(i).getCategoryName();
//                    String image = categories.get(i).getImage();
//
//                    categoryArrayList.add(new Category(R.drawable.semi_trans_bg, categoryName, image));
//                }
//                Log.i("CATEGORIES", categoryArrayList.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Category>> call, Throwable t) {
//                categoryArrayList.add(new Category(R.drawable.img4, "Blank", "Blank"));
//
//                Context context = getActivity().getApplicationContext();
//                CharSequence text = getString(R.string.network_error);
//                int duration = Toast.LENGTH_LONG;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        });

        categoryArrayList.add(new Category(R.drawable.semi_trans_bg, "Love", "Love Image"));
        categoryArrayList.add(new Category(R.drawable.semi_trans_bg, "Hate", "Hate Image"));
        categoryArrayList.add(new Category(R.drawable.semi_trans_bg, "Feelings", "Feelings Image"));

        Log.i("Categories", categoryArrayList.toString());
    }

    private void loadCategoryView(View v){
        ArrayAdapter<Category> adapter = new MyListAdapter();
        ListView list = (ListView) v.findViewById(R.id.categoryActivityView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Category> {
        private MyListAdapter(){
            super(getActivity(), R.layout.category_item, categoryArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getActivity().getLayoutInflater().inflate(R.layout.category_item, parent, false);
            }

            //Find the category to work with
            Category currentCategory = categoryArrayList.get(position);

            //Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.categoryImageView);
            imageView.setImageResource(currentCategory.getId());

            return itemView;
        }
    }
}
