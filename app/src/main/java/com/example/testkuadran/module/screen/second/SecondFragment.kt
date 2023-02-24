package com.example.testkuadran.module.screen.second

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testkuadran.R
import com.example.testkuadran.core.util.convertLongToTime
import com.example.testkuadran.core.util.getAge
import com.example.testkuadran.databinding.FragmentSecondBinding
import com.example.testkuadran.module.data.db.UserDB
import com.example.testkuadran.module.data.model.UserData
import com.example.testkuadran.module.util.getZodiac
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), SecondFragmentContract {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db: UserDB by lazy { UserDB(requireContext()) }

    // FORM DATA
    private var inputName: String? = null
    private var inputDate: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initUI()
        initAction()
        initObserver()
        initNavigation()
        super.onViewCreated(
            view,
            savedInstanceState
        )
    }

    override fun initUI() {
        binding.fab.isEnabled = false
    }

    override fun initAction() {
        binding.fab.setOnClickListener {
            submit()
            displayZodiacDialog()
        }

        // prepare dat input
        binding.dateInput.inputType = InputType.TYPE_NULL
        binding.dateInput.keyListener = null
        binding.dateInput.setOnClickListener {
            openDatePicker()
        }
        binding.dateInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) openDatePicker()
        }
    }

    override fun initObserver() {
        binding.nameInput.doOnTextChanged { text, start, before, count ->

            if (text == null || text.isBlank())
                inputName = null
            else
                inputName = text.toString()

            requestValidation()
        }
    }

    private fun openDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select dates")
                .build()

        datePicker.show(childFragmentManager, "Pick Date")
        datePicker.addOnPositiveButtonClickListener {
            inputDate = it
            showPickedDate()
            requestValidation()
        }
    }

    private fun showPickedDate() {
        binding.dateInput.setText(
            inputDate?.run(::convertLongToTime) ?: ""
        )
    }

    private fun requestValidation() {
        if (inputName.isNullOrBlank())
            return
        if (inputDate == null)
            return

        binding.fab.isEnabled = true
    }

    private fun submitAndDone() {
        if (submit()) {
            showSuccessSnackBar()
            goBack()
        }
    }

    private fun submit(): Boolean {
        db.addUserData(
            UserData(
                inputName ?: "",
                inputDate ?: 0L
            )
        )

        return true
    }

    private fun displayZodiacDialog() {

        val dateBirth = convertLongToTime(inputDate ?: 0L, "yyyy,MM,dd")
            .split(",")
            .map { it.toInt() }

        val age = getAge(
            dateBirth.first(),
            dateBirth[1],
            dateBirth[2]
        )

        val zodiac = getZodiac(dateBirth[1], dateBirth[2])

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Halo $inputName")
            .setMessage(
                with(StringBuilder()) {

                    append("Usia anda saat ini adalah :")
                    append("\n")
                    append("\n${age.first} Tahun")
                    append("\n${age.second} Bulan")
                    append("\n${age.third} Hari")

                    append("\n")
                    append("\nZodiac anda adalah :")
                    append("\n${zodiac.symbol} ${zodiac.name}")

                    toString()
                }
            )
            .setPositiveButton(
                resources.getString(R.string.action_ok)
            ) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSuccessSnackBar() {
        with(
            Snackbar.make(
                binding.root,
                "Success added",
                BaseTransientBottomBar.LENGTH_SHORT
            )
        ) {
            setAction(
                "OK"
            ) { _ ->
                dismiss()
            }

            show()
        }
    }

    private fun goBack() {
        findNavController()
            .navigate(
                R.id.action_SecondFragment_to_FirstFragment
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}