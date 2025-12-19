package apps.CalculatorApp;

public class CalculatorLogic {
    private StringBuilder currentInput;
    private String operator;
    private double firstOperand;
    private boolean startNewNumber;
    private boolean resultDisplayed;

    public CalculatorLogic() {
        reset();   
    }

    //set all the necessary input to reset
    public void reset() {
        currentInput = new StringBuilder("0");
        operator = "";
        firstOperand = 0;
        startNewNumber = true;
        resultDisplayed = false;
    }

    public String getCurrentDisplay() {
        return currentInput.toString();
    }

    // if new number, 0 or result then make a new one else append
    public void appendDigit(String digit) {
        if (startNewNumber || currentInput.toString().equals("0") || resultDisplayed) {
            currentInput = new StringBuilder(digit);
            startNewNumber = false;
            resultDisplayed = false;
        } else {
            currentInput.append(digit);
        }
    }

    //if start number or result then make 0. else if na contain na append lng .
    public void appendDecimal() {
        if (startNewNumber || resultDisplayed) {
            currentInput = new StringBuilder("0.");
            startNewNumber = false;
            resultDisplayed = false;
        } else if (!currentInput.toString().contains(".")) {
            currentInput.append(".");
        }
    }

//dow minus and plus sign so it just converts it to + to - and viceversa
    public void toggleSign() {
        double value = Double.parseDouble(currentInput.toString());
        value = -value;
        currentInput = new StringBuilder(formatNumber(value));
    }

    //percentage it (convert input to number parse it then using formatnumber go back to string )
    public void applyPercentage() {
        double value = Double.parseDouble(currentInput.toString());
        value = value / 100;
        currentInput = new StringBuilder(formatNumber(value));
        resultDisplayed = true;
    }

    // if operator is set
    public void setOperator(String op) {
        if (!operator.isEmpty() && !startNewNumber) {
            calculate();    //if pending then calculate anay then go sa imo nga i butang nga operator
        }
        
        firstOperand = Double.parseDouble(currentInput.toString());
        operator = op;
        startNewNumber = true;   //go back user has not inputted any value again
    }


    //calculates it normally like calculator
    public void calculate() {
        if (operator.isEmpty()) return;
        
        double secondOperand = Double.parseDouble(currentInput.toString());
        double result = 0;
        
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "×":
                result = firstOperand * secondOperand;
                break;
            case "÷":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    currentInput = new StringBuilder("Error");
                    operator = "";
                    startNewNumber = true;
                    resultDisplayed = true;
                    return;
                }
                break;
        }
        
        currentInput = new StringBuilder(formatNumber(result));
        operator = "";
        startNewNumber = true;
        resultDisplayed = true;
    }

    //formats it to become string
    private String formatNumber(double number) {
        if (number == (long) number) {   //checks if the nubmer is integer ((long) num
                                        // converts it to long para madula ang decimals)
            return String.format("%d", (long) number);
        } else {                                                   //if decimal
            String formatted = String.format("%.8f", number);   //limit to only 8 decimal palces
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");   //remove trailing dot or 0 na d need
            return formatted;
        }
    }
}