package  com.wxgz.plugin

import com.android.build.gradle.AppExtension
import com.wxgz.pluin.WxgzAnalyticsExtension
import com.wxgz.pluin.WxgzAnalyticsTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyPlugin implements Plugin<Project> {

    void apply(Project project) {

        WxgzAnalyticsExtension extension = project.extensions.create("wxgzAnalytics", WxgzAnalyticsExtension)

        boolean disableWxgzAnalyticsPlugin = false

        if (project.rootProject.file('gradle.properties').exists()) {
            Properties properties = new Properties()
            properties.load(project.rootProject.file('gradle.properties').newDataInputStream())
            disableWxgzAnalyticsPlugin = Boolean.parseBoolean(properties.getProperty("wxgzAnalytics.disablePlugin", "false"))
        }
        if (!disableWxgzAnalyticsPlugin) {
            AppExtension appExtension = project.extensions.findByType(AppExtension.class)
            appExtension.registerTransform(new WxgzAnalyticsTransform(project, extension))
        } else {
            println("------------WXGZ Plugin--------------"+disableWxgzAnalyticsPlugin)
        }
    }
}
