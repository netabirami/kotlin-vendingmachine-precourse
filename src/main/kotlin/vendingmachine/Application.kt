package vendingmachine

import vendingmachine.InputView.getBalanceAmount
import vendingmachine.InputView.getChangeInventory
import vendingmachine.InputView.getVendingMachineProducts
import vendingmachine.InputView.getPurchase
import vendingmachine.OutputView.announceCoinsInVendingMachine
import vendingmachine.OutputView.printChange
import vendingmachine.OutputView.printUnableToReturn
import vendingmachine.Product
import vendingmachine.VendingMachine

fun main() {
    val vendingMachine = initVendingMachine()
    val products = getVendingMachineProducts()

    processUserPurchases(vendingMachine, products)
    processChange(vendingMachine)
}

fun initVendingMachine(): VendingMachine {
    val vendingMachine = VendingMachine(getChangeInventory())
    vendingMachine.getRandomCoins()
    announceCoinsInVendingMachine(vendingMachine)
    return vendingMachine
}

fun processUserPurchases(vendingMachine: VendingMachine, products: List<Product>) {
    vendingMachine.userBalance = getBalanceAmount()
    println("Inserted amount: ${vendingMachine.userBalance} KRW")

    executePurchase(getCheapestPrice(products), vendingMachine, products)
}

fun processChange(vendingMachine: VendingMachine) {
    val change = vendingMachine.calculateChange()
    printChange(change)
    printUnableToReturn(vendingMachine.userBalance)
}

fun getPurchaseFromProducts(products: List<Product>, purchase: String): Product? {
    for (product in products) {
        if (product.name == purchase)
            return product
    }
    return null
}

fun getCheapestPrice(products: List<Product>): Int {
    var min = products[0].price
    for (product in products) {
        if (product.price < min)
            min = product.price
    }
    return min
}

fun executePurchase(minPrice: Int, vendingMachine: VendingMachine, products: List<Product>) {
    while (vendingMachine.userBalance >= minPrice) {
        val purchase = getPurchase()

        val product = getPurchaseFromProducts(products, purchase)
        if (product == null) {
            println("[ERROR] Product is not supported by vending machine.")
        } else {
            vendingMachine.userBalance -= product.price
            println("Inserted amount: ${vendingMachine.userBalance} KRW")
        }
    }
}

