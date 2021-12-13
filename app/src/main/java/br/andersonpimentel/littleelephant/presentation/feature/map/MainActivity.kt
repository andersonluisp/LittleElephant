package br.andersonpimentel.littleelephant.presentation.feature.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.andersonpimentel.littleelephant.databinding.ActivityMainBinding
import br.andersonpimentel.littleelephant.domain.usecases.GetMockedMapUseCase
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.MapTilesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = MapTilesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()
    }

    private fun setupRecyclerview(){
        binding.rvMap.let {
            it.layoutManager = GridLayoutManager(this, 4).apply {
                reverseLayout = true
            }
            adapter.items = GetMockedMapUseCase().execute()
            it.adapter = adapter
        }

    }
}