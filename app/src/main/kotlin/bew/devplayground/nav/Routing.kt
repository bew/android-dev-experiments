package bew.devplayground.nav

import kotlinx.serialization.Serializable

// NOTE: `sealed` enforces that all subclasses are known at compile-time, and that all subclasses
// are in the current package (cannot be outside).
// NOTE: In this case its subclasses are defined _inside_ it for nicer access (`Route.Foo`), but
// it's not a requirement.
sealed interface Route {
    @Serializable
    data object Home : Route

    // ------------------------------------------------------
    // Navigation routes for each experiment

    @Serializable
    data object HelloWorld : Route
}
