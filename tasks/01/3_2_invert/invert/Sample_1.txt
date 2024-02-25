////////////////////////////////////////////////////////////////////////////////
 # include < string.h >
 # include < stdio.h >
 # define abs(a)(((a) > 0) ? (a) : (-a))
////////////////////////////////////////////////////////////////////////////////
int prec = 3;
////////////////////////////////////////////////////////////////////////////////
class Matrix{
    public:
    Matrix(int);
    Matrix(const Matrix & );
    void Transpose();
    void operator = (const Matrix & );
    void SetElement(int, int, double);
    Matrix Minor(int, int);
    double Determinant();
    Matrix Inverse();
    double GetElement(int, int)const;
    Matrix operator * (const Matrix & );
    void Print(TMemo * );
    ~Matrix();
    private:
    bool tflag_;
    int size_;
    double * data_;
};
////////////////////////////////////////////////////////////////////////////////
Matrix Matrix::Inverse() {
    Matrix res(size_);
    double d = Determinant();
    for (int i = 0; i < size_; i++) {
        for (int j = 0; j < size_; j++) {
            res.SetElement(i, j, Minor(j, i).Determinant() / d *
                ((i + j) % 2 == 0 ? -1.0 : 1.0));
        }
    }
    return res;
}
////////////////////////////////////////////////////////////////////////////////
double Matrix::Determinant() {
    if (size_ == 1)
        return data_[0];
    else {
        double res = 0;
        for (int i = 0; i < size_; i++) {
            res += GetElement(i, 0) * (Minor(i, 0).Determinant())
             * (i % 2 == 0 ? -1.0 : 1.0);
        }
        return res;
    }

}
////////////////////////////////////////////////////////////////////////////////
Matrix Matrix::Minor(int i, int j) {
    if (i < size_ && j < size_ && i >= 0 && j >= 0) {
        if (size_ > 1) {
            Matrix res(size_ - 1);
            for (int i1 = 0; i1 < size_; i1++) {

                for (int j1 = 0; j1 < size_; j1++) {
                    if (i1 != i && j1 != j) {
                        int it = (i1 > i) ? -1 : 0;
                        int jt = (j1 > j) ? -1 : 0;
                        res.SetElement(i1 + it, j1 + jt,
                            GetElement(i1, j1));
                    }
                }
            }
            return res;
        }
    }
}
////////////////////////////////////////////////////////////////////////////////
void Matrix::Transpose() {
    tflag_ = !tflag_;
}
////////////////////////////////////////////////////////////////////////////////
void Matrix::operator = (const Matrix & m) {
    delete []data_;
    data_ = new double[m.size_ * m.size_];
    size_ = m.size_;
    memcpy((void * )data_, (void * )m.data_,
        size_ * size_ * sizeof(double));
    tflag_ = m.tflag_;
}
////////////////////////////////////////////////////////////////////////////////
Matrix::Matrix(const Matrix & m):
data_(new double[m.size_ * m.size_]),
tflag_(m.tflag_) {
    size_ = m.size_;
    memcpy((void * )data_, (void * )m.data_,
        size_ * size_ * sizeof(double));
}
////////////////////////////////////////////////////////////////////////////////
Matrix::Matrix(int n):
size_(abs(n)), data_(new double[n * n]),
tflag_(false) {
    memset((void * )data_, 0, n * n * sizeof(double));
}
////////////////////////////////////////////////////////////////////////////////
Matrix::~Matrix() {
    delete []data_;
}
////////////////////////////////////////////////////////////////////////////////
void Matrix::Print(TMemo * memo) {
    char num[100] = "";
    AnsiString c = "";
    memo->Clear();
    char str[100] = "";
    sprintf(str, "\t%%.%df", prec);
    for (int i = 0; i < size_; i++) {
        c = "";
        for (int j = 0; j < size_; j++) {
            sprintf(num, str, GetElement(j, i));
            c += num;
        }
        memo->Lines->Add(c);
    }
}
////////////////////////////////////////////////////////////////////////////////
void Matrix::SetElement(int i, int j, double val) {
    if (i < size_ && j < size_ && i >= 0 && j >= 0) {
        if (tflag_)
            data_[j + i * size_] = val;
        else
            data_[i + j * size_] = val;
    }
}
////////////////////////////////////////////////////////////////////////////////
double Matrix::GetElement(int i, int j)const{
    if (i < size_ && j < size_ && i >= 0 && j >= 0) {
        if (tflag_)
            return data_[j + i * size_];
        else
            return data_[i + j * size_];
    }
}
////////////////////////////////////////////////////////////////////////////////
Matrix Matrix::operator * (const Matrix & a) {
    if (size_ == a.size_) {
        double val = 0;
        Matrix res(size_);
        for (int i = 0; i < size_; i++) {
            for (int j = 0; j < size_; j++) {
                for (int k = 0; k < size_; k++) {
                    val += GetElement(k, i) *
                    a.GetElement(j, k);
                }
                res.SetElement(j, i, val);
                val = 0;
            }
        }
        return res;
    }
    return Matrix(0);
}
////////////////////////////////////////////////////////////////////////////////


//---------------------------------------------------------------------------

 # include < vcl.h >
 # pragma hdrstop

 # include "Unit1.h"
 # include "lib.h"
//---------------------------------------------------------------------------
 # pragma package(smart_init)
 # pragma link "CSPIN"
 # pragma resource "*.dfm"
TForm1 * Form1;

int n;
float s;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent * Owner)
: TForm(Owner) {}
//---------------------------------------------------------------------------

void __fastcall TForm1::Button1Click(TObject * Sender) {
    Matrix e(n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            e.SetElement(i, j, atof(Matrix_A->Cells[i][j].c_str()));
        }
    }
    Matrix x(n);
    if (e.Determinant() == 0) {
        ShowMessage("Невозможно найти обратную");
    } else {
        x = e.Inverse();
        x.Print(Memo1);

    }

}
//---------------------------------------------------------------------------

void __fastcall TForm1::CSpinEdit1Change(TObject * Sender) {
    n = StrToInt(CSpinEdit1->Value);
    Matrix_A->ColCount = n;
    Matrix_A->RowCount = n;
}
//---------------------------------------------------------------------------
