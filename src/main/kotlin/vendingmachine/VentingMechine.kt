package vendingmachine

import vendingMachine.Coin

class VendingMachine(
    private var changeInventory: Int,
    var userBalance: Int = 0,

    var numberOf500Coin: Int = 0,
    var numberOf100Coin: Int = 0,
    var numberOf50Coin: Int = 0,
    var numberOf10Coin: Int = 0
) {

    fun getRandomCoins() {
        while (changeInventory >= 10) {
            val randomCoin = Coin.getRandomCoin()
            if (randomCoin <= changeInventory) {
                addCoin(randomCoin)
                changeInventory -= randomCoin
            } else {
                getRandomCoins()
            }
        }
    }

    private fun addCoin(amount: Int) {
        when (amount) {
            500 -> numberOf500Coin += 1
            100 -> numberOf100Coin += 1
            50 -> numberOf50Coin += 1
            10 -> numberOf10Coin += 1
        }
    }

    fun calculateChange(): List<Int> {
        val number500 = calculateChange500()
        val number100 = calculateChange100()
        val number50 = calculateChange50()
        val number10 = calculateChange10()
        return listOf(number500, number100, number50, number10)
    }

    private fun calculateChange500(): Int {
        var count = 0
        while (userBalance >= 500 && numberOf500Coin > 0) {
            userBalance -= 500
            count += 1
            numberOf500Coin -= 1
        }
        return count
    }

    private fun calculateChange100(): Int {
        var count = 0
        while (userBalance >= 100 && numberOf100Coin > 0) {
            userBalance -= 100
            count += 1
            numberOf100Coin -= 1
        }
        return count
    }

    private fun calculateChange50(): Int {
        var count = 0
        while (userBalance >= 50 && numberOf50Coin > 0) {
            userBalance -= 50
            count += 1
            numberOf50Coin -= 1
        }
        return count
    }

    private fun calculateChange10(): Int {
        var count = 0
        while (userBalance >= 10 && numberOf10Coin > 0) {
            userBalance -= 10
            count += 1
            numberOf10Coin -= 1
        }
        return count
    }

}
