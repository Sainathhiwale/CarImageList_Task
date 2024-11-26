package com.examen.carimagetask.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.examen.carimagetask.R
import com.examen.carimagetask.data.utils.AppConstants
import com.examen.carimagetask.databinding.FragmentCarDetailsBinding
import com.examen.carimagetask.presentation.viewmodel.SharedCarViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarDetailsFragment : Fragment(), View.OnClickListener {
    private val TAG = "CarDetailsFragment"

    @Inject
    lateinit var sharedCarViewModel: SharedCarViewModel
    lateinit var carDetailsBinding: FragmentCarDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        carDetailsBinding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return carDetailsBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carDetailsBinding.buttonUpdateCar.setOnClickListener(this)
        carDetailsBinding.buttonUpdateCancel.setOnClickListener(this)
        try {
            sharedCarViewModel.getCarList().observe(viewLifecycleOwner) {
                Log.i(TAG, "onViewCreated: ${it.toString()}")
                carDetailsBinding.editTextUpdateCarName.setText(it.name)
                carDetailsBinding.editTextUpdateBrand.setText(it.brand)
                carDetailsBinding.editTextUpdatePrice.setText(it.price.toString())
                Glide.with(requireActivity()).load(it.imageUrl).into(carDetailsBinding.updateCarImage)
            }
        }catch (e:Exception){
            e.toString()
        }

    }

    private fun initView() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            carDetailsBinding.buttonUpdateCancel.id -> {
                backToCarList()
            }

            carDetailsBinding.buttonUpdateCar.id -> {
                val errors = checkValidation()

                if (errors.isNotEmpty()) {
                    for (error in errors) {
                        val snackBar = Snackbar.make(
                            requireActivity(),
                            carDetailsBinding.root,
                            error,
                            Snackbar.LENGTH_SHORT
                        )
                        snackBar.show()
                        val toast = Toast.makeText(
                            requireActivity(),
                            "Please fill all the fields",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                } else {
                    updateCarDetails()
                }
            }
        }
    }

    private fun checkValidation(): List<String> {
        val errors = mutableListOf<String>()
        if (!AppConstants.hasValidAmountForTextView(carDetailsBinding.editTextUpdatePrice)) {
            errors.add("Please enter car price")
        }
        if (!AppConstants.hasEditTextCarName(carDetailsBinding.editTextUpdateCarName)) {
            errors.add("Please enter car name")
        }
        if (!AppConstants.hasEditTextCarBrand(carDetailsBinding.editTextUpdateBrand)) {
            errors.add("Please enter car brand name")
        }
        return errors
    }

    private fun backToCarList() {
        TODO("Not yet implemented")
    }

    private fun updateCarDetails() {
        val name = carDetailsBinding.editTextUpdateCarName.text.toString()
        val brand = carDetailsBinding.editTextUpdateBrand.text.toString()
        val price = carDetailsBinding.editTextUpdatePrice.text.toString()

    }
}