import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val kotlinVersion = "1.9.20"
val serializationVersion = "1.6.0"
val ktorVersion = "2.3.3"
val logbackVersion = "1.4.11"
val kotlinWrappersVersion = "1.0.0-pre.637"
val mongodbVersion = "4.11.0"

// Projekt Metadata
group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    // Code für JVM, JS, iOS, Android usw.
    kotlin("multiplatform") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    application

    // Google App Engine + FAT JAR
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.google.cloud.tools.appengine") version "2.4.2"
}

repositories {
    // Hier werden die Libraries gezogen
    mavenCentral()
}

dependencies {
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongodbVersion")
}

kotlin {
    jvm {
        // Interop mit Java ermöglichen
        withJava()
    }
    js {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // KTOR CORE
                implementation("io.ktor:ktor-client-core:$ktorVersion")

                // KOTLIN SER + COROUTINES
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.mongodb:bson:$mongodbVersion")
            }
        }

        val jvmMain by getting {
            dependencies {
                // KTOR SER, CORE, NETTY
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-server-cors:$ktorVersion")
                implementation("io.ktor:ktor-server-compression:$ktorVersion")
                implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")

                // LOGGING
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                // React, React DOM + Wrappers, Kotlin React Emotion (CSS)
                implementation(project.dependencies.enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")

                // Video Player, Share Button
                implementation(npm("react-player", "2.12.0"))
                implementation(npm("react-share", "4.4.1"))

                // MUI
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-base-js:5.0.0-beta.18-pre.638")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-material-js:5.14.12-pre.639")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-js:5.14.12-pre.638")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons:5.14.12-pre.638")

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

// Application plugin Einstellungen, hier wird z.B. Ort der main() angegeben
application {
    mainClass.set("ServerKt")
    // für Development Mode
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

// include JS artifacts in any JAR we generate
tasks.named<Jar>("jvmJar").configure {
    val taskName = if (project.hasProperty("isProduction")
        || project.gradle.startParameter.taskNames.contains("installDist")
    ) {
        "jsBrowserProductionWebpack"
    } else {
        "jsBrowserDevelopmentWebpack"
    }
    val webpackTask = tasks.named<KotlinWebpack>(taskName)
    dependsOn(webpackTask)
    from(webpackTask.map { it.mainOutputFile.get().asFile }) // bring output file along into the JAR
    into("static")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}

distributions {
    main {
        contents {
            from("$buildDir/libs") {
                rename("${rootProject.name}-jvm", rootProject.name)
                into("lib")
            }
        }
    }
}

// Alias "installDist" as "stage" (for cloud providers)
tasks.register("stage") {
    dependsOn(tasks.named("installDist"))
    dependsOn("build")
}

tasks.named<JavaExec>("run").configure {
    classpath(tasks.named<Jar>("jvmJar")) // so that the JS artifacts generated by `jvmJar` can be found and served
}

// Für GCloud
configure<AppEngineAppYamlExtension> {
    stage {
        // Pfad zur Jar angeben, also kann in build dir bleiben...
        setArtifact("build/libs/${project.name}-all.jar")
    }
    deploy {
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}

