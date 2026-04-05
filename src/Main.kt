import Models.Task
import Services.TaskManager
import Utils.handleResult
import java.lang.Thread.sleep

fun main() {
    val taskManager = TaskManager()
    val task1 = Task(title = "Comprar pão", description =  "Comprar pão na padaria", isCompleted = false)
    val task2= Task(title ="Estudar Kotlin", description = "Estudar", isCompleted = false)
    val task3= Task(title = "Fazer execricios", description = "Academia", isCompleted = false)

    handleResult(taskManager.add(task1))

    handleResult(taskManager.add(task2))
    handleResult(taskManager.add(task3))

    taskManager.list()
    println(taskManager.findById(1))
    println(taskManager.findById(2))
    println(taskManager.findById(3))

    handleResult(taskManager.completeTask(task2))
    handleResult(taskManager.completeTask(task3))

    taskManager.list()
    taskManager.listCompleted()

    handleResult(taskManager.remove(task3))
    taskManager.list()
}