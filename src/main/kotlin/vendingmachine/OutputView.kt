package vendingmachine
object OutputView {

    fun announceCoinsInVendingMachine(vendingMachine: VendingMachine) {
        println("Coins in the vending machine:")
        printNumberOfCoin(500, vendingMachine.numberOf500Coin)
        printNumberOfCoin(100, vendingMachine.numberOf100Coin)
        printNumberOfCoin(50, vendingMachine.numberOf50Coin)
        printNumberOfCoin(10, vendingMachine.numberOf10Coin)
    }

    fun printNumberOfCoin(coin: Int, number: Int) {
        when (coin) {
            500 -> println("500 KRW - $number")
            100 -> println("100 KRW - $number")
            50 -> println("50 KRW - $number")
            10 -> println("10 KRW - $number")
        }
    }

    fun printChange(change: List<Int>) {
        println("Change returned:")
        if (change[0] != 0) {
            println("500 KRW - ${change[0]}")
        }
        if (change[1] != 0) {
            println("100 KRW - ${change[1]}")
        }
        if (change[2] != 0) {
            println("50 KRW - ${change[2]}")
        }
        if (change[3] != 0) {
            println("10 KRW - ${change[3]}")
        }
    }

    fun printUnableToReturn(amount: Int) {
        println("Unable to return: $amount KRW")
    }
}