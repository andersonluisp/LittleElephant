package br.andersonpimentel.littleelephant.map.view.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.andersonpimentel.littleelephant.entities.Map
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ActivityMapBinding
import br.andersonpimentel.littleelephant.map.databinding.TooltipLayoutBinding
import br.andersonpimentel.littleelephant.map.util.setMessage
import br.andersonpimentel.littleelephant.map.util.showToolTip
import br.andersonpimentel.littleelephant.map.view.adapter.MapTilesAdapter
import br.andersonpimentel.littleelephant.map.view.viewmodel.MapViewModel
import br.andersonpimentel.littleelephant.map.view.viewmodel.ViewState
import com.skydoves.balloon.*
import kotlinx.android.synthetic.main.tooltip_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var tooltipBinding: TooltipLayoutBinding
    private lateinit var tooltip: Balloon
    private lateinit var adapter: MapTilesAdapter
    private val viewModel: MapViewModel by viewModel()
    private val apiBroadcastReceiver = ApiBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        tooltipBinding = TooltipLayoutBinding.bind(
            layoutInflater.inflate(
                R.layout.tooltip_layout,
                binding.root,
                false
            )
        )
        setContentView(binding.root)
        setupTooltip()
        setupObservers()
        viewModel.getMap()
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
            if(stepTile.message == null){
                Toast.makeText(this, getString(R.string.empty_step_message), Toast.LENGTH_SHORT)
                    .show()
            } else{
                if (!stepTile.hasElephant) {
                    viewModel.setLastElephantPosition(stepTile)
                    if (tooltip.isShowing) {
                        tooltip.dismiss()
                    }
                    tooltipBinding.setMessage(stepTile.message.toString())
                    tooltip.showToolTip(tooltipBinding.layoutBalloon, view)
                } else {
                    tooltipBinding.setMessage(stepTile.message.toString())
                    tooltip.showToolTip(tooltipBinding.layoutBalloon, view)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(this) {
            showViewState(it)
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

    private fun showViewState(viewState: ViewState<Map>) {
        binding.apply {
            when (viewState) {
                is ViewState.Success -> {
                    setupAdapter()
                    adapter.items = viewState.data.tiles
                    setupRecyclerview(viewState.data.spanCount)
                    lottieProgress.isVisible = false
                    lottieProgress.cancelAnimation()
                    rvMap.isVisible = true
                    noMap.isVisible = false
                    tvNoMap.isVisible = false
                    noMap.cancelAnimation()
                }
                is ViewState.Loading -> {
                    lottieProgress.isVisible = true
                    lottieProgress.playAnimation()
                    noMap.isVisible = false
                    tvNoMap.isVisible = false
                    noMap.cancelAnimation()
                    rvMap.isVisible = false
                }
                is ViewState.Failed -> {
                    lottieProgress.isVisible = false
                    lottieProgress.cancelAnimation()
                    tvNoMap.isVisible = true
                    noMap.playAnimation()
                    noMap.isVisible = true
                }
            }
        }
    }

    private fun setupRecyclerview(spanCount: Int) {
        binding.rvMap.let {
            it.layoutManager = GridLayoutManager(this, spanCount).apply {
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
            Toast.makeText(context, getString(R.string.api_ok), Toast.LENGTH_LONG).show()
        }
    }
}
