package router

import entity.Page
import entity.Role
import entity.UserInfo
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import modules.Context
import modules.Mock
import modules.Router
import kotlin.random.Random

fun role() = Router(jsObject { prefix = "/role" }).apply {
  get<Page<Role>>("/list") { it.roleList() }
  get<String>("/create") { it.ok() }
  get<Array<UserInfo>>("/userList") { it.userList() }
  get<String>("/userRoleEdit") { it.ok() }
}

private fun Context<Page<Role>>.roleList() = this.also { ctx ->
  GlobalScope.launch {
    val roleList = getRoleList()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject {
        page = ctx.asDynamic().request.query.page.unsafeCast<Number>().toInt()
        pageSize = 10
        totalCount = 25
        pageCount = 3
        list = roleList
      }
    })
  }
}

private fun Context<Array<UserInfo>>.userList() {
  GlobalScope.launch {
    val userList = getUserList()
    body = Mock.mock(jsObject {
      code = "0"
      result = userList
    })
  }
}

private suspend fun getUserList(): Array<UserInfo> {
  return flow {
    var autoId = 1
    repeat(20) {
      emit(jsObject<UserInfo> {
        status = Random.nextInt(0, 2)
        userId = autoId ++
        userName = "@cname"
      })
    }
  }.toList().toTypedArray()
}

private suspend fun getRoleList(): Array<Role> {
  return flow {
    var autoId = 1
    repeat(7) {
      emit(jsObject<Role> {
        id = autoId ++
        roleName = js("/(管理人员)|(客服专员)|(财务专员)|(市场专员)|(人力专员)|(研发)|(测试)|(系统管理员)/")
        status = Random.nextInt(0, 2)
        authorizeUsername = "@cname"
        authorizeTime = "1521270166000"
        createTime = "1499305790000"
        menus = arrayOf("/home", "/ui/buttons", "/ui/modals", "/ui/loadings", "/ui/notification", "/ui/messages", "/ui/tabs", "/ui/gallery", "/ui/carousel", "/ui")
      })
    }
  }.toList().toTypedArray()
}