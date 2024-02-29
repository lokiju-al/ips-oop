@echo off

REM Путь к тестируемой программе передается через первый аргумент командной строки
SET MyProgram="%~1"

REM Защита от запуска без аргумента, задающего путь к программе
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM ------------------------------- НЕГАТИВНЫЕ КЕЙСЫ -------------------------------
echo -------------
REM Запуск без параметров
%MyProgram% && goto err
echo Test 1 passed
echo -------------
REM Запуск с лишним параметром
%MyProgram% MatrixFile.txt 123 && goto err
echo Test 2 passed
echo -------------
REM Запуск с несуществующим файлом в параметрах
%MyProgram% MissingFile.txt && goto err
echo Test 3 passed
echo -------------
REM ------------------------------- ПОЗИТИВНЫЕ КЕЙСЫ -------------------------------
REM Запуск с файлом в параметрах
%MyProgram% Test1.txt || goto err
echo Test 4 passed
echo -------------
REM Запуск с файлом в параметрах
%MyProgram% Test2.txt || goto err
echo Test 5 passed
echo -------------
REM Запуск с файлом в параметрах
%MyProgram% Test3.txt || goto err
echo Test 6 passed
echo -------------
REM Запуск с файлом в параметрах
%MyProgram% Test4.txt || goto err
echo Test 7 passed
echo -------------
REM Запуск с файлом в параметрах
%MyProgram% Test5.txt || goto err
echo Test 8 passed
echo -------------
REM Запуск с файлом в параметрах
%MyProgram% Test6.txt || goto err
echo Test 9 passed
echo -------------
REM -------------------------------------------------------------------------------
REM Тесты прошли успешно
echo All tests passed successfuly
exit /B 0

REM Сюда будем попадать в случае ошибки
:err
echo Test failed
exit /B 1