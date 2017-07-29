package nic.gst;
import java.text.DecimalFormat;

/**
 * Created by Ha on 24/07/2017.
 */

public class Calculator {
    public Calculator() {
        super();
    }

    public String calculate_inclusive(double value, int percentage, boolean interstate) {
        double output;
        double temp;
        String final_result;
        double original_amount, factor, final_amount;

        if (interstate) {

            switch (percentage) {
                case 5:
                    temp = .04761904761904;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output;
                    final_amount = original_amount + factor;
                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", final_amount);

                    break;

                case 12:
                    temp = .1071428571428;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", final_amount);

                    break;
                case 18:
                    temp = .1525423728813;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", final_amount);

                    break;

                case 28:
                    temp = .21875;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", final_amount);

                    break;


                default:
                    final_result = "Wrong Values";
                    break;
            }
            return final_result;
        } else//cgst+igst
        {
            switch (percentage) {
                case 5:
                    temp = .04761904761904;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output ;
                    output = output/2.00;
                    final_amount = original_amount + factor;
                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", final_amount);
                    //DecimalFormat df = new DecimalFormat("#.00");


                    break;

                case 12:
                    temp = .1071428571428;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output ;
                    output = output/2.00;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", final_amount);

                    break;
                case 18:
                    temp = .1525423728813;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output ;
                    output = output/2.00;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", final_amount);

                    break;

                case 28:
                    temp = .21875;
                    output = (temp * value);
                    original_amount = (value - output);
                    factor = output ;
                    output = output/2.00;
                    final_amount = original_amount + factor;

                    final_result = String.format("%.2f", original_amount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", final_amount);

                    break;


                default:
                    final_result = "Wrong Values";
                    break;
            }
            return final_result;

        }


    }

    public String calculate_Exclusive(double value, int operandvalue, boolean interstate) {
        double output;
        double temp;
        String finalresult;
        double originalamount, factor, finalamount;


        if (interstate) {
            switch (operandvalue) {
                case 5:
                    temp = .05;
                    output = (temp * value);
                    originalamount = value;
                    factor = output;
                    finalamount = originalamount + factor;

                    finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", finalamount);


                    break;

                case 12:
                    temp = .12;
                    output = (temp * value);
                    originalamount = value;
                    factor = output;
                    finalamount = originalamount + factor;

                    finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", finalamount);

                    break;

                case 18:
                    temp = .18;
                    output = (temp * value);
                    originalamount = value;
                    factor = output;
                    finalamount = originalamount + factor;

                    finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", finalamount);

                    break;

                case 28:
                    temp = .28;
                    output = (temp * value);
                    originalamount = value;
                    factor = output;
                    finalamount = originalamount + factor;

                    finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", factor) + "#" + String.format("%.2f", finalamount);

                    break;


                default:
                    finalresult = "Wrong Values";
                    break;


            }


            return finalresult;
        }

    else // cgst igst

    {
        switch (operandvalue) {
            case 5:
                temp = .025;
                output = (temp * value);
                originalamount = value;
                factor = output*2.0;
                finalamount = originalamount + factor;

                finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", finalamount);


                break;

            case 12:
                temp = .06;
                output = (temp * value);
                originalamount = value;
                factor = output*2.0;
                finalamount = originalamount + factor;

                finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", finalamount);

                break;

            case 18:
                temp = .09;
                output = (temp * value);
                originalamount = value;
                factor = output*2.0;
                finalamount = originalamount + factor;

                finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", finalamount);

                break;

            case 28:
                temp = .14;
                output = (temp * value);
                originalamount = value;
                factor = output*2.0;
                finalamount = originalamount + factor;

                finalresult = String.format("%.2f", originalamount) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", output) + "#" + String.format("%.2f", finalamount);

                break;


            default:
                finalresult = "Wrong Values";
                break;


        }


        return finalresult;

    }
}


   
}
