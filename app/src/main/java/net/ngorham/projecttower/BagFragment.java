package net.ngorham.projecttower;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ngorham.projecttower.BagContent.BagItem;

/**
 * Activities that contain this fragment must implement the
 * {@link BagFragment.BagFragmentListener} interface
 * to handle interaction events.
 * Use the {@link BagFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Project Tower
 * BagFragment.java
 * Detail
 * Purpose: Fragment for displaying Bag items
 *
 * @author Neil Gorham
 * @version 1.0 06/18/2018
 *
 */

public class BagFragment extends Fragment {
    //Private variables
    private BagFragmentListener listener;

    public BagFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BagFragment.
     */
    public static BagFragment newInstance(){
        BagFragment fragment = new BagFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bag_list, container, false);

        // Set up recyclerView
        if (view instanceof RecyclerView){
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //Set up LayoutManager
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //SharedPreference list and grid views
            //Set up adapter
            recyclerView.setAdapter(new BagRecyclerViewAdapter(BagContent.ITEMS, listener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof BagFragmentListener) {
            listener = (BagFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface BagFragmentListener {
        void bagItemClicked(BagItem item);
    }
}
