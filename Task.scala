
/** A task created by the user.
  *
  * @constructor create a new task with a name, description, due date, priority
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

	


}