@echo off

REM ���� � ����������� ��������� ���������� ����� ������ �������� ��������� ������
SET MyProgram="%~1"

REM ������ �� ������� ��� ���������, ��������� ���� � ���������
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM ------------------------------- ���������� ����� -------------------------------
echo -------------------------------------------------------------------------------
REM ������ ��� ������������ ���������� ����������
%MyProgram% MissingFile.txt && goto err
echo Test 1 passed
echo -------------------------------------------------------------------------------
REM ������ ��� ����������
%MyProgram% && goto err
echo Test 2 passed
echo -------------------------------------------------------------------------------
REM ������ � ������ ����������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 123 ��� qwe && goto err
echo Test 3 passed
echo -------------------------------------------------------------------------------
REM ������ � �������������� �����
%MyProgram% MissingFile.txt "%TEMP%\output.txt" 123 qwe && goto err
echo Test 4 passed
echo -------------------------------------------------------------------------------
REM ------------------------------- ���������� ����� -------------------------------
REM ������ � ������ �����
%MyProgram% EmptyFile.txt "%TEMP%\output.txt" 123 qwe || goto err
fc "%TEMP%\output.txt" EmptyFile.txt > nul || goto err
echo Test 5 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����� ������� �������
%MyProgram% OneStringFile.txt "%TEMP%\output.txt" Microsoft MS || goto err
fc "%TEMP%\output.txt" test-etalon\OneStringFile-Microsoft-MS.txt > nul || goto err
echo Test 6 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����������� ��������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 1231234 W || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-1231234-W.txt > nul || goto err
echo Test 7 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����������� ��������, ���� ��������� �� �������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" ������������������ . || goto err
fc "%TEMP%\output.txt" NotEmptyFile.txt > nul || goto err
echo Test 8 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����������� ��������, ���� ������ ������ ������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 12312312345 "" || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-12312312345.txt > nul || goto err
echo Test 9 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����������� ��������, �������� ������������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 12312312345 1231231234512312312345 || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile-ma-mama.txt > nul || goto err
echo Test 10 passed
echo -------------------------------------------------------------------------------
REM ������ � �������� ����� � ����������� ��������, ���� ������ ������ ������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" "" 1231231234512312312345 || goto err
fc "%TEMP%\output.txt" test-etalon\NotEmptyFile.txt > nul || goto err
echo Test 11 passed
echo -------------------------------------------------------------------------------
REM --------------------------------------------------------------------------------
REM ����� ������ �������
echo All tests passed successfuly
exit /B 0

REM ���� ����� �������� � ������ ������
:err
echo Test failed
exit /B 1