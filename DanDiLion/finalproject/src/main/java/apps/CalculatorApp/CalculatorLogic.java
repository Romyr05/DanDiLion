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

//Continue
    public void toggleSign() {
        double value = Double.parseDouble(currentInput.toString());
        value = -value;
        currentInput = new StringBuilder(formatNumber(value));
    }

    //
    public void applyPercentage() {
        double value = Double.parseDouble(currentInput.toString());
        value = value / 100;
        currentInput = new StringBuilder(formatNumber(value));
        resultDisplayed = true;
    }

    public void setOperator(String op) {
        if (!operator.isEmpty() && !startNewNumber) {
            calculate();
        }
        
        firstOperand = Double.parseDouble(currentInput.toString());
        operator = op;
        startNewNumber = true;
    }


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

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            String formatted = String.format("%.8f", number);
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }
}