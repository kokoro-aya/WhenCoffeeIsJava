import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java")
    id("com.vanniktech.maven.publish") version ("0.30.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "moe.irony.java.when"
version = "0.9.0"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("moe.irony.java.when", "when-coffee-is-java", "0.9.0")
    pom {
        name.set("When the Coffee is Java")
        description.set("A type-safe Java 8 library to simulate the Kotlin \"when\" construct")
        inceptionYear.set("2024")
        url.set("https://github.com/kokoro-aya/WhenCoffeeIsJava/")
        licenses {
            license {
                name.set("BSD-3-Clause license")
                url.set("https://opensource.org/license/bsd-3-clause/")
                distribution.set("https://github.com/kokoro-aya/WhenCoffeeIsJava/blob/main/LICENSE.md")
            }
        }
        developers {
            developer {
                id.set("kokoro-aya")
                name.set("irony")
                url.set("https://github.com/kokoro-aya")
            }
        }
        scm {
            url.set("https://github.com/kokoro-aya/WhenCoffeeIsJava/")
            connection.set("scm:git:git://github.com/kokoro-aya/WhenCoffeeIsJava.git")
            developerConnection.set("scm:git:ssh://git@github.com/kokoro-aya/WhenCoffeeIsJava.git")
        }
    }
}