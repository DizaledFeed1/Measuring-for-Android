import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class SQL() {

    private val url = "jdbc:mysql://87.228.18.70:3306/mr.dverkin" // Проверьте, что название базы данных корректно и без пробелов
    private val user = "root"
    private val password = "Porfik 10" // Убедитесь, что пароль корректен и не содержит лишних пробелов

    private var con: Connection? = null
    private var statement: Statement? = null
    private var resultSet: ResultSet? = null

    fun connect() {
        try {
            con = DriverManager.getConnection(url, user, password)
            println("Connection successful")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun executeQuery(query: String): ResultSet? {
        try {
            statement = con?.createStatement()
            resultSet = statement?.executeQuery(query)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return resultSet
    }

    fun close() {
        try {
            resultSet?.close()
            statement?.close()
            con?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
