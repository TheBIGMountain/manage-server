package router

import entity.BikeInfo
import entity.OrderDetail
import entity.Page
import entity.Position
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import modules.Context
import modules.Mock
import modules.Router
import kotlin.random.Random

fun order() = Router(jsObject { prefix = "/order" }).apply {
  get<OrderDetail>("/detail") { it.orderDetail() }
  get<Page<OrderDetail>>("/list") { it.orderList() }
  get<BikeInfo>("/bikeInfo") { it.bikeInfo() }
  get<String>("/finish") { it.ok() }
}

private fun Context<OrderDetail>.orderDetail() {
  body = Mock.mock(jsObject {
    code = "0"
    result = jsObject {
      status = 2
      orderSn = "T1803244422704080JGJI"
      bikeSn = "802410001"
      mode = Random.nextInt(1, 3)
      startLocation = "北京市昌平区回龙观东大街"
      endLocation = "北京市海淀区奥林匹克公园"
      cityId = 1
      mobile = "13597482075"
      username = "@cname"
      distance = 10000
      bikeGps = "116.398806,40.008637"
      startTime = "1521865027000"
      endTime = "1521865251000"
      totalTime = 224
      positionList = getPositionList()
      area = getServiceList()
      nplList = arrayOf(jsObject {
        id = 8265
        name = "北辰世纪中心-a座"
        cityId = 1
        type = 3
        status = 0
        mapPoint = "116.39338796444|40.008120315215;116.39494038009002|40.008177258745;116.39496911688|40.006268094213;116.39512457763|40.004256795877;116.39360214742|40.004222412241;116.39357190147|40.005075745782;116.39351397873|40.005836165232;116.39338796444|40.008120315215"
        mapPointArray = arrayOf("116.39338796444|40.008120315215", "116.396053|40.008273", "116.396448|40.006338", "116.396915|40.004266", "116.39192|40.004072", "116.391525|40.004984", "116.391381|40.005924", "116.391166|40.007913")
        mapStatus = 1
        creatorName = "赵程程"
        createTime = "1507863539000"
      })
    }
  })
}

private fun Context<Page<OrderDetail>>.orderList() = this.also { ctx ->
  GlobalScope.launch {
    val orderList = getOrderList()
    val current = ctx.asDynamic().request.query.page.unsafeCast<Number>()
    body = Mock.mock(jsObject {
      code = "0"
      result = jsObject {
        page = current.toInt()
        pageSize = 10
        totalCount = 85
        pageCount = 9
        list = orderList
      }
    })
  }
}

private fun Context<BikeInfo>.bikeInfo() {
  body = Mock.mock(jsObject {
    code = "0"
    result = jsObject {
      id = 27296
      bikeSn = "800116116"
      battery = 100
      startTime = "@datetime"
      location = "北京市海淀区奥林匹克公园"
    }
  })
}

private suspend fun getOrderList(): Array<OrderDetail> {
  var autoId = 1
  return flow {
    repeat(10) {
      emit(jsObject<OrderDetail> {
        id = autoId ++
        orderSn = js("/T180[0-9]{6}/")
        bikeSn = "800116090"
        userId = 908352
        userName = "@cname"
        mobile = js("/1[0-9]{10}/")
        distance = 2000
        totalTime = 4000
        status = Random.nextInt(1, 2)
        startTime = "@datetime"
        endTime = "@datetime"
        totalFee = 1000
        userPay = 300
      })
    }
  }.toList().toTypedArray()
}

private fun getPositionList(): Array<Position> {
  return arrayOf(
    jsObject { lon = "116.361221"; lat = "40.043776" },
    jsObject { lon = "116.363736"; lat = "40.038086" },
    jsObject { lon = "116.364599"; lat = "40.036484" },
    jsObject { lon = "116.373438"; lat = "40.03538" },
    jsObject { lon = "116.377966"; lat = "40.036263" },
    jsObject { lon = "116.379762"; lat = "40.03654" },
    jsObject { lon = "116.38084"; lat = "40.033225" },
    jsObject { lon = "116.38084"; lat = "40.029413" },
    jsObject { lon = "116.381343"; lat = "40.021291" },
    jsObject { lon = "116.381846"; lat = "40.015821" },
    jsObject { lon = "116.382637"; lat = "40.008084" },
    jsObject { lon = "116.398806"; lat = "40.008637" },
  )
}
