package id.azwar.mymoviecatalogue.ui.views

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import id.azwar.mymoviecatalogue.ui.components.navigation.RootNavigationScaffold
import id.azwar.mymoviecatalogue.viewModels.RootViewModel

@Composable
fun RootView(
    viewModel: RootViewModel = hiltViewModel(),
) {
    RootNavigationScaffold()
}
