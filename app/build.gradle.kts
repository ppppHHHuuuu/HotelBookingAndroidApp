import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.mobdev_nhom7"
    compileSdk = 33

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    defaultConfig {
        applicationId = "com.example.mobdev_nhom7"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {


        getByName("main") {

            res {
                srcDirs("src\\main\\res", "src\\main\\res\\login",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\login", "src\\main\\res", "src\\main\\res\\layouts\\sign-up",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\component", "src\\main\\res", "src\\main\\res\\layouts\\main",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\main\\fragments", "src\\main\\res", "src\\main\\res\\layouts\\layout",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\main-activity\\layout", "src\\main\\res", "src\\main\\res\\layouts\\sign-up\\layout",
                    "src\\main\\res",
                    "src\\main\\res\\main-page", "src\\main\\res", "src\\main\\res\\layouts\\main-page",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\main-page\\layout", "src\\main\\res", "src\\main\\res\\layouts\\components\\button",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\components\\toast",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\components\\button\\layout",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\hotel-detail",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\hotel-detail\\layout",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\components\\cards",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\components\\cards\\layout",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\trip-page\\layout"
                )
            }
            assets {
                srcDirs("src\\main\\assets", "src\\main\\res\\assets",
                    "src\\main\\assets",
                    "src\\main\\assets"
                )
            }
            resources {
                srcDirs("src\\main\\resources", "src\\main\\res\\layouts\\trip-page")
            }
        }

    }

}

dependencies {
    implementation("androidx.test:monitor:1.6.1")
    testImplementation ("org.mock-server:mockserver-netty:3.10.8")
    testImplementation ("org.mock-server:mockserver-client-java:3.10.8")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
    testImplementation ("com.github.tomakehurst:wiremock-jre8:2.32.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation ("com.google.guava:guava:31.1-jre")
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.test:monitor:1.6.1")
    testImplementation ("org.mock-server:mockserver-netty:3.10.8")
    testImplementation ("org.mock-server:mockserver-client-java:3.10.8")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
    testImplementation ("com.github.tomakehurst:wiremock-jre8:2.32.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")

}
