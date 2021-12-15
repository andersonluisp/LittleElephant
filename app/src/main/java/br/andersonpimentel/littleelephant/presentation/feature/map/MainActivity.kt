package br.andersonpimentel.littleelephant.presentation.feature.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ActivityMainBinding
import br.andersonpimentel.littleelephant.databinding.TooltipLayoutBinding
import br.andersonpimentel.littleelephant.domain.usecases.GetMockedMapUseCase
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.MapTilesAdapter
import com.skydoves.balloon.*
import kotlinx.android.synthetic.main.tooltip_layout.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tooltipBinding: TooltipLayoutBinding
    private lateinit var tooltip: Balloon

    private val adapter = MapTilesAdapter { view, item ->
        if (!item.hasElephant) {
            if (tooltip.isShowing) {
                tooltip.dismiss()
            }
            tooltip.showAlignTop(view)
        }
    }

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

        setupTooltip()
        setupRecyclerview()
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
            adapter.items = GetMockedMapUseCase().execute()
            it.adapter = adapter
        }

    }
}