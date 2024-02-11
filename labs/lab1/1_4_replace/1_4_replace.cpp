// 1_4_replace.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>

int main(int argc, char* argv[])
{
	std::ifstream input;
	input.open(argv[1]);
	char ch;
	while (input.get(ch))
	{
		std::cout.put(ch);
	}

	/* std::cout << argc << "\n";
	for (int i = 0; i < argc; ++i)
	{
		std::cout << argv[i] << "\n";
	}*/
	return 0;
}
