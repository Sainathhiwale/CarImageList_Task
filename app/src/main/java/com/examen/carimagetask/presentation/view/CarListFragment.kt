package com.examen.carimagetask.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.carimagetask.R
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.utils.AppConstants
import com.examen.carimagetask.data.utils.NetworkUtils
import com.examen.carimagetask.databinding.FragmentCarListBinding
import com.examen.carimagetask.presentation.adapter.CarListAdapter
import com.examen.carimagetask.presentation.viewmodel.CarViewModel
import com.examen.carimagetask.presentation.viewmodel.SharedCarViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarListFragment : Fragment(), CarListAdapter.OnItemClickCarListener {
    private val TAG = "CarListFragment"
    lateinit var carListAdapter: CarListAdapter
    @Inject
    lateinit var carViewModel: CarViewModel
    @Inject
    lateinit var sharedCarViewModel: SharedCarViewModel
    lateinit var viewBinding: FragmentCarListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCarListBinding.inflate(inflater,container,false)
        initView()
        return viewBinding.root
    }

    private fun initView(){
        if (NetworkUtils.isConnected(requireActivity())){
            carViewModel.fetchCars()
            carViewModel.carList.observe(requireActivity()) {
                Log.d(TAG, "initView: ${it[0]} ")
                if (it.isNotEmpty()){
                    viewBinding.recyclerview.layoutManager = LinearLayoutManager(requireActivity())
                    carListAdapter = CarListAdapter(it)
                    viewBinding.recyclerview.adapter = carListAdapter
                    carListAdapter.setOnItemClickCarListener(this)
                }else{
                    Toast.makeText(requireActivity(), "Car List is empty", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            AppConstants.showSnackBar(viewBinding.root, "No Internet Connection",Color.WHITE)
        }

    }

    override fun onItemClickCar(car: CarList) {
            if (car.name!=null){
                Log.d(TAG, "onItemClickCar: $car")
                sharedCarViewModel.setCarList(car)
                findNavController().navigate(R.id.action_carListFragment_to_carDetailsFragment)

            }else{
                val snackBar = Snackbar.make(viewBinding.root,"car list is Empty!", Snackbar.LENGTH_SHORT)
                snackBar.setTextColor(Color.RED)
                snackBar.show()
            }

    }
}