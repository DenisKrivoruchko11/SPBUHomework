package tests.reExam

import java.lang.ArithmeticException

interface Ordered<T> {
    operator fun plus(other: T): T
    operator fun minus(other: T): T
    operator fun times(other: T): T

    fun sqrt(): T
    fun isZero(): Boolean
}

class Vector<T : Ordered<T>>(private val coordinates: List<T>) {

    private fun getSize(): Int {
        return coordinates.size
    }

    operator fun plus(vector: Vector<T>): Vector<T> {
        if (vector.getSize() != getSize()) throw ArithmeticException("Size of vectors must be equals.")

        val vectorCoordinates = vector.getCoordinates()
        val result = mutableListOf<T>()

        for (i in coordinates.indices) {
            result.add(coordinates[i] + vectorCoordinates[i])
        }

        return Vector(result)
    }

    operator fun minus(vector: Vector<T>): Vector<T> {
        if (vector.getSize() != getSize()) throw ArithmeticException("Size of vectors must be equals.")

        val vectorCoordinates = vector.getCoordinates()
        val result = mutableListOf<T>()

        for (i in coordinates.indices) {
            result.add(coordinates[i] - vectorCoordinates[i])
        }

        return Vector(result)
    }

    operator fun times(vector: Vector<T>): T {
        if (vector.getSize() != getSize()) throw ArithmeticException("Size of vectors must be equals.")

        val vectorCoordinates = vector.getCoordinates()

        var result = coordinates[coordinates.lastIndex] * vectorCoordinates[vectorCoordinates.lastIndex]

        for (i in 0 until coordinates.lastIndex) {
            result += coordinates[i] * vectorCoordinates[i]
        }

        return result
    }

    fun getCoordinates(): List<T> {
        return coordinates
    }

    fun norma(): T {
        var result = coordinates[coordinates.lastIndex] * coordinates[coordinates.lastIndex]

        for (i in 0 until coordinates.lastIndex) {
            result += coordinates[i] * coordinates[i]
        }

        return result.sqrt()
    }

    fun clone(): Vector<T> {
        return Vector(coordinates)
    }

    fun isZero(): Boolean {
        coordinates.forEach { if (!(it.isZero())) return false }

        return true
    }
}
