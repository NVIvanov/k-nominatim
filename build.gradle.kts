plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    signing
    id("io.codearte.nexus-staging") version "0.30.0"
}

group = "io.github.nvivanov"
version = "0.0.2-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.0")
    implementation("io.ktor:ktor-server-core:1.6.8")
    implementation("io.ktor:ktor-client-cio:1.6.8")
    implementation("io.ktor:ktor-client-auth:1.6.8")
    implementation("io.ktor:ktor-client-jackson:1.6.8")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "k-nominatim"
            groupId = "io.github.nvivanov"
            version = "0.0.2-SNAPSHOT"
            from(components["java"])
            pom {
                packaging = "jar"
                name.set("Kotlin Nominatim API client")
                url.set("https://github.com/NVIvanov/k-nominatim")
                description.set("Some description")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }

                scm {
                    connection.set("scm:https://github.com/NVIvanov/k-nominatim.git")
                    developerConnection.set("scm:git@github.com:NVIvanov/k-nominatim.git")
                    url.set("https://github.com/NVIvanov/k-nominatim")
                }

                developers {
                    developer {
                        id.set("NVIvanov")
                        name.set("Nikolay Ivanov")
                        email.set("ivanov.nikolay.711@gmail.com")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
            credentials {
                username = project.properties["sonatypeUsername"].toString()
                password = project.properties["sonatypePassword"].toString()
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

nexusStaging {
    serverUrl = "https://s01.oss.sonatype.org/service/local/"
    username = project.properties["sonatypeUsername"].toString()
    password = project.properties["sonatypePassword"].toString()
}