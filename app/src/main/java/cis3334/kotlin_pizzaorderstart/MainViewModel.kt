package cis3334.kotlin_pizzaorderstart

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Very simple ViewModel to mirror the Java version, adapted for Compose.
 * - Holds current UI selections
 * - Builds a Pizza and adds it to the PizzaOrder
 * - Exposes a single String (orderText) that the UI can show
 *
 * Kept intentionally small and beginner-friendly (no prices, no validation yet).
 */
class MainViewModel : ViewModel() {


    // Current selections
    var size by mutableStateOf(PizzaSize.MEDIUM)
        private set


    var chicken by mutableStateOf(false)
        private set
    var pepperoni by mutableStateOf(false)
        private set
    var greenPeppers by mutableStateOf(false)
        private set


    // Order state
    private val order = PizzaOrder()
    var orderText by mutableStateOf("")
        private set


    // --- Events (called by the UI) ---
    fun setSizeIndex(index: Int) {
        size = when (index.coerceIn(0, 3)) {
            0 -> PizzaSize.SMALL
            1 -> PizzaSize.MEDIUM
            2 -> PizzaSize.LARGE
            else -> PizzaSize.XLARGE
        }
    }


    fun toggleChicken() { chicken = !chicken }
    fun togglePepperoni() { pepperoni = !pepperoni }
    fun toggleGreenPeppers() { greenPeppers = !greenPeppers }


    fun addToOrder() {
        val tops = buildSet {
            if (chicken) add(Topping.CHICKEN)
            if (pepperoni) add(Topping.PEPPERONI)
            if (greenPeppers) add(Topping.GREEN_PEPPERS)
        }
        val pizza = Pizza(size = size, toppings = tops)
        order.add(pizza)
        orderText = order.asText() + if (order.count() > 0) "\n" else ""
        resetSelectionsForNext()
    }


    fun placeOrder() {
        order.clear()
        orderText = ""
        resetSelectionsToDefaults()
    }


    // --- Helpers ---
    private fun resetSelectionsForNext() {
// Keep the same size; clear toppings for convenience
        chicken = false
        pepperoni = false
        greenPeppers = false
    }


    private fun resetSelectionsToDefaults() {
        size = PizzaSize.MEDIUM
        chicken = false
        pepperoni = false
        greenPeppers = false
    }
}