package br.andersonpimentel.littleelephant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.andersonpimentel.littleelephant.databinding.ActivityMainBinding
import br.andersonpimentel.littleelephant.map.view.activity.MapActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        startMapActivity()
    }

    private fun startMapActivity(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }
}
