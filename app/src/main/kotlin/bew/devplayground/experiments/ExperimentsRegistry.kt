package bew.devplayground.experiments

import bew.devplayground.nav.Route
import kotlinx.serialization.Serializable

@Serializable
data class ExperimentEntry(
    val title: String,
    val description: String,
    val tags: List<String>,
    val route: Route,
)

// ------------------------------------------------------
// Data entries for each experiment

val experiments =
    listOf(
        ExperimentEntry(
            title = "Hello World",
            description = "Basic demo of some components o/",
            tags = listOf("first", "compose"),
            route = Route.HelloWorld,
        ),
    )
