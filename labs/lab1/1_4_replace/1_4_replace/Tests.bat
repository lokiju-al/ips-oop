@echo off

REM Путь к тестируемой программе передается через первый аргумент командной строки
SET MyProgram="%~1"

REM Защита от запуска без аргумента, задающего путь к программе
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM Копирование пустого файла
%MyProgram% EmptyFile.txt "%TEMP%\output.txt" || goto err
fc EmptyFile.txt "%TEMP%\output.txt" > nul || goto err
echo Test 1 passed

REM Копирование непустого файла с несколькими строчками
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" || goto err
fc NotEmptyFile.txt "%TEMP%\output.txt" > nul || goto err
echo Test 2 passed

REM Копирование несуществующего файла
%MyProgram% MissingFile.txt "%TEMP%\output.txt" && goto err
echo Test 3 passed

REM Запуск без одного из параметров
%MyProgram% MissingFile.txt && goto err
echo Test 4 passed

REM Запуск без обоих параметров
%MyProgram% && goto err
echo Test 5 passed

REM Запуск с лишним параметром
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 123 йцу && goto err
echo Test 6 passed

REM Тесты прошли успешно
echo All tests passed successfuly
exit /B 0

REM Сюда будем попадать в случае ошибки
:err
echo Test failed
exit /B 1