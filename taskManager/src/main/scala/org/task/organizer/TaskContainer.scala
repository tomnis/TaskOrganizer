package org.task.organizer

import java.util.{Calendar, Date}

import org.task.organizer.TaskPriority.TaskPriority

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by jayonhuh on 11/8/15. The class that contains all tasks for a given account.
  *
  * @constructor Creates a new task container with a name and an ordering type of tasks specified.
  * @param name The name of the task container
  * @param orderType The ordering to order the tasks in the container.
  */
class TaskContainer(var name: String, var orderType: Ordering[Task]) {

  // Instantiate a queue to contain all Task instances with due date ordering as the default sorting.
  val taskQueue: mutable.PriorityQueue[Task] = new mutable.PriorityQueue[Task]()(this.orderType)

  // Instantiate a ListBuffer that will contain all completed tasks.
  val completedTaskList: mutable.ListBuffer[Task] = new ListBuffer[Task]

  /** Creates a task container with only the name as an input.
    *
    * @param name The name of the task container.
    */
  def this(name: String) {
    this(name, DueDateOrdering)
  }

  /** Method that creates a task and adds it to the task container
    *
    *@param name The name of the task to be created
    *@param description Description of the task
    *@param dueDate The Due date of the task
    *@param priorityLevel The level of priority of the task
    *@param dependencies The other tasks that are dependencies of the task to be created
    */
  def createAndAddTask(name: String,
                       description: String,
                       dueDate: Calendar,
                       priorityLevel: TaskPriority,
                       dependencies: List[Task]): Unit = {

    // Call the Task constructor.
    val newTask: Task = new Task(name, description, dueDate, priorityLevel, dependencies)

    // Add it to the taskQueue using the addTask method.
    this.addTask(newTask)

  }

  /** Completion logic when a task is completed.
    *
    * The input of this method is the task that has been completed by the user.
    * The task will first call it's own completion logic, where its 'complete' flag
    * is set as true and a Calendar instance is created to represent its complete time.
    * then the task will put be in the TaskContainer's CompletedTasks.
    *
    * @param completedTask task that has been completed by the user
    */
  def completeTask(completedTask: Task): Unit = {

    // Run completion logic on the task
    completedTask.completeTask()

    // Remove task from the taskQueue
    this.removeTask(completedTask)

    // add the completed task to the completedTaskList
    completedTaskList += completedTask

  }

  /** Method that adds a task to the task container with no return type.
    *
    * @param inputTask The task that is being added to the task container.
    */
  def addTask(inputTask: Task): Unit = this.taskQueue += inputTask

  /** Method to remove a task element from the priority queue of a TaskContainer
    *
    * @param taskToBeRemoved The task that will be removed from the priority queue.
    */
  def removeTask(taskToBeRemoved: Task): Unit = {

    // Convert the TaskContainer's queue in to a list for node deletion.
    val newList: mutable.Buffer[Task] = this.taskQueue.toBuffer

    // Remove task specified from the list
    newList -= taskToBeRemoved

    // Clear the priority queue for the TaskContainer
    this.taskQueue.clear

    // Loop through each task in newList and add them to our queue
    for (task: Task <- newList) {
      this.taskQueue += task
    }

  }

}
