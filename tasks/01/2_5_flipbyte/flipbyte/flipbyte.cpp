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

unsigned char flipByte(unsigned char num)
{
	unsigned char result = 0;
	for (int i = 7; i >= 0; --i)
	{
		result += (num & 1) << i;
		num >>= 1;
	}
	return result;
}

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
		inputByte = std::stoi(args->inputString);
		if (!(inputByte >= 0 && inputByte < 256))
		{
			std::cout << "Invalid input number\n";
			std::cout << "Input number must be in range [0 - 255]\n";
			return 1;
		}
	}
	catch (const std::exception& e)
	{
		std::cout << "Invalid input number\n";
		std::cout << "Input number must be in range [0 - 255]\n";
		return 1;
	}
	
	std::cout << std::to_string(flipByte(inputByte)) << "\n";

	return 0;
}
