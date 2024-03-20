class WrongPasswordException extends Exception {
    public WrongPasswordException(String message) {
        super(message);
    }
}

class WrongLoginException extends Exception {
    public WrongLoginException(String message) {
        super(message);
    }
}

public class LoginChecker {
    public static boolean isValidLogin(String login) {
        return login.length() <= 20 && login.matches("^[a-zA-Z0-9_]+$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^[a-zA-Z0-9_]+$");
    }

    public static boolean checkLoginAndPassword(String login, String password, String confirmPassword) {
        try {
            if (!isValidLogin(login)) {
                throw new WrongLoginException("Нэвтрэх нэр буруу байна.");
            }

            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Нууц үг таарахгүй байна.");
            }

            if (!isValidPassword(password)) {
                throw new WrongPasswordException("Нууц үг буруу байна.");
            }

            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.err.println(e);
            return false;
        }
    }

    public static void main(String[] args) {
        //nevter nerni urt 20 oos hetrehgui baih ystoi. ontsgoi tohioldliig haruulah uudnes urt hiiv
        String login = "admindasdhbjasbdashbjdahjsdhjadjhba";
        String password = "adminadmin123";
        // passwordo adminadmin123 gevel amjilttai burtguulne ontsgoi tohioldol haruulah uudnees buruu hiiv
        String confirmPassword = "adminadmin";

        boolean isValid = checkLoginAndPassword(login, password, confirmPassword);
        if (isValid) {
            System.out.println("Амжилттай!");
        } else {
            System.out.println("Бүртгэл амжилтгүй боллоо.");
        }
    }
}
