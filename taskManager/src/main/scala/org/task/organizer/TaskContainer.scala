package org.task.organizer

import scala.collection.mutable

/**
  * Created by jayonhuh on 11/8/15. The class that contains all tasks for a given account.
  */
class TaskContainer {

  // Instantiate a queue to contain all Task instances with due date ordering as the default sorting.
  val taskQueue: mutable.Queue[Task] = new mutable.Queue[Task]()(DueDateOrdering)


}
