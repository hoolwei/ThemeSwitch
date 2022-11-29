LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src/main)

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/src/main/res

LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_PROGUARD_FLAG_FILES := proguard-rules.pro

LOCAL_MANIFEST_FILE := src/main/AndroidManifest.xml

LOCAL_STATIC_JAVA_LIBRARIES += androidx.appcompat_appcompat

LOCAL_PRIVATE_PLATFORM_APIS := true

LOCAL_PACKAGE_NAME := MyApplication
include $(BUILD_PACKAGE)

