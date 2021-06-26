package com.example.menu.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.menu.R
import com.example.menu.application.FavDishApplication
import com.example.menu.databinding.FragmentAllDishesBinding
import com.example.menu.view.activities.AddUpdateDishActivity
import com.example.menu.view.adapter.FavDishAdapter
import com.example.menu.viewmodel.FavDishViewModel
import com.example.menu.viewmodel.FavDishViewModelFactory

import com.example.menu.viewmodel.HomeViewModel

class AllDishesFragment : Fragment() {

    // private lateinit var homeViewModel: HomeViewModel
    private lateinit var mBinding: FragmentAllDishesBinding

    private val mFavDishViewModel : FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).respository)
    }

    private var _binding: FragmentAllDishesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(), 2)
        val favDishAdapter = FavDishAdapter(this@AllDishesFragment)

        mBinding.rvDishesList.adapter = favDishAdapter

        mFavDishViewModel.allDishesList.observe(viewLifecycleOwner){
            dishes ->
                dishes.let {
                    if(it.isNotEmpty()){
                        Log.i("TAG", "NOT EMPTY")
                        mBinding.rvDishesList.visibility = View.VISIBLE
                        mBinding.tvNoDishesAddedYet.visibility = View.GONE
                        favDishAdapter.dishesList(it)
                    } else {
                        Log.i("TAG", "EMPTY")
                        mBinding.rvDishesList.visibility = View.GONE
                        mBinding.tvNoDishesAddedYet.visibility = View.VISIBLE
                    }
                   /* for(item in it){
                        Log.i("TAG", "${item.id} :: ${item.title}")
                    } */
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_add_dish->{
                startActivity(Intent(requireActivity(), AddUpdateDishActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAllDishesBinding.inflate(inflater, container, false)
        /*  homeViewModel =
                 ViewModelProvider(this).get(HomeViewModel::class.java)

         _binding = FragmentAllDishesBinding.inflate(inflater, container, false)
         val root: View = binding.root

        // val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
             textView.text = it
         })*/
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}