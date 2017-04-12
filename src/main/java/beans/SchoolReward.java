package beans;

public class SchoolReward {
    private int[] JavaA = new int[3], JavaB = new int[3], JavaC = new int[3];
    private int[] CppA = new int[3], CppB = new int[3], CppC = new int[3];

    public int[] getJavaA() {
        return JavaA;
    }

    public void setJavaAIncrementOne(int index) {
        JavaA[index]++;
    }

    public int getTotalJavaA() {
        return JavaA[0] + JavaA[1] + JavaA[2];
    }


    public int[] getJavaB() {
        return JavaB;
    }

    public void setJavaBIncrementOne(int index) {
        JavaB[index]++;
    }

    public int getTotalJavaB() {
        return JavaB[0] + JavaB[1] + JavaB[2];
    }

    public int[] getJavaC() {
        return JavaC;
    }

    public void setJavaCIncrementOne(int index) {
        JavaC[index]++;
    }

    public int getTotalJavaC() {
        return JavaC[0] + JavaC[1] + JavaC[2];
    }

    public int[] getCppA() {
        return CppA;
    }

    public void setCppAIncrementOne(int index) {
        CppA[index]++;
    }

    public int getTotalCppA() {
        return CppA[0] + CppA[1] + CppA[2];
    }

    public int[] getCppB() {
        return CppB;
    }

    public void setCppBIncrementOne(int index) {
        CppB[index]++;
    }

    public int getTotalCppB() {
        return CppB[0] + CppB[1] + CppB[2];
    }

    public int[] getCppC() {
        return CppC;
    }

    public void setCppCIncrementOne(int index) {
        CppC[index]++;
    }

    public int getTotalCppC() {
        return CppC[0] + CppC[1] + CppC[2];
    }
}
