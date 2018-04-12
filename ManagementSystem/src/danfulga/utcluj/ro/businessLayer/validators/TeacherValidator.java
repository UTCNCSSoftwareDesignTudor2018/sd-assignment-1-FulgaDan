package danfulga.utcluj.ro.businessLayer.validators;

import danfulga.utcluj.ro.dataLayer.models.Teacher;

import java.util.regex.Pattern;

public class TeacherValidator implements Validator<Teacher> {
    private static final String usernameValidator = "[a-zA-Z0-9._-]{3,}";
    private static final String passwordValidator = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";


    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }
    public static boolean validateAddress(String address) {
        return address.matches(
                "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" );
    }

    public static boolean validateFirstName(String firstName){
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    public static boolean validatePNC(int PNC){ // length of 9
        if(PNC >= 100000000 && PNC <= 999999999){
            return true;
        }
        else return false;
    }

    public static boolean validateICN(int ICN){ //length of 4
        if(ICN >= 1000 && ICN <= 9999){
            return true;
        }
        else return false;
    }

    @Override
    public boolean validate(Teacher teacher) {
        Pattern pattern = Pattern.compile(usernameValidator);
        Pattern pattern1 = Pattern.compile(passwordValidator);
        if(!validatePhoneNumber(teacher.getPhoneNo())){
            throw new IllegalArgumentException("Phone Number not valid");
        }
        if(!validateAddress(teacher.getAddress())){
            throw  new IllegalArgumentException("Address is not valid");
        }
        if(!validateFirstName(teacher.getName())){
            throw  new IllegalArgumentException("Name is not valid (cannot contain numbers/symbols");
        }
        if(!validatePNC(teacher.getPNC())){
            throw new IllegalArgumentException("PNC format is not valid");
        }
        if(!validateICN(teacher.getICN())){
            throw new IllegalArgumentException("ICN format is not valid");
        }
        if(!pattern.matcher(teacher.getUsername()).matches()){
            throw new IllegalArgumentException("Username format is not valid");
        }
        if(!pattern1.matcher(teacher.getPassword()).matches()){
            throw new IllegalArgumentException("Password format is not valid. Please satisfy the following conditions: " +
                    " a digit must occur at least once, a lower case letter must occur at least once, an upper case letter must" +
                    "occur at least once, a special character must occur at least once, no whitespace allowed in the " +
                    "entire string, anything, at least eight places though");
        }
        else return true;
    }

}
