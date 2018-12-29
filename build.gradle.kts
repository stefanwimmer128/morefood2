import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import net.minecraftforge.gradle.user.IReobfuscator
import net.minecraftforge.gradle.user.ReobfMappingType
import net.minecraftforge.gradle.user.UserBaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.utils.addExtendsFromRelation

object Config {
    val modid = "morefood2"
    val version = "1.1.0"
    val type = "release"
    
    val minecraft = "1.12.2"
    val forge = "14.23.5.2768"
    val mappings = "snapshot_20171003"
    
    val curse = "273296"
}


buildscript {
    repositories {
        jcenter()
        
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
        
        maven {
            setUrl("http://files.minecraftforge.net/maven")
        }
    }
    
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:2.3-SNAPSHOT")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.11"
    id("com.matthewprenger.cursegradle") version "1.1.2"
    id("com.jfrog.bintray") version "1.8.4"
    id("maven-publish")
}

apply(plugin = "net.minecraftforge.gradle.forge")

group = "eu.stefanwimmer128.morefood2"
version = Config.version

repositories {
    jcenter()
    
    maven {
        setUrl("https://dl.bintray.com/stefanwimmer128/maven")
    }
}

dependencies {
    "provided"("eu.stefanwimmer128.kotlin3:kotlin3-api:1.1.0")
    
    runtime("eu.stefanwimmer128.kotlin3:kotlin3:1.1.0")
}

extensions.getByName<UserBaseExtension>("minecraft").apply {
    version = "${Config.minecraft}-${Config.forge}"
    mappings = Config.mappings
    
    runDir = "run"
    
    makeObfSourceJar = true
    
    replaceIn("src/api/kotlin/eu/stefanwimmer128/morefood2/api/MoreFood2API.kt")
    replace("#{VERSION}", Config.version)
}

the<JavaPluginConvention>().apply {
    sourceCompatibility = JavaVersion.toVersion("1.8")
    targetCompatibility = JavaVersion.toVersion("1.8")
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
    
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    
    getByName<ProcessResources>("processResources") {
        inputs.property("version", Config.version)
        inputs.property("mcversion", Config.minecraft)
        
        from(sourceSets["main"].resources.srcDirs) {
            include("mcmod.info")
            
            expand(mapOf("version" to Config.version, "mcversion" to Config.minecraft))
        }
        
        from(sourceSets["main"].resources.srcDirs) {
            exclude("mcmod.info")
        }
    }
    
    getByName<Jar>("jar") {
        from(sourceSets["api"].output)
    }
    
    getByName<Jar>("sourceJar") {
        from(sourceSets["api"].allSource)
    }
    
    create<Jar>("apiJar") {
        from(sourceSets["api"].output)
        
        classifier = "api"
    }
    
    create<Jar>("deobfJar") {
        from(sourceSets["api"].output)
        from(sourceSets["main"].output)
        
        classifier = "deobf"
    }
    
    withType<Jar> {
        baseName = "${Config.modid}_${Config.minecraft}"
    }
}

artifacts {
    add("archives", tasks["apiJar"])
    add("archives", tasks["deobfJar"])
}

curseforge {
    apiKey = System.getenv("CURSE_API_TOKEN") ?: ""
    
    project(closureOf<CurseProject> {
        id = Config.curse
        
        changelog = "Forge version: ${Config.minecraft}-${Config.forge}"
        
        releaseType = Config.type
        
        addGameVersion(Config.minecraft)
        
        mainArtifact(tasks["jar"])
        
        addArtifact(tasks["sourceJar"])
        addArtifact(tasks["deobfJar"])
        addArtifact(tasks["apiJar"])
        
        relations(closureOf<CurseRelation> {
            requiredDependency("kotlin3")
        })
    })
}

val PUBLICATIONS = mapOf(
    "jar" to null,
    "apiJar" to "api",
    "deobfJar" to "deobf"
)

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    pkg.apply {
        repo = "maven"
        name = Config.modid
        
        version.apply {
            name = Config.version
            desc = "Minecraft Forge: ${Config.minecraft}-${Config.forge}"
            vcsTag = "v${Config.version}"
        }
    }
    withGroovyBuilder {
        "publications"(PUBLICATIONS.keys.toTypedArray())
    }
    override = true
    publish = true
}

publishing {
    publications {
        PUBLICATIONS.forEach { source, target ->
            create<MavenPublication>(source) {
                from(components["java"])
                
                artifactId = Config.modid + (target?.let { "-$it" } ?: "")
                version = Config.version
                
                artifacts.removeAll(artifacts)
                artifact(tasks[source]) {
                    classifier = null
                }
                artifact(tasks["sourceJar"])
                
                pom.withGroovyBuilder {
                    "setName"("MoreFood 2")
                    "setDescription"("Minecraft Forge: ${Config.minecraft}-${Config.forge}")
                    "setUrl"("https://minecraft.curseforge.com/projects/morefood2")
                    "licenses" {
                        "license" {
                            "setName"("ISC")
                            "setUrl"("https://raw.githubusercontent.com/stefanwimmer128/morefood2/master/LICENSE")
                            "setDistribution"("repo")
                        }
                    }
                    "developers" {
                        "developer" {
                            "setId"("stefanwimmer128")
                            "setName"("Stefan Wimmer")
                            "setEmail"("info@stefanwimmer128.eu")
                            "setUrl"("https://stefanwimmer128.eu")
                            "setRoles"(setOf("developer"))
                        }
                    }
                    "contributors" {
                        "contributor" {
                            "setName"("uriba")
                        }
                    }
                    "scm" {
                        "setConnection"("scm:git:https://github.com/stefanwimmer128/morefood2.git")
                        "setDeveloperConnection"("scm:git:git@github.com:stefanwimmer128/morefood2.git")
                        "setUrl"("https://github.com/stefanwimmer128/morefood2")
                    }
                    "issueManagement" {
                        "setSystem"("github")
                        "setUrl"("https://github.com/stefanwimmer128/morefood2/issues")
                    }
                }
            }
        }
    }
}
