package Services

import Models.Task
import Utils.Result
import jdk.internal.org.jline.utils.ShutdownHooks
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskManager {
    val tasks = mutableListOf<Task>()

    fun add(task: Task): Result {
        require(task.title.isNotEmpty()) { "Title cannot be empty" }
        tasks.add(task)
        return Result.Success("Tarefa adicionada com sucesso! ID: ${task.id}")
    }

    fun list() {
        println("Tarefas:")
        for(task in tasks) {
            val (title, _, isCompleted) = task
            println("${task.id} - $title, $isCompleted")
        }
    }

    fun listCompleted() {
        val completedTasks = tasks.filter { it.isCompleted }
        require(completedTasks.isNotEmpty()) { "No tasks were completed." }
        println("Tarefas concluídas:")
        for(task in completedTasks) {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("pt", "BR"))
            val dataFormatada = sdf.format(Date(task.createdAt))
            println("ID: ${task?.id} | Titulo: ${task?.title} | Concluido: ${task?.isCompleted} | Criado em: ${dataFormatada}")
        }

    }

    fun findById(id: Int): Result {
        val task = tasks.find { it.id == id }
        if (task == null) {
            return Result.Error("Task não encontrada")
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("pt", "BR"))
        val dataFormatada = sdf.format(Date(task.createdAt))
        return Result.Success("ID: ${task?.id} | Titulo: ${task?.title} | Concluido: ${task?.isCompleted} | Criado em: ${dataFormatada}")
    }

    fun completeTask(task: Task): Result {
        require(task.title.isNotEmpty()) { "Task não pode ser null" }
        val currentTask = tasks.find { it.id == task.id }
        if (currentTask == null) {
            return Result.Error("Não foi possivel concluir tarefa ID ${task.id}")
        }
        task.isCompleted = true

        return Result.Success("Status da tarefa ID ${task.id} atualizado para true")
    }

    fun remove(task: Task): Result {
        require(task.title.isNotEmpty()) { "Task não pode ser null" }
        val currentTask = tasks.find { it.id == task.id }
        if (currentTask == null) {
            return Result.Error("Não foi possivel remover tarefa ID ${task.id}")
        }

        tasks.remove(currentTask)
        return Result.Success("Tarefa com ID ${currentTask.id} removida com sucesso")
    }
}