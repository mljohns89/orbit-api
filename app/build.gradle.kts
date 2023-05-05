plugins {
    scala
    application
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.scala)
    implementation(libs.guava)
    implementation(libs.bundles.http4s)
    implementation(libs.bundles.circe)

    testImplementation(libs.junit)
    testImplementation(libs.scalaTest)
    testImplementation(libs.scalaTestPlus)

    testRuntimeOnly(libs.scalaLangModules)

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.orbit.api.App")
}
