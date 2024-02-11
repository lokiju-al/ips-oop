#include <algorithm>
#include <iostream>

// ������������ ������������ ������ �������� ������� ���������
// �� ����������� ������, ����� �� ���������� ���
bool IsStringInLowerCase(const std::string& s)
{
	for (char ch : s)
		if (ch != std::tolower(static_cast<unsigned char>(ch)))
			return false;
	return true;
}

// �������� n � �������. �� �����������, ������� ��� ����� ��������� �� ��������
bool IsEven(int n)
{
	return n % 2 == 0;
}

/*
���������, ��� ������� ������� �������� ����������� �� ���������.
������� ��� ��������� s �� ������
*/
void TrimBlanks(std::string& s)
{
	if (auto start = s.find_first_not_of(' '); start != s.npos)
	{
		s = s.substr(start, s.find_last_not_of(' ') - start + 1);
	}
}

struct Point
{
	int x, y;
};

// ������� �������� ���� Point � �����������.
// �� ����� ���� ���������� ������� �� ��������.
void PrintPoint(Point p)
{
	std::cout << p.x << ", " << p.y << "\n";
}

// ���������� ��� �������, ��� ������� ������� ������ s,
// ���������� �������.������� �������� s ����������� �� ������
void ToLower(std::string& s)
{
	for (char& ch : s)
		ch = std::tolower(static_cast<unsigned char>(ch));
}

int main()
{
}
