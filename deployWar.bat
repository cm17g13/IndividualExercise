echo off
del C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments\Individual-Exercise.war
del C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments\Individual-Exercise.war.deployed

xcopy /s C:\Users\Admin\eclipse-workspace\Individual-Exercise\target\Individual-Exercise.war C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments

call C:\Users\Admin\external-application\wildfly-10.1.0.Final\bin\standalone.bat