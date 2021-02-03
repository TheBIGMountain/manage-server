package router

import entity.Page
import entity.User
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import modules.Context
import modules.Mock
import modules.Router
import kotlin.random.Random

fun user() = Router(jsObject { prefix = "/user" }).apply {
  get<String>("/add") { it.ok() }
  get<String>("/edit") { it.ok() }
  get<String>("/delete") { it.ok() }
  get<Page<User>>("/list") { it.userList() }
}

private fun Context<Page<User>>.userList() {
  val cur = this.asDynamic().request.query.page.unsafeCast<Number>()
  GlobalScope.launch {
    val userList = getUserList()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject {
        page = cur.toInt()
        pageSize = 10
        totalCount = 30
        list = userList
      }
    })
  }
}

private suspend fun getUserList(): Array<User> {
  return flow {
    var autoId = 1
    repeat(10) {
      emit(jsObject<User> {
        id = autoId ++
        username = "@cname"
        gender = Random.nextInt(1, 3)
        status = Random.nextInt(1, 6)
        hobby = Random.nextInt(1, 9)
        isMarried = Random.nextInt(0, 2)
        birthday = "2000-01-01"
        address = "北京市海淀区"
        time = "09:00:00"
      })
    }
  }.toList().toTypedArray()
}
