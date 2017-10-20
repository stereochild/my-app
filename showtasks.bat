call runcrud
if "%ERRORLEVEL%" == "0" goto launchbrowser
echo.
echo RUNCRUD has error - breaking work
goto fail

:launchbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot launch browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
