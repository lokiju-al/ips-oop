// 1_4_replace.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>

int main(int argc, char* argv[])
{
	if (argc != 3)
	{
		std::cout << "Invalid arguments count\n";
		std::cout << "Usage: CopyFile.exe <input>";
		return 1;
	}

	std::ifstream input;
	input.open(argv[1]);
	if (!input.is_open())
	{
		std::cout << "Failed to open '" << argv[1] << "' for reading\n";
		return 1;
	}

	std::ofstream output;
	output.open(argv[2]);
	if (!output.is_open())
	{
		std::cout << "Failed to open '" << argv[2] << "' for writing\n";
		return 1;
	}

	char ch;
	while (input.get(ch))
	{
		output.put(ch);
	}

	return 0;
}
