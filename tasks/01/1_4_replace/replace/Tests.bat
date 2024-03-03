@echo off

REM Путь к тестируемой программе передается через первый аргумент командной строки
SET MyProgram="%~1"

REM Защита от запуска без аргумента, задающего путь к программе
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM ------------------------------- НЕГАТИВНЫЕ КЕЙСЫ -------------------------------
echo -------------------------------------------------------------------------------
REM Запуск без достаточного количества параметров
%MyProgram% MissingFile.txt && goto err
echo Test 1 passed
echo -------------------------------------------------------------------------------
REM Запуск без параметров
%MyProgram% && goto err
echo Test 2 passed
echo -------------------------------------------------------------------------------
REM Запуск с лишним параметром
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 123 йцу qwe && goto err
echo Test 3 passed
echo -------------------------------------------------------------------------------
REM Замена в несуществующем файле
%MyProgram% MissingFile.txt "%TEMP%\output.txt" 123 qwe && goto err
echo Test 4 passed
echo -------------------------------------------------------------------------------
REM ------------------------------- ПОЗИТИВНЫЕ КЕЙСЫ -------------------------------
REM Замена в пустом файле
%MyProgram% EmptyFile.txt "%TEMP%\output.txt" 123 qwe || goto err
fc "%TEMP%\output.txt" EmptyFile.txt > nul || goto err
echo Test 5 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с одной длинной строкой
%MyProgram% OneStringFile.txt "%TEMP%\output.txt" Microsoft MS || goto err
fc "%TEMP%\output.txt" test-etalon\OneStringFile-Microsoft-MS.txt > nul || goto err
echo Test 6 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с несколькими строками
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 1231234 W || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-1231234-W.txt > nul || goto err
echo Test 7 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с несколькими строками, если подстрока не найдена
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" йййцццууукккеееннн . || goto err
fc "%TEMP%\output.txt" NotEmptyFile.txt > nul || goto err
echo Test 8 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с несколькими строками, если пустая строка замены
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 12312312345 "" || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-12312312345.txt > nul || goto err
echo Test 9 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с несколькими строками, проверка зацикливания
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 12312312345 1231231234512312312345 || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-ma-mama.txt > nul || goto err
echo Test 10 passed
echo -------------------------------------------------------------------------------
REM Замена в непустом файле с несколькими строками, если пустая строка поиска
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" "" 1231231234512312312345 || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile.txt > nul || goto err
echo Test 11 passed
echo -------------------------------------------------------------------------------
REM --------------------------------------------------------------------------------
REM Тесты прошли успешно
echo All tests passed successfuly
exit /B 0

REM Сюда будем попадать в случае ошибки
:err
echo Test failed
exit /B 1