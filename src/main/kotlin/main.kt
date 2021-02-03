import kotlinext.js.jsObject
import modules.Koa
import modules.Router
import modules.cors
import router.*

fun main() {
  val app = Koa().apply { use(cors()) }
  val router = Router(jsObject { prefix = "/api" })

  role().also { router.use(it.routes(), it.allowedMethods()) }
  order().also { router.use(it.routes(), it.allowedMethods()) }
  map().also { router.use(it.routes(), it.allowedMethods()) }
  city().also { router.use(it.routes(), it.allowedMethods()) }
  table().also { router.use(it.routes(), it.allowedMethods()) }
  user().also { router.use(it.routes(), it.allowedMethods()) }

  app.use(router.routes(), router.allowedMethods())
  app.listen(4000)
}