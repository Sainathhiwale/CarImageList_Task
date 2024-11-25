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
import com.examen.carimagetask.presentation.viewmodel.CarViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarDetailsFragment : Fragment(), View.OnClickListener {
      private val TAG = "CarDetailsFragment"
      lateinit var viewBinding: FragmentCarDetailsBinding
      @Inject
      lateinit var carViewModel: CarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return  viewBinding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        carViewModel.selectedCarDetails.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated:$it ")
            viewBinding.editTextUpdateCarName.setText(it.name)
            viewBinding.editTextUpdateBrand.setText(it.brand)
            viewBinding.editTextUpdatePrice.setText(it.price.toString())
            Glide.with(this).load(it.imageUrl).into(viewBinding.updateCarImage)
        }
        viewBinding.buttonUpdateCar.setOnClickListener(this)
        viewBinding.buttonUpdateCancel.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            viewBinding.buttonUpdateCancel.id -> {
                backToCarList()
            }
            viewBinding.buttonUpdateCar.id -> {
                val errors = checkValidation()

                if (errors.isNotEmpty()) {
                    for (error in errors) {
                        val snackBar = Snackbar.make(requireActivity(), viewBinding.root, error, Snackbar.LENGTH_SHORT)
                        snackBar.show()
                        val toast = Toast.makeText(requireActivity(), "Please fill all the fields", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }else{
                    updateCarDetails()
                }
            }
        }
    }

    private fun checkValidation(): List<String> {
        val errors = mutableListOf<String>()
        if (!AppConstants.hasValidAmountForTextView(viewBinding.editTextUpdatePrice)) {
            errors.add("Please enter amount")
        }
        if (!AppConstants.hasValidAmountForTextView(viewBinding.editTextUpdateCarName)) {
            errors.add("Please enter car name")
        }
        if (!AppConstants.hasValidAmountForTextView(viewBinding.editTextUpdateBrand)) {
            errors.add("Please enter car brand name")
        }
        return errors
    }

    private fun backToCarList() {
        TODO("Not yet implemented")
    }

    private fun updateCarDetails(){
        val name = viewBinding.editTextUpdateCarName.text.toString()
        val brand = viewBinding.editTextUpdateBrand.text.toString()
        val price = viewBinding.editTextUpdatePrice.text.toString()

    }
}