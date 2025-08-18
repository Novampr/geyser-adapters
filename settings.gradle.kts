
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "geyser-adapters"

include(":common")
include(":spigot")
include(":spigot:all")
include(":spigot:base")
include(":spigot:v1_17_R1")
include(":spigot:v1_18_R1")
include(":spigot:v1_18_R2")
include(":spigot:v1_19_R1")
include(":spigot:v1_19_R2")
include(":spigot:v1_19_R3")
include(":spigot:v1_20_R1")
include(":spigot:v1_20_R2")
include(":spigot:v1_20_R3")
include(":spigot:v1_20_R4")
include(":spigot:v1_21_R1")
include(":spigot:v1_21_R2")
include(":spigot:v1_21_R3")

include(":paper")
include(":paper:all")
include(":paper:base")
include(":paper:v766")
include(":paper:v768")

// For whatever reason, the name cannot be ":modded:v1_21_7:common", the fabric and neoforge modules won't
// be able to find the module, but if the name is unique like this, it's fine
include(":modded_common_v1_21_7")
project(":modded_common_v1_21_7").projectDir = file("modded/v1_21_7/common")
include(":modded:v1_21_7:fabric")
include(":modded:v1_21_7:neoforge")
