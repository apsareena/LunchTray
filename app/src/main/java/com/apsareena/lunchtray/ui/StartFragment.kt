package com.apsareena.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apsareena.lunchtray.R
import com.apsareena.lunchtray.databinding.FragmentStartBinding
import com.apsareena.lunchtray.model.OrderViewModel


class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentStartBinding = FragmentStartBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        _binding = fragmentStartBinding
        return fragmentStartBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startFragment = this
    }

    private fun startOrder(){
        if (sharedViewModel.hasNoMainDishSet()){
            sharedViewModel.resetOrder()
        }
    }


    fun goToNextScreen(){
        startOrder()
        findNavController().navigate(R.id.action_startFragment_to_mainDishFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}