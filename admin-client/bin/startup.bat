@echo off
rem ======================================================================
rem windows startup script
rem
rem author: geekidea
rem date: 2018-12-2
rem ======================================================================
rem Open in a browser
rem start "" "http://localhost:8080/admin-client/swagger-ui.html"

rem startup jar
java -jar ../boot/@project.build.finalName@.jar --spring.config.location=../config/

pause
