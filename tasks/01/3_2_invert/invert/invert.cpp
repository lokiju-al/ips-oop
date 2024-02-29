#include <fstream>
#include <iostream>
#include <optional>
#include <string>
#include <iomanip>

using Matrix2x2 = float[2][2];
using Matrix3x3 = float[3][3];

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
	std::cout << "Input matrix:\n";
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			input >> arr[i][j];
			std::cout << arr[i][j] << " ";
		}
		std::cout << "\n";
	}
}

float DeterminantOfArray(Matrix3x3 arr)
{
	return arr[0][0] * arr[1][1] * arr[2][2] + arr[2][0] * arr[1][2] * arr[0][1] 
			+ arr[0][2] * arr[1][0] * arr[2][1] - arr[0][2] * arr[1][1] * arr[2][0] 
			- arr[0][1] * arr[1][0] * arr[2][2] - arr[0][0] * arr[1][2] * arr[2][1];
}

void AdditionArray(Matrix3x3 inp, Matrix3x3 add)
{
	add[0][0] = (inp[1][1] * inp[2][2] - inp[2][1] * inp[1][2]);
	add[0][1] = (inp[1][0] * inp[2][2] - inp[2][0] * inp[1][2]) * (-1);
	add[0][2] = (inp[1][0] * inp[2][1] - inp[2][0] * inp[1][1]);
	add[1][0] = (inp[0][1] * inp[2][2] - inp[2][1] * inp[0][2]) * (-1);
	add[1][1] = (inp[0][0] * inp[2][2] - inp[2][0] * inp[0][2]);
	add[1][2] = (inp[0][0] * inp[2][1] - inp[2][0] * inp[0][1]) * (-1);
	add[2][0] = (inp[0][1] * inp[1][2] - inp[1][1] * inp[0][2]);
	add[2][1] = (inp[0][0] * inp[1][2] - inp[1][0] * inp[0][2]) * (-1);
	add[2][2] = (inp[0][0] * inp[1][1] - inp[1][0] * inp[0][1]);
}

void AttachedArray(Matrix3x3 inp, Matrix3x3 add)
{
	inp[0][0] = add[0][0];
	inp[0][1] = add[1][0];
	inp[0][2] = add[2][0];
	inp[1][0] = add[0][1];
	inp[1][1] = add[1][1];
	inp[1][2] = add[2][1];
	inp[2][0] = add[0][2];
	inp[2][1] = add[1][2];
	inp[2][2] = add[2][2];
}

int main(int argc, char* argv[])
{
	auto args = ParseArgs(argc, argv);

	// Проверка правильности аргументов командной строки
	if (!args)
	{
		return 1;
	}

	Matrix3x3 inputArray, additionArray;

	// Открываем входной файл для чтения
	std::ifstream input;
	input.open(args->inputFileName);
	if (!input.is_open())
	{
		std::cout << "Failed to open '" << args->inputFileName << "' for reading\n";
		return 1;
	}

	ReadArray(input, inputArray);
	if (input.bad())
	{
		std::cout << "Failed to read data from input file\n";
		return 1;
	}

	float determinantOfInputArray = DeterminantOfArray(inputArray);
	std::cout << "Determinant of input matrix: " << determinantOfInputArray << "\n";
	if (determinantOfInputArray == 0)
	{
		std::cout << "The matrix is singular and there is no inverse for it\n";
		return 0;
	}

	AdditionArray(inputArray, additionArray);

	AttachedArray(inputArray, additionArray);

	std::cout << "Inverse matrix:\n";
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			if (inputArray[i][j] == 0)
			{
				inputArray[i][j] = 0;
			}
			std::cout << std::setprecision(3) << (inputArray[i][j] / determinantOfInputArray) << " ";
		}
		std::cout << "\n";
	}

	return 0;
}
