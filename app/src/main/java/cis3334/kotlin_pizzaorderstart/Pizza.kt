package cis3334.kotlin_pizzaorderstart

// --- File: Pizza.kt ---
// Keep enums readable for beginners
enum class PizzaSize { SMALL, MEDIUM, LARGE, XLARGE }
enum class Topping { CHICKEN, PEPPERONI, GREEN_PEPPERS }

// Simple model with a convenience description for the order text
data class Pizza(
    val size: PizzaSize = PizzaSize.MEDIUM,
    val toppings: Set<Topping> = emptySet()
) {
    fun description(): String {
        val sizeText = when (size) {
            PizzaSize.SMALL -> "Small"
            PizzaSize.MEDIUM -> "Medium"
            PizzaSize.LARGE -> "Large"
            PizzaSize.XLARGE -> "X-Large"
        }
        val toppingText = if (toppings.isEmpty()) "Cheese" else toppings
            .map {
                when (it) {
                    Topping.CHICKEN -> "Chicken"
                    Topping.PEPPERONI -> "Pepperoni"
                    Topping.GREEN_PEPPERS -> "Green Peppers"
                }
            }
            .joinToString(", ")
        return "$sizeText pizza with $toppingText"
    }
}