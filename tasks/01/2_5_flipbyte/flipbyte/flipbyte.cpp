#include <iostream>
#include <optional>
#include <string>

struct Args
{
	std::string inputString;
};

std::optional<Args> ParseArgs(int argc, char* argv[])
{
	if (argc != 2)
	{
		std::cout << "Invalid arguments count\n";
		std::cout << "Usage: flipbyte.exe <input byte>\n";
		return std::nullopt;
	}
	Args args;
	args.inputString = argv[1];
	return args;
}

int ConvertInputStringToNumber(const std::string& inputString)
{
	int inputByte;
	try
	{
		inputByte = stoi(inputString);
	}
	catch (std::exception& e)
	{
		std::cout << "Exception catched : " << e.what() << "\n";
		throw std::string
		{
			"Please input a number in range [0 - 255]\n"
		};
	}
	if (!(inputByte >= 0 && inputByte < 256))
	{
		throw std::string{
			"Invalid input number\nInput number must be in range [0 - 255]\n"
		};
	}
	return inputByte;
}


unsigned char flipByte(unsigned char inputNumber)			//i		inputNumber		resultNumber
{															//		10011011		00000000
	unsigned char resultNumber = 0;							//7		01001101		10000000
	for (int i = 7; i >= 0; --i)							//6		00100110		11000000
	{														//5		00010011		11000000
		resultNumber += (inputNumber & 1) << i;				//4		00001001		11010000
		inputNumber >>= 1;									//3		00000100		11011000
	}														//2		00000010		11011000
	return resultNumber;									//1		00000001		11011000
}															//0		00000000		11011001

int main(int argc, char* argv[])
{
	auto args = ParseArgs(argc, argv);
	// Проверка правильности аргументов командной строки
	if (!args)
	{
		return 1;
	}
	int inputByte;
	try
	{
		inputByte = ConvertInputStringToNumber(args->inputString);
	}
	catch (std::string error_message)
	{
		std::cout << error_message;
		return 1;
	}
	std::cout << std::to_string(flipByte(inputByte)) << "\n";

	return 0;
}
