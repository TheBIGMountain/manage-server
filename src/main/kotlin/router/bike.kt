package router

import entity.BikeList
import entity.Service
import kotlinext.js.jsObject
import modules.Context
import modules.Mock
import modules.Router

fun map() = Router().apply {
  get<BikeList>("/map/bikeList") { it.mapBikeList() }
}

private fun Context<BikeList>.mapBikeList() {
  body = Mock.mock(jsObject {
    code = "0"
    result = jsObject {
      totalCount = 100
      bikeList = arrayOf("116.356619,40.017782", "116.437107,39.975331", "116.34972,40.070808", "116.323849,39.964714", "116.404912,40.015129", "116.365243,39.958078")
      routeList = arrayOf("116.353101,40.067835", "116.357701,40.053699", "116.374086,40.027626", "116.397801,40.01641")
      serviceList = getServiceList()
    }
  })
}

fun getServiceList(): Array<Service> {
  return arrayOf(
    jsObject { lon = "116.274737"; lat = "40.139759" },
    jsObject { lon = "116.316562"; lat = "40.144943" },
    jsObject { lon = "116.351631"; lat = "40.129498" },
    jsObject { lon = "116.390582"; lat = "40.082481" },
    jsObject { lon = "116.38742"; lat = "40.01065" },
    jsObject { lon = "116.414297"; lat = "40.01181" },
    jsObject { lon = "116.696242"; lat = "39.964035" },
    jsObject { lon = "116.494498"; lat = "39.851306" },
    jsObject { lon = "116.238086"; lat = "39.848647" },
    jsObject { lon = "116.189454"; lat = "39.999418" },
    jsObject { lon = "116.244646"; lat = "39.990574" },
    jsObject { lon = "116.281441"; lat = "40.008703" },
    jsObject { lon = "116.271092"; lat = "40.142201" },
    jsObject { lon = "116.271092"; lat = "40.142201" },
  )
}