// WHAT:
// The single navigation graph for the app.
// NavHost observes the back stack and swaps the composable for the current route.
// Each `composable<Route.Foo>` block declares one destination.

package bew.devplayground.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bew.devplayground.experiments.helloworld.ui.hello.HelloScreen
import bew.devplayground.home.HomeScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    // The NavController is the central coordinator for managing navigation between destinations.
    // The controller offers methods for navigating between destinations, handling deep links,
    // managing the back stack, and more.
    // ref: https://developer.android.com/guide/navigation
    val navController = rememberNavController()

    // A NavHost is a container that shows one destination at a time.
    // It manages a back stack: navigate() pushes a new entry, the back button pops it.
    // startDestination is what's shown first.
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier,
    ) {
        // Each composable<Route.Foo> block registers one destination in the graph.
        // NavHost will only render the block matching the current top of the back stack.
        // 👉 Every Route that can ever be navigated to must have a block here.

        // HomeScreen needs to trigger navigation, so we pass navController.navigate as a lambda.
        // Screens don't hold a navController reference directly, which keeps them decoupled and
        // easier to preview/test.
        composable<Route.Home> {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }

        // (HelloScreen has no outgoing navigation, so no lambda needed)
        composable<Route.HelloWorld> {
            HelloScreen()
        }
    }
}
