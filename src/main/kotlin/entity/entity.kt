package entity

interface Page<T> {
  var page: Int
  var pageSize: Int
  var totalCount: Int
  var pageCount: Int
  var list: Array<T>
}

interface User {
  var id: Int
  var username: String
  var gender: Int
  var status: Int
  var hobby: Int
  var isMarried: Int
  var birthday: String
  var address: String
  var time: String
}

interface BasicTable {
  var id: Int
  var username: String
  var age: Int
  var gender: Int
  var status: Int
  var hobby: Int
  var isMarried: Int
  var birthday: String
  var address: String
  var time: String
}

interface HighTable: BasicTable {
  var isMarried1: Int
  var isMarried2: Int
  var isMarried3: Int
  var isMarried4: Int
  var isMarried5: Int
  var isMarried6: Int
  var isMarried7: Int
  var isMarried8: Int
}

interface BikeInfo {
  var id: Int
  var bikeSn: String
  var battery: Int
  var startTime: String
  var location: String
}

interface Role {
  var id: Int
  var roleName: dynamic
  var status: Int
  var authorizeUsername: String
  var authorizeTime: String
  var createTime: String
  var menus: Array<String>
}

interface CityInfo {
  var id: Int
  var name: String
  var mode: Int
  var opMode: Int
  var franchiseeId: Int
  var franchiseeName: String
  var cityAdmins: Array<UserInfo>
  var openTime: String
  var sysUsername: String
  var updateTime: String
}

interface UserInfo {
  var userName: String
  var userId: Int
  var status: Int
}

interface BikeList {
  var totalCount: Int
  var bikeList: Array<String>
  var routeList: Array<String>
  var serviceList: Array<Service>
}

interface Service {
  var lon: String
  var lat: String
}

interface Position {
  var lon: String
  var lat: String
}

interface OrderDetail {
  var id: Int
  var userId: Int
  var userName: String
  var totalFee: Int
  var userPay: Int
  var status: Int
  var orderSn: dynamic
  var bikeSn: String
  var mode: Int
  var startLocation: String
  var endLocation: String
  var cityId: Int
  var mobile: dynamic
  var username: String
  var distance: Int
  var bikeGps: String
  var startTime: String
  var endTime: String
  var totalTime: Int
  var positionList: Array<Position>
  var area: Array<Service>
  var nplList: Array<Npl>
}

interface Npl {
  var id: Int
  var name: String
  var cityId: Int
  var type: Int
  var status: Int
  var mapPoint: String
  var mapPointArray: Array<String>
  var mapStatus: Int
  var creatorName: String
  var createTime: String
}
