import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "hobigon"

version = "0.0.2"

java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("nu.studer.jooq") version "8.0"

    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

repositories { mavenCentral() }

dependencies {
    runtimeOnly("mysql:mysql-connector-java")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.auth0:java-jwt:4.2.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")

    jooqGenerator("mysql:mysql-connector-java")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
}

jooq {
    configurations {
        create("main") {
            // FIXME: 設定値の定義を一箇所にまとめる（できればapplication.properties）
            jooqConfiguration.apply {
                jdbc.apply {
                    url = "jdbc:mysql://localhost:13306/hobigon"
                    user = "root"
                    password = "mysql"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "hobigon"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "hobigon.userbox.infrastructure.jooq"
                        directory = "${buildDir}/generated/jooq"
                    }
                }
            }
        }
    }
}

detekt {
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    source = files(".")
    autoCorrect = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> { useJUnitPlatform() }
