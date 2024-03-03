#include <fstream>
#include <iostream>
#include <optional>
#include <string>

struct Args
{
	std::string inputFileName;
	std::string outputFileName;
	std::string searchString;
	std::string replacementString;
};

std::optional<Args> ParseArgs(int argc, char* argv[])
{
	if (argc != 5)
	{
		std::cout << "Invalid arguments count\n";
		std::cout << "Usage: replace.exe <input file> <output file> <search string> <replace string>\n";
		return std::nullopt;
	}
	Args args;
	args.inputFileName = argv[1];
	args.outputFileName = argv[2];
	args.searchString = argv[3];
	args.replacementString = argv[4];
	return args;
}

// Возвращает результат замены всех вхождений строки searchString внутри строки subject на replacementString
// Если строка searchString пустая, то возвращается subject
std::string ReplaceString(const std::string& subject,
	const std::string& searchString, const std::string& replacementString)
{
	//------------------------------------------------функция работает некорректно с входной пустой строкой
	//------------------------------------------------добавить в тесты пустую строку
	if (searchString == "")
	{
		return subject;
	}
	size_t pos = 0;
	// Результат будет записан в новую строку result, оставляя строку subject неизменной
	std::string result;
	while (pos < subject.length())
	{
		// Находим позицию искомой строки, начиная с pos
		size_t foundPos = subject.find(searchString, pos);
		// Если searchString не найдена, в остатке subject, дописываем этот остато к результату и выходим
		if (foundPos == std::string::npos)
		{
			//--------------------------------------------1 find 2 appends
			result.append(subject, pos, foundPos - pos);
			break;
		}
		// В результирующую строку записываем текст из диапазона [pos,foundPos)
		result.append(subject, pos, foundPos - pos);
		result.append(replacementString);
		pos = foundPos + searchString.length();
	}
	return result;
}

void CopyStreamWithReplacement(std::istream& input, std::ostream& output,
	const std::string& searchString, const std::string& replacementString)
{
	// Построчно считываем из входного файла и записываем в выходной файл с заменой подстроки
	std::string line;
	while (std::getline(input, line))
	{
		if (!(output << ReplaceString(line, searchString, replacementString) << "\n"))
		{
			break;
		}
	}
}

int main(int argc, char* argv[])
{
	auto args = ParseArgs(argc, argv);
	// Проверка правильности аргументов командной строки
	if (!args)
	{
		return 1;
	}

	//------------------------------------------------выделить в отдельные функции
	// Открываем входной файл для чтения
	std::ifstream input;
	//OpenInputFile();
	input.open(args->inputFileName);
	if (!input.is_open())
	{
		std::cout << "Failed to open '" << args->inputFileName << "' for reading\n";
		return 1;
	}

	// Открываем выходной файл для записи
	std::ofstream output;
	output.open(args->outputFileName);
	if (!output.is_open())
	{
		std::cout << "Failed to open '" << args->outputFileName << "' for writing\n";
		return 1;
	}

	CopyStreamWithReplacement(input, output, args->searchString, args->replacementString);

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
