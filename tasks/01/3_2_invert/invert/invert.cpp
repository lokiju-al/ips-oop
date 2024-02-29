#include <fstream>
#include <iostream>
#include <optional>
#include <string>

using Matrix2x2 = int[2][2];
using Matrix3x3 = int[3][3];

struct Args
{
	std::string inputFileName;
};

std::optional<Args> ParseArgs(int argc, char* argv[])
{
	if (argc != 2)
	{
		std::cout << "Invalid arguments count\n";
		std::cout << "Usage: invert.exe <matrix file>\n";
		return std::nullopt;
	}
	Args args;
	args.inputFileName = argv[1];
	return args;
}

// Считываем числа из входного файла в массив
void ReadArray(std::istream& input, Matrix3x3 arr)
{
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			input >> arr[i][j];
		}
	}
	std::cout << "inputArray\n";
	std::cout << arr[0][0] << " " << arr[0][1] << " " << arr[0][2] << "\n";
	std::cout << arr[1][0] << " " << arr[1][1] << " " << arr[1][2] << "\n";
	std::cout << arr[2][0] << " " << arr[2][1] << " " << arr[2][2] << "\n";
}

int DeterminantOfArray(Matrix3x3 arr, int n)
{
	int det = 0;
	if (n == 2)
		det = arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
	else if (n == 3)
	{
		det = arr[0][0] * arr[1][1] * arr[2][2] + arr[2][0] * arr[1][2] * arr[0][1] + arr[0][2] * arr[1][0] * arr[2][1] 
			- (arr[0][2] * arr[1][1] * arr[2][0] + arr[0][1] * arr[1][0] * arr[2][2] + arr[0][0] * arr[1][2] * arr[2][1]);
	}
	else
	{
		return 0;
	}
	return det;
}

int main(int argc, char* argv[])
{
	auto args = ParseArgs(argc, argv);
	// Проверка правильности аргументов командной строки
	if (!args)
	{
		return 1;
	}

	Matrix3x3 inputArray;

	// Открываем входной файл для чтения
	std::ifstream input;
	input.open(args->inputFileName);
	if (!input.is_open())
	{
		std::cout << "Failed to open '" << args->inputFileName << "' for reading\n";
		return 1;
	}

	ReadArray(input, inputArray);
	std::cout << "DeterminantOfArray: " << DeterminantOfArray(inputArray, 3);

	if (input.bad())
	{
		std::cout << "Failed to read data from input file";
		return 1;
	}











	return 0;
}
