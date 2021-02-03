package modules

@JsModule("koa")
@JsNonModule
external class Koa {
  fun use(routes: () -> dynamic, allowedMethods: () -> dynamic)
  fun listen(port: Int)
  fun use(middleWare: dynamic)
}

@JsModule("koa-router")
@JsNonModule
external class Router(option: RouterOption = definedExternally) {
  fun routes(): () -> dynamic
  fun allowedMethods(): () -> dynamic
  fun <T> get(url: String, func: (Context<T>) -> Unit)
  fun use(routes: () -> dynamic, allowedMethods: () -> dynamic)
}

@JsModule("@koa/cors")
@JsNonModule
external val cors: () -> dynamic


@JsModule("mockjs")
@JsNonModule
external object Mock {
  fun <T> mock(result: ResultVo<T>): ResultVo<T>
}

interface RouterOption {
  var prefix: String
}

interface Context<T> {
  var body: ResultVo<T>
}

interface ResultVo<T> {
  var code: String
  var result: T
}