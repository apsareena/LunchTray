package com.apsareena.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsareena.lunchtray.R
import com.apsareena.lunchtray.adapter.FoodAdapter
import com.apsareena.lunchtray.data.FoodData
import com.apsareena.lunchtray.databinding.FragmentMainDishBinding
import com.apsareena.lunchtray.model.OrderViewModel

class MainDishFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentMainDishBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val _mMAINDISHID = 1

    companion object {
        val MAIN_DISHES = FoodData.mainDishes
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentMainDishBinding = FragmentMainDishBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        _binding = fragmentMainDishBinding
        binding.handler = myHandlersListener
        return fragmentMainDishBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerViewMainDish
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FoodAdapter(MAIN_DISHES, requireContext(), mainDish = sharedViewModel.mainDish.value.toString()) {
            sharedViewModel.preferedItem(it, _mMAINDISHID, requireContext())
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            mainDishFragment = this@MainDishFragment
        }
    }

    private val myHandlersListener: MyHandler = object : MyHandler {
        override fun goToNextScreen() {
            findNavController().navigate(R.id.action_mainDishFragment_to_sideDishFragment)
        }

        override fun cancelOrder() {
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_mainDishFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}