package org.task.organizer

import java.util.Date

import org.task.organizer.TaskPriority.TaskPriority

/** Created by jayonhuh on 11/8/15. A task created by the user.
  *
  * @constructor Create a new task with a name, description, due date, priority
  * level, and dependencies. 
  * @param name The name of the task.
  * @param description The description of the task.
  * @param dueDate The date the task is due.
  * @param priorityLevel A number from 1 to 10 that represents how urgent the
  * task is.
  * @param dependencies A list of other tasks that this task is dependent upon
  */
class Task(val name: String,
 		   val description: String,
 		   val dueDate: Date,
 		   val priorityLevel: TaskPriority,
 		   val dependencies: List[Task]) {

	// persist the creation time for comparison purposes
	val timeCreated: Long = System.currentTimeMillis


	/** Creates a task with only a name and description specified.
		*
		* @param name The name of the task.
		* @param description The description of the task
		*/
	def this(name: String, description: String) {
		this(name, description, null, TaskPriority.zero, List.empty)
	}

}


/** DueDateOrdering is an ordering of type tasks based on their due dates.
	*
	* This object is used for the PriorityQueue in TaskContainer to order its tasks.
	*/
object DueDateOrdering extends Ordering[Task] {
	def compare(a:Task, b:Task): Int = a.dueDate compareTo b.dueDate
}

/** PriorityOrdering is an ordering of type tasks based on their priority level from 1-10. See TaskPriority.scala.
	*
	* This object is used for the PriorityQueue in TaskContainer to order its tasks.
	*/
object PriorityOrdering extends Ordering[Task] {
	def compare(a:Task, b:Task): Int = b.priorityLevel compareTo a.priorityLevel
}