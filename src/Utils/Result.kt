package Utils

sealed class Result {
    data class Success(val message: String): Result()
    data class Error(val message: String): Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Result.Success -> println(result)
        is Result.Error -> println(result)
    }
}