package router

import entity.BasicTable
import entity.HighTable
import entity.Page
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import modules.Context
import modules.Mock
import modules.Router
import kotlin.random.Random

fun table() = Router(jsObject { prefix = "/table" }).apply {
  get<Page<BasicTable>>("/list") { it.tableList(5) }
  get<Page<BasicTable>>("/list1") { it.tableList(10) }
  get<Page<HighTable>>("/highList") { it.highList() }
}

private fun Context<Page<BasicTable>>.tableList(count: Int) = this.also { ctx ->
  GlobalScope.launch {
    val tableList = getTableList(count)
    val current = ctx.asDynamic().request.query.page.unsafeCast<Number>()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject<Page<dynamic>> {
        page = current.toInt()
        pageSize = 10
        totalCount = 30
        list = tableList
      }
    })
  }
}

private fun Context<Page<HighTable>>.highList() {
  GlobalScope.launch {
    val highList = getHighList()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject {
        page = 1
        pageSize = 5
        totalCount = 30
        list = highList
      }
    })
  }
}

private suspend fun getTableList(count: Int): Array<BasicTable> {
  return flow {
    var autoId = 1
    repeat(count) {
      emit(jsObject<BasicTable> {
        id = autoId ++
        username = "@cname"
        age = Random.nextInt(22, 51)
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

private suspend fun getHighList(): Array<HighTable> {
  return flow {
    var autoId = 1
    repeat(5) {
      emit(jsObject<HighTable> {
        id = autoId ++
        username = "@cname"
        age = Random.nextInt(22, 51)
        gender = Random.nextInt(1, 3)
        status = Random.nextInt(1, 6)
        hobby = Random.nextInt(1, 9)
        isMarried1 = Random.nextInt(0, 2)
        isMarried2 = Random.nextInt(0, 2)
        isMarried3 = Random.nextInt(0, 2)
        isMarried4 = Random.nextInt(0, 2)
        isMarried5 = Random.nextInt(0, 2)
        isMarried6 = Random.nextInt(0, 2)
        isMarried7 = Random.nextInt(0, 2)
        isMarried8 = Random.nextInt(0, 2)
        birthday = "2000-01-01"
        address = "北京市海淀区"
        time = "09:00:00"
      })
    }
  }.toList().toTypedArray()
}
