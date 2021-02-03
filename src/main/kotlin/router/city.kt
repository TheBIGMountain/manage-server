package router

import entity.CityInfo
import entity.Page
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

fun city() = Router().apply {
  get<Page<CityInfo>>("/openCities") { it.getOpenCities() }
  get<String>("/city/open") { it.ok() }
  get<String>("/permission/edit") { it.ok() }
}

private fun Context<Page<CityInfo>>.getOpenCities() = this.let { ctx ->
  GlobalScope.launch {
    val cityList = getCityList()
    val current = ctx.asDynamic().request.query.page.unsafeCast<Number>()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject {
        page = current.toInt()
        pageSize = 10
        totalCount = 60
        pageCount = 6
        list = cityList
      }
    })
  }
}

fun Context<String>.ok() {
  body = Mock.mock(jsObject { code = "0"; result = "Ok" })
}

private suspend fun getCityList(): Array<CityInfo> {
  var autoId = 1
  var autoUserId = 10001
  val randomUser = Random.nextInt(1, 3).let {
    flow {
      repeat(it) { emit(jsObject<UserInfo> { userName = "@cname"; userId = autoUserId ++ }) }
    }.toList()
  }.toTypedArray()

  return flow<CityInfo> {
    repeat(10) {
      emit(jsObject {
        id = autoId ++
        name = "@city"
        mode = Random.nextInt(1, 3)
        opMode = Random.nextInt(1, 3)
        franchiseeId = 77
        franchiseeName = "松果自营"
        cityAdmins = randomUser
        openTime = "@datetime"
        sysUsername = "@cname"
        updateTime = "@datetime"
      })
    }
  }.toList().toTypedArray()
}




