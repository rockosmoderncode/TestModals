package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*

private const val BOTTOMSHEETSECONDFRAGMENT = "SecondFragment"
private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    private val mSecondFragment = SecondFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /***
         * Qualquer resource pode ser definida em arquivos diferentes, não necessariamente em strings.xml,
         * colors.xml ou dimens.xml
         */
        Log.d(TAG, "onViewCreated: ${getString(R.string.string_in_another_file)}")

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                tryIsAdded()
                delay(1000)
                tryIsAdded()
            }
        }

        view.findViewById<Button>(R.id.button_first_second).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                tryCheckFragmentManager()
//                delay(1000)
                tryCheckFragmentManager()
            }
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                tryIsAddedAsMember()
                delay(1000)
                tryIsAddedAsMember()
            }
        }
    }

    private fun tryIsAdded() {
        val secondFragment = SecondFragment()
        if (!secondFragment.isAdded) {
            Log.d(TAG, "tryIsAdded: fragment added")
            secondFragment.show(childFragmentManager, BOTTOMSHEETSECONDFRAGMENT)
        }
    }

    private fun tryCheckFragmentManager() {
        if (childFragmentManager.findFragmentByTag(BOTTOMSHEETSECONDFRAGMENT) == null) {
            Log.d(TAG, "tryCheckFragmentManager: fragment added")
            SecondFragment().show(childFragmentManager, BOTTOMSHEETSECONDFRAGMENT)
        }
    }

    private fun tryIsAddedAsMember() {
        if (!mSecondFragment.isAdded) {
            Log.d(TAG, "tryIsAddedAsMember: fragment added")
            mSecondFragment.show(childFragmentManager, BOTTOMSHEETSECONDFRAGMENT)
        }
    }

}