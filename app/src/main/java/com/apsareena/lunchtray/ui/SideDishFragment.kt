package com.apsareena.lunchtray.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsareena.lunchtray.R
import com.apsareena.lunchtray.adapter.FoodAdapter
import com.apsareena.lunchtray.data.FoodData
import com.apsareena.lunchtray.databinding.FragmentSideDishBinding
import com.apsareena.lunchtray.model.OrderViewModel


class SideDishFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentSideDishBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val _mSIDEDISHID = 2

    companion object {
        val SIDE_DISHES = FoodData.sideDishes
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val savedState = savedInstanceState?.getString(mSIDEDISHKEY)
//        Log.d("savedstate", savedState?: "")
//        sharedViewModel.checkedOrNot(savedState?: "", _mSIDEDISHID)
//        sharedViewModel.savedState = savedState?: ""
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(mSIDEDISHKEY, sharedViewModel.mainDish.toString())
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentSideDishBinding = FragmentSideDishBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        _binding = fragmentSideDishBinding
        binding.handler = myHandlersListener
        return fragmentSideDishBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FoodAdapter(SIDE_DISHES, requireContext(), mainDish = sharedViewModel.sideDish.value.toString()){
            sharedViewModel.preferedItem(it, _mSIDEDISHID, requireContext())
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            sideDishFragment = this@SideDishFragment
        }

    }

    private val myHandlersListener: MyHandler = object: MyHandler{
        override fun goToNextScreen() {
            findNavController().navigate(R.id.action_sideDishFragment_to_accompanimentFragment)
        }

        override fun cancelOrder() {
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_sideDishFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}