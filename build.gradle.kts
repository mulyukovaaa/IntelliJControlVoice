fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)

plugins {
    java
    alias(libs.plugins.gradleIntelliJPlugin) // Gradle IntelliJ Plugin
}

group = "ru.itmo.devdays"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName = properties("pluginName")
    version = properties("platformVersion")
    type = properties("platformType")

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins = properties("platformPlugins").map { it.split(',').map(String::trim).filter(String::isNotEmpty) }

    version.set("2023.3.6")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.alphacephei:vosk:0.3.45")
    implementation("com.theokanning.openai-gpt3-java:service:0.14.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }
    withType<JavaExec> {
        jvmArgs("--enable-preview")
    }

    wrapper {
        gradleVersion = properties("gradleVersion").get()
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    patchPluginXml {
        version = properties("pluginVersion")
        sinceBuild = properties("pluginSinceBuild")
        untilBuild = properties("pluginUntilBuild")
    }
}
