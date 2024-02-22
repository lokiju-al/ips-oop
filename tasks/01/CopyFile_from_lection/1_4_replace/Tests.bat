@echo off

REM ���� � ����������� ��������� ���������� ����� ������ �������� ��������� ������
SET MyProgram="%~1"

REM ������ �� ������� ��� ���������, ��������� ���� � ���������
if %MyProgram%=="" (
	echo Please specify path to program
	exit /B 1
)

REM ����������� ������� �����
%MyProgram% EmptyFile.txt "%TEMP%\output.txt" || goto err
fc EmptyFile.txt "%TEMP%\output.txt" > nul || goto err
echo Test 1 passed

REM ����������� ��������� ����� � ����������� ���������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" || goto err
fc NotEmptyFile.txt "%TEMP%\output.txt" > nul || goto err
echo Test 2 passed

REM ����������� ��������������� �����
%MyProgram% MissingFile.txt "%TEMP%\output.txt" && goto err
echo Test 3 passed

REM ������ ��� ������ �� ����������
%MyProgram% MissingFile.txt && goto err
echo Test 4 passed

REM ������ ��� ����� ����������
%MyProgram% && goto err
echo Test 5 passed

REM ������ � ������ ����������
%MyProgram% NotEmptyFile.txt "%TEMP%\output.txt" 123 ��� && goto err
echo Test 6 passed

REM ����� ������ �������
echo All tests passed successfuly
exit /B 0

REM ���� ����� �������� � ������ ������
:err
echo Test failed
exit /B 1