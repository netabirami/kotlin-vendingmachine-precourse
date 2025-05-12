package vendingmachine

import camp.nextstep.edu.missionutils.Console

object InputView {

    private fun readInput(): String {
        return Console.readLine()
    }

    private fun validateUserInputNotEmpty(input: String?) {
        if (input.isNullOrBlank()) {
            throw IllegalArgumentException("[ERROR] Input can not be empty.")
        }
    }

    private fun validateDivisibleByTen(input: Int) {
        if (input % 10 != 0) {
            throw IllegalArgumentException("[ERROR] Input must be divisible by 10.")
        }
    }

    private fun tryToInt(input: String): Int {
        try {
            return input.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("[ERROR] Input must be a number.")
        }
    }

    private fun readPurchase(): String {
        println("Please enter the name of the product to purchase:")
        val input = readInput()
        validateUserInputNotEmpty(input)
        return input
    }

    private fun readBalanceAmount(): Int {
        println("Please enter the amount of money:")
        val input = readInput()
        validateUserInputNotEmpty(input)
        val amount = tryToInt(input)
        validateDivisibleByTen(amount)
        return amount
    }

    private fun readAndValidateChangeInventory(): Int {
        println("Enter the amount the vending machine holds:")
        val input = readInput()
        validateUserInputNotEmpty(input)
        val amount = tryToInt(input)
        validateDivisibleByTen(amount)
        return amount
    }

    fun getChangeInventory(): Int {
        while (true) {
            try {
                return readAndValidateChangeInventory()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun tryToProduct(input: String): Product {
        val product = input.removeSurrounding("[", "]").split(",")
        val name = product[0]
        val price = product[1].toInt()
        val quantity = product[2].toInt()
        return Product(name, price, quantity)
    }

    private fun tryToProducts(input: String): List<Product> {
        val result = mutableListOf<Product>()
        try {
            val products = input.split(";")
            for (product in products) {
                result.add(tryToProduct(product))
            }
            return result
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("[ERROR] Price and amount must be valid numbers.")
        }
    }

    private fun validatePrices(products: List<Product>) {
        for (product in products) {
            if (product.price < 100 || product.price % 10 != 0)
                throw IllegalArgumentException("[ERROR] Prices must be at least 100 KRW and multiples of 10.")
        }
    }

    private fun readAndValidateProducts(): List<Product> {
        println("Enter product names, prices, and quantities:")
        val input = readInput()
        validateUserInputNotEmpty(input)
        val products = tryToProducts(input)
        validatePrices(products)
        return products
    }

    fun getVendingMachineProducts(): List<Product> {
        while (true) {
            try {
                return readAndValidateProducts()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getBalanceAmount(): Int {
        while (true) {
            try {
                return readBalanceAmount()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getPurchase(): String {
        while (true) {
            try {
                return readPurchase()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}