package com.apsareena.lunchtray.ui

import android.os.Bundle
import android.util.Log
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
import com.apsareena.lunchtray.databinding.FragmentAccompanimentBinding
import com.apsareena.lunchtray.model.OrderViewModel

class AccompanimentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentAccompanimentBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val _mACCOMPANIMENTDISHID = 3

    companion object {
        val ACCOMPANIMENT_DISHES = FoodData.accompaniment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentAccompanimentBinding = FragmentAccompanimentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        _binding = fragmentAccompanimentBinding
        binding.handler = myHandlersListener
        return fragmentAccompanimentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FoodAdapter(ACCOMPANIMENT_DISHES, requireContext(), mainDish = sharedViewModel.accompaniment.value.toString()){
            sharedViewModel.preferedItem(it, _mACCOMPANIMENTDISHID, requireContext())
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            accompanimentFragment = this@AccompanimentFragment
        }
    }

    private val myHandlersListener: MyHandler = object : MyHandler {
        override fun goToNextScreen() {
            findNavController().navigate(R.id.action_accompanimentFragment_to_summaryFragment)
        }

        override fun cancelOrder() {
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_accompanimentFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}