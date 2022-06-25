import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class SumResearch {
    String findRes(String fieldFirst, String fieldSecond, String nameCompany, String path) throws FileNotFoundException, UnsupportedEncodingException {
        String result = "";
        ArrayList<ArrayList<String>> answerList = new ArrayList<ArrayList<String>>();
        File file = new File(path);
        Scanner scanner = new Scanner(file, "windows-1251");
        while(scanner.hasNextLine()){
            String check = scanner.nextLine();
            System.out.println(check);
            if(check.toCharArray()[1] >= '0' && check.toCharArray()[1] <= '9'){
                if(check.contains(fieldFirst) && check.contains(fieldSecond)) {
                    char[] fullLineFirst = check.toCharArray();
                    int ccch = check.indexOf("\t" + fieldSecond + "\t");
                    check = scanner.nextLine();
                    check = scanner.nextLine();
                    check = scanner.nextLine();
                    char[] fullLineSecond = check.toCharArray();
                    char[] dataLine = new char[10];
                    char[] nameLine = new char[fullLineSecond.length-4];
                    char[] sumLine = new char[50];
                    int counterT = 0;
                    for(int t = 0; fullLineFirst[t] != ','; t++){
                        counterT++;
                    }
                    counterT += 3;
                    int w = 0;
                    for(int t = counterT; t > ccch + 4; t--, w++){
                        sumLine[w] = fullLineFirst[t];
                        //System.out.print(sumLine[w]);
                    }
                    for (int t = 0; t < sumLine.length / 2; t++) {
                        char tmp = sumLine[t];
                        sumLine[t] = sumLine[sumLine.length - t - 1];
                        sumLine[sumLine.length - t - 1] = tmp;
                    }
                    String bufSum = "";
                    for(int t = 0; t<sumLine.length;t++){
                        if(sumLine[t]==','){
                            bufSum += '.';
                        }
                        if(sumLine[t] >= '0' && sumLine[t] <= '9'){
                            bufSum += sumLine[t];
                        }
                    }
                    for(int t = 1; t<11;t++)
                    {
                        dataLine[t-1] = fullLineFirst[t];
                    }
                    for(int t = 4; t<fullLineSecond.length-1;t++)
                    {
                        nameLine[t-4] = fullLineSecond[t];
                    }
                    String strNameLine = "";
                    for(int t = 1; nameLine[t - 1] != '\t' ; t++){
                        strNameLine += nameLine[t - 1];
                    }
                    String strDataLine = new String(dataLine);
                    ArrayList<String> reAnswerList = new ArrayList<String>();
                    reAnswerList.add(strDataLine);
                    reAnswerList.add(strNameLine);
                    reAnswerList.add(bufSum);
                    answerList.add(reAnswerList);
                }
            }
        }
        double resSum = 0;
        ArrayList<String> lastAnswerList = new ArrayList<String>();
        for(Integer t = 0; t < answerList.size(); t++)
        {
            resSum += Double.parseDouble(answerList.get(t).get(2));
            for(Integer l = t+1; l < answerList.size(); l++)
            {
                if(answerList.get(t).get(1).equals(answerList.get(l).get(1))
                        && answerList.get(t).get(0).toCharArray()[3] == answerList.get(l).get(0).toCharArray()[3]
                        && answerList.get(t).get(0).toCharArray()[4] == answerList.get(l).get(0).toCharArray()[4]
                        && answerList.get(t).get(0).toCharArray()[8] == answerList.get(l).get(0).toCharArray()[8]
                        && answerList.get(t).get(0).toCharArray()[9] == answerList.get(l).get(0).toCharArray()[9])
                {
                    resSum += Double.parseDouble(answerList.get(l).get(2));
                    answerList.remove(answerList.get(l));
                    l--;
                }
            }
            lastAnswerList.add(answerList.get(t).get(0) + " " + answerList.get(t).get(1) + " " + String.format("%.2f",resSum));
            if(answerList.get(t).get(1).contains(nameCompany)){
                result += lastAnswerList.get(t);
                result += "\n";
            }
            resSum = 0;
            answerList.remove(answerList.get(t));
        }
        return result;
    }
}
