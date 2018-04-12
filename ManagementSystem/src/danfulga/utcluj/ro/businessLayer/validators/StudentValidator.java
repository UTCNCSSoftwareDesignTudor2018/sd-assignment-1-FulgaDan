package danfulga.utcluj.ro.businessLayer.validators;

import danfulga.utcluj.ro.dataLayer.models.Student;

import java.util.regex.Pattern;

public  class StudentValidator implements Validator<Student> {

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

    public static boolean validatePNC(int PNC){
        if(PNC >= 100000000 && PNC <= 999999999){
            return true;
        }
        else return false;
    }

    public static boolean validateICN(int ICN){
        if(ICN >= 1000 && ICN <= 9999){
            return true;
        }
        else return false;
    }

    public static boolean validateCurrentYear(int currentYear){
        if(currentYear >= 1 && currentYear <= 4){
            return true;
        }
        else return false;
    }

    @Override
    public boolean validate(Student student) {
        Pattern pattern = Pattern.compile(usernameValidator);
        Pattern pattern1 = Pattern.compile(passwordValidator);
        if(!validatePhoneNumber(student.getPhoneNo())){
            throw new IllegalArgumentException("Phone Number not valid");
        }
        if(!validateAddress(student.getAddress())){
            throw  new IllegalArgumentException("Address is not valid");
        }
        if(!validateFirstName(student.getName())){
            throw  new IllegalArgumentException("Name is not valid (cannot contain numbers/symbols");
        }
        if(!validatePNC(student.getPNC())){
            throw new IllegalArgumentException("PNC format is not valid");
        }
        if(!validateICN(student.getICN())){
            throw new IllegalArgumentException("ICN format is not valid");
        }
        if(!pattern.matcher(student.getUsername()).matches()){
            throw new IllegalArgumentException("Username format is not valid");
        }
        if(!pattern1.matcher(student.getPassword()).matches()){
            throw new IllegalArgumentException("Password format is not valid. Please satisfy the following conditions: " +
            " a digit must occur at least once, a lower case letter must occur at least once, an upper case letter must" +
                    "occur at least once, a special character must occur at least once, no whitespace allowed in the " +
                    "entire string, anything, at least eight places though");
        }
        if(!validateCurrentYear(student.getCurrentYear())){
            throw new IllegalArgumentException("The year of study must be 1,2, 3 or 4");
        }
        else return true;
    }
}
