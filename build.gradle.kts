plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("net.nemerosa.versioning") version "4.0.1"
}

versioning {
    releaseMode = "snapshot"
    displayMode = "snapshot"
    dirty = { it }
    releaseBuild = false
}

group = "pe.edu.nova.java"
version = findProperty("version") as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:4.0.5")
}

gradlePlugin {
    plugins {
        create("novaSpringBoot") {
            id = "pe.edu.nova.java.spring-boot"
            implementationClass = "pe.edu.nova.java.gradle.NovaSpringBootPlugin"
            displayName = "Nova Spring Boot Plugin"
            description = "Convention plugin que configura proyectos Spring Boot con el meta-framework Nova."
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahincho/nova-java-spring-boot-gradle-plugin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
