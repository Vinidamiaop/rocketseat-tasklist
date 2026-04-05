package Models

import java.util.Date

data class Task(
    val title: String,
    val description: String?,
    var isCompleted: Boolean = false
) {
    val id: Int = nextId++
    val createdAt: Long = Date().time

    companion object {
        private var nextId: Int = 1
    }
}