package homeworks.semester3.homework1.task1.models

class Simulator(private val network: Network) {

    fun simulate(stepsQuantity: Int): String {
        if (stepsQuantity < 0) throw ArithmeticException("Steps has to be positive number")

        for (i in 0 until stepsQuantity) {
            network.infect()
        }

        return network.getStatistics()
    }
}
