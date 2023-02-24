package com.example.testkuadran.module.screen.first

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testkuadran.R
import com.example.testkuadran.core.util.convertLongToTime
import com.example.testkuadran.core.util.dp
import com.example.testkuadran.databinding.FragmentFirstBinding
import com.example.testkuadran.module.data.db.UserDB
import com.example.testkuadran.module.data.db.ZodiacDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), FirstFragmentContract {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val userDB by lazy {
        UserDB(requireContext())
    }
    private val zodiacDB by lazy {
        ZodiacDB()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding
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
        initData()
        initUI()
        initAction()
        super.onViewCreated(
            view,
            savedInstanceState
        )
    }

    override fun initData() {

    }

    override fun initUI() {
        binding.fab.visibility = View.VISIBLE

        binding.fab.shrink()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            binding.fab.extend()
            delay(3000)
            binding.fab.shrink()
        }

        displayZodiacList()
    }

    override fun initAction() {
        binding.fab.setOnClickListener {
            goToSecondFragment()
        }
    }

    private fun displayZodiacList() {
        zodiacDB.getZodiacList()
            .map {
                TableRow(requireContext())
                    .apply {

                        addView(
                            TextView(
                                requireContext()
                            ).apply {
                                text = convertLongToTime(
                                    it.date.first,
                                    "dd-MMMM"
                                )
                                layoutParams = TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                                )
                                setPadding(8.dp())
                            }
                        )

                        addView(
                            TextView(
                                requireContext()
                            ).apply {
                                text = convertLongToTime(
                                    it.date.second,
                                    "dd-MMMM"
                                )
                                layoutParams = TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                                    )
                                setPadding(8.dp())
                            }
                        )

                        addView(
                            TextView(
                                requireContext()
                            ).apply {
                                text = "${it.symbol} ${it.name}"
                                layoutParams = TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                                )
                                setPadding(8.dp())
                            }
                        )
                    }
            }
            .iterator()
            .forEach {
                binding.table.addView(it)
            }
    }

    private fun goToSecondFragment() {
        findNavController()
            .navigate(
                R.id.action_FirstFragment_to_SecondFragment
            )

        binding.fab.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}