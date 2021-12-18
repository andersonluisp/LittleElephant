package br.andersonpimentel.littleelephant.presentation.feature.map

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ActivityMainBinding
import br.andersonpimentel.littleelephant.databinding.TooltipLayoutBinding
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.MapTilesAdapter
import br.andersonpimentel.littleelephant.presentation.feature.viewmodel.ViewState
import br.andersonpimentel.littleelephant.presentation.util.setMessage
import br.andersonpimentel.littleelephant.presentation.util.showToolTip
import com.skydoves.balloon.*
import kotlinx.android.synthetic.main.tooltip_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tooltipBinding: TooltipLayoutBinding
    private lateinit var tooltip: Balloon
    private lateinit var adapter: MapTilesAdapter
    private val viewModel: MainViewModel by viewModel()
    private val apiBroadcastReceiver = ApiBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        tooltipBinding = TooltipLayoutBinding.bind(
            layoutInflater.inflate(
                R.layout.tooltip_layout,
                binding.root,
                false
            )
        )
        setContentView(binding.root)
        setupAdapter()
        setupTooltip()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("ShowToast")
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(apiBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(apiBroadcastReceiver)
    }


    private fun setupAdapter() {
        adapter = MapTilesAdapter { view, stepTile ->
            if (!stepTile.hasElephant) {
                tooltipBinding.setMessage(stepTile.message.toString())
                viewModel.setLastElephantPosition(stepTile)
                if (tooltip.isShowing) {
                    tooltip.dismiss()
                }
                tooltip.showToolTip(view)
            } else {
                tooltip.showToolTip(view)
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    adapter.items = it.data.tiles
                    setupRecyclerview()
                }
            }
        }
    }

    private fun setupTooltip(): Balloon {
        tooltip = createBalloon(this) {
            setArrowSize(10)
            setArrowPosition(0.5f)
            setArrowOrientation(ArrowOrientation.TOP)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setCornerRadius(10f)
            setAlpha(0.9f)
            setPaddingHorizontal(10)
            setBackgroundColorResource(R.color.white)
            setBalloonAnimation(BalloonAnimation.FADE)
            setDismissWhenTouchOutside(false)
            setLifecycleOwner(lifecycleOwner)
            setLayout(tooltipBinding.root)
        }
        setupTooltipListener(tooltip)
        return tooltip
    }

    private fun setupTooltipListener(balloon: Balloon) {
        tooltipBinding.root.button.setOnClickListener {
            balloon.dismiss()
        }
    }

    private fun setupRecyclerview() {
        binding.rvMap.let {
            it.layoutManager = GridLayoutManager(this, 4).apply {
                reverseLayout = true
            }
            it.itemAnimator = null
            it.adapter = adapter
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    tooltip.dismiss()
                }
            })
        }
    }

    inner class ApiBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Api OK", Toast.LENGTH_LONG).show()
        }
    }
}
