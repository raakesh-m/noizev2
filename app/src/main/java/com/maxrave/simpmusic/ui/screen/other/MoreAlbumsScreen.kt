package com.maxrave.simpmusic.ui.screen.other

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.maxrave.simpmusic.R
import com.maxrave.simpmusic.extension.toAlbumsResult
import com.maxrave.simpmusic.ui.component.CenterLoadingBox
import com.maxrave.simpmusic.ui.component.EndOfPage
import com.maxrave.simpmusic.ui.component.HomeItemContentPlaylist
import com.maxrave.simpmusic.ui.component.RippleIconButton
import com.maxrave.simpmusic.ui.navigation.destination.list.AlbumDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.MoreAlbumsDestination
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.viewModel.MoreAlbumsUIState
import com.maxrave.simpmusic.viewModel.MoreAlbumsViewModel
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@UnstableApi
@Composable
fun MoreAlbumsScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    id: String? = null,
    type: String? = null,
    viewModel: MoreAlbumsViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val hazeState = rememberHazeState()

    LaunchedEffect(id, type) {
        Log.w("MoreAlbumsScreen", "id: $id, type: $type")
        if (id != null) {
            if (type != null) {
                when (type) {
                    MoreAlbumsDestination.ALBUM_TYPE -> {
                        viewModel.getAlbumMore(id)
                    }
                    MoreAlbumsDestination.SINGLE_TYPE -> {
                        viewModel.getSingleMore(id)
                    }
                }
            }
        }
    }

    Crossfade(targetState = uiState) { state ->
        when (state) {
            is MoreAlbumsUIState.Success -> {
                val data = state.albumItems
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .hazeSource(state = hazeState),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item(
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        Spacer(
                            Modifier
                                .size(
                                    innerPadding.calculateTopPadding() + 64.dp,
                                ),
                        )
                    }
                    items(data) { album ->
                        val data = album.toAlbumsResult()
                        HomeItemContentPlaylist(
                            data = data,
                            onClick = {
                                navController.navigate(
                                    AlbumDestination(
                                        browseId = album.id,
                                    ),
                                )
                            },
                        )
                    }
                    item(
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        EndOfPage()
                    }
                }
                TopAppBar(
                    modifier =
                        Modifier
                            .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin()) {
                                blurEnabled = true
                            },
                    title = {
                        Text(
                            text = state.title,
                            style = typo.titleMedium,
                            maxLines = 1,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(
                                        align = Alignment.CenterVertically,
                                    ).basicMarquee(
                                        iterations = Int.MAX_VALUE,
                                        animationMode = MarqueeAnimationMode.Immediately,
                                    ).focusable(),
                        )
                    },
                    navigationIcon = {
                        Box(Modifier.padding(horizontal = 5.dp)) {
                            RippleIconButton(
                                R.drawable.baseline_arrow_back_ios_new_24,
                                Modifier
                                    .size(32.dp),
                                true,
                            ) {
                                navController.navigateUp()
                            }
                        }
                    },
                    colors =
                        TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
                )
            }
            is MoreAlbumsUIState.Error -> {
                Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
            }
            MoreAlbumsUIState.Loading -> {
                CenterLoadingBox(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                )
            }
        }
    }
}