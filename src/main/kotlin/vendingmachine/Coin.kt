package vendingMachine

import camp.nextstep.edu.missionutils.Randoms

enum class Coin(val amount: Int) {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10), ;

    companion object {
        fun getRandomCoin(): Int {
            val amount =
                Randoms.pickNumberInList(listOf(COIN_500.amount, COIN_100.amount, COIN_50.amount, COIN_10.amount))
            return amount
        }
    }
}
