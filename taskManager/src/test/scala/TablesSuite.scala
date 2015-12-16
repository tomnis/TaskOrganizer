import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import slick.driver.H2Driver.api._
import slick.jdbc.meta._

class TablesSuite extends FunSuite with BeforeAndAfter with ScalaFutures {
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  val tasks = TableQuery[Tasks]

  var db: Database = _

  def createSchema() =
    db.run(tasks.schema.create).futureValue

  def insertTask(): Int =
    db.run(tasks += (101, "email jan", "invite jan out for lunch", 7)).futureValue

  before { db = Database.forConfig("h2mem1") }

  test("Creating the Schema works") {
    createSchema()

    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("tasks")) == 1)
  }

  test("Inserting a Task works") {
    createSchema()

    val insertCount = insertTask()
    assert(insertCount == 1)
  }

  test("Query Tasks works") {
    createSchema()
    insertTask()
    val results = db.run(tasks.result).futureValue
    assert(results.size == 1)
    assert(results.head._1 == 101)
  }

  after { db.close }
}
