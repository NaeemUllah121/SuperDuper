package com.zasa.superduper.CategoryListCompanyWise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.R;

import java.util.ArrayList;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {


    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    //  private List<ParentItem> parentItemList;
    //  private List<ChildItemm> childItemmList;

    private ArrayList<CategoryItemCombineModel> categoryItemCombineModelArrayList;
    private ArrayList<ChildItemList> childItemList;/////Item List (Comany & Category Wise)

    Context context;


    private ArrayList<ParentItemCatList> parentItemList;////Category List (Company Wise)
    ChildItemAdapter childItemAdapter;

    //    public ParentItemAdapter(List<ParentItemCatList> parentItemList, List<ChildItemList> childItemList) {
//        this.parentItemList = parentItemList;
//        this.childItemList = childItemList;
//    }


//    public ParentItemAdapter(ArrayList<ParentItemCatList> parentItemList, ArrayList<ChildItemList> childItemList, ArrayList<ModelOfLIst> modelOfLIstArrayList, Context context) {
//        this.parentItemList = parentItemList;
//        this.childItemList = childItemList;
//        this.modelOfLIstArrayList = modelOfLIstArrayList;
//        this.context = context;
//    }

    public ParentItemAdapter(ArrayList<CategoryItemCombineModel> categoryItemCombineModelArrayList, Context context) {
        this.categoryItemCombineModelArrayList = categoryItemCombineModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Here we inflate the corresponding
        // layout of the parent item
        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_category_parent_item, viewGroup, false);
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_category_parent_item, viewGroup, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position) {

        // Create an instance of the ParentItem
        // class for the given position
/*
        ParentItem parentItem = parentItemList.get(position);
        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        parentViewHolder.ParentItemTitle.setText(parentItem.getParentItemTitle());
*/

//        String Item_Category_Name = parentItemList.get(position).getItem_Category_Name();
//        int Item_Category_Code = parentItemList.get(position).getItem_Category_Code();
//        parentViewHolder.ParentItemTitle.setText(Item_Category_Name);



        String Item_Category_Name = categoryItemCombineModelArrayList.get(position).getItem_Category_Name();
        int Item_Category_Code = categoryItemCombineModelArrayList.get(position).getItem_Category_Code();
        parentViewHolder.ParentItemTitle.setText(Item_Category_Name);


        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.
        // Here we have assigned the layout
        // as LinearLayout with vertical orientation

//        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext(),
//        LinearLayoutManager.HORIZONTAL,
//        false);
//
//        layoutManager.setInitialPrefetchItemCount(childItemList.size());
//        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);

        //Toast.makeText(context, "onBindViewHolder", Toast.LENGTH_SHORT).show();
        if (categoryItemCombineModelArrayList.size() > 1) {

            LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);

            //layoutManager.setInitialPrefetchItemCount(childItemList.size());
            layoutManager.setInitialPrefetchItemCount(categoryItemCombineModelArrayList.get(position).getItemList().size());
            parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);

            } else {

            GridLayoutManager gridLayoutManager = new GridLayoutManager(parentViewHolder.ChildRecyclerView.getContext(), 2);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            //  gridLayoutManager.setInitialPrefetchItemCount(childItemList.size());
            gridLayoutManager.setInitialPrefetchItemCount(categoryItemCombineModelArrayList.get(position).getItemList().size());
            parentViewHolder.ChildRecyclerView.setLayoutManager(gridLayoutManager);
        }


        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method

        ///// layoutManager.setInitialPrefetchItemCount(childItemList.size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool

        childItemAdapter = new ChildItemAdapter(categoryItemCombineModelArrayList.get(position).getItemList(), Item_Category_Code, context);
        //parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);

    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    @Override
    public int getItemCount() {
        //return parentItemList.size();
        //return modelOfLIstArrayList.size();
        return categoryItemCombineModelArrayList.size();
    }

    //    public void filter(String text) {
//        ArrayList<ChildItemList> filteredList = new ArrayList<>();
//        for (ChildItemList item : childItemList) {
//            if (item.getItem_Name().toLowerCase().contains(text.toLowerCase())|| item.getItem_Sale_Price()==Long.parseLong(text)) {
//                filteredList.add(item);
//            }
//        }
//        childItemAdapter.filteredListMethod(filteredList);
//
//    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    public class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView) {
            super(itemView);

            ParentItemTitle = itemView.findViewById(R.id.parent_item_title);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }


    }
}