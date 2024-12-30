package com.example.coffeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapter.CategoryAdapter
import com.example.coffeshop.Adapter.OfferAdapter
import com.example.coffeshop.Adapter.PopularAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityMainBinding
import com.example.coffeshop.databinding.ViewholderCategoryBinding
import com.example.coffeshop.ui.theme.CoffeShopTheme

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()
        initPopular()
        initOffers()
        bottomMenu()



    }

    private fun bottomMenu() {
        binding.cartBtn.setOnClickListener{startActivity(Intent(this,CartActivity::class.java))}

    }

    private fun initOffers() {
        binding.progressBarOffer.visibility=View.VISIBLE
        viewModel.offer.observe(this, Observer {
            binding.recyclerViewOffer.layoutManager=LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewOffer.adapter=OfferAdapter(it)
            binding.progressBarOffer.visibility=View.GONE

        })
        viewModel.loadOffer()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.recyclerViewPopular.layoutManager=LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewPopular.adapter=PopularAdapter(it)
            binding.progressBarPopular.visibility=View.GONE
        })
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.recyclerViewCategory.layoutManager=
                LinearLayoutManager(
                    this@MainActivity
            ,LinearLayoutManager.HORIZONTAL,
                false)
            binding.recyclerViewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
}
