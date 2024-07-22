package classes;

public enum TypeOfFile{
    DIRECTORY(0),
    FILE(1);

    private int value;

    private TypeOfFile(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}