@echo off

SET MyProgram="%~1"
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM Copy empty file
%MyProgram% Empty.txt "%TEMP%\output.txt" || goto err
fc Empty.txt "%TEMP%\output.txt" || goto err



REM Тесты прошли успешно
exit /B 0

REM Сюда будем попадать в случае ошибки
:err
echo Test failed
exit /B 1