package pl.wojtek.koziol.pogoda.ui.home_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.home_fragment.*

import pl.wojtek.koziol.pogoda.R
import kotlin.apply

class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setupButton()

    }

    private fun setupButton() {
        apply.setOnClickListener {
            if (cityTextInput.text.isNullOrBlank()){
                Toast.makeText(this.context,"Pusto tu",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val action = HomeFragmentDirections.actionHomeFragmentToForecastFragment()
            action.cityName = cityTextInput.text.toString()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
