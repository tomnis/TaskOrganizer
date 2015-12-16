import slick.driver.H2Driver.api._
import slick.lifted.{ProvenShape, ForeignKeyQuery}

class Tasks(tag: Tag)
  extends Table[(Int, String, String, Int)](tag, "tasks") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("task_id", O.PrimaryKey)
  def name: Rep[String] = column[String]("name")
  def description: Rep[String] = column[String]("description")
  def priority: Rep[Int] = column[Int]("priority")
  // def dueDate: Rep[String] = column[String]("due_date")
  // def dependencies

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, Int)] =
    (id, name, description, priority)
}
