pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/Galateyta/PublishLibrary")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "DemoPublishLibrary"
include(":app")
include (":kinegram")
include(":publishLibrary")
include(":android_common")
project(":kinegram").projectDir = file("android_common/kinegram")
project(":android_common").projectDir = file("android_common")
