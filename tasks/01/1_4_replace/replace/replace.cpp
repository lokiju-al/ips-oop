#include <fstream>
#include <iostream>
#include <optional>
#include <string>

struct Args
{
	std::string inputFileName;
	std::string outputFileName;
};

std::optional<Args> ParseArgs(int argc, char* argv[])
{
	if (argc != 3)
	{
		std::cout << "Invalid arguments count\n";
		std::cout << "Usage: replace.exe <input file name> <output file name>\n";
		return std::nullopt;
	}
	Args args;
	args.inputFileName = argv[1];
	args.outputFileName = argv[2];
	return args;
}
/*
// ���������� ��������� ������ ���� ��������� ������ searchString ������ ������ subject �� replacementString
// ���� ������ searchString ������, �� ������������ subject
std::string ReplaceString(const std::string& subject,
	const std::string& searchString, const std::string& replacementString)
{
	size_t pos = 0;
	// ��������� ����� ������� � ����� ������ result, �������� ������ subject ����������
	// ����� ������������ ���� � ����� ������� �� ��������� � ����������, �����������
	// ������ ����� � ������ subject?
	std::string result;
	while (pos < subject.length())
	{
		// ������� ������� ������� ������, ������� � pos
		size_t foundPos = subject.find(searchString, pos);
		// � �������������� ������ ���������� ����� �� ��������� [pos,foundPos)
		result.append(subject, pos, foundPos - pos);

		// �������� ����������� ��� ��������������, ����� ������� �������� ���������
	}
	return result;
}

void CopyStreamWithReplacement(std::istream& input, std::ostream& output,
	const std::string& searchString, const std::string& replacementString)
{
	std::string line;

	while (std::getline(input, line))
	{
		output << ReplaceString(line, searchString, replacementString) << "\n";
	}
}*/

void CopyStreams(std::ifstream& input, std::ofstream& output)
{
	// �������� ������� ���� � ��������
	char ch;
	while (input.get(ch))
	{
		if (!output.put(ch))
		{
			break;
		}
	}
}

int main(int argc, char* argv[])
{
	auto args = ParseArgs(argc, argv);
	// �������� ������������ ���������� ��������� ������
	if (!args)
	{
		return 1;
	}

	// ��������� ������� ���� ��� ������
	std::ifstream input;
	input.open(args->inputFileName);
	if (!input.is_open())
	{
		std::cout << "Failed to open '" << args->inputFileName << "' for reading\n";
		return 1;
	}

	// ��������� �������� ���� ��� ������
	std::ofstream output;
	output.open(args->outputFileName);
	if (!output.is_open())
	{
		std::cout << "Failed to open '" << args->outputFileName << "' for writing\n";
		return 1;
	}

	CopyStreams(input, output);

	if (input.bad())
	{
		std::cout << "Failed to read data from input file";
		return 1;
	}

	if (!output.flush())
	{
		std::cout << "Failed to write data to output file\n";
		return 1;
	}

	return 0;
}
