plugins {
    `java-gradle-plugin`
    `maven-publish`
}

group = "pe.edu.galaxy.training.java"
version = "1.0.0"

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
        create("galaxyTrainingSpringBoot") {
            id = "pe.edu.galaxy.training.spring-boot"
            implementationClass = "pe.edu.galaxy.training.java.gradle.GalaxyTrainingSpringBootPlugin"
            displayName = "Galaxy Training Spring Boot Plugin"
            description = "Convention plugin que configura proyectos Spring Boot con el meta-framework Galaxy Training."
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/OWNER/galaxy-training-spring-boot-gradle-plugin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
