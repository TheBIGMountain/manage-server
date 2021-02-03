plugins {
  kotlin("js") version "1.4.21"
}

group = "com.dqpi"
version = "1.0-SNAPSHOT"

repositories {
  jcenter()
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

dependencies {
  implementation(npm("koa", "^2.11.0"))
  implementation(npm("koa-router", "^8.0.7"))
  implementation(npm("@koa/cors", "^3.0.0"))
  implementation(npm("mockjs", "^1.1.0"))
  implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.93-kotlin-1.3.70")
  implementation("org.jetbrains.kotlinx:kotlinx-nodejs:0.0.7")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

kotlin {
  js(LEGACY) {
    nodejs {
      binaries.executable()
    }
  }
}