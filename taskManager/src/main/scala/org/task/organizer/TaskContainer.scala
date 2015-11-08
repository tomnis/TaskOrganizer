package org.task.organizer

import scala.collection.mutable

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

  /** Creates a task container with only the name as an input.
    *
    * @param name The name of the task container.
    */
  def this(name: String) {
    this(name, DueDateOrdering)
  }

  /** Method that adds a task to the task container with no return type.
    *
    * @param inputTask The task that is being added to the task container.
    */
  def addTask(inputTask: Task): Unit = this.taskQueue += inputTask




}
