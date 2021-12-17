package br.andersonpimentel.littleelephant.presentation.feature.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.data.remote.repository.MessagesRepository
import br.andersonpimentel.littleelephant.data.remote.source.RemoteDataSource
import br.andersonpimentel.littleelephant.databinding.ActivityMainBinding
import br.andersonpimentel.littleelephant.databinding.TooltipLayoutBinding
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetStepMessagesUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetStepTilesUseCase
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.MapTilesAdapter
import br.andersonpimentel.littleelephant.presentation.util.showToolTip
import com.skydoves.balloon.*
import kotlinx.android.synthetic.main.tooltip_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tooltipBinding: TooltipLayoutBinding
    private lateinit var tooltip: Balloon
    private val remoteDataSource: RemoteDataSource by inject()

    private val adapter = MapTilesAdapter { view, item ->
        if (!item.hasElephant) {
            if (tooltip.isShowing) {
                tooltip.dismiss()
            }
            tooltipBinding.tvMessage.text = item.message
            tooltip.showToolTip(view)
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
            val getMapUseCase = GetMapUseCase(GetStepMessagesUseCase(repository = MessagesRepository(remoteDataSource)), GetStepTilesUseCase())
            CoroutineScope(Dispatchers.IO).launch {
                getMapUseCase().collect { map ->
                    adapter.items = map.tiles
                    it.adapter = adapter
                }
            }
        }

    }
}