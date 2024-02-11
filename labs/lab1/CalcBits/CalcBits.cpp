/*
������������ ���������� calcbits.exe, ����������� ������� � ����� � output ���������� ��������� �����
� �����. ��� �������� ��������� ����� ����������� �������� ��������������� ������.
������ ��������� ������:

calcbits.exe <byte>
��� byte � ����� � ���������� �������, � �������� ������������� �������� ������ ������������� �������
������������� � ������� ���.

��������, �����

calcbits.exe 5
������ �������� � output �������� 2 (����� 5 � �������� ������� ����� 00000101).

� ������ ������������ ������� ������ ��������� ������ �������� ������������ ��������� �� ������
� ���������� ��������� ��������.

*/
#include <cassert>
#include <iostream>
#include <limits>
#include <string>

int main(int argc, char* argv[])
{
	std::cout << argv[0] << std::endl;
	if (argc != 2)
	{
		std::cout << "Usage: calcbits.exe <byte>" << std::endl;

		return EXIT_SUCCESS;
	}

	assert(argc == 2);
	std::cout << argv[1] << std::endl;

	try
	{
		int byte = std::stoi(argv[1]);
		std::cout << byte << std::endl;
		if (byte < 0 || byte > std::numeric_limits<uint8_t>::max())
		{
			std::cout << "Argument is out of range" << std::endl;
			return EXIT_FAILURE;
		}

		/* �������� ��������� �������������� */
	}
	catch (const std::exception& e)
	{
		std::cout << e.what() << std::endl;
		return EXIT_FAILURE;
	}
}
